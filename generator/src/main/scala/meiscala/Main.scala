package meiscala

import scala.xml.XML
import meiscala.generator.ODD
import meiscala.generator.ODD.Ident
import meiscala.generator.ODD.Spec
import meiscala.generator.Generator
import java.nio.file.{Paths, Files}
import java.util.Comparator

object Main {

  def main(args: Array[String]): Unit = {

    List(
      RunEachArgs("/odd/mei-basic_compiled_formatted.odd", "meibasic", "mei-basic/src/main/scala"),
      RunEachArgs("/odd/mei-CMN_compiled.odd", "meicmn", "mei-cmn/src/main/scala"),
      RunEachArgs("/odd/mei-all_compiled.odd", "meiall", "mei-all/src/main/scala"),
    ).foreach(runEach)
  }

  case class RunEachArgs(
      oddFilePath: String,
      packageNamePrefix: String,
      outputPath: String,
  )
  def runEach(args: RunEachArgs): Unit = {
    val xml = XML.load(getClass.getResourceAsStream(args.oddFilePath))

    val specsMap: Map[Ident, Spec] = ODD.Spec.getSpecsFromXML(xml)

    val files = Generator.generate(specsMap, args.packageNamePrefix)

    val outputRoot = Paths.get(args.outputPath)

    if (Files.exists(outputRoot)) {
      Files
        .walk(outputRoot)
        .sorted(Comparator.reverseOrder())
        .forEach(path => if (path != outputRoot) Files.delete(path))
    }

    println(s"Writing ${files.size} files to ${outputRoot.toAbsolutePath}...")

    files.foreach { case (path, content) =>
      val targetPath = outputRoot.resolve(path)
      Files.createDirectories(targetPath.getParent)
      Files.writeString(targetPath, content)
    }

    println("Done.")
  }

}
