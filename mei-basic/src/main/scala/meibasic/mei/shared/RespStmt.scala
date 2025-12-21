package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Transcription of text that names one or more individuals, groups, or in rare cases, mechanical processes, responsible for creation, realization, production, funding, or distribution of the intellectual or artistic content.
 * 
 * Ident: respStmt
 * Module: MEI.shared
 */
case class RespStmt(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.model.RespLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_respStmt)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object RespStmt {
  def apply(`label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): RespStmt = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new RespStmt(elements.toList, attributes)
  }
}
