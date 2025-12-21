package meiscala.generator

import java.nio.file.{Path, Paths}
import meiscala.generator.ODD.*
import meiscala.generator.ODD.Spec.*

object Generator {
  def generate(specsMap: Map[Ident, Spec], prefix: String): Map[Path, String] = {
    val generatedSpecs = specsMap.map { case (ident, spec) =>
      spec match {
        case s: MacroSpecDt    => generateMacroSpecDt(ident, s, prefix, specsMap)
        case s: MacroSpecPe    => generateMacroSpecPe(ident, s, prefix, specsMap)
        case s: ClassSpecAtts  => generateClassSpecAtts(ident, s, prefix, specsMap)
        case s: ClassSpecModel => generateClassSpecModel(ident, s, prefix, specsMap)
        case s: ElementSpec    => generateElementSpec(ident, s, prefix, specsMap)
      }
    }

    // 基底クラスの生成
    val baseClasses = generateBaseClasses(prefix)

    // Syntaxオブジェクトの生成
    val syntax = generateSyntax(specsMap, prefix)

    // Schemasオブジェクトの生成
    val schemas = generateSchemas(specsMap, prefix)

    // MeiXMLの生成
    val meiXml = generateMeiXML(specsMap, prefix)

    generatedSpecs ++ baseClasses + syntax + schemas + meiXml
  }

  private def generateBaseClasses(prefix: String): Map[Path, String] = {
    val prefixPath = if (prefix.isEmpty) Paths.get(".") else Paths.get(prefix.replace('.', '/'))

    val elementPath    = prefixPath.resolve("Element.scala")
    val elementContent =
      s"""package $prefix
         |
         |trait Element {
         |  def validate(): Boolean = true
         |  def children: scala.List[Element]
         |  def withChildren(newChildren: scala.List[Element]): Element
         |  def transform(rule: Element => Element): Element = {
         |    val newChildren = this.children.map(_.transform(rule))
         |    val newNode = this.withChildren(newChildren)
         |    rule(newNode)
         |  }
         |}
         |
         |case class Text(value: java.lang.String) extends Element {
         |  override def children: scala.List[Element] = scala.Nil
         |  override def withChildren(newChildren: scala.List[Element]): Element = {
         |    if (newChildren.nonEmpty) throw new IllegalArgumentException("Text node cannot have children")
         |    this
         |  }
         |}
         |""".stripMargin

    Map(
      elementPath -> elementContent,
    )
  }

  private def generateSyntax(specsMap: Map[Ident, Spec], prefix: String): (Path, String) = {
    val prefixPath = if (prefix.isEmpty) Paths.get(".") else Paths.get(prefix.replace('.', '/'))
    val syntaxPath = prefixPath.resolve("Syntax.scala")

    val elementSpecs = specsMap.values.collect { case s: ElementSpec => s }.toSeq.sortBy(_.ident.value)

    val factoryMethods = elementSpecs
      .map { spec =>
        val (_, packageName, className) = getPathInfo(spec.ident, spec.module, prefix)
        val methodName                  = s"${className.head.toLower}${className.tail}"

        val fullyQualifiedClass = s"$packageName.$className"
        val resolvedAttrs       = resolveAttributes(spec, specsMap).sortBy(_.ident)

        val attrParams = resolvedAttrs
          .map { attr =>
            val attrName  = attr.ident
            val paramName = s"`$attrName`"
            s"$paramName: java.lang.String = null"
          }
          .mkString(", ")

        val attrArgs = resolvedAttrs
          .map { attr =>
            val attrName = attr.ident
            s"`$attrName`"
          }
          .mkString(", ")

        s"""  def $methodName($attrParams)(elements: Element*): $fullyQualifiedClass = 
           |    $fullyQualifiedClass.apply($attrArgs)(elements*)""".stripMargin
      }
      .mkString("\n\n")

    val content =
      s"""package $prefix
         |
         |import scala.language.implicitConversions
         |
         |object Syntax {
         |  implicit def stringToElement(s: java.lang.String): Element = Text(s)
         |
         |$factoryMethods
         |
         |}
         |""".stripMargin

    syntaxPath -> content
  }

  private def generateSchemas(specsMap: Map[Ident, Spec], prefix: String): (Path, String) = {
    val prefixPath  = if (prefix.isEmpty) Paths.get(".") else Paths.get(prefix.replace('.', '/'))
    val schemasPath = prefixPath.resolve("Schemas.scala")

    val schemaDefinitions = specsMap.toSeq
      .sortBy(_._1.value)
      .map { case (ident, spec) =>
        val schemaCode = contentToSchema(spec.content, specsMap, prefix)
        val valName    = "schema_" + sanitizeIdent(ident.value)
        s"  lazy val $valName: SchemaNode = $schemaCode"
      }
      .mkString("\n")

    val content =
      s"""package $prefix
         |
         |trait SchemaNode
         |case object SEmpty extends SchemaNode
         |case object SText extends SchemaNode
         |case class SRef(clazz: Class[?]) extends SchemaNode
         |case class SChoice(nodes: scala.List[SchemaNode]) extends SchemaNode
         |case class SSeq(nodes: scala.List[SchemaNode]) extends SchemaNode
         |case class SZeroOrMore(node: SchemaNode) extends SchemaNode
         |case class SOneOrMore(node: SchemaNode) extends SchemaNode
         |case class SOptional(node: SchemaNode) extends SchemaNode
         |case class SMacroRef(lazyNode: () => SchemaNode) extends SchemaNode
         |
         |object Validator {
         |  def validate(elements: scala.List[Element], schema: SchemaNode): Boolean = {
         |    matchSchema(elements, schema).exists(_.isEmpty)
         |  }
         |  
         |  private def matchSchema(elems: scala.List[Element], node: SchemaNode): Option[scala.List[Element]] = node match {
         |    case SEmpty => Some(elems)
         |    case SText => 
         |      if (elems.headOption.exists(_.isInstanceOf[Text])) Some(elems.tail) else None
         |      
         |    case SRef(clazz) => 
         |       elems.headOption.filter(e => clazz.isAssignableFrom(e.getClass)).map(_ => elems.tail)
         |       
         |    case SSeq(nodes) => 
         |       nodes.foldLeft(Option(elems)) { (acc, n) => acc.flatMap(es => matchSchema(es, n)) }
         |       
         |    case SChoice(nodes) =>
         |       nodes.view.map(n => matchSchema(elems, n)).find(_.isDefined).flatten
         |       
         |    case SZeroOrMore(child) =>
         |       var current = elems
         |       var lastMatch: Option[scala.List[Element]] = Some(current)
         |       while (lastMatch.isDefined) {
         |         matchSchema(current, child) match {
         |           case Some(next) if next != current => 
         |             current = next
         |             lastMatch = Some(current)
         |           case _ => 
         |             lastMatch = None
         |         }
         |       }
         |       Some(current)
         |       
         |    case SOneOrMore(child) =>
         |       matchSchema(elems, child).flatMap(rest => matchSchema(rest, SZeroOrMore(child)))
         |       
         |    case SOptional(child) =>
         |       matchSchema(elems, child).orElse(Some(elems))
         |       
         |    case SMacroRef(lazyNode) =>
         |       matchSchema(elems, lazyNode())
         |  }
         |}
         |
         |object Schemas {
         |$schemaDefinitions
         |}
         |""".stripMargin

    schemasPath -> content
  }

  private def generateMeiXML(specsMap: Map[Ident, Spec], prefix: String): (Path, String) = {
    val prefixPath = if (prefix.isEmpty) Paths.get(".") else Paths.get(prefix.replace('.', '/'))
    val meiXmlPath = prefixPath.resolve("MeiXML.scala")

    val elementSpecs = specsMap.values.collect { case s: ElementSpec => s }.toSeq.sortBy(_.ident.value)

    val loadCases = elementSpecs
      .map { case spec =>
        val tagName                     = spec.ident.value
        val (_, packageName, className) = getPathInfo(spec.ident, spec.module, prefix)
        val fqcn                        = s"$packageName.$className"
        s"      case \"$tagName\" => $fqcn(children, attributes)"
      }
      .mkString("\n")

    val toXmlCases = elementSpecs
      .map { spec =>
        val tagName                     = spec.ident.value
        val (_, packageName, className) = getPathInfo(spec.ident, spec.module, prefix)
        val fqcn                        = s"$packageName.$className"
        s"""    case e: $fqcn => new scala.xml.Elem(null, "$tagName", mkAttributes(e.attributes), scope, true, e.elements.map(el => toXmlWithScope(el, scope))*)"""
      }
      .mkString("\n")

    val meiSpec       = elementSpecs.find(_.ident.value == "mei")
    val meiReturnType = meiSpec match {
      case Some(spec) =>
        val (_, packageName, className) = getPathInfo(spec.ident, spec.module, prefix)
        s"$packageName.$className"
      case None => s"$prefix.Element"
    }

    val content = s"""package $prefix

import scala.xml.*

object MeiXML {

  def load(xml: Elem): $meiReturnType = {
    loadElement(xml) match {
      case m: $meiReturnType => m
      case other => throw new IllegalArgumentException(s"Root element must be 'mei', but got $${other.getClass.getSimpleName}")
    }
  }

  def loadElement(node: Node): $prefix.Element = node match {
    case e: Elem =>
      val attributes: scala.collection.immutable.SeqMap[String, String] = scala.collection.immutable.SeqMap.from(
        e.attributes.map {
          case p: scala.xml.PrefixedAttribute => (s"$${p.pre}:$${p.key}", p.value.text)
          case u: scala.xml.UnprefixedAttribute => (u.key, u.value.text)
          case m => (m.key, m.value.text)
        }
      )
      val children = e.child.flatMap(n => Option(loadElement(n))).toList
      
      e.label match {
$loadCases
        case _ => throw new IllegalArgumentException(s"Unknown tag: $${e.label}")
      }

    case t: scala.xml.Text => $prefix.Text(t.text)
    case _ => null // Ignore comments, etc.
  } 
  
  private def mkAttributes(attributes: scala.collection.immutable.SeqMap[String, String]): MetaData = {
    attributes.foldRight[MetaData](Null) { case ((k, v), acc) =>
      if (k.contains(":")) {
        val parts = k.split(":", 2)
        new PrefixedAttribute(parts(0), parts(1), v, acc)
      } else {
        new UnprefixedAttribute(k, v, acc)
      }
    }
  }

  private val meiScope = scala.xml.NamespaceBinding(null, "http://www.music-encoding.org/ns/mei", scala.xml.TopScope)

  def toXml(element: $prefix.Element): Node = toXmlWithScope(element, meiScope)

  private def toXmlWithScope(element: $prefix.Element, scope: NamespaceBinding): Node = element match {
    case $prefix.Text(value) => scala.xml.Text(value)
$toXmlCases
    case _ => throw new IllegalArgumentException(s"Unknown element type: $${element.getClass.getName}")
  }
}
""".stripMargin
    meiXmlPath -> content
  }

  private def contentToSchema(nodes: Seq[ContentNode], specsMap: Map[Ident, Spec], prefix: String): String = {
    if (nodes.isEmpty) "SEmpty"
    else if (nodes.size == 1) nodeToSchema(nodes.head, specsMap, prefix)
    else s"SSeq(scala.List(${nodes.map(n => nodeToSchema(n, specsMap, prefix)).mkString(", ")}))"
  }

  private def nodeToSchema(node: ContentNode, specsMap: Map[Ident, Spec], prefix: String): String = node match {
    case ContentNode.Text       => "SText"
    case ContentNode.Empty      => "SEmpty"
    case ContentNode.Ref(ident) =>
      val className = resolveClassName(ident, specsMap, prefix)
      s"SRef(classOf[$className])"
    case ContentNode.MacroRef(ident) =>
      s"SMacroRef(() => Schemas.schema_${sanitizeIdent(ident.value)})"
    case ContentNode.Choice(children) =>
      s"SChoice(scala.List(${children.map(n => nodeToSchema(n, specsMap, prefix)).mkString(", ")}))"
    case ContentNode.ZeroOrMore(child) => s"SZeroOrMore(${nodeToSchema(child, specsMap, prefix)})"
    case ContentNode.OneOrMore(child)  => s"SOneOrMore(${nodeToSchema(child, specsMap, prefix)})"
    case ContentNode.Optional(child)   => s"SOptional(${nodeToSchema(child, specsMap, prefix)})"
    case ContentNode.Group(children)   =>
      s"SSeq(scala.List(${children.map(n => nodeToSchema(n, specsMap, prefix)).mkString(", ")}))"
    case _ => "SText"
  }

  private def resolveClassName(ident: Ident, specsMap: Map[Ident, Spec], prefix: String): String = {
    specsMap.get(ident) match {
      case Some(spec) =>
        val (_, packageName, className) = getPathInfo(spec.ident, spec.module, prefix)
        s"$packageName.$className"
      case None =>
        s"$prefix.Element"
    }
  }

  private def generateMacroSpecDt(
      ident: Ident,
      spec: MacroSpecDt,
      prefix: String,
      specsMap: Map[Ident, Spec],
  ): (Path, String) = {
    generateCommon(ident, spec, prefix, specsMap)
  }

  private def generateMacroSpecPe(
      ident: Ident,
      spec: MacroSpecPe,
      prefix: String,
      specsMap: Map[Ident, Spec],
  ): (Path, String) = {
    generateCommon(ident, spec, prefix, specsMap)
  }

  private def generateClassSpecAtts(
      ident: Ident,
      spec: ClassSpecAtts,
      prefix: String,
      specsMap: Map[Ident, Spec],
  ): (Path, String) = {
    generateCommon(ident, spec, prefix, specsMap)
  }

  private def generateClassSpecModel(
      ident: Ident,
      spec: ClassSpecModel,
      prefix: String,
      specsMap: Map[Ident, Spec],
  ): (Path, String) = {
    generateCommon(ident, spec, prefix, specsMap)
  }

  private def generateElementSpec(
      ident: Ident,
      spec: ElementSpec,
      prefix: String,
      specsMap: Map[Ident, Spec],
  ): (Path, String) = {

    val (path, packageName, className) = getPathInfo(ident, spec.module, prefix)
    val inheritance                    = resolveInheritance(spec, specsMap, prefix)

    val resolvedAttrs = resolveAttributes(spec, specsMap).sortBy(_.ident)

    // 属性名リスト生成
    val allowedAttrs = resolvedAttrs.map(a => s""""${a.ident}"""").mkString(", ")

    // applyメソッドの引数生成
    val attrParams = resolvedAttrs
      .map { attr =>
        val attrName  = attr.ident
        val paramName = s"`$attrName`"
        s"$paramName: java.lang.String = null"
      }
      .mkString(", ")

    // Map生成ロジック
    val mapEntries = resolvedAttrs
      .map { attr =>
        val attrName  = attr.ident
        val paramName = s"`$attrName`"
        s"""if ($paramName != null) Some(\"$attrName\" -> $paramName) else None"""
      }
      .mkString(",\n      ")

    val applyMethod =
      s"""  def apply($attrParams)(elements: Element*): $className = {
         |    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
         |      $mapEntries
         |    ).flatten)
         |    new $className(elements.toList, attributes)
         |  }""".stripMargin

    val content =
      s"""package $packageName
         |
         |import $prefix.Element
         |import $prefix.Schemas
         |import $prefix.Validator
         |
         |/**
         | * ${spec.desc.getOrElse("No description available.")}
         | * 
         | * Ident: ${spec.ident.value}
         | * Module: ${spec.module.value}
         | */
         |case class $className(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String]) $inheritance {
         |  override def validate(): Boolean = {
         |    val allowedAttrs = Set($allowedAttrs)
         |    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
         |    val elementsValid = Validator.validate(elements, Schemas.schema_${sanitizeIdent(ident.value)})
         |    attributesValid && elementsValid
         |  }
         |  override def children: scala.List[Element] = elements
         |  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
         |}
         |
         |object $className {
         |$applyMethod
         |}
         |""".stripMargin

    path -> content

  }

  private def resolveAttributes(spec: Spec, specsMap: Map[Ident, Spec]): List[AttDef] = {
    val ownAttrs       = spec.attList.flatMap(_.attDefs)
    val inheritedAttrs = spec.classes.flatMap { classIdent =>
      specsMap.get(classIdent) match {
        case Some(classSpec) => resolveAttributes(classSpec, specsMap)
        case None            => Nil
      }
    }
    (ownAttrs ++ inheritedAttrs).distinctBy(_.ident).toList
  }

  private def resolveInheritance(spec: Spec, specsMap: Map[Ident, Spec], prefix: String): String = {
    val parents = spec.classes.flatMap(specsMap.get).map { parentSpec =>
      val (_, packageName, className) = getPathInfo(parentSpec.ident, parentSpec.module, prefix)
      s"$packageName.$className"
    }
    spec match {
      case _: ElementSpec =>
        (s"$prefix.Element" +: parents).mkString(" extends ", " with ", "")
      case _ =>
        if (parents.isEmpty) "" else parents.mkString(" extends ", " with ", "")
    }
  }

  private def generateCommon(ident: Ident, spec: Spec, prefix: String, specsMap: Map[Ident, Spec]): (Path, String) = {

    val (path, packageName, className) = getPathInfo(ident, spec.module, prefix)
    val inheritance                    = resolveInheritance(spec, specsMap, prefix)

    val content = s"""package $packageName
                     |
                     |/**
                     | * ${spec.desc.getOrElse("No description available.")}
                     | * 
                     | * Ident: ${spec.ident.value}
                     | * Module: ${spec.module.value}
                     | */
                     |trait $className $inheritance {
                     |
                     |} 
                     |""".stripMargin

    path -> content

  }

  private def getPathInfo(ident: Ident, module: Module, prefix: String): (Path, String, String) = {
    val moduleParts = module.value.split('.').map(_.toLowerCase).map(escapeKeyword)
    val identParts  = ident.value.split('.')
    // sanitizeIdentを使用してクラス名をサニタイズ
    val className       = sanitizeIdent(identParts.last).capitalize
    val subPackageParts = identParts.dropRight(1).map(_.toLowerCase).map(escapeKeyword)
    val prefixParts     = if (prefix.isEmpty) Array.empty[String] else prefix.split('.')
    val allPackageParts = prefixParts ++ moduleParts ++ subPackageParts
    val dirPath         = Paths.get(allPackageParts.head, allPackageParts.tail*)
    val fileName        = s"$className.scala"
    val path            = dirPath.resolve(fileName)
    val packageName     = allPackageParts.mkString(".")
    (path, packageName, className)
  }

  private def escapeKeyword(s: String): String = {
    val keywords = Set(
      "abstract",
      "case",
      "catch",
      "class",
      "def",
      "do",
      "else",
      "extends",
      "false",
      "final",
      "finally",
      "for",
      "forSome",
      "if",
      "implicit",
      "import",
      "lazy",
      "match",
      "new",
      "null",
      "object",
      "override",
      "package",
      "private",
      "protected",
      "return",
      "sealed",
      "super",
      "this",
      "throw",
      "trait",
      "try",
      "true",
      "type",
      "val",
      "var",
      "while",
      "with",
      "yield",
      "macro",
    )
    if (keywords.contains(s)) s + "_" else s
  }

  private def sanitizeIdent(s: String): String = s.replace('.', '_').replace(':', '_').replace('-', '_')

}
