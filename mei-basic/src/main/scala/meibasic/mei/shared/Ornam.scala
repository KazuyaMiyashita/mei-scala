package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An element indicating an ornament that is not a mordent, turn, or trill.
 * 
 * Ident: ornam
 * Module: MEI.shared
 */
case class Ornam(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.ornam.Ges with meibasic.mei.shared.att.ornam.Log with meibasic.mei.visual.att.ornam.Vis with meibasic.mei.shared.model.ControlEventLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("accidlower", "accidlower.ges", "accidupper", "accidupper.ges", "color", "dur", "enclose", "endho", "endid", "ho", "label", "layer", "place", "plist", "staff", "startho", "startid", "tstamp", "tstamp2", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_ornam)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Ornam {
  def apply(`accidlower`: java.lang.String = null, `accidlower.ges`: java.lang.String = null, `accidupper`: java.lang.String = null, `accidupper.ges`: java.lang.String = null, `color`: java.lang.String = null, `dur`: java.lang.String = null, `enclose`: java.lang.String = null, `endho`: java.lang.String = null, `endid`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startho`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Ornam = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`accidlower` != null) Some("accidlower" -> `accidlower`) else None,
      if (`accidlower.ges` != null) Some("accidlower.ges" -> `accidlower.ges`) else None,
      if (`accidupper` != null) Some("accidupper" -> `accidupper`) else None,
      if (`accidupper.ges` != null) Some("accidupper.ges" -> `accidupper.ges`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`enclose` != null) Some("enclose" -> `enclose`) else None,
      if (`endho` != null) Some("endho" -> `endho`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startho` != null) Some("startho" -> `startho`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Ornam(elements.toList, attributes)
  }
}
