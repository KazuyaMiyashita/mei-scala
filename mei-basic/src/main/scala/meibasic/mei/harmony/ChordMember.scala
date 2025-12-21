package meibasic.mei.harmony

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An individual pitch in a chord defined by achordDefelement.
 * 
 * Ident: chordMember
 * Module: MEI.harmony
 */
case class ChordMember(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.chordmember.Ges with meibasic.mei.harmony.att.chordmember.Log {
  override def validate(): Boolean = {
    val allowedAttrs = Set("accid.ges", "label", "oct", "pname", "tab.course", "tab.fing", "tab.fret", "tab.line", "tab.string", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_chordMember)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object ChordMember {
  def apply(`accid.ges`: java.lang.String = null, `label`: java.lang.String = null, `oct`: java.lang.String = null, `pname`: java.lang.String = null, `tab.course`: java.lang.String = null, `tab.fing`: java.lang.String = null, `tab.fret`: java.lang.String = null, `tab.line`: java.lang.String = null, `tab.string`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): ChordMember = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`accid.ges` != null) Some("accid.ges" -> `accid.ges`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`oct` != null) Some("oct" -> `oct`) else None,
      if (`pname` != null) Some("pname" -> `pname`) else None,
      if (`tab.course` != null) Some("tab.course" -> `tab.course`) else None,
      if (`tab.fing` != null) Some("tab.fing" -> `tab.fing`) else None,
      if (`tab.fret` != null) Some("tab.fret" -> `tab.fret`) else None,
      if (`tab.line` != null) Some("tab.line" -> `tab.line`) else None,
      if (`tab.string` != null) Some("tab.string" -> `tab.string`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new ChordMember(elements.toList, attributes)
  }
}
