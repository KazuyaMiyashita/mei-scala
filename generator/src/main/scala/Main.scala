import scala.xml.XML
import java.io.InputStream
import generator.*

object Main {

  def main(args: Array[String]): Unit = {

    List(
      RunArgs("/mei-zero.rng", "mei.zero", "src/main/scala/mei/zero"),
      RunArgs("/mei-minimum.rng", "mei.minimum", "src/main/scala/mei/minimum"),
      RunArgs("/mei-basic.rng", "mei.basic", "src/main/scala/mei/basic"),
      RunArgs("/mei-CMN.rng", "mei.cmn", "src/main/scala/mei/cmn"),
      RunArgs("/mei-Mensural.rng", "mei.mensural", "src/main/scala/mei/mensural"),
      RunArgs("/mei-Neumes.rng", "mei.neumes", "src/main/scala/mei/neumes"),
      RunArgs("/mei-all.rng", "mei.all", "src/main/scala/mei/all"),
      RunArgs("/mei-all_anyStart.rng", "mei.allanystart", "src/main/scala/mei/allanystart"),
    ).foreach(runEach)
  }

  case class RunArgs(
      filename: String,
      packageName: String,
      outputFile: String,
  )
  def runEach(
      args: RunArgs,
  ): Unit = {
    // 1. Load file from Resources
    val stream: InputStream = getClass.getResourceAsStream(args.filename)

    if (stream == null) {
      throw new RuntimeException(s"Error: ${args.filename} not found in resources.")
    }

    println(s"Loading ${args.filename} ...")

    // 2. Load with Scala XML
    val xmlRoot = XML.load(stream)

    // 3. Convert XML to Scala AST (RngPattern)
    val astRoot = RelaxNGParser.parse(xmlRoot)
    println("Parsing completed.\n")

    // Phase 2: Semantic Analysis
    println("Generating Semantic Model...")
    val semanticDefinitions = MeiSchemaAnalyzer.analyze(astRoot)
    println(s"Generated ${semanticDefinitions.size} semantic definitions.")

    // Phase 3: Code Generation (Writer)
    println("Generating Scala Source Code...")
    val sourceCode = MeiModelWriter.generate(semanticDefinitions, args.packageName)

    // Output result (save to file)
    val outputPath = java.nio.file.Paths.get(s"${args.outputFile}/MeiModel.scala")
    if (outputPath.getParent != null) {
      java.nio.file.Files.createDirectories(outputPath.getParent): Unit
    }
    java.nio.file.Files.write(outputPath, sourceCode.getBytes("UTF-8"))

    println(s"Generated code written to: $outputPath")

    // Phase 4: Parser Generation
    println("Generating Scala Parser Code...")
    val parserCode       = MeiModelParserWriter.generate(semanticDefinitions, args.packageName)
    val parserOutputPath = java.nio.file.Paths.get(s"${args.outputFile}/parser/MeiModelParser.scala")
    if (parserOutputPath.getParent != null) {
      java.nio.file.Files.createDirectories(parserOutputPath.getParent): Unit
    }
    java.nio.file.Files.write(parserOutputPath, parserCode.getBytes("UTF-8"))
    println(s"Generated parser written to: $parserOutputPath")

    // Phase 5: Writer Generation
    println("Generating Scala Writer Code...")
    val writerCode       = MeiModelWriterWriter.generate(semanticDefinitions, args.packageName)
    val writerOutputPath = java.nio.file.Paths.get(s"${args.outputFile}/writer/MeiModelWriter.scala")
    if (writerOutputPath.getParent != null) {
      java.nio.file.Files.createDirectories(writerOutputPath.getParent): Unit
    }
    java.nio.file.Files.write(writerOutputPath, writerCode.getBytes("UTF-8"))
    println(s"Generated writer written to: $writerOutputPath")
  }

}
