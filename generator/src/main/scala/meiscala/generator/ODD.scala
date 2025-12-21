package meiscala.generator

import scala.xml.{Node, Elem, Utility}
import scala.util.chaining.*

object ODD {

  /** e.g. MEI.shared */
  case class Module(value: String)

  /** e.g. note (element), model.noteModifierLike (model class) */
  case class Ident(value: String)

  private def getAttr(node: Node, attr: String): String            = node.attribute(attr).get.head.text
  private def getAttrOpt(node: Node, attr: String): Option[String] =
    node.attribute(attr).flatMap(_.headOption.map(_.text))

  sealed trait Spec {

    def node: Node

    val module: Module = Module(getAttr(node, "module"))
    val ident: Ident   = Ident(getAttr(node, "ident"))

    def gloss: Option[String] = (node \ "gloss").headOption.map(n => Utility.trim(n).text)

    def desc: Option[String] = (node \ "desc").headOption.map(n => Utility.trim(n).text)

    def classes: Seq[Ident] = (node \ "classes" \ "memberOf").map(getAttr(_, "key")).map(Ident.apply)

    def content: Seq[ContentNode] = (node \ "content" \ "_").flatMap(ContentNode.parse)

    def attList: Seq[AttList] = (node \ "attList").flatMap(AttList.parse)

    // TODO: constraintSpec

    def remarks: Option[String] = (node \ "remarks").headOption.map(n => Utility.trim(n).text)

  }

  object Spec {

    /** <macroSpec type="dt">
      *
      * 値の定義
      */
    case class MacroSpecDt(override val node: Node) extends Spec {
      // assert(content.nonEmpty) // mei-allで成り立たない
      assert(classes.isEmpty)
    }

    /** <macroSpec type="pe">
      *
      * マクロとして用いるもので、各クラスの生成時に参照はするが、これ自体は最終的に出力されない。
      */
    case class MacroSpecPe(override val node: Node) extends Spec {
      // assert(content.nonEmpty) // mei-allで成り立たない
      assert(classes.isEmpty)
    }

    /** <classSpec type="atts">
      *
      * 属性の共通定義
      */
    case class ClassSpecAtts(override val node: Node) extends Spec {
      assert(content.isEmpty)
    }

    /** <classSpec type="model">
      *
      * 親子関係の親を表す
      */
    case class ClassSpecModel(override val node: Node) extends Spec {
      assert(content.isEmpty)

    }

    /** <elementSpec> */
    case class ElementSpec(override val node: Node) extends Spec {
      // assert(content.nonEmpty) // mei-allで成り立たない
      assert(classes.nonEmpty) // mei-basicでは成り立つが、一般には成り立たないかも？

      // アトリビュートは要素固有のものをattListで定義したものと、共通のものをclassesで含めたものの両方がある。
    }

    /** ODDファイルを XML.load した要素から型定義に必要な macroSpec, classSpec, elementSpec を取得する */
    def getSpecsFromXML(xml: Elem): Map[Ident, Spec] = {

      require(xml.label == "TEI")

      val schemaSpec = (xml \ "text" \ "body" \ "schemaSpec")
        .tap(a => assert(a.length == 1))
        .head
      val allSpecs = (schemaSpec \ "_")

      assert(
        allSpecs
          .map(_.label)
          .toSet
          .subsetOf(
            Set(
              "macroSpec",
              "classSpec",
              "elementSpec",
              "constraintSpec",
              "moduleSpec",
              "moduleRef",
            ),
          ),
      )

      // <schemaSpec> 直下の xxxSpec の中には以下のような <constraintSpec>, <moduleSpec> が含まれるが、
      // これらはScalaスキーマの生成には用いないためフィルタする。
      //
      // <constraintSpec ident="set_ns" scheme="schematron" mode="add">
      //   <constraint>
      //     <sch:ns prefix="mei" uri="http://www.music-encoding.org/ns/mei"/>
      //     <sch:ns prefix="xlink" uri="http://www.w3.org/1999/xlink"/>
      //   </constraint>
      // </constraintSpec>
      // <moduleSpec n="" ident="MEI" xmlns:xi="http://www.w3.org/2001/XInclude"/>
      // <moduleSpec n="" ident="MEI.analytical" xmlns:xi="http://www.w3.org/2001/XInclude"/>
      // <moduleSpec n="" ident="MEI.cmn" xmlns:xi="http://www.w3.org/2001/XInclude"/>
      // ...

      // mei-all には moduleRef というのがあった。
      val specs = allSpecs.filter(s => s.label != "constraintSpec" && s.label != "moduleSpec" && s.label != "moduleRef")

      // 残った <macroSpec>, <classSpec>, <elementSpec> は全て属性 "module" と "ident" が存在する。
      assert(
        specs.forall(s =>
          s.attribute("module").exists(_.length == 1) &&
            s.attribute("ident").exists(_.length == 1),
        ),
      )

      // macroSpec の type は dt または pe
      // dt は値の定義
      // peはマクロとして用いるもので、各クラスの生成時に参照はするが、これ自体は最終的に出力されない。
      assert(
        specs
          .filter(_.label == "macroSpec")
          .forall(s => {
            val attr = s.attribute("type").map(_.map(_.text))
            attr == Some("dt" :: Nil) || attr == Some("pe" :: Nil)
          }),
      )

      // classSpec の type は atts または model
      // atts は属性の共通定義
      // model は親子関係の親を表す
      assert(
        specs
          .filter(_.label == "classSpec")
          .forall(s => {
            val attr = s.attribute("type").map(_.map(_.text))
            attr == Some("atts" :: Nil) || attr == Some("model" :: Nil)
          }),
      )

      // elementSpec には type はない
      assert(
        specs
          .filter(_.label == "elementSpec")
          .forall(s => s.attribute("type").isEmpty),
      )

      // specsの数とidentの数は一致する。すなわち、module を問わず ident は一意である。
      assert(specs.map(_.attribute("ident")).distinct.size == specs.size)

      specs.map(parse).map(s => (s.ident, s)).toMap
    }

    // macroSpec, classSpec, elementSpec のいずれかをパースする
    def parse(node: Node): Spec = {
      def getType(node: Node): String = getAttr(node, "type")
      node.label match {
        case "macroSpec" =>
          val tpe = getType(node)
          tpe match {
            case "dt" => MacroSpecDt(node)
            case "pe" => MacroSpecPe(node)
            case _    => throw new RuntimeException
          }
        case "classSpec" =>
          val tpe = getType(node)
          tpe match {
            case "atts"  => ClassSpecAtts(node)
            case "model" => ClassSpecModel(node)
            case _       => throw new RuntimeException
          }
        case "elementSpec" => ElementSpec(node)
        case _             => throw new RuntimeException
      }
    }

  }

  sealed trait ContentNode {
    def iterator: Iterator[ContentNode]
  }

  object ContentNode {

    def parse(node: Node): Option[ContentNode] = {
      (node.label, node.namespace) match {
        case ("valList", "http://www.tei-c.org/ns/1.0") =>
          val tpe      = getAttr(node, "type")
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          val items    = children.collect { case v: ValItem => v }.toList
          val desc     = children.collect { case v: Desc => v }.headOption
          Some(ValList(tpe, items, desc))
        case ("valItem", "http://www.tei-c.org/ns/1.0") =>
          val ident    = getAttr(node, "ident")
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          val desc     = children.collect { case v: Desc => v }.headOption
          Some(ValItem(ident, desc))
        case ("desc", "http://www.tei-c.org/ns/1.0") =>
          Some(Desc(Utility.trim(node).text))
        case ("choice", "http://relaxng.org/ns/structure/1.0") =>
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          Some(Choice(children))
        case ("ref", "http://relaxng.org/ns/structure/1.0") =>
          val name = getAttr(node, "name")
          Some(Ref(Ident(name)))
        case ("empty", "http://www.tei-c.org/ns/1.0") =>
          Some(Empty)
        case ("zeroOrMore", "http://relaxng.org/ns/structure/1.0") =>
          val child = (node \ "_").map(parse).collect { case Some(v) => v }.head
          Some(ZeroOrMore(child))
        case ("text", "http://relaxng.org/ns/structure/1.0") =>
          Some(Text)
        case ("oneOrMore", "http://relaxng.org/ns/structure/1.0") =>
          val child = (node \ "_").map(parse).collect { case Some(v) => v }.head
          Some(OneOrMore(child))
        case ("optional", "http://relaxng.org/ns/structure/1.0") =>
          val child = (node \ "_").map(parse).collect { case Some(v) => v }.head
          Some(Optional(child))
        case ("data", "http://relaxng.org/ns/structure/1.0") =>
          val tpe      = getAttr(node, "type")
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          val params   = children.collect { case v: RngParam => v }.toList
          Some(RngData(tpe, params))
        case ("param", "http://relaxng.org/ns/structure/1.0") =>
          val name  = getAttr(node, "name")
          val value = node.text
          Some(RngParam(name, value))
        case ("alternate", "http://www.tei-c.org/ns/1.0") =>
          val minOccurs = getAttr(node, "minOccurs").toInt
          val maxOccurs = getAttr(node, "maxOccurs").toInt
          val child     = (node \ "_").map(parse).collect { case Some(v) => v }.head
          Some(Alternate(minOccurs = minOccurs, maxOccurs = maxOccurs, child))
        case ("macroRef", "http://www.tei-c.org/ns/1.0") =>
          val key = getAttr(node, "key")
          Some(MacroRef(Ident(key)))
        case ("group", "http://relaxng.org/ns/structure/1.0") =>
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          Some(Group(children))
        case ("except", "http://relaxng.org/ns/structure/1.0") =>
          val children = (node \ "_").map(parse).collect { case Some(v) => v }.toList
          Some(RngExcept(children))
        case _ => None
      }
    }

    // type="closed" 以外はある？ -> closed, semi, open があるっぽい
    // children は ValItem だけでよい？
    case class ValList(tpe: String, children: List[ValItem], desc: Option[Desc]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(children), Iterable.from(desc))
    }

    // このidentはitem自体のident
    case class ValItem(ident: String, desc: Option[Desc]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(desc))
    }

    // 中にabbrを含むことがあったが、.textを得よう
    case class Desc(value: String) extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }

    case class Choice(children: List[ContentNode]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(children))
    }

    /** このname: Ident は参照先を示す
      *
      * 参照先がXML内に存在しないことが時々ある
      *  // Ref(Ident(model.frontLike))
      *  // Ref(Ident(model.backLike))
      *  // Ref(Ident(expansion))
      *  // Ref(Ident(curve))
      *  // Ref(Ident(model.symbolTableLike))
      *  // Ref(Ident(instrGrp))
      *  // Ref(Ident(ambitus))
      *  // Ref(Ident(publisher))
      *  // Ref(Ident(data.DURATIONRESTS.mensural))
      *  // Ref(Ident(data.DURATION.mensural))
      *  // Ref(Ident(pgDesc))
      */
    case class Ref(name: Ident) extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }
    case class MacroRef(key: Ident) extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }
    case object Empty extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }
    case object Text extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }

    case class ZeroOrMore(child: ContentNode) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.single(child))
    }
    case class OneOrMore(child: ContentNode) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.single(child))
    }
    // refしかないけど何が妥当だろうか
    case class Optional(child: ContentNode) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.single(child))
    }

    // macroSpec の content に含まれるrng:dataは中にrng:paramをとることがある
    // <rng:data type="nonNegativeInteger">
    //   <rng:param name="maxInclusive">4</rng:param>
    // </rng:data>
    case class RngData(tpe: String, children: List[RngParam]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(children))
    }
    case class RngParam(name: String, value: String) extends ContentNode {
      override def iterator: Iterator[ContentNode] = Iterator.apply(this)
    }
    // dataの中で除外条件を書くらしい
    case class RngExcept(children: List[ContentNode]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(children))
    }
    case class Group(children: List[ContentNode]) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(Iterable.single(this), Iterable.from(children))
    }
    // Option[Int] ?
    case class Alternate(minOccurs: Int, maxOccurs: Int, child: ContentNode) extends ContentNode {
      override def iterator: Iterator[ContentNode] =
        Iterator.concat(
          Iterable.single(this),
          Iterable.single(child),
        )
    }
  }

  case class AttList(
      attDefs: List[AttDef],
      org: AttList.Org,
  )
  object AttList {

    /** (organization) リスト中の属性が全て使用できるか(org="group")、またはその1つだけ が使用できるか(org="choice")を示す。 */
    enum Org {

      /** 全て (初期値) */
      case Group

      /** ひとつを選択
        *
        * mei-basicの中では利用例はなかった。
        */
      case Choice
    }
    object Org {
      def fromOptionString(str: Option[String]): Org = str match {
        case Some("group")  => Group
        case Some("choice") => Choice
        case Some(other)    => throw new IllegalArgumentException(s"invalid value: $other")
        case None           => Group
      }
    }

    def parse(node: Node): Option[AttList] = {
      (node.label, node.namespace) match {
        case ("attList", "http://www.tei-c.org/ns/1.0") =>
          val attDefs = (node \ "attDef").flatMap(AttDef.parse).toList
          val org     = Org.fromOptionString(getAttrOpt(node, "org"))
          Some(AttList(attDefs, org))
        case _ => None
      }
    }

  }

  case class AttDef(
      ident: String,
      usage: AttDef.Usage,
      gloss: Option[String],
      remarks: Option[String],
      desc: Option[String],
      datatype: Option[AttDef.Datatype],
      valList: Option[AttDef.ValList],
  ) {
    // datatypeかvalListのいずれかがある…と思いきや、そうではない。
    // datatype で data.MNTOKEN　を指定し、valList type="semi" で値を書くということがある。 (att.pedal.log)
    // assert((datatype.isDefined && valList.isEmpty) || (datatype.isEmpty && valList.isDefined))

    // とはいえどちらかはある
    assert(datatype.isDefined || valList.isDefined)

    // attDefの直下はいかのいずれか
    // (gloss,http://www.tei-c.org/ns/1.0)
    // (desc,http://www.tei-c.org/ns/1.0)
    // (remarks,http://www.tei-c.org/ns/1.0)
    // -
    // (valList,http://www.tei-c.org/ns/1.0)
    // (datatype,http://www.tei-c.org/ns/1.0)
    // -
    // (defaultVal,http://www.tei-c.org/ns/1.0)
    // (constraintSpec,http://www.tei-c.org/ns/1.0)

    // core: desc gloss
    // tagdocs: altIdent constraintSpec datatype defaultVal equiv exemplum remarks valDesc valList

  }

  object AttDef {

    enum Usage {

      /** req: 必須 */
      case Req

      /** rec: 推奨 */
      case Rec

      /** opt: 選択的(初期値) */
      case Opt
    }

    // <datatype> の中に <ref>か<rng:data>, <choice>, <list> がある
    sealed trait Datatype
    object Datatype {

      case object Text extends Datatype

      case class Ref(name: String) extends Datatype
      object Ref {
        def parse(node: Node): Ref = Ref(name = getAttr(node, "name"))
      }

      // attDefのdatatypeの中のrng:dataはtypeを示すのみ…と思いきやパラメータを取ることがある。option?list?
      case class RngData(`type`: String, rngParam: Option[RngData.RngParam]) extends Datatype
      object RngData {
        case class RngParam(name: String, value: String)

        def parse(node: Node): RngData = {
          val tpe      = getAttr(node, "type")
          val rngParam = (node \ "param").headOption.map { p =>
            Datatype.RngData.RngParam(
              name = getAttr(p, "name"),
              value = p.text,
            )
          }
          Datatype.RngData(tpe, rngParam)
        }
      }

      // att.typography の attDef ident="lineheight"
      // choiceの中にはrefのみ
      case class Choice(refs: List[Ref]) extends Datatype
      object Choice {
        def parse(node: Node): Choice = {
          val refs = (node \ "ref").map(Ref.parse).toList
          Choice(refs)
        }
      }

      // att.stringtab.tuning の attDef ident="tab.strings んところ
      // datatypeの中にlistもある
      // listの直下には oneOrMore -> data ->  param
      // <datatype>
      //   <list xmlns="http://relaxng.org/ns/structure/1.0">
      //     <oneOrMore>
      //       <rng:data type="token" xmlns="http://www.tei-c.org/ns/1.0">
      //         <rng:param name="pattern">[a-g][0-9](s|f|ss|x|ff|xs|sx|ts|tf|n|nf|ns|su|sd|fu|fd|nu|nd|1qf|3qf|1qs|3qs)?([a-g][0-9](s|f|ss|x|ff|xs|sx|ts|tf|n|nf|ns|su|sd|fu|fd|nu|nd|1qf|3qf|1qs|3qs)?)*</rng:param>
      //       </rng:data>
      //     </oneOrMore>
      //   </list>
      // </datatype>
      case class RngList(oneOrMore: RngList.OneOrMore) extends Datatype
      object RngList {
        def parse(node: Node): RngList = {
          val rngDatas = (node \ "oneOrMore" \ "data").map(RngData.parse).toList
          RngList(OneOrMore(rngDatas))
        }
        case class OneOrMore(rngDatas: List[RngData])
      }

      def parse(node: Node): Datatype = {
        val inners = node \ "_"
        if (inners.length != 1) { throw new IllegalArgumentException(s"invalid datatype: $inners") }
        val inner = inners.head

        (inner.label, inner.namespace) match {
          case ("text", "http://relaxng.org/ns/structure/1.0")   => Text
          case ("ref", "http://relaxng.org/ns/structure/1.0")    => Ref.parse(inner)
          case ("data", "http://relaxng.org/ns/structure/1.0")   => RngData.parse(inner)
          case ("choice", "http://relaxng.org/ns/structure/1.0") => Choice.parse(inner)
          case ("list", "http://relaxng.org/ns/structure/1.0")   => RngList.parse(inner)
          case (label, namespace)                                =>
            throw new IllegalArgumentException(s"unexpected datatype inner: $label, $namespace")
        }

      }
    }

    // tpe(@type)はclosed, semi, open(初期値)
    case class ValList(tpe: ValList.Type, children: List[ValList.ValItem], desc: Option[String])
    object ValList {

      /** 属性値リストの拡張性を示す */
      enum Type {

        /** 許可された値のみ。 */
        case Closed

        /** (semi-open) 付与される全値が支持されるべきであるが、その他の値も可能であ る。ソフトウェアは、そのための適切な代替処理を用意すべきである。 */
        case Semi

        /** 付与された値は、参考値である。 [初期値] */
        case Open
      }
      object Type {
        def fromOptionString(str: Option[String]): Type = str match {
          case Some("closed") => Type.Closed
          case Some("semi")   => Type.Semi
          case Some("open")   => Type.Open
          case Some(other)    => throw new IllegalArgumentException(s"invalid value: $other")
          case None           => Type.Open
        }
      }

      // このidentはitem自体のident
      case class ValItem(ident: String, desc: Option[String])

      def parse(node: Node): ValList = {
        val tpe      = ValList.Type.fromOptionString(getAttrOpt(node, "type"))
        val desc     = getAttrOpt(node, "desc")
        val children = (node \ "valItem").map { i =>
          ValList.ValItem(
            ident = getAttr(i, "ident"),
            desc = getAttrOpt(i, "desc"),
          )
        }.toList
        ValList(tpe, children, desc)
      }

    }

    object Usage {
      def fromOptionString(str: Option[String]): Usage = str match {
        case Some("req") => Usage.Req
        case Some("rec") => Usage.Rec
        case Some("opt") => Usage.Opt
        case Some(other) => throw new IllegalArgumentException(s"invalid value: $other")
        case None        => Usage.Opt
      }
    }

    def parse(node: Node): Option[AttDef] = {

      Option.when(node.label == "attDef" && node.namespace == "http://www.tei-c.org/ns/1.0") {
        AttDef(
          ident = getAttr(node, "ident"),
          usage = Usage.fromOptionString(getAttrOpt(node, "usage")),
          gloss = getAttrOpt(node, "gloss"),
          desc = getAttrOpt(node, "desc"),
          remarks = getAttrOpt(node, "remarks"),
          datatype = (node \ "datatype").headOption.map(Datatype.parse),
          valList = (node \ "valList").headOption.map(ValList.parse),
        )
      }
    }

  }

}
