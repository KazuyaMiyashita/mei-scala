package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An independent stream of events on a staff.
 * 
 * Ident: layer
 * Module: MEI.shared
 */
case class Layer(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.NInteger with meibasic.mei.shared.att.Typed with meibasic.mei.visual.att.layer.Vis with meibasic.mei.shared.model.LayerLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "n", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_layer)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Layer {
  def apply(`label`: java.lang.String = null, `n`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Layer = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Layer(elements.toList, attributes)
  }
}
