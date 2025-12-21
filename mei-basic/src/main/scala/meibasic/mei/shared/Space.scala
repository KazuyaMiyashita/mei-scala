package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A placeholder used to fill an incomplete measure, layer, etc. most often so that the combined duration of the events equals the number of beats in the measure.
 * 
 * Ident: space
 * Module: MEI.shared
 */
case class Space(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.space.Log with meibasic.mei.visual.att.space.Vis with meibasic.mei.gestural.att.space.Ges with meibasic.mei.shared.model.EventLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("compressable", "cutout", "dots", "dur", "label", "layer", "staff", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_space)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Space {
  def apply(`compressable`: java.lang.String = null, `cutout`: java.lang.String = null, `dots`: java.lang.String = null, `dur`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Space = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`compressable` != null) Some("compressable" -> `compressable`) else None,
      if (`cutout` != null) Some("cutout" -> `cutout`) else None,
      if (`dots` != null) Some("dots" -> `dots`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Space(elements.toList, attributes)
  }
}
