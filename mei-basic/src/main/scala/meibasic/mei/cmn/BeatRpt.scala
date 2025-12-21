package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An indication that material on a preceding beat should be repeated.
 * 
 * Ident: beatRpt
 * Module: MEI.cmn
 */
case class BeatRpt(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.beatrpt.Log with meibasic.mei.visual.att.beatrpt.Vis with meibasic.mei.shared.att.Plist with meibasic.mei.cmn.model.eventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("beatdef", "color", "expand", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "label", "layer", "plist", "slash", "staff", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_beatRpt)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object BeatRpt {
  def apply(`beatdef`: java.lang.String = null, `color`: java.lang.String = null, `expand`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `plist`: java.lang.String = null, `slash`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): BeatRpt = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`beatdef` != null) Some("beatdef" -> `beatdef`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`expand` != null) Some("expand" -> `expand`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`slash` != null) Some("slash" -> `slash`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new BeatRpt(elements.toList, attributes)
  }
}
