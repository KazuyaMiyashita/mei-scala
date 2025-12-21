package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An indication that a passage should be performed one or more octaves above or below its written pitch.
 * 
 * Ident: octave
 * Module: MEI.cmn
 */
case class Octave(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.octave.Log with meibasic.mei.visual.att.octave.Vis with meibasic.mei.gestural.att.octave.Ges with meibasic.mei.cmn.model.controleventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("coll", "color", "dis", "dis.place", "dur", "endho", "endid", "extender", "ho", "label", "layer", "lendsym", "lendsym.size", "lform", "lsegs", "lstartsym", "lstartsym.size", "lwidth", "plist", "staff", "startho", "startid", "tstamp", "tstamp2", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_octave)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Octave {
  def apply(`coll`: java.lang.String = null, `color`: java.lang.String = null, `dis`: java.lang.String = null, `dis.place`: java.lang.String = null, `dur`: java.lang.String = null, `endho`: java.lang.String = null, `endid`: java.lang.String = null, `extender`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lendsym`: java.lang.String = null, `lendsym.size`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lstartsym`: java.lang.String = null, `lstartsym.size`: java.lang.String = null, `lwidth`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startho`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Octave = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`coll` != null) Some("coll" -> `coll`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dis` != null) Some("dis" -> `dis`) else None,
      if (`dis.place` != null) Some("dis.place" -> `dis.place`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`endho` != null) Some("endho" -> `endho`) else None,
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
    new Octave(elements.toList, attributes)
  }
}
