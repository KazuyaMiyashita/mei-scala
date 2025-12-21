package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Indicates continuous dynamics expressed on the score as wedge-shaped graphics,e.g., < and >.
 * 
 * Ident: hairpin
 * Module: MEI.cmn
 */
case class Hairpin(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.hairpin.Log with meibasic.mei.visual.att.hairpin.Vis with meibasic.mei.gestural.att.hairpin.Ges with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("angle.optimize", "closed", "color", "dur", "endho", "endid", "endvo", "form", "ho", "label", "layer", "lform", "lsegs", "lwidth", "niente", "opening", "opening.vertical", "place", "plist", "staff", "startho", "startid", "startvo", "tstamp", "tstamp2", "type", "val", "val2", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_hairpin)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Hairpin {
  def apply(`angle.optimize`: java.lang.String = null, `closed`: java.lang.String = null, `color`: java.lang.String = null, `dur`: java.lang.String = null, `endho`: java.lang.String = null, `endid`: java.lang.String = null, `endvo`: java.lang.String = null, `form`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lwidth`: java.lang.String = null, `niente`: java.lang.String = null, `opening`: java.lang.String = null, `opening.vertical`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startho`: java.lang.String = null, `startid`: java.lang.String = null, `startvo`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `val`: java.lang.String = null, `val2`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Hairpin = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`angle.optimize` != null) Some("angle.optimize" -> `angle.optimize`) else None,
      if (`closed` != null) Some("closed" -> `closed`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`endho` != null) Some("endho" -> `endho`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`endvo` != null) Some("endvo" -> `endvo`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`niente` != null) Some("niente" -> `niente`) else None,
      if (`opening` != null) Some("opening" -> `opening`) else None,
      if (`opening.vertical` != null) Some("opening.vertical" -> `opening.vertical`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startho` != null) Some("startho" -> `startho`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`startvo` != null) Some("startvo" -> `startvo`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`val` != null) Some("val" -> `val`) else None,
      if (`val2` != null) Some("val2" -> `val2`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Hairpin(elements.toList, attributes)
  }
}
