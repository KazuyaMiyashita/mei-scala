package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Contains the whole of a single musical text, excluding any front or back matter.
 * 
 * Ident: body
 * Module: MEI.shared
 */
case class Body(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_body)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Body {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Body = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Body(elements.toList, attributes)
  }
}
