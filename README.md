# The Music Encoding Initiative in Scala

This project transforms the schema of the [Music Encoding Initiative (MEI)](https://music-encoding.org/) into Scala classes, making it accessible for programmatic manipulation.

## Usage

### Creating MEI Structures

An MEI structure can be created as a case class hierarchy, as shown below:

```scala
import meibasic.Syntax.*

val meiStructure = mei()(
  meiHead()(
    fileDesc()(
      titleStmt()(
        title()("My Works"),
      ),
    ),
  ),
  music()(
    body()(
      mdiv()(
        score()(
          scoreDef(`meter.count` = "4", `meter.unit` = "4", keysig = "1f")(),
          section()(
            measure(n = "1")(
              staff(n = "1")(
                layer(n = "1")(
                  mRest(`xml:id` = "d1e220")(),
                ),
              ),
            ),
            measure(n = "1")(
              staff(n = "1")(
                layer(n = "1")(
                  rest(`xml:id` = "d1e282", dur = "4")(),
                  note(`xml:id` = "d1e290", pname = "g", oct = "3", dur = "4", `stem.dir` = "down")(),
                  note(`xml:id` = "d1e304", pname = "g", oct = "3", dur = "4", `stem.dir` = "down")(),
                  note(`xml:id` = "d1e318", pname = "b", oct = "3", dur = "4", `stem.dir` = "down")(),
                ),
              ),
            ),
          ),
        ),
      ),
    ),
  ),
)

meiStructure.validate() // true
```

### XML Conversion

You can load MEI structures from XML and convert them back to XML using the `MeiXML` object.

```scala
import meibasic.MeiXML
import scala.xml.XML

// Load from XML file
val xml = XML.loadFile("path/to/file.mei")
val mei = MeiXML.load(xml)

// Manipulate the MEI structure...

// Convert back to XML
val newXml = MeiXML.toXml(mei)
XML.save("path/to/output.mei", newXml, "UTF-8", true)
```

### Transforming MEI Structures

You can use the `transform` method to apply a rewrite rule to the entire MEI tree. This is useful for tasks like renaming elements or modifying specific nodes recursively.

```scala
import meiall.Element
import meiall.mei.shared.{Dir, Tempo}

// Define a transformation rule
val rule: Element => Element = {
  // Convert Dir elements to Tempo if they contain "Allegro"
  case d: Dir if d.elements.exists {
    case meiall.Text(t) => t.contains("Allegro")
    case _ => false
  } =>
    Tempo(d.elements, d.attributes)
    
  case other => other
}

// Apply the transformation to the entire tree
val transformedMei = mei.transform(rule)
```
