package generator

import scala.xml.{Node, Elem}
import RelaxNG.*

object RelaxNGParser {

  def parse(node: Node): RelaxNG = node match {
    // Structure
    case e: Elem if e.label == "grammar" =>
      Grammar(e.child.map(parse).toList)

    case e: Elem if e.label == "div" =>
      Div(e.child.map(parse).toList)

    case e: Elem if e.label == "define" =>
      Define(e \@ "name", Option(e \@ "combine").filter(_.nonEmpty), e.child.map(parse).toList)

    case e: Elem if e.label == "ref" =>
      val name = e \@ "name"
      if (name.isEmpty) println(s"WARNING: Found ref with empty name: $e")
      Ref(name)

    case e: Elem if e.label == "start" =>
      Start(e.child.map(parse).toList)

    case e: Elem if e.label == "include" =>
      // Note: Although files are normally loaded here, only paths are retained during AST construction.
      Include(e \@ "href", e.child.map(parse).toList)

    // Elements and Attributes
    case e: Elem if e.label == "element" =>
      Element(e \@ "name", e.child.map(parse).toList)

    case e: Elem if e.label == "attribute" =>
      Attribute(e \@ "name", e.child.map(parse).toList)

    // Control
    case e: Elem if e.label == "choice" =>
      Choice(e.child.map(parse).toList)

    case e: Elem if e.label == "group" =>
      Group(e.child.map(parse).toList)

    case e: Elem if e.label == "interleave" =>
      Interleave(e.child.map(parse).toList)

    // Quantifiers
    case e: Elem if e.label == "optional" =>
      Optional(e.child.map(parse).toList)

    case e: Elem if e.label == "zeroOrMore" =>
      ZeroOrMore(e.child.map(parse).toList)

    case e: Elem if e.label == "oneOrMore" =>
      OneOrMore(e.child.map(parse).toList)

    // Data
    case e: Elem if e.label == "text" =>
      Text()

    case e: Elem if e.label == "notAllowed" =>
      NotAllowed()

    case e: Elem if e.label == "data" =>
      Data(e \@ "type", Map.empty) // Parameter extraction omitted

    case e: Elem if e.label == "value" =>
      Value(e.text.trim)

    case e: Elem if e.label == "documentation" =>
      Documentation(e.text.trim)

    case e: Elem if e.label == "anyName" =>
      AnyName()

    // Other (whitespace text nodes are ignored or treated as Other)
    case _ =>
      if (node.text.trim.isEmpty) Empty() // Nodes containing only whitespace are treated as Empty
      else Other(node.label, node.text.trim)
  }
}
