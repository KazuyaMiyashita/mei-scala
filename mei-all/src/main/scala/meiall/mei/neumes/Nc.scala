package meiall.mei.neumes

import meiall.Element
import meiall.Schemas
import meiall.Validator

/**
 * Sign representing a single pitched event, although the exact pitch may not be known.
 * 
 * Ident: nc
 * Module: MEI.neumes
 */
case class Nc(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meiall.Element with meiall.mei.shared.att.Basic with meiall.mei.shared.att.Classed with meiall.mei.facsimile.att.Facsimile with meiall.mei.shared.att.Labelled with meiall.mei.shared.att.Linking with meiall.mei.shared.att.NNumberLike with meiall.mei.shared.att.Responsibility with meiall.mei.analytical.att.nc.Anl with meiall.mei.gestural.att.nc.Ges with meiall.mei.neumes.att.nc.Log with meiall.mei.visual.att.nc.Vis with meiall.mei.neumes.model.NeumePart {
  override def validate(): Boolean = {
    val allowedAttrs = Set("accid.ges", "altsym", "angled", "artic.ges", "class", "color", "con", "copyof", "corresp", "curve", "deg", "dots.ges", "dur.ges", "dur.metrical", "dur.ppq", "dur.real", "dur.recip", "facs", "follows", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "hooked", "instr", "intm", "label", "layer", "letterspacing", "ligated", "lineheight", "loc", "mfunc", "n", "next", "oct", "oct.ges", "pclass", "pname", "pname.ges", "pnum", "precedes", "prev", "psolfa", "rellen", "resp", "s-shape", "sameas", "staff", "synch", "tilt", "type", "vel", "visible", "when", "x", "xml:base", "xml:id", "y")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_nc)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Nc {
  def apply(`accid.ges`: java.lang.String = null, `altsym`: java.lang.String = null, `angled`: java.lang.String = null, `artic.ges`: java.lang.String = null, `class`: java.lang.String = null, `color`: java.lang.String = null, `con`: java.lang.String = null, `copyof`: java.lang.String = null, `corresp`: java.lang.String = null, `curve`: java.lang.String = null, `deg`: java.lang.String = null, `dots.ges`: java.lang.String = null, `dur.ges`: java.lang.String = null, `dur.metrical`: java.lang.String = null, `dur.ppq`: java.lang.String = null, `dur.real`: java.lang.String = null, `dur.recip`: java.lang.String = null, `facs`: java.lang.String = null, `follows`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `hooked`: java.lang.String = null, `instr`: java.lang.String = null, `intm`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `letterspacing`: java.lang.String = null, `ligated`: java.lang.String = null, `lineheight`: java.lang.String = null, `loc`: java.lang.String = null, `mfunc`: java.lang.String = null, `n`: java.lang.String = null, `next`: java.lang.String = null, `oct`: java.lang.String = null, `oct.ges`: java.lang.String = null, `pclass`: java.lang.String = null, `pname`: java.lang.String = null, `pname.ges`: java.lang.String = null, `pnum`: java.lang.String = null, `precedes`: java.lang.String = null, `prev`: java.lang.String = null, `psolfa`: java.lang.String = null, `rellen`: java.lang.String = null, `resp`: java.lang.String = null, `s-shape`: java.lang.String = null, `sameas`: java.lang.String = null, `staff`: java.lang.String = null, `synch`: java.lang.String = null, `tilt`: java.lang.String = null, `type`: java.lang.String = null, `vel`: java.lang.String = null, `visible`: java.lang.String = null, `when`: java.lang.String = null, `x`: java.lang.String = null, `xml:base`: java.lang.String = null, `xml:id`: java.lang.String = null, `y`: java.lang.String = null)(elements: Element*): Nc = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`accid.ges` != null) Some("accid.ges" -> `accid.ges`) else None,
      if (`altsym` != null) Some("altsym" -> `altsym`) else None,
      if (`angled` != null) Some("angled" -> `angled`) else None,
      if (`artic.ges` != null) Some("artic.ges" -> `artic.ges`) else None,
      if (`class` != null) Some("class" -> `class`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`con` != null) Some("con" -> `con`) else None,
      if (`copyof` != null) Some("copyof" -> `copyof`) else None,
      if (`corresp` != null) Some("corresp" -> `corresp`) else None,
      if (`curve` != null) Some("curve" -> `curve`) else None,
      if (`deg` != null) Some("deg" -> `deg`) else None,
      if (`dots.ges` != null) Some("dots.ges" -> `dots.ges`) else None,
      if (`dur.ges` != null) Some("dur.ges" -> `dur.ges`) else None,
      if (`dur.metrical` != null) Some("dur.metrical" -> `dur.metrical`) else None,
      if (`dur.ppq` != null) Some("dur.ppq" -> `dur.ppq`) else None,
      if (`dur.real` != null) Some("dur.real" -> `dur.real`) else None,
      if (`dur.recip` != null) Some("dur.recip" -> `dur.recip`) else None,
      if (`facs` != null) Some("facs" -> `facs`) else None,
      if (`follows` != null) Some("follows" -> `follows`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`hooked` != null) Some("hooked" -> `hooked`) else None,
      if (`instr` != null) Some("instr" -> `instr`) else None,
      if (`intm` != null) Some("intm" -> `intm`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`ligated` != null) Some("ligated" -> `ligated`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`loc` != null) Some("loc" -> `loc`) else None,
      if (`mfunc` != null) Some("mfunc" -> `mfunc`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`next` != null) Some("next" -> `next`) else None,
      if (`oct` != null) Some("oct" -> `oct`) else None,
      if (`oct.ges` != null) Some("oct.ges" -> `oct.ges`) else None,
      if (`pclass` != null) Some("pclass" -> `pclass`) else None,
      if (`pname` != null) Some("pname" -> `pname`) else None,
      if (`pname.ges` != null) Some("pname.ges" -> `pname.ges`) else None,
      if (`pnum` != null) Some("pnum" -> `pnum`) else None,
      if (`precedes` != null) Some("precedes" -> `precedes`) else None,
      if (`prev` != null) Some("prev" -> `prev`) else None,
      if (`psolfa` != null) Some("psolfa" -> `psolfa`) else None,
      if (`rellen` != null) Some("rellen" -> `rellen`) else None,
      if (`resp` != null) Some("resp" -> `resp`) else None,
      if (`s-shape` != null) Some("s-shape" -> `s-shape`) else None,
      if (`sameas` != null) Some("sameas" -> `sameas`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`synch` != null) Some("synch" -> `synch`) else None,
      if (`tilt` != null) Some("tilt" -> `tilt`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vel` != null) Some("vel" -> `vel`) else None,
      if (`visible` != null) Some("visible" -> `visible`) else None,
      if (`when` != null) Some("when" -> `when`) else None,
      if (`x` != null) Some("x" -> `x`) else None,
      if (`xml:base` != null) Some("xml:base" -> `xml:base`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`y` != null) Some("y" -> `y`) else None
    ).flatten)
    new Nc(elements.toList, attributes)
  }
}
