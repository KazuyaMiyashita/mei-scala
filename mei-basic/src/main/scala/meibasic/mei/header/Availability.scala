package meibasic.mei.header

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Groups elements that describe the availability of and access to a bibliographic item, including an MEI-encoded document.
 * 
 * Ident: availability
 * Module: MEI.header
 */
case class Availability(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.DataPointing {
  override def validate(): Boolean = {
    val allowedAttrs = Set("data", "label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_availability)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Availability {
  def apply(`data`: java.lang.String = null, `label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Availability = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`data` != null) Some("data" -> `data`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Availability(elements.toList, attributes)
  }
}
