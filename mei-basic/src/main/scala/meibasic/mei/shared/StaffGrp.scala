package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * (staff group) – A group of bracketed or braced staves.
 * 
 * Ident: staffGrp
 * Module: MEI.shared
 */
case class StaffGrp(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.visual.att.staffgrp.Vis with meibasic.mei.gestural.att.staffgrp.Ges with meibasic.mei.shared.model.StaffGrpLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("bar.thru", "instr", "label", "symbol", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_staffGrp)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object StaffGrp {
  def apply(`bar.thru`: java.lang.String = null, `instr`: java.lang.String = null, `label`: java.lang.String = null, `symbol`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): StaffGrp = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`bar.thru` != null) Some("bar.thru" -> `bar.thru`) else None,
      if (`instr` != null) Some("instr" -> `instr`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`symbol` != null) Some("symbol" -> `symbol`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new StaffGrp(elements.toList, attributes)
  }
}
