package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A single pitched event.
 * 
 * Ident: note
 * Module: MEI.shared
 */
case class Note(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.gestural.att.note.Ges with meibasic.mei.shared.att.note.Log with meibasic.mei.visual.att.note.Vis with meibasic.mei.shared.model.ChordPart with meibasic.mei.shared.model.EventLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("breaksec", "color", "cue", "dots", "dur", "enclose", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "grace", "grace.time", "ho", "instr", "label", "layer", "letterspacing", "lineheight", "oct", "oct.ges", "pname", "pname.ges", "staff", "stem.dir", "stem.len", "stem.mod", "tab.course", "tab.fing", "tab.fret", "tab.line", "tab.string", "type", "vel", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_note)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Note {
  def apply(`breaksec`: java.lang.String = null, `color`: java.lang.String = null, `cue`: java.lang.String = null, `dots`: java.lang.String = null, `dur`: java.lang.String = null, `enclose`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `grace`: java.lang.String = null, `grace.time`: java.lang.String = null, `ho`: java.lang.String = null, `instr`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `oct`: java.lang.String = null, `oct.ges`: java.lang.String = null, `pname`: java.lang.String = null, `pname.ges`: java.lang.String = null, `staff`: java.lang.String = null, `stem.dir`: java.lang.String = null, `stem.len`: java.lang.String = null, `stem.mod`: java.lang.String = null, `tab.course`: java.lang.String = null, `tab.fing`: java.lang.String = null, `tab.fret`: java.lang.String = null, `tab.line`: java.lang.String = null, `tab.string`: java.lang.String = null, `type`: java.lang.String = null, `vel`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Note = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`breaksec` != null) Some("breaksec" -> `breaksec`) else None,
      if (`color` != null) Some("color" -> `color`) else None,
      if (`cue` != null) Some("cue" -> `cue`) else None,
      if (`dots` != null) Some("dots" -> `dots`) else None,
      if (`dur` != null) Some("dur" -> `dur`) else None,
      if (`enclose` != null) Some("enclose" -> `enclose`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`grace` != null) Some("grace" -> `grace`) else None,
      if (`grace.time` != null) Some("grace.time" -> `grace.time`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`instr` != null) Some("instr" -> `instr`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`oct` != null) Some("oct" -> `oct`) else None,
      if (`oct.ges` != null) Some("oct.ges" -> `oct.ges`) else None,
      if (`pname` != null) Some("pname" -> `pname`) else None,
      if (`pname.ges` != null) Some("pname.ges" -> `pname.ges`) else None,
      if (`staff` != null) Some("staff" -> `staff`) else None,
      if (`stem.dir` != null) Some("stem.dir" -> `stem.dir`) else None,
      if (`stem.len` != null) Some("stem.len" -> `stem.len`) else None,
      if (`stem.mod` != null) Some("stem.mod" -> `stem.mod`) else None,
      if (`tab.course` != null) Some("tab.course" -> `tab.course`) else None,
      if (`tab.fing` != null) Some("tab.fing" -> `tab.fing`) else None,
      if (`tab.fret` != null) Some("tab.fret" -> `tab.fret`) else None,
      if (`tab.line` != null) Some("tab.line" -> `tab.line`) else None,
      if (`tab.string` != null) Some("tab.string" -> `tab.string`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vel` != null) Some("vel" -> `vel`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Note(elements.toList, attributes)
  }
}
