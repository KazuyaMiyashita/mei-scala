package meibasic.mei.namesdates

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Designation for an individual, including any or all of that individual's forenames, surnames, honorific titles, and added names.
 * 
 * Ident: persName
 * Module: MEI.namesdates
 */
case class PersName(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.Lang with meibasic.mei.shared.att.Name with meibasic.mei.namesdates.model.namelike.Agent {
  override def validate(): Boolean = {
    val allowedAttrs = Set("enddate", "isodate", "label", "notafter", "notbefore", "nymref", "role", "startdate", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_persName)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object PersName {
  def apply(`enddate`: java.lang.String = null, `isodate`: java.lang.String = null, `label`: java.lang.String = null, `notafter`: java.lang.String = null, `notbefore`: java.lang.String = null, `nymref`: java.lang.String = null, `role`: java.lang.String = null, `startdate`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): PersName = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`enddate` != null) Some("enddate" -> `enddate`) else None,
      if (`isodate` != null) Some("isodate" -> `isodate`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`notafter` != null) Some("notafter" -> `notafter`) else None,
      if (`notbefore` != null) Some("notbefore" -> `notbefore`) else None,
      if (`nymref` != null) Some("nymref" -> `nymref`) else None,
      if (`role` != null) Some("role" -> `role`) else None,
      if (`startdate` != null) Some("startdate" -> `startdate`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new PersName(elements.toList, attributes)
  }
}
