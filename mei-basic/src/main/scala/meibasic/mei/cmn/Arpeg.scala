package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Indicates that the notes of a chord are to be performed successively rather than simultaneously, usually from lowest to highest. Sometimes called a "roll".
 * 
 * Ident: arpeg
 * Module: MEI.cmn
 */
case class Arpeg(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.arpeg.Log with meibasic.mei.visual.att.arpeg.Vis with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("arrow", "arrow.color", "arrow.fillcolor", "arrow.shape", "arrow.size", "color", "enclose", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "label", "layer", "letterspacing", "lform", "lineheight", "lsegs", "lwidth", "order", "plist", "staff", "startid", "tstamp", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_arpeg)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Arpeg {
  def apply(`arrow`: java.lang.String = null, `arrow.color`: java.lang.String = null, `arrow.fillcolor`: java.lang.String = null, `arrow.shape`: java.lang.String = null, `arrow.size`: java.lang.String = null, `color`: java.lang.String = null, `enclose`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lform`: java.lang.String = null, `lineheight`: java.lang.String = null, `lsegs`: java.lang.String = null, `lwidth`: java.lang.String = null, `order`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Arpeg = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`arrow` != null) Some("arrow" -> `arrow`) else None,
      if (`arrow.color` != null) Some("arrow.color" -> `arrow.color`) else None,
      if (`arrow.fillcolor` != null) Some("arrow.fillcolor" -> `arrow.fillcolor`) else None,
      if (`arrow.shape` != null) Some("arrow.shape" -> `arrow.shape`) else None,
      if (`arrow.size` != null) Some("arrow.size" -> `arrow.size`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`enclose` != null) Some("enclose" -> `enclose`) else None,
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
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`order` != null) Some("order" -> `order`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Arpeg(elements.toList, attributes)
  }
}
