package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * (score definition) – Container for score meta-information.
 * 
 * Ident: scoreDef
 * Module: MEI.shared
 */
case class ScoreDef(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.model.ScoreDefLike with meibasic.mei.shared.att.keysigdefault.Log with meibasic.mei.shared.att.metersigdefault.Log {
  override def validate(): Boolean = {
    val allowedAttrs = Set("keysig", "label", "meter.count", "meter.sym", "meter.unit", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_scoreDef)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object ScoreDef {
  def apply(`keysig`: java.lang.String = null, `label`: java.lang.String = null, `meter.count`: java.lang.String = null, `meter.sym`: java.lang.String = null, `meter.unit`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): ScoreDef = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`keysig` != null) Some("keysig" -> `keysig`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`meter.count` != null) Some("meter.count" -> `meter.count`) else None,
      if (`meter.sym` != null) Some("meter.sym" -> `meter.sym`) else None,
      if (`meter.unit` != null) Some("meter.unit" -> `meter.unit`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new ScoreDef(elements.toList, attributes)
  }
}
