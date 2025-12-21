package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A container for a series of explicitly beamed events that begins and ends entirely within a measure.
 * 
 * Ident: beam
 * Module: MEI.cmn
 */
case class Beam(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.beam.Log with meibasic.mei.visual.att.beam.Vis with meibasic.mei.cmn.model.eventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "cue", "label", "layer", "staff", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_beam)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Beam {
  def apply(`color`: java.lang.String = null, `cue`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Beam = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`cue` != null) Some("cue" -> `cue`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Beam(elements.toList, attributes)
  }
}
