package meibasic.mei.fingering

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An individual finger in a fingering indication.
 * 
 * Ident: fing
 * Module: MEI.fingering
 */
case class Fing(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.fing.Ges with meibasic.mei.fingering.att.fing.Log with meibasic.mei.visual.att.fing.Vis with meibasic.mei.fingering.model.FingeringLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "dur", "endid", "extender", "ho", "label", "layer", "lendsym", "lendsym.size", "lform", "lsegs", "lstartsym", "lstartsym.size", "lwidth", "place", "plist", "staff", "startid", "tstamp", "tstamp2", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_fing)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Fing {
  def apply(`color`: java.lang.String = null, `dur`: java.lang.String = null, `endid`: java.lang.String = null, `extender`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lendsym`: java.lang.String = null, `lendsym.size`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lstartsym`: java.lang.String = null, `lstartsym.size`: java.lang.String = null, `lwidth`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Fing = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`extender` != null) Some("extender" -> `extender`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`lendsym` != null) Some("lendsym" -> `lendsym`) else None,
      if (`lendsym.size` != null) Some("lendsym.size" -> `lendsym.size`) else None,
      if (`lform` != null) Some("lform" -> `lform`) else None,
      if (`lsegs` != null) Some("lsegs" -> `lsegs`) else None,
      if (`lstartsym` != null) Some("lstartsym" -> `lstartsym`) else None,
      if (`lstartsym.size` != null) Some("lstartsym.size" -> `lstartsym.size`) else None,
      if (`lwidth` != null) Some("lwidth" -> `lwidth`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Fing(elements.toList, attributes)
  }
}
