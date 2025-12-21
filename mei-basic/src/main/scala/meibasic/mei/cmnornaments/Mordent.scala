package meibasic.mei.cmnornaments

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An ornament indicating rapid alternation of the main note with a secondary note, usually a step below, but sometimes a step above.
 * 
 * Ident: mordent
 * Module: MEI.cmnOrnaments
 */
case class Mordent(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmnornaments.att.mordent.Log with meibasic.mei.visual.att.mordent.Vis with meibasic.mei.cmnornaments.model.ornamentlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("accidlower", "accidlower.ges", "accidupper", "accidupper.ges", "color", "enclose", "endid", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "form", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "label", "layer", "letterspacing", "lineheight", "long", "place", "plist", "staff", "startid", "tstamp", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_mordent)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Mordent {
  def apply(`accidlower`: java.lang.String = null, `accidlower.ges`: java.lang.String = null, `accidupper`: java.lang.String = null, `accidupper.ges`: java.lang.String = null, `color`: java.lang.String = null, `enclose`: java.lang.String = null, `endid`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `form`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `long`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Mordent = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`accidlower` != null) Some("accidlower" -> `accidlower`) else None,
      if (`accidlower.ges` != null) Some("accidlower.ges" -> `accidlower.ges`) else None,
      if (`accidupper` != null) Some("accidupper" -> `accidupper`) else None,
      if (`accidupper.ges` != null) Some("accidupper.ges" -> `accidupper.ges`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`enclose` != null) Some("enclose" -> `enclose`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`long` != null) Some("long" -> `long`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Mordent(elements.toList, attributes)
  }
}
