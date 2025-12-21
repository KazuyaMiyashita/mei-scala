package meibasic.mei.stringtab

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A visual indication of the duration of atabGrp.
 * 
 * Ident: tabDurSym
 * Module: MEI.stringtab
 */
case class TabDurSym(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.stringtab.att.Stringtab with meibasic.mei.stringtab.att.tabdursym.Log with meibasic.mei.visual.att.tabdursym.Vis with meibasic.mei.gestural.att.tabdursym.Ges with meibasic.mei.analytical.att.tabdursym.Anl {
  override def validate(): Boolean = {
    val allowedAttrs = Set("color", "fontfam", "fontname", "fontsize", "fontstyle", "fontweight", "glyph.auth", "glyph.name", "glyph.num", "glyph.uri", "ho", "label", "layer", "letterspacing", "lineheight", "place", "tab.course", "tab.fing", "tab.fret", "tab.line", "tab.string", "type", "vo", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_tabDurSym)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object TabDurSym {
  def apply(`color`: java.lang.String = null, `fontfam`: java.lang.String = null, `fontname`: java.lang.String = null, `fontsize`: java.lang.String = null, `fontstyle`: java.lang.String = null, `fontweight`: java.lang.String = null, `glyph.auth`: java.lang.String = null, `glyph.name`: java.lang.String = null, `glyph.num`: java.lang.String = null, `glyph.uri`: java.lang.String = null, `ho`: java.lang.String = null, `label`: java.lang.String = null, `layer`: java.lang.String = null, `letterspacing`: java.lang.String = null, `lineheight`: java.lang.String = null, `place`: java.lang.String = null, `tab.course`: java.lang.String = null, `tab.fing`: java.lang.String = null, `tab.fret`: java.lang.String = null, `tab.line`: java.lang.String = null, `tab.string`: java.lang.String = null, `type`: java.lang.String = null, `vo`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): TabDurSym = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`color` != null) Some("color" -> `color`) else None,
      if (`fontfam` != null) Some("fontfam" -> `fontfam`) else None,
      if (`fontname` != null) Some("fontname" -> `fontname`) else None,
      if (`fontsize` != null) Some("fontsize" -> `fontsize`) else None,
      if (`fontstyle` != null) Some("fontstyle" -> `fontstyle`) else None,
      if (`fontweight` != null) Some("fontweight" -> `fontweight`) else None,
      if (`glyph.auth` != null) Some("glyph.auth" -> `glyph.auth`) else None,
      if (`glyph.name` != null) Some("glyph.name" -> `glyph.name`) else None,
      if (`glyph.num` != null) Some("glyph.num" -> `glyph.num`) else None,
      if (`glyph.uri` != null) Some("glyph.uri" -> `glyph.uri`) else None,
      if (`ho` != null) Some("ho" -> `ho`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`layer` != null) Some("layer" -> `layer`) else None,
      if (`letterspacing` != null) Some("letterspacing" -> `letterspacing`) else None,
      if (`lineheight` != null) Some("lineheight" -> `lineheight`) else None,
      if (`place` != null) Some("place" -> `place`) else None,
      if (`tab.course` != null) Some("tab.course" -> `tab.course`) else None,
      if (`tab.fing` != null) Some("tab.fing" -> `tab.fing`) else None,
      if (`tab.fret` != null) Some("tab.fret" -> `tab.fret`) else None,
      if (`tab.line` != null) Some("tab.line" -> `tab.line`) else None,
      if (`tab.string` != null) Some("tab.string" -> `tab.string`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`vo` != null) Some("vo" -> `vo`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new TabDurSym(elements.toList, attributes)
  }
}
