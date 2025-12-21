package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Segment of music data.
 * 
 * Ident: section
 * Module: MEI.shared
 */
case class Section(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.section.Ges with meibasic.mei.shared.att.section.Log with meibasic.mei.visual.att.section.Vis with meibasic.mei.shared.model.SectionLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("attacca", "label", "restart", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_section)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Section {
  def apply(`attacca`: java.lang.String = null, `label`: java.lang.String = null, `restart`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Section = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`attacca` != null) Some("attacca" -> `attacca`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`restart` != null) Some("restart" -> `restart`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Section(elements.toList, attributes)
  }
}
