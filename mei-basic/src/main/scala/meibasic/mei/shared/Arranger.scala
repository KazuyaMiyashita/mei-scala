package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A person or organization who transcribes a musical composition, usually for a different medium from that of the original; in an arrangement the musical substance remains essentially unchanged.
 * 
 * Ident: arranger
 * Module: MEI.shared
 */
case class Arranger(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Lang with meibasic.mei.shared.model.RespLikePart {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_arranger)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Arranger {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Arranger = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Arranger(elements.toList, attributes)
  }
}
