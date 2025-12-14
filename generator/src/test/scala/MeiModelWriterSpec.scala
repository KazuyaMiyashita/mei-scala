package generator

import org.scalatest.funsuite.AnyFunSuite
import scala.xml.XML
import java.io.InputStream
import scala.io.Source
import generator.{RelaxNGParser, MeiModelWriter}

class MeiModelWriterSpec extends AnyFunSuite {

  test("Generate model code from mei-zero.rng") {
    runEach("/mei-zero.rng", "mei.zero", "/zero/MeiModel.scala")
  }
  test("Generate model code from mei-minimum.rng") {
    runEach("/mei-minimum.rng", "mei.minimum", "/minimum/MeiModel.scala")
  }
  test("Generate model code from mei-basic.rng") {
    runEach("/mei-basic.rng", "mei.basic", "/basic/MeiModel.scala")
  }
  test("Generate model code from mei-cmn.rng") {
    runEach("/mei-cmn.rng", "mei.cmn", "/cmn/MeiModel.scala")
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
    val generatedCode       = MeiModelWriter.generate(semanticDefinitions, packageName)

    // 2. Load Expected Code from test resources
    val expectedStream: InputStream = getClass.getResourceAsStream(expectedFile)
    assert(expectedStream != null, s"$expectedFile not found in test resources")

    val expectedCode = Source.fromInputStream(expectedStream).mkString

    // 3. Compare (Normalizing whitespace/newlines for robustness)
    assert(normalize(generatedCode) == normalize(expectedCode))

    ()
  }

}
