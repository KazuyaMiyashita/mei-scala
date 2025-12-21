package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A group of notes with "irregular" (sometimes called "irrational") rhythmic values, for example, three notes in the time normally occupied by two or nine in the time of five.
 * 
 * Ident: tuplet
 * Module: MEI.cmn
 */
case class Tuplet(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.tuplet.Log with meibasic.mei.visual.att.tuplet.Vis with meibasic.mei.gestural.att.tuplet.Ges with meibasic.mei.cmn.model.eventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("bracket.place", "bracket.visible", "color", "dur", "dur.visible", "endid", "label", "layer", "num", "num.format", "num.place", "num.visible", "numbase", "staff", "startid", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_tuplet)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Tuplet {
  def apply(`bracket.place`: java.lang.String = null, `bracket.visible`: java.lang.String = null, `color`: java.lang.String = null, `dur`: java.lang.String = null, `dur.visible`: java.lang.String = null, `endid`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `num`: java.lang.String = null, `num.format`: java.lang.String = null, `num.place`: java.lang.String = null, `num.visible`: java.lang.String = null, `numbase`: java.lang.String = null, `staff`: java.lang.String = null, `startid`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Tuplet = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`bracket.place` != null) Some("bracket.place" -> `bracket.place`) else None,
      if (`bracket.visible` != null) Some("bracket.visible" -> `bracket.visible`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`dur.visible` != null) Some("dur.visible" -> `dur.visible`) else None,
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`num` != null) Some("num" -> `num`) else None,
      if (`num.format` != null) Some("num.format" -> `num.format`) else None,
      if (`num.place` != null) Some("num.place" -> `num.place`) else None,
      if (`num.visible` != null) Some("num.visible" -> `num.visible`) else None,
      if (`numbase` != null) Some("numbase" -> `numbase`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Tuplet(elements.toList, attributes)
  }
}
