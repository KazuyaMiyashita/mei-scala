package meiall

import org.scalatest.funsuite.AnyFunSuite

import meiall.MeiXML
import scala.xml.XML
import scala.xml.PrettyPrinter

class MeiSyntaxSpec extends AnyFunSuite {

  test("No blank Text nodes are created") {

    val xml = XML.load(getClass.getResourceAsStream("/mei/001.mei"))

    val mei = MeiXML.load(xml)

    // Use transform to remove blank Text nodes
    val filteredMei = MeiXML.load(xml).transform { element =>
      val cleanChildren = element.children.filterNot {
        case Text(value) => value.trim.isEmpty
        case _           => false
      }
      if (cleanChildren.size != element.children.size) {
        element.withChildren(cleanChildren)
      } else {
        element
      }
    }

    assert(mei == filteredMei)
  }

  test("The original XML and the one loaded and toXml match except for blank information.") {

    val printer = new PrettyPrinter(Int.MaxValue, 2)

    val xml  = XML.load(getClass.getResourceAsStream("/mei/001.mei"))
    val mei  = MeiXML.load(xml)
    val xml2 = MeiXML.toXml(mei)

    assert(printer.format(xml) == printer.format(xml2))
  }
}
