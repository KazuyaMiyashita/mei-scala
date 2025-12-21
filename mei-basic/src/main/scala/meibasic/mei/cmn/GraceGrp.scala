package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A container for a sequence of grace notes.
 * 
 * Ident: graceGrp
 * Module: MEI.cmn
 */
case class GraceGrp(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.gracegrp.Log with meibasic.mei.visual.att.gracegrp.Vis with meibasic.mei.cmn.model.eventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("attach", "color", "grace", "grace.time", "label", "layer", "staff", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_graceGrp)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object GraceGrp {
  def apply(`attach`: java.lang.String = null, `color`: java.lang.String = null, `grace`: java.lang.String = null, `grace.time`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): GraceGrp = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`attach` != null) Some("attach" -> `attach`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`grace` != null) Some("grace" -> `grace`) else None,
      if (`grace.time` != null) Some("grace.time" -> `grace.time`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new GraceGrp(elements.toList, attributes)
  }
}
