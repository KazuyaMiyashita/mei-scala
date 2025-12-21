package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A formatting element indicating special visual rendering,e.g., bold or italicized, of a text word or phrase.
 * 
 * Ident: rend
 * Module: MEI.shared
 */
case class Rend(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Color with meibasic.mei.shared.att.Common with meibasic.mei.externalsymbols.att.extsym.Auth with meibasic.mei.shared.att.HorizontalAlign with meibasic.mei.shared.att.Lang with meibasic.mei.shared.att.TextRendition with meibasic.mei.shared.att.Typography with meibasic.mei.shared.att.VerticalAlign with meibasic.mei.shared.att.Whitespace with meibasic.mei.shared.model.RendLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("altrend", "color", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "glyph.auth", "glyph.uri", "halign", "label", "letterspacing", "lineheight", "rend", "rotation", "type", "valign", "xml:id", "xml:lang", "xml:space")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_rend)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Rend {
  def apply(`altrend`: java.lang.String = null, `color`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `halign`: java.lang.String = null, `label`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `rend`: java.lang.String = null, `rotation`: java.lang.String = null, `type`: java.lang.String = null, `valign`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null, `xml:space`: java.lang.String = null)(elements: Element*): Rend = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`altrend` != null) Some("altrend" -> `altrend`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`halign` != null) Some("halign" -> `halign`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`rend` != null) Some("rend" -> `rend`) else None,
      if (`rotation` != null) Some("rotation" -> `rotation`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`valign` != null) Some("valign" -> `valign`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None,
      if (`xml:space` != null) Some("xml:space" -> `xml:space`) else None
    ).flatten)
    new Rend(elements.toList, attributes)
  }
}
