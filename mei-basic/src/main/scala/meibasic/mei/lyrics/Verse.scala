package meibasic.mei.lyrics

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Division of a poem or song lyrics, sometimes having a fixed length, meter or rhyme scheme; a stanza.
 * 
 * Ident: verse
 * Module: MEI.lyrics
 */
case class Verse(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.visual.att.verse.Vis with meibasic.mei.lyrics.model.VerseLike with meibasic.mei.shared.att.NNumberLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "label", "letterspacing", "lineheight", "n", "place", "type", "vo", "voltasym", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_verse)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Verse {
  def apply(`color`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `label`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `n`: java.lang.String = null, `place`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `voltasym`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Verse = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`voltasym` != null) Some("voltasym" -> `voltasym`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Verse(elements.toList, attributes)
  }
}
