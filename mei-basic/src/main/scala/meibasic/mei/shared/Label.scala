package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A container for document text that identifies the feature to which it is attached. For a "tool tip" or other generated label, use thelabelattribute.
 * 
 * Ident: label
 * Module: MEI.shared
 */
case class Label(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Lang with meibasic.mei.shared.model.LabelLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_label)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Label {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Label = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Label(elements.toList, attributes)
  }
}
