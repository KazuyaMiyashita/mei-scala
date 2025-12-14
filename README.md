# The Music Encoding Initiative in Scala

This project transforms the schema of the [Music Encoding Initiative (MEI)](https://music-encoding.org/) into Scala classes, making it accessible for programmatic manipulation.

The schema used is **Version 5.1** from the [MEI Schema repository](https://github.com/music-encoding/schema).

The models are provided as separate packages, corresponding to each of the following MEI Profiles:

  * [mei-CMN](./src/main/scala/mei/cmn/MeiModel.scala)
  * [mei-Mensural](./src/main/scala/mei/mensural/MeiModel.scala)
  * [mei-Neumes](./src/main/scala/mei/neumes/MeiModel.scala)
  * [mei-Basic](./src/main/scala/mei/basic/MeiModel.scala)
  * [mei-all](./src/main/scala/mei/all/MeiModel.scala)
  * [mei-all\_anyStart](./src/main/scala/mei/allanystart/MeiModel.scala)

## Usage

### Creating MEI Structures

An MEI structure can be created as a case class hierarchy, as shown below:

```scala
import mei.cmn.{List => MeiList, String => MeiString, *}

Mei(
  MeiHead(
    FileDesc(
      TitleStmt(
        Title(
          "My Works" :: Nil
        ) :: Nil
      ) :: Nil
    ) :: Nil
  ) :: 
  Music(
    /* ... */
  )
  :: Nil
)
```

### Reading and Writing MEI XML Files

The library also supports reading from and writing to MEI XML files.

```scala
import scala.xml.XML
import mei.cmn.parser.MeiParser
import mei.cmn.writer.MeiWriter

val inputFile = "/path/to/your/input.mei"
val outputPath = "/path/to/save/output.mei"

// Reading: XML file -> Scala Case Class
val meiRoot = MeiParser.parse(XML.loadFile(inputFile))

// Writing: Scala Case Class -> XML File
XML.save(outputPath, MeiWriter.toXml(meiRoot), "UTF-8", true, null)
```

-----

### Customizing MEI

MEI is customizable (see: [https://music-encoding.org/guidelines/v5/content/introduction.html\#meiCustomization](https://music-encoding.org/guidelines/v5/content/introduction.html#meiCustomization)).

By referring to [https://github.com/music-encoding/music-encoding](https://github.com/music-encoding/music-encoding) or [https://github.com/music-encoding/docker-mei](https://github.com/music-encoding/docker-mei) to create your own custom Relax NG files (`.rng`), you can then modify and run the **[generator subproject](./generator/src/main/scala/Main.scala)** to obtain the corresponding Scala files.

> (Note: The customized MEI schemas `mei-zero.rng` and `mei-minimum.rng` are for this project's development purposes only and can be ignored.)

### Important Note

**Compilation is extremely slow.** On the development environment, it takes approximately 200 seconds. This is due to the large number of class definitions and the fact that all MEI Profiles are grouped into a single project. This issue is currently under improvement, but be aware that **the interface is highly likely to change significantly** during this process.
