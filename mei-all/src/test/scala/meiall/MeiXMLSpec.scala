package meiall

import org.scalatest.funsuite.AnyFunSuite

import meiall.MeiXML
import scala.xml.XML

class MeiSyntaxSpec extends AnyFunSuite {

  test("Convert Mei XML") {
    val xml  = XML.load(getClass.getResourceAsStream("/mei/001.mei"))
    val mei  = MeiXML.load(xml)
    val xml2 = MeiXML.toXml(mei)

    assert(xml.toString == xml2.toString)
  }
}
