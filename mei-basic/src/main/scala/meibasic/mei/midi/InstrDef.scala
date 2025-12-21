package meibasic.mei.midi

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * MIDI instrument declaration.
 * 
 * Ident: instrDef
 * Module: MEI.midi
 */
case class InstrDef(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.NInteger with meibasic.mei.shared.att.Typed with meibasic.mei.gestural.att.instrdef.Ges with meibasic.mei.midi.att.instrdef.Log with meibasic.mei.shared.model.InstrDefLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("azimuth", "elevation", "label", "midi.channel", "midi.duty", "midi.instrname", "midi.instrnum", "midi.pan", "midi.patchname", "midi.patchnum", "midi.port", "midi.track", "midi.volume", "n", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_instrDef)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object InstrDef {
  def apply(`azimuth`: java.lang.String = null, `elevation`: java.lang.String = null, `label`: java.lang.String = null, `midi.channel`: java.lang.String = null, `midi.duty`: java.lang.String = null, `midi.instrname`: java.lang.String = null, `midi.instrnum`: java.lang.String = null, `midi.pan`: java.lang.String = null, `midi.patchname`: java.lang.String = null, `midi.patchnum`: java.lang.String = null, `midi.port`: java.lang.String = null, `midi.track`: java.lang.String = null, `midi.volume`: java.lang.String = null, `n`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): InstrDef = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`azimuth` != null) Some("azimuth" -> `azimuth`) else None,
      if (`elevation` != null) Some("elevation" -> `elevation`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`midi.channel` != null) Some("midi.channel" -> `midi.channel`) else None,
      if (`midi.duty` != null) Some("midi.duty" -> `midi.duty`) else None,
      if (`midi.instrname` != null) Some("midi.instrname" -> `midi.instrname`) else None,
      if (`midi.instrnum` != null) Some("midi.instrnum" -> `midi.instrnum`) else None,
      if (`midi.pan` != null) Some("midi.pan" -> `midi.pan`) else None,
      if (`midi.patchname` != null) Some("midi.patchname" -> `midi.patchname`) else None,
      if (`midi.patchnum` != null) Some("midi.patchnum" -> `midi.patchnum`) else None,
      if (`midi.port` != null) Some("midi.port" -> `midi.port`) else None,
      if (`midi.track` != null) Some("midi.track" -> `midi.track`) else None,
      if (`midi.volume` != null) Some("midi.volume" -> `midi.volume`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new InstrDef(elements.toList, attributes)
  }
}
