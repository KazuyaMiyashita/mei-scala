package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A running footer.
 * 
 * Ident: pgFoot
 * Module: MEI.shared
 */
case class PgFoot(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Formework with meibasic.mei.shared.att.HorizontalAlign with meibasic.mei.shared.att.Lang {
  override def validate(): Boolean = {
    val allowedAttrs = Set("func", "halign", "label", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_pgFoot)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object PgFoot {
  def apply(`func`: java.lang.String = null, `halign`: java.lang.String = null, `label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): PgFoot = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`func` != null) Some("func" -> `func`) else None,
      if (`halign` != null) Some("halign" -> `halign`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new PgFoot(elements.toList, attributes)
  }
}
