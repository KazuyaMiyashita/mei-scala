package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A string identifying a point in time or the time period between two such points.
 * 
 * Ident: date
 * Module: MEI.shared
 */
case class Date(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Calendared with meibasic.mei.shared.att.Datable with meibasic.mei.shared.att.Lang {
  override def validate(): Boolean = {
    val allowedAttrs = Set("calendar", "enddate", "isodate", "label", "notafter", "notbefore", "startdate", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_date)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Date {
  def apply(`calendar`: java.lang.String = null, `enddate`: java.lang.String = null, `isodate`: java.lang.String = null, `label`: java.lang.String = null, `notafter`: java.lang.String = null, `notbefore`: java.lang.String = null, `startdate`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Date = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`calendar` != null) Some("calendar" -> `calendar`) else None,
      if (`enddate` != null) Some("enddate" -> `enddate`) else None,
      if (`isodate` != null) Some("isodate" -> `isodate`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`notafter` != null) Some("notafter" -> `notafter`) else None,
      if (`notbefore` != null) Some("notbefore" -> `notbefore`) else None,
      if (`startdate` != null) Some("startdate" -> `startdate`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Date(elements.toList, attributes)
  }
}
