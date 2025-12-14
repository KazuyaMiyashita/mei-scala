package generator

import org.scalatest.funsuite.AnyFunSuite
import scala.xml.XML
import java.io.InputStream
import scala.io.Source
import generator.{MeiModelParserWriter, RelaxNGParser}

class MeiModelParserWriterSpec extends AnyFunSuite {

  test("Generate parser code from mei-zero.rng") {
    runEach("/mei-zero.rng", "mei.zero", "/zero/parser/MeiModelParser.scala")
  }
  test("Generate parser code from mei-minimum.rng") {
    runEach("/mei-minimum.rng", "mei.minimum", "/minimum/parser/MeiModelParser.scala")
  }
  test("Generate parser code from mei-basic.rng") {
    runEach("/mei-basic.rng", "mei.basic", "/basic/parser/MeiModelParser.scala")
  }
  test("Generate parser code from mei-cmn.rng") {
    runEach("/mei-cmn.rng", "mei.cmn", "/cmn/parser/MeiModelParser.scala")
  }
  private def normalize(s: String): String = {
    s.linesIterator.map(_.trim).filter(_.nonEmpty).mkString("\n")
  }

  private def runEach(filename: String, packageName: String, expectedFile: String): Unit = {
    // 1. Load RNG from test resources
    val rngStream: InputStream = getClass.getResourceAsStream(filename)
    assert(rngStream != null, s"$filename not found in test resources")

    val xmlRoot             = XML.load(rngStream)
    val astRoot             = RelaxNGParser.parse(xmlRoot)
    val semanticDefinitions = MeiSchemaAnalyzer.analyze(astRoot)
    val generatedCode       = MeiModelParserWriter.generate(semanticDefinitions, packageName)

    // 2. Load Expected Code from test resources
    val expectedStream: InputStream = getClass.getResourceAsStream(expectedFile)
    assert(expectedStream != null, s"$expectedFile not found in test resources")

    val expectedCode = Source.fromInputStream(expectedStream).mkString

    // 3. Compare (Normalizing whitespace/newlines for robustness)
    assert(normalize(generatedCode) == normalize(expectedCode))

    ()
  }

}
