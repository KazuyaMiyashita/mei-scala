package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A group of equidistant horizontal lines on which notes are placed in order to represent pitch or a grouping element for individual 'strands' of notes, rests, etc. that may or may not actually be rendered on staff lines; that is, both diastematic and non-diastematic signs.
 * 
 * Ident: staff
 * Module: MEI.shared
 */
case class Staff(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.NInteger with meibasic.mei.shared.att.Typed with meibasic.mei.visual.att.staff.Vis with meibasic.mei.shared.model.StaffLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "n", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_staff)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Staff {
  def apply(`label`: java.lang.String = null, `n`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Staff = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Staff(elements.toList, attributes)
  }
}
