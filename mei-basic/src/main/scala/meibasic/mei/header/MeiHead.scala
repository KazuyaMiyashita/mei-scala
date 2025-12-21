package meibasic.mei.header

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * (MEI header) – Supplies the descriptive and declarative metadata prefixed to every MEI-conformant text.
 * 
 * Ident: meiHead
 * Module: MEI.header
 */
case class MeiHead(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.Lang {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_meiHead)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object MeiHead {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): MeiHead = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new MeiHead(elements.toList, attributes)
  }
}
