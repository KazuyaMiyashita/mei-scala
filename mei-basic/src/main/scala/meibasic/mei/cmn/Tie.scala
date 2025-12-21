package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An indication that two notes of the same pitch form a single note with their combined rhythmic values.
 * 
 * Ident: tie
 * Module: MEI.cmn
 */
case class Tie(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.tie.Log with meibasic.mei.visual.att.tie.Vis with meibasic.mei.gestural.att.tie.Ges with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "curvedir", "endho", "endid", "endvo", "ho", "label", "layer", "lform", "lsegs", "lwidth", "plist", "staff", "startho", "startid", "startvo", "tstamp", "tstamp2", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_tie)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Tie {
  def apply(`color`: java.lang.String = null, `curvedir`: java.lang.String = null, `endho`: java.lang.String = null, `endid`: java.lang.String = null, `endvo`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lwidth`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startho`: java.lang.String = null, `startid`: java.lang.String = null, `startvo`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Tie = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`curvedir` != null) Some("curvedir" -> `curvedir`) else None,
      if (`endho` != null) Some("endho" -> `endho`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`endvo` != null) Some("endvo" -> `endvo`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startho` != null) Some("startho" -> `startho`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`startvo` != null) Some("startvo" -> `startvo`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Tie(elements.toList, attributes)
  }
}
