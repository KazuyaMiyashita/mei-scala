package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * Title of a bibliographic entity.
 * 
 * Ident: title
 * Module: MEI.shared
 */
case class Title(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Basic with meibasic.mei.shared.att.Labelled with meibasic.mei.shared.att.Lang with meibasic.mei.shared.att.NNumberLike with meibasic.mei.shared.model.TitleLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("label", "level", "n", "type", "xml:id", "xml:lang")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_title)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Title {
  def apply(`label`: java.lang.String = null, `level`: java.lang.String = null, `n`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null, `xml:lang`: java.lang.String = null)(elements: Element*): Title = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`label` != null) Some("label" -> `label`) else None,
      if (`level` != null) Some("level" -> `level`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None,
      if (`xml:lang` != null) Some("xml:lang" -> `xml:lang`) else None
    ).flatten)
    new Title(elements.toList, attributes)
  }
}
