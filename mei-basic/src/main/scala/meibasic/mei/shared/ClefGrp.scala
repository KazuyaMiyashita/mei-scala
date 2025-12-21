package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A set of simultaneously-occurring clefs.
 * 
 * Ident: clefGrp
 * Module: MEI.shared
 */
case class ClefGrp(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Event with meibasic.mei.shared.model.EventLike with meibasic.mei.shared.model.StaffDefPart {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "layer", "staff", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_clefGrp)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object ClefGrp {
  def apply(`label`: java.lang.String = null, `layer`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): ClefGrp = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new ClefGrp(elements.toList, attributes)
  }
}
