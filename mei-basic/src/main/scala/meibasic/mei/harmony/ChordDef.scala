package meibasic.mei.harmony

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Chord tablature definition.
 * 
 * Ident: chordDef
 * Module: MEI.harmony
 */
case class ChordDef(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.harmony.att.chorddef.Log {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "tab.courses", "tab.pos", "tab.strings", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_chordDef)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object ChordDef {
  def apply(`label`: java.lang.String = null, `tab.courses`: java.lang.String = null, `tab.pos`: java.lang.String = null, `tab.strings`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): ChordDef = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`tab.courses` != null) Some("tab.courses" -> `tab.courses`) else None,
      if (`tab.pos` != null) Some("tab.pos" -> `tab.pos`) else None,
      if (`tab.strings` != null) Some("tab.strings" -> `tab.strings`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new ChordDef(elements.toList, attributes)
  }
}
