package meibasic.mei.stringtab

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * A barre in a chord tablature grid.
 * 
 * Ident: barre
 * Module: MEI.stringtab
 */
case class Barre(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.StartEndId {
  override def validate(): Boolean = {
    val allowedAttrs = Set("endid", "fret", "label", "startid", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_barre)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Barre {
  def apply(`endid`: java.lang.String = null, `fret`: java.lang.String = null, `label`: java.lang.String = null, `startid`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Barre = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`endid` != null) Some("endid" -> `endid`) else None,
      if (`fret` != null) Some("fret" -> `fret`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`startid` != null) Some("startid" -> `startid`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Barre(elements.toList, attributes)
  }
}
