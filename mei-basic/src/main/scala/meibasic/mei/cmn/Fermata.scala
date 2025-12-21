package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An indication placed over a note or rest to indicate that it should be held longer than its written value. May also occur over a bar line to indicate the end of a phrase or section. Sometimes called a 'hold' or 'pause'.
 * 
 * Ident: fermata
 * Module: MEI.cmn
 */
case class Fermata(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.fermata.Log with meibasic.mei.visual.att.fermata.Vis with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "endid", "form", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "label", "layer", "place", "plist", "shape", "staff", "startid", "tstamp", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_fermata)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Fermata {
  def apply(`color`: java.lang.String = null, `endid`: java.lang.String = null, `form`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `shape`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Fermata = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`shape` != null) Some("shape" -> `shape`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Fermata(elements.toList, attributes)
  }
}
