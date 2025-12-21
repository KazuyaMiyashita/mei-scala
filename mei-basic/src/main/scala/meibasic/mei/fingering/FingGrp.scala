package meibasic.mei.fingering

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A group of individual fingers in a fingering indication.
 * 
 * Ident: fingGrp
 * Module: MEI.fingering
 */
case class FingGrp(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.finggrp.Ges with meibasic.mei.fingering.att.finggrp.Log with meibasic.mei.fingering.model.FingeringLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("dur", "endid", "form", "label", "layer", "plist", "staff", "startid", "tstamp", "tstamp2", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_fingGrp)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object FingGrp {
  def apply(`dur`: java.lang.String = null, `endid`: java.lang.String = null, `form`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): FingGrp = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new FingGrp(elements.toList, attributes)
  }
}
