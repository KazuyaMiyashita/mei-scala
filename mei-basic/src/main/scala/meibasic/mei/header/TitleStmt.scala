package meibasic.mei.header

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Container for title and responsibility meta-data.
 * 
 * Ident: titleStmt
 * Module: MEI.header
 */
case class TitleStmt(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_titleStmt)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object TitleStmt {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): TitleStmt = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new TitleStmt(elements.toList, attributes)
  }
}
