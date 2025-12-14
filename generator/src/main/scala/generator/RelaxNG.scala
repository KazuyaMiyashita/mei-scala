package generator

/** Base trait for all RelaxNG elements.
  * Corresponds one-to-one with XML tags.
  */
sealed trait RelaxNG

object RelaxNG {

  // ==========================================
  // 構造・定義 (Structure & Definitions)
  // ==========================================

  /** <grammar>: The root element of RelaxNG, containing define and start elements. */
  case class Grammar(children: List[RelaxNG]) extends RelaxNG

  /** * <define name="...">: Definition of reusable patterns.
    * The combine attribute is frequently used in MEI (e.g., combine="choice").
    */
  case class Define(name: String, combine: Option[String], children: List[RelaxNG]) extends RelaxNG

  /** <ref name="...">: Reference to a pattern defined by `define`. */
  case class Ref(name: String) extends RelaxNG

  /** <start>: The starting point of validation (specifying the root element). */
  case class Start(children: List[RelaxNG]) extends RelaxNG

  /** * <include href="...">: Inclusion of external RNG files.
    * Frequently used in MEI for modularization. The content is expanded into children after loading.
    */
  case class Include(href: String, children: List[RelaxNG]) extends RelaxNG

  // ==========================================
  // XML要素・属性 (Elements & Attributes)
  // ==========================================

  /** <element name="...">: Definition of an XML element. */
  case class Element(name: String, children: List[RelaxNG]) extends RelaxNG

  /** <attribute name="...">: Definition of an XML attribute. */
  case class Attribute(name: String, children: List[RelaxNG]) extends RelaxNG

  // ==========================================
  // 制御構造 (Control Structures)
  // ==========================================

  /** <choice>: Selects one of multiple patterns (OR condition). */
  case class Choice(children: List[RelaxNG]) extends RelaxNG

  /** <group>: Ordered sequence of patterns (AND condition). */
  case class Group(children: List[RelaxNG]) extends RelaxNG

  /** <interleave>: Unordered sequence. Commonly used for attribute definitions and mixed content. */
  case class Interleave(children: List[RelaxNG]) extends RelaxNG

  // ==========================================
  // 数量子 (Quantifiers)
  // ==========================================

  /** <optional>: Zero or one occurrence (?). */
  case class Optional(children: List[RelaxNG]) extends RelaxNG

  /** <zeroOrMore>: Zero or more occurrences (*). */
  case class ZeroOrMore(children: List[RelaxNG]) extends RelaxNG

  /** <oneOrMore>: One or more occurrences (+). */
  case class OneOrMore(children: List[RelaxNG]) extends RelaxNG

  // ==========================================
  // データ・値 (Data & Values)
  // ==========================================

  /** <text>: Any text content. */
  case class Text() extends RelaxNG

  /** <empty>: Empty content. */
  case class Empty() extends RelaxNG

  /** <notAllowed>: Disallowed content. */
  case class NotAllowed() extends RelaxNG

  /** <data type="...">: Data type (e.g., xsd:int, xsd:date). */
  case class Data(datatype: String, params: Map[String, String]) extends RelaxNG

  /** <value>: Specific fixed value (used in enumeration definitions, etc.). */
  case class Value(content: String) extends RelaxNG

  // ==========================================
  // その他 (Metadata & Fallback)
  // ==========================================

  /** Annotations such as <a:documentation>, or for unknown tags. */
  case class Other(tag: String, content: String) extends RelaxNG

  /** <a:documentation>: Documentation element. */
  case class Documentation(content: String) extends RelaxNG

  /** <anyName>: Any name. */
  case class AnyName() extends RelaxNG

  /** <div>: Namespace or grouping. */
  case class Div(children: List[RelaxNG]) extends RelaxNG

}
