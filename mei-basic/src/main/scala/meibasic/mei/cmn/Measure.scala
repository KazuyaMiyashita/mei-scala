package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Unit of musical time consisting of a fixed number of note values of a given type, as determined by the prevailing meter, and delimited in musical notation by bar lines.
 * 
 * Ident: measure
 * Module: MEI.cmn
 */
case class Measure(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.measure.Ges with meibasic.mei.cmn.att.measure.Log with meibasic.mei.cmn.model.MeasureLike with meibasic.mei.shared.att.NNumberLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "left", "metcon", "n", "right", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_measure)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Measure {
  def apply(`label`: java.lang.String = null, `left`: java.lang.String = null, `metcon`: java.lang.String = null, `n`: java.lang.String = null, `right`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Measure = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`left` != null) Some("left" -> `left`) else None,
      if (`metcon` != null) Some("metcon" -> `metcon`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`right` != null) Some("right" -> `right`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Measure(elements.toList, attributes)
  }
}
