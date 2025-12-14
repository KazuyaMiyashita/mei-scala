package generator

import RelaxNG.*
import generator.MeiModel.*

/** Analyzer that generates the MEI semantic model from the RelaxNG AST. */
object MeiSchemaAnalyzer {

  private val AttributePrefixes = Set("att.", "mei_att.")
  private val MeiPrefix         = "mei_"
  private val MacroAnyXML       = "macro.anyXML"

  // ==========================================
  // Helpers (Utility functions, placed at top for visibility)
  // ==========================================

  private def findDoc(children: List[RelaxNG]): Option[String] = {
    children.collectFirst { case Documentation(content) => content }
  }

  private def findFirstElement(patterns: List[RelaxNG]): Option[Element] = {
    patterns
      .collectFirst {
        case e: Element           => e
        case Choice(children)     => findFirstElement(children).orNull
        case Group(children)      => findFirstElement(children).orNull
        case Interleave(children) => findFirstElement(children).orNull
        case Optional(children)   => findFirstElement(children).orNull
        case ZeroOrMore(children) => findFirstElement(children).orNull
        case OneOrMore(children)  => findFirstElement(children).orNull
      }
      .flatMap(Option(_))
  }

  private def toPascalCase(s: String): String = {
    s.split("[._:-]")
      .map(part => {
        if (part.nonEmpty) s"${part.head.toUpper}${part.tail}" else ""
      })
      .mkString
  }

  private def toTypeName(s: String): String = {
    if (s == MacroAnyXML) return "scala.xml.NodeSeq"

    val pascal = toPascalCase(s)
    val result = if (s.startsWith(MeiPrefix)) {
      pascal.replaceFirst("Mei", "")
    } else {
      pascal
    }
    if (result.isEmpty) {
      throw new IllegalArgumentException(s"Could not generate type name for empty or invalid input: $s")
    } else {
      result
    }
  }

  private def toSafeName(s: String): String = {
    s"`@$s`"
  }

  private def formatAttributePath(elementOriginalName: String, pathSegments: List[String]): String = {
    val formattedAndCleanedSegments = pathSegments
      .map { case segment =>
        var cleaned = segment
        if (cleaned.startsWith(MeiPrefix)) {
          cleaned = cleaned.replaceFirst(MeiPrefix, "")
        }
        if (cleaned.endsWith(".attributes")) {
          cleaned = cleaned.replace(".attributes", "")
        }
        if (cleaned.endsWith(".attribute.xmlid")) {
          cleaned = cleaned.replace(".attribute.xmlid", ".xmlid")
        }
        if (cleaned.endsWith(".attribute.xmlbase")) {
          cleaned = cleaned.replace(".attribute.xmlbase", ".xmlbase")
        }
        if (cleaned.endsWith(".attribute.class")) {
          cleaned = cleaned.replace(".attribute.class", ".class")
        }
        if (cleaned.endsWith(".attribute.type")) {
          cleaned = cleaned.replace(".attribute.type", ".type")
        }
        cleaned
      }
      .foldLeft(List.empty[String]) {
        case (acc, segment) if acc.lastOption.contains(segment) && !segment.startsWith("@") =>
          acc // Remove consecutive duplicates other than attributes
        case (acc, segment) => acc :+ segment
      }

    // If the first segment is an element name, format it as <elementOriginalName>
    val finalSegments = formattedAndCleanedSegments match {
      case head :: tail if head == elementOriginalName       => s"<$elementOriginalName>" :: tail
      case head :: tail if head == s"<$elementOriginalName>" => head :: tail // If already in <name> format
      case other                                             => other
    }

    finalSegments.mkString(" -> ")
  }

  private def formatElementPath(elementOriginalName: String, pathSegments: List[String]): String = {
    formatAttributePath(elementOriginalName, pathSegments)
  }

  // ==========================================
  // Public API
  // ==========================================

  def analyze(pattern: RelaxNG): List[MeiModel] = {
    val defineMap = buildDefineMap(pattern)
    val parentMap = buildParentMap(defineMap)

    val definitions = pattern match {
      case Grammar(children) =>
        children.flatMap(child => analyzeNode(child, defineMap, parentMap))
      case _ =>
        List.empty
    }

    // Exclude duplicate names (prioritize the first definition)
    definitions.foldLeft(List.empty[MeiModel]) { (acc, defn) =>
      if (acc.exists(_.name == defn.name)) acc
      else acc :+ defn
    }
  }

  // ==========================================
  // Analysis Logic
  // ==========================================

  private def analyzeNode(
      node: RelaxNG,
      defineMap: Map[String, Define],
      parentMap: Map[String, List[String]],
      extraParents: List[String] = Nil,
      inheritedDoc: Option[String] = None,
  ): List[MeiModel] = {
    node match {
      case Element(name, children) =>
        analyzeElement(name, children, defineMap, parentMap, extraParents, inheritedDoc)

      case Define(name, _, children) =>
        analyzeDefine(name, children, defineMap, parentMap)

      case Start(children) =>
        children.flatMap(child => analyzeNode(child, defineMap, parentMap))

      case Div(children) =>
        children.flatMap(child => analyzeNode(child, defineMap, parentMap))

      case _ => Nil
    }
  }

  private def analyzeElement(
      name: String,
      children: List[RelaxNG],
      defineMap: Map[String, Define],
      parentMap: Map[String, List[String]],
      extraParents: List[String],
      inheritedDoc: Option[String],
  ): List[MeiModel] = {
    if (name.isEmpty) {
      // println(s"WARNING: Skipping SClass generation for empty Element name.")
      Nil
    } else {
      val doc              = inheritedDoc.orElse(findDoc(children))
      val extractionResult =
        extractMembers(
          children,
          defineMap,
          currentDefineOriginalName = None,
          flattenAttributeRefs = true,
          elementOriginalName = name,
          currentPathSegments = List(s"<$name>"),
        )

      // Constructing the 'elements' field
      val elementsField = if (extractionResult.childElements.nonEmpty) {
        val uniqueResolutions = extractionResult.childElements.distinct

        // Create type list (exclude Nothing, remove duplicates)
        val types = uniqueResolutions
          .map(_.typeName)
          .filterNot(_ == "scala.Nothing")
          .distinct
          .sorted

        // Create documentation
        val docLines = uniqueResolutions
          .groupBy(_.typeName)
          .filterNot(_._1 == "scala.Nothing")
          .toList
          .sortBy(_._1)
          .flatMap { case (typeName, resolutions) =>
            resolutions.map { case r =>
              formatElementPath(name, r.path) + s" (-> $typeName)"
            }
          }

        val elementDoc = if (docLines.nonEmpty) Some(docLines.mkString("\n")) else None

        if (types.nonEmpty) {
          Some(MeiField("elements", types, Cardinality.Multiple, elementDoc))
        } else {
          None
        }
      } else {
        None
      }

      val allFields         = elementsField.toList ++ extractionResult.fields
      val className         = toTypeName(name)
      val structuralParents = parentMap
        .getOrElse(name, Nil)
        .filter(p => AttributePrefixes.exists(p.startsWith))
        .map(toTypeName)
      val allParents = (extractionResult.parents ++ extraParents ++ structuralParents).distinct

      List(MeiElement(className, name, allFields, allParents, doc))
    }
  }

  private def analyzeDefine(
      name: String,
      children: List[RelaxNG],
      defineMap: Map[String, Define],
      parentMap: Map[String, List[String]],
  ): List[MeiModel] = {
    val defineDoc         = findDoc(children)
    val wrappedElement    = findFirstElement(children)
    val structuralParents = parentMap
      .getOrElse(name, Nil)
      .filter(p => AttributePrefixes.exists(p.startsWith))
      .map(toTypeName)

    wrappedElement match {
      case Some(element) =>
        analyzeNode(
          element,
          defineMap,
          parentMap,
          extraParents = structuralParents,
          inheritedDoc = defineDoc,
        )

      case None =>
        val isExplicitAttributeDefine = AttributePrefixes.exists(name.startsWith)

        if (isAttributeGroup(children, defineMap) || (isExplicitAttributeDefine && children.exists(_ == Empty()))) {
          val extractionResult =
            extractMembers(
              children,
              defineMap,
              currentDefineOriginalName = Some(name),
              flattenAttributeRefs = false,
              elementOriginalName = name,
              currentPathSegments = List(s"<$name>"),
            )
          val allParents      = (extractionResult.parents ++ structuralParents).distinct
          val traitName       = toTypeName(name)
          val filteredParents = allParents.filterNot(_ == traitName)

          List(MeiTrait(traitName, name, filteredParents, defineDoc))

        } else if (isSimpleContent(children, defineMap)) {
          val targetType = if (children.exists(_.isInstanceOf[NotAllowed])) "scala.Nothing" else "java.lang.String"
          List(MeiTypeAlias(toTypeName(name), name, targetType, defineDoc))

        } else {
          Nil
        }
    }
  }

  // ==========================================
  // Member Extraction (Immutable)
  // ==========================================

  private case class TypeResolution(
      typeName: String,
      path: List[String],
  )

  private case class ExtractionResult(
      fields: List[MeiField] = Nil,              // Attributes
      childElements: List[TypeResolution] = Nil, // Candidate child elements
      parents: List[String] = Nil,
      childTypes: List[String] = Nil, // May become obsolete
  ) {
    def merge(other: ExtractionResult): ExtractionResult = {
      ExtractionResult(
        (fields ++ other.fields).distinctBy(_.name),
        (childElements ++ other.childElements), // Duplicates handled in analyzeElement
        (parents ++ other.parents).distinct,
        (childTypes ++ other.childTypes).distinct,
      )
    }

    def addField(field: MeiField): ExtractionResult = copy(fields = (fields :+ field).distinctBy(_.name))
    def addParent(parent: String): ExtractionResult =
      if (!parents.contains(parent)) copy(parents = parents :+ parent) else this
  }

  private def extractMembers(
      patterns: List[RelaxNG],
      defineMap: Map[String, Define],
      currentDefineOriginalName: Option[String],
      flattenAttributeRefs: Boolean,
      elementOriginalName: String,
      currentPathSegments: List[String],
  ): ExtractionResult = {

    def recurse(
        p: RelaxNG,
        cardinality: Cardinality, // For attributes
        pathAcc: List[String],
    ): ExtractionResult = p match {
      case Attribute(attrName, attrChildren) =>
        val originalDoc = findDoc(attrChildren)
        val fullPath    = formatAttributePath(elementOriginalName, pathAcc :+ s"@$attrName")
        val enrichedDoc = originalDoc.map(d => s"$d\n\n$fullPath").orElse(Some(fullPath))

        // Find type definition (Ref) (simplified implementation)
        val typeName = findTypeRef(attrChildren) match {
          case Some(refName) if isSimpleContent(List(Ref(refName)), defineMap) => toTypeName(refName) // Use TypeAlias
          case _                                                               => "java.lang.String"
        }

        ExtractionResult(fields = List(MeiField(toSafeName(attrName), List(typeName), cardinality, enrichedDoc)))

      case Ref(name) =>
        if (name.isEmpty) {
          ExtractionResult()
        } else if (AttributePrefixes.exists(name.startsWith)) {
          // Attribute group
          handleAttributeRef(
            name,
            defineMap,
            currentDefineOriginalName,
            flattenAttributeRefs,
            elementOriginalName,
            pathAcc :+ name,
          )
        } else {
          // Structural reference (model.xxx) -> Resolution of child elements
          val resolutions = resolveElementTypes(Ref(name), defineMap, pathAcc)
          ExtractionResult(childElements = resolutions)
        }

      case Element(name, _) =>
        val resolutions = List(TypeResolution(toTypeName(name), pathAcc :+ s"<$name>"))
        ExtractionResult(childElements = resolutions)

      case Text() | Data(_, _) =>
        val resolutions = List(TypeResolution("java.lang.String", pathAcc :+ "text"))
        ExtractionResult(childElements = resolutions)

      case NotAllowed() =>
        val resolutions = List(TypeResolution("scala.Nothing", pathAcc :+ "(notAllowed)"))
        ExtractionResult(childElements = resolutions)

      case Group(children) => children.map(recurse(_, cardinality, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))
      case Interleave(children) =>
        children.map(recurse(_, cardinality, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))
      case Choice(children) =>
        val nextCard = if (cardinality == Cardinality.Required) Cardinality.Optional else cardinality
        children.map(recurse(_, nextCard, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))

      case Optional(children) =>
        children.map(recurse(_, Cardinality.Optional, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))
      case ZeroOrMore(children) =>
        children.map(recurse(_, Cardinality.Multiple, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))
      case OneOrMore(children) =>
        children.map(recurse(_, Cardinality.Multiple, pathAcc)).foldLeft(ExtractionResult())(_.merge(_))

      case _ => ExtractionResult()
    }

    patterns.map(p => recurse(p, Cardinality.Required, currentPathSegments)).foldLeft(ExtractionResult())(_.merge(_))
  }

  private def resolveElementTypes(
      pattern: RelaxNG,
      defineMap: Map[String, Define],
      currentPath: List[String],
  ): List[TypeResolution] = pattern match {
    case Ref(name) =>
      if (name.isEmpty || AttributePrefixes.exists(name.startsWith)) Nil
      else {
        defineMap.get(name) match {
          case Some(define) =>
            // Expand the contents of the Define
            resolveElementTypesList(define.children, defineMap, currentPath :+ name)
          case None =>
            if (name.trim.isEmpty) Nil
            else List(TypeResolution(toTypeName(name), currentPath :+ name))
        }
      }
    case Element(name, _) =>
      if (name.isEmpty) Nil
      else List(TypeResolution(toTypeName(name), currentPath :+ s"<$name>"))
    case Choice(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case Group(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case Interleave(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case Optional(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case ZeroOrMore(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case OneOrMore(children) =>
      resolveElementTypesList(children, defineMap, currentPath)
    case Text() | Data(_, _) =>
      List(TypeResolution("java.lang.String", currentPath :+ "text"))
    case NotAllowed() =>
      List(TypeResolution("scala.Nothing", currentPath :+ "(notAllowed)"))
    case _ => Nil
  }

  private def resolveElementTypesList(
      patterns: List[RelaxNG],
      defineMap: Map[String, Define],
      currentPath: List[String],
  ): List[TypeResolution] = {
    patterns.flatMap(p => resolveElementTypes(p, defineMap, currentPath))
  }

  // Simple helper (for attribute type resolution)
  private def findTypeRef(patterns: List[RelaxNG]): Option[String] = {
    patterns
      .collectFirst {
        case Ref(name)        => name
        case Choice(children) => findTypeRef(children).orNull
        // Add here if support for structures like List(Ref) is needed
      }
      .flatMap(Option(_))
  }

  // ... (handleAttributeRef, buildDefineMap, buildParentMap, collectParents, isAttributeGroup, isSimpleContent are unchanged) ...
  private def handleAttributeRef(
      name: String,
      defineMap: Map[String, Define],
      currentDefineOriginalName: Option[String],
      flattenAttributeRefs: Boolean,
      elementOriginalName: String,
      currentPathSegments: List[String],
  ): ExtractionResult = {
    val typeName      = toTypeName(name)
    val isSelf        = currentDefineOriginalName.contains(name)
    val initialResult = if (!isSelf) ExtractionResult(parents = List(typeName)) else ExtractionResult()

    if (flattenAttributeRefs) {
      defineMap.get(name) match {
        case Some(define) =>
          if (currentDefineOriginalName.forall(_ != define.name)) {
            val nestedResult = extractMembers(
              define.children,
              defineMap,
              currentDefineOriginalName,
              flattenAttributeRefs = true,
              elementOriginalName,
              currentPathSegments :+ define.name,
            )
            initialResult.copy(fields = nestedResult.fields)
          } else {
            val directFields = define.children.collect { case Attribute(attrName, attrChildren) =>
              val originalDoc = findDoc(attrChildren)
              val fullPath    = formatAttributePath(elementOriginalName, currentPathSegments :+ s"@$attrName")
              val enrichedDoc = originalDoc.map(d => s"$d\n\n$fullPath").orElse(Some(fullPath))
              val typeName    = findTypeRef(attrChildren).map(toTypeName).getOrElse("java.lang.String") // Simplified
              MeiField(toSafeName(attrName), List(typeName), Cardinality.Required, enrichedDoc)
            }
            initialResult.copy(fields = directFields)
          }
        case None => initialResult
      }
    } else {
      initialResult
    }
  }

  private def buildDefineMap(root: RelaxNG): Map[String, Define] = {
    def collect(p: RelaxNG): Seq[Define] = p match {
      case Grammar(children) => children.flatMap(collect)
      case Div(children)     => children.flatMap(collect)
      case d: Define         => Seq(d)
      case _                 => Seq.empty
    }
    collect(root).map(d => d.name -> d).toMap
  }

  private def buildParentMap(defineMap: Map[String, Define]): Map[String, List[String]] = {
    defineMap.foldLeft(Map.empty[String, List[String]]) { case (accMap, (defName, define)) =>
      if (!isAttributeGroup(define.children, defineMap)) {
        collectParents(define.children, defName, accMap)
      } else {
        accMap
      }
    }
  }

  private def collectParents(
      patterns: List[RelaxNG],
      currentDefineName: String,
      accMap: Map[String, List[String]],
  ): Map[String, List[String]] = {
    patterns.foldLeft(accMap) { (map, p) =>
      p match {
        case Ref(targetName) =>
          if (!AttributePrefixes.exists(targetName.startsWith)) {
            val existing = map.getOrElse(targetName, Nil)
            map.updated(targetName, existing :+ currentDefineName)
          } else map
        case Group(c)      => collectParents(c, currentDefineName, map)
        case Choice(c)     => collectParents(c, currentDefineName, map)
        case Interleave(c) => collectParents(c, currentDefineName, map)
        case Optional(c)   => collectParents(c, currentDefineName, map)
        case ZeroOrMore(c) => collectParents(c, currentDefineName, map)
        case OneOrMore(c)  => collectParents(c, currentDefineName, map)
        case Div(c)        => collectParents(c, currentDefineName, map)
        case _             => map
      }
    }
  }

  private def isAttributeGroup(children: List[RelaxNG], defineMap: Map[String, Define]): Boolean = {
    children.exists {
      case _: Attribute                                           => true
      case Ref(name) if AttributePrefixes.exists(name.startsWith) => true
      case Group(c)                                               => isAttributeGroup(c, defineMap)
      case Interleave(c)                                          => isAttributeGroup(c, defineMap)
      case Optional(c)                                            => isAttributeGroup(c, defineMap)
      case Choice(c)                                              => isAttributeGroup(c, defineMap)
      case _                                                      => false
    }
  }

  private def isSimpleContent(children: List[RelaxNG], defineMap: Map[String, Define]): Boolean = {
    def check(p: RelaxNG, visited: Set[String]): Boolean = p match {
      case Text() | Empty() | Value(_) | Data(_, _) => true
      case Element(_, _) | Attribute(_, _)          => false
      case Ref(name)                                =>
        if (visited.contains(name)) false
        else if (AttributePrefixes.exists(name.startsWith)) false
        else defineMap.get(name).exists(d => d.children.forall(check(_, visited + name)))

      case Group(c)      => c.forall(check(_, visited))
      case Choice(c)     => c.forall(check(_, visited))
      case Interleave(c) => c.forall(check(_, visited))
      case Optional(c)   => c.forall(check(_, visited))
      case ZeroOrMore(c) => c.forall(check(_, visited))
      case OneOrMore(c)  => c.forall(check(_, visited))
      case _             => true
    }
    children.forall(check(_, Set.empty))
  }
}
