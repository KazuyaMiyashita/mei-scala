package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Piano pedal mark.
 * 
 * Ident: pedal
 * Module: MEI.cmn
 */
case class Pedal(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.pedal.Log with meibasic.mei.visual.att.pedal.Vis with meibasic.mei.gestural.att.pedal.Ges with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "dir", "endid", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "form", "func", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "label", "layer", "lendsym", "lendsym.size", "letterspacing", "lform", "lineheight", "lsegs", "lstartsym", "lstartsym.size", "lwidth", "place", "plist", "staff", "startid", "tstamp", "tstamp2", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_pedal)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Pedal {
  def apply(`color`: java.lang.String = null, `dir`: java.lang.String = null, `endid`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `form`: java.lang.String = null, `func`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lendsym`: java.lang.String = null, `lendsym.size`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lform`: java.lang.String = null, `lineheight`: java.lang.String = null, `lsegs`: java.lang.String = null, `lstartsym`: java.lang.String = null, `lstartsym.size`: java.lang.String = null, `lwidth`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Pedal = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dir` != null) Some("dir" -> `dir`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`func` != null) Some("func" -> `func`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`lendsym` != null) Some("lendsym" -> `lendsym`) else None,
      if (`lendsym.size` != null) Some("lendsym.size" -> `lendsym.size`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lstartsym` != null) Some("lstartsym" -> `lstartsym`) else None,
      if (`lstartsym.size` != null) Some("lstartsym.size" -> `lstartsym.size`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Pedal(elements.toList, attributes)
  }
}
