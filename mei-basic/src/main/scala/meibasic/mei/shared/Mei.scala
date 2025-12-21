package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Contains a single MEI-conformant document, consisting of an MEI header and a musical text, either in isolation or as part of an meiCorpus element.
 * 
 * Ident: mei
 * Module: MEI.shared
 */
case class Mei(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Id with meibasic.mei.shared.att.MeiVersion {
  override def validate(): Boolean = {
    val allowedAttrs = Set("meiversion", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_mei)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Mei {
  def apply(`meiversion`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Mei = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`meiversion` != null) Some("meiversion" -> `meiversion`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Mei(elements.toList, attributes)
  }
}
