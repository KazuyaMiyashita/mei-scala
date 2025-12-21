package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A non-sounding event found in the source being transcribed.
 * 
 * Ident: rest
 * Module: MEI.shared
 */
case class Rest(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.rest.Ges with meibasic.mei.shared.att.rest.Log with meibasic.mei.visual.att.rest.Vis with meibasic.mei.shared.model.EventLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("breaksec", "color", "cue", "dots", "dur", "enclose", "ho", "instr", "label", "layer", "oloc", "ploc", "staff", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_rest)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Rest {
  def apply(`breaksec`: java.lang.String = null, `color`: java.lang.String = null, `cue`: java.lang.String = null, `dots`: java.lang.String = null, `dur`: java.lang.String = null, `enclose`: java.lang.String = null, `ho`: java.lang.String = null, `instr`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `oloc`: java.lang.String = null, `ploc`: java.lang.String = null, `staff`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Rest = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`breaksec` != null) Some("breaksec" -> `breaksec`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`cue` != null) Some("cue" -> `cue`) else None,
      if (`dots` != null) Some("dots" -> `dots`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`enclose` != null) Some("enclose" -> `enclose`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`instr` != null) Some("instr" -> `instr`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`oloc` != null) Some("oloc" -> `oloc`) else None,
      if (`ploc` != null) Some("ploc" -> `ploc`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Rest(elements.toList, attributes)
  }
}
