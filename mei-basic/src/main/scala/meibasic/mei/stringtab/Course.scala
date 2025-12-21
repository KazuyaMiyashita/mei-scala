package meibasic.mei.stringtab

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Describes the tuning of a course on a stringed instrument (e.g., guitar, lute).
 * 
 * Ident: course
 * Module: MEI.stringtab
 */
case class Course(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.stringtab.att.course.Log with meibasic.mei.visual.att.course.Vis with meibasic.mei.gestural.att.course.Ges with meibasic.mei.analytical.att.course.Anl {
  override def validate(): Boolean = {
    val allowedAttrs = Set("accid", "label", "oct", "pname", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_course)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Course {
  def apply(`accid`: java.lang.String = null, `label`: java.lang.String = null, `oct`: java.lang.String = null, `pname`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Course = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`accid` != null) Some("accid" -> `accid`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`oct` != null) Some("oct" -> `oct`) else None,
      if (`pname` != null) Some("pname" -> `pname`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Course(elements.toList, attributes)
  }
}
