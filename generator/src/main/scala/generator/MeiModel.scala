package generator

sealed trait MeiModel {
  def name: String
  def doc: Option[String]
}

object MeiModel {

  /** Represents a Scala case class (e.g., <element>) */
  case class MeiElement(
      name: String,
      originalName: String,
      fields: List[MeiField], // Attributes and child elements
      parents: List[String],  // Inherited traits (e.g., att.common)
      doc: Option[String],
  ) extends MeiModel

  /** Represents a Scala trait (e.g., <define> att.common)
    * Structural traits (model.xxx) have been deprecated, so only attribute groups are targeted.
    */
  case class MeiTrait(
      name: String,
      originalName: String, // Original RNG define name
      parents: List[String],
      doc: Option[String],
  ) extends MeiModel

  /** Represents a Scala type alias.
    * This will be mostly unnecessary with the current changes, but may be kept for simple type definitions like DataURI.
    */
  case class MeiTypeAlias(
      name: String,
      originalName: String,
      targetType: String,
      doc: Option[String],
  ) extends MeiModel

  /** Fields for classes and traits */
  case class MeiField(
      name: String,
      types: List[String], // Components of a Union Type (e.g., List("Title", "Foo"))
      cardinality: Cardinality,
      doc: Option[String] = None,
  )

  sealed trait Cardinality
  object Cardinality {
    case object Required extends Cardinality
    case object Optional extends Cardinality // Option[T]
    case object Multiple extends Cardinality // List[T]
  }
}
