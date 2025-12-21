package meibasic.mei.header

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * (file description) – Contains a full bibliographic description of the MEI file.
 * 
 * Ident: fileDesc
 * Module: MEI.header
 */
case class FileDesc(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common {
  override def validate(): Boolean = {
    val allowedAttrs = Set("corresp", "label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_fileDesc)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object FileDesc {
  def apply(`corresp`: java.lang.String = null, `label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): FileDesc = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`corresp` != null) Some("corresp" -> `corresp`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new FileDesc(elements.toList, attributes)
  }
}
