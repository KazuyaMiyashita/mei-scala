package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Alternative ending for a repeated passage of music;i.e., prima volta, seconda volta, etc.
 * 
 * Ident: ending
 * Module: MEI.shared
 */
case class Ending(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.ending.Log with meibasic.mei.visual.att.ending.Vis with meibasic.mei.shared.model.EndingLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "lendsym", "lendsym.size", "lform", "lsegs", "lstartsym", "lstartsym.size", "lwidth", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_ending)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Ending {
  def apply(`label`: java.lang.String = null, `lendsym`: java.lang.String = null, `lendsym.size`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lstartsym`: java.lang.String = null, `lstartsym.size`: java.lang.String = null, `lwidth`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Ending = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`lendsym` != null) Some("lendsym" -> `lendsym`) else None,
      if (`lendsym.size` != null) Some("lendsym.size" -> `lendsym.size`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lstartsym` != null) Some("lstartsym" -> `lstartsym`) else None,
      if (`lstartsym.size` != null) Some("lstartsym.size" -> `lstartsym.size`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Ending(elements.toList, attributes)
  }
}
