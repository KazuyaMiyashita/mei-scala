package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Individual lyric syllable.
 * 
 * Ident: syl
 * Module: MEI.shared
 */
case class Syl(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Lang with meibasic.mei.shared.att.syl.Log with meibasic.mei.visual.att.syl.Vis with meibasic.mei.shared.model.SylLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("con", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "halign", "ho", "label", "letterspacing", "lineheight", "place", "type", "vo", "wordpos", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_syl)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Syl {
  def apply(`con`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `halign`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `place`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `wordpos`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Syl = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`con` != null) Some("con" -> `con`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`halign` != null) Some("halign" -> `halign`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`wordpos` != null) Some("wordpos" -> `wordpos`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Syl(elements.toList, attributes)
  }
}
