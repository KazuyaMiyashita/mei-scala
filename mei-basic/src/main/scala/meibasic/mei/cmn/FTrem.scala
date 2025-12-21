package meibasic.mei.cmn

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A rapid alternation between a pair of notes (or chords or perhaps between a note and a chord) that are (usually) farther apart than a major second.
 * 
 * Ident: fTrem
 * Module: MEI.cmn
 */
case class FTrem(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.cmn.att.ftrem.Log with meibasic.mei.gestural.att.ftrem.Ges with meibasic.mei.cmn.model.eventlike.Cmn {
  override def validate(): Boolean = {
    val allowedAttrs = Set("dots", "dur", "form", "label", "layer", "staff", "type", "unitdur", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_fTrem)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object FTrem {
  def apply(`dots`: java.lang.String = null, `dur`: java.lang.String = null, `form`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `unitdur`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): FTrem = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`dots` != null) Some("dots" -> `dots`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`form` != null) Some("form" -> `form`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`unitdur` != null) Some("unitdur" -> `unitdur`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new FTrem(elements.toList, attributes)
  }
}
