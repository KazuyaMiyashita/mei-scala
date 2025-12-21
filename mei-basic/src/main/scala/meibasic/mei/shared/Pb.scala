package meibasic.mei.shared

import meibasic.Element
import meibasic.Schemas
import meibasic.Validator

/**
 * An empty formatting element that forces text to begin on a new page.
 * 
 * Ident: pb
 * Module: MEI.shared
 */
case class Pb(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meibasic.Element with meibasic.mei.shared.att.Common with meibasic.mei.shared.att.pb.Log with meibasic.mei.visual.att.pb.Vis with meibasic.mei.shared.model.PbLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("folium", "label", "type", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_pb)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Pb {
  def apply(`folium`: java.lang.String = null, `label`: java.lang.String = null, `type`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Pb = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`folium` != null) Some("folium" -> `folium`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Pb(elements.toList, attributes)
  }
}
