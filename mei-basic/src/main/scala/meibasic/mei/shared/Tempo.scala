package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Text and symbols descriptive of tempo, mood, or style,e.g., "allarg.", "a tempo", "cantabile", "Moderato", "♩=60", "Moderato ♩ =60").
 * 
 * Ident: tempo
 * Module: MEI.shared
 */
case class Tempo(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Lang with meibasic.mei.gestural.att.tempo.Ges with meibasic.mei.shared.att.tempo.Log with meibasic.mei.visual.att.tempo.Vis with meibasic.mei.shared.model.ControlEventLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "endho", "endid", "extender", "func", "ho", "label", "layer", "lendsym", "lendsym.size", "lform", "lsegs", "lstartsym", "lstartsym.size", "lwidth", "midi.bpm", "midi.mspb", "mm", "mm.dots", "mm.unit", "place", "plist", "staff", "startho", "startid", "tstamp", "tstamp2", "type", "vo", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_tempo)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Tempo {
  def apply(`color`: java.lang.String = null, `endho`: java.lang.String = null, `endid`: java.lang.String = null, `extender`: java.lang.String = null, `func`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `lendsym`: java.lang.String = null, `lendsym.size`: java.lang.String = null, `lform`: java.lang.String = null, `lsegs`: java.lang.String = null, `lstartsym`: java.lang.String = null, `lstartsym.size`: java.lang.String = null, `lwidth`: java.lang.String = null, `midi.bpm`: java.lang.String = null, `midi.mspb`: java.lang.String = null, `mm`: java.lang.String = null, `mm.dots`: java.lang.String = null, `mm.unit`: java.lang.String = null, `place`: java.lang.String = null, `plist`: java.lang.String = null, `staff`: java.lang.String = null, `startho`: java.lang.String = null, `startid`: java.lang.String = null, `tstamp`: java.lang.String = null, `tstamp2`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Tempo = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`endho` != null) Some("endho" -> `endho`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`extender` != null) Some("extender" -> `extender`) else None,
      if (`func` != null) Some("func" -> `func`) else None,
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
      if (`midi.bpm` != null) Some("midi.bpm" -> `midi.bpm`) else None,
      if (`midi.mspb` != null) Some("midi.mspb" -> `midi.mspb`) else None,
      if (`mm` != null) Some("mm" -> `mm`) else None,
      if (`mm.dots` != null) Some("mm.dots" -> `mm.dots`) else None,
      if (`mm.unit` != null) Some("mm.unit" -> `mm.unit`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`plist` != null) Some("plist" -> `plist`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startho` != null) Some("startho" -> `startho`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`tstamp` != null) Some("tstamp" -> `tstamp`) else None,
      if (`tstamp2` != null) Some("tstamp2" -> `tstamp2`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Tempo(elements.toList, attributes)
  }
}
