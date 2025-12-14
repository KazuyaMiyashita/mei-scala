package mei.cmn

import org.scalatest.funsuite.AnyFunSuite
import scala.xml.XML
import java.io.InputStream
import mei.cmn.parser.MeiParser
import mei.cmn.writer.MeiWriter

class MeiModelParserAndWriterWriterSpec extends AnyFunSuite {

  test("Parse Bach-JC_Fughette_No2.mei") {
    val filename               = "/Bach-JC_Fughette_No2.mei"
    val rngStream: InputStream = getClass.getResourceAsStream(filename)
    assert(rngStream != null, s"$filename not found in test resources")

    val xmlRoot = XML.load(rngStream)
    val meiRoot = MeiParser.parse(xmlRoot)

    // println(meiRoot)

    MeiWriter.toXml(meiRoot)

    // println(writenXml.toString)

  }
}
