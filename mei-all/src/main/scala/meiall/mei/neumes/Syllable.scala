package meiall.mei.neumes

import meiall.Element
import meiall.Schemas
import meiall.Validator

/**
 * Neume notation can be thought of as "neumed text". Therefore, the syllable element provides high-level organization in this repertoire.
 * 
 * Ident: syllable
 * Module: MEI.neumes
 */
case class Syllable(elements: scala.List[Element], attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String])  extends meiall.Element with meiall.mei.shared.att.Common with meiall.mei.facsimile.att.Facsimile with meiall.mei.analytical.att.syllable.Anl with meiall.mei.gestural.att.syllable.Ges with meiall.mei.neumes.att.syllable.Log with meiall.mei.visual.att.syllable.Vis with meiall.mei.neumes.model.SyllableLike {
  override def validate(): Boolean = {
    val allowedAttrs = Set("class", "copyof", "corresp", "facs", "follows", "label", "n", "next", "precedes", "prev", "resp", "sameas", "synch", "type", "when", "xml:base", "xml:id")
    val attributesValid = attributes.keys.forall(allowedAttrs.contains)
    val elementsValid = Validator.validate(elements, Schemas.schema_syllable)
    attributesValid && elementsValid
  }
  override def children: scala.List[Element] = elements
  override def withChildren(newChildren: scala.List[Element]): Element = copy(elements = newChildren)
}

object Syllable {
  def apply(`class`: java.lang.String = null, `copyof`: java.lang.String = null, `corresp`: java.lang.String = null, `facs`: java.lang.String = null, `follows`: java.lang.String = null, `label`: java.lang.String = null, `n`: java.lang.String = null, `next`: java.lang.String = null, `precedes`: java.lang.String = null, `prev`: java.lang.String = null, `resp`: java.lang.String = null, `sameas`: java.lang.String = null, `synch`: java.lang.String = null, `type`: java.lang.String = null, `when`: java.lang.String = null, `xml:base`: java.lang.String = null, `xml:id`: java.lang.String = null)(elements: Element*): Syllable = {
    val attributes: scala.collection.immutable.SeqMap[java.lang.String, java.lang.String] = scala.collection.immutable.SeqMap.from(scala.List(
      if (`class` != null) Some("class" -> `class`) else None,
      if (`copyof` != null) Some("copyof" -> `copyof`) else None,
      if (`corresp` != null) Some("corresp" -> `corresp`) else None,
      if (`facs` != null) Some("facs" -> `facs`) else None,
      if (`follows` != null) Some("follows" -> `follows`) else None,
      if (`label` != null) Some("label" -> `label`) else None,
      if (`n` != null) Some("n" -> `n`) else None,
      if (`next` != null) Some("next" -> `next`) else None,
      if (`precedes` != null) Some("precedes" -> `precedes`) else None,
      if (`prev` != null) Some("prev" -> `prev`) else None,
      if (`resp` != null) Some("resp" -> `resp`) else None,
      if (`sameas` != null) Some("sameas" -> `sameas`) else None,
      if (`synch` != null) Some("synch" -> `synch`) else None,
      if (`type` != null) Some("type" -> `type`) else None,
      if (`when` != null) Some("when" -> `when`) else None,
      if (`xml:base` != null) Some("xml:base" -> `xml:base`) else None,
      if (`xml:id` != null) Some("xml:id" -> `xml:id`) else None
    ).flatten)
    new Syllable(elements.toList, attributes)
  }
}
