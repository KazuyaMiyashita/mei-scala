package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Container for staff meta-information.
 * 
 * Ident: staffDef
 * Module: MEI.shared
 */
case class StaffDef(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.NInteger with meibasic.mei.shared.att.Typed with meibasic.mei.gestural.att.staffdef.Ges with meibasic.mei.shared.att.staffdef.Log with meibasic.mei.visual.att.staffdef.Vis with meibasic.mei.shared.model.StaffDefLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("clef.dis", "clef.dis.place", "clef.line", "clef.shape", "instr", "keysig", "label", "lines", "lines.color", "lines.visible", "meter.count", "meter.sym", "meter.unit", "n", "ppq", "scale", "tab.courses", "tab.strings", "trans.diat", "trans.semi", "tune.Hz", "tune.pname", "tune.temper", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_staffDef)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object StaffDef {
  def apply(`clef.dis`: java.lang.String = null, `clef.dis.place`: java.lang.String = null, `clef.line`: java.lang.String = null, `clef.shape`: java.lang.String = null, `instr`: java.lang.String = null, `keysig`: java.lang.String = null, `label`: java.lang.String = null, `lines`: java.lang.String = null, `lines.color`: java.lang.String = null, `lines.visible`: java.lang.String = null, `meter.count`: java.lang.String = null, `meter.sym`: java.lang.String = null, `meter.unit`: java.lang.String = null, `n`: java.lang.String = null, `ppq`: java.lang.String = null, `scale`: java.lang.String = null, `tab.courses`: java.lang.String = null, `tab.strings`: java.lang.String = null, `trans.diat`: java.lang.String = null, `trans.semi`: java.lang.String = null, `tune.Hz`: java.lang.String = null, `tune.pname`: java.lang.String = null, `tune.temper`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): StaffDef = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`clef.dis` != null) Some("clef.dis" -> `clef.dis`) else None,
      if (`clef.dis.place` != null) Some("clef.dis.place" -> `clef.dis.place`) else None,
      if (`clef.line` != null) Some("clef.line" -> `clef.line`) else None,
      if (`clef.shape` != null) Some("clef.shape" -> `clef.shape`) else None,
      if (`instr` != null) Some("instr" -> `instr`) else None,
      if (`keysig` != null) Some("keysig" -> `keysig`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`lines` != null) Some("lines" -> `lines`) else None,
      if (`lines.color` != null) Some("lines.color" -> `lines.color`) else None,
      if (`lines.visible` != null) Some("lines.visible" -> `lines.visible`) else None,
      if (`meter.count` != null) Some("meter.count" -> `meter.count`) else None,
      if (`meter.sym` != null) Some("meter.sym" -> `meter.sym`) else None,
      if (`meter.unit` != null) Some("meter.unit" -> `meter.unit`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`ppq` != null) Some("ppq" -> `ppq`) else None,
      if (`scale` != null) Some("scale" -> `scale`) else None,
      if (`tab.courses` != null) Some("tab.courses" -> `tab.courses`) else None,
      if (`tab.strings` != null) Some("tab.strings" -> `tab.strings`) else None,
      if (`trans.diat` != null) Some("trans.diat" -> `trans.diat`) else None,
      if (`trans.semi` != null) Some("trans.semi" -> `trans.semi`) else None,
      if (`tune.Hz` != null) Some("tune.Hz" -> `tune.Hz`) else None,
      if (`tune.pname` != null) Some("tune.pname" -> `tune.pname`) else None,
      if (`tune.temper` != null) Some("tune.temper" -> `tune.temper`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new StaffDef(elements.toList, attributes)
  }
}
