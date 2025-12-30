package meiall.mei.shared.model

/**
 * Groups elements, such as dynamics, ties, phrase marks, pedal marks, etc., which depend upon other events, such as notes or rests, for their existence.
 * 
 * Ident: model.controlEventLike
 * Module: MEI.shared
 */
trait ControlEventLike  extends _root_.meiall.mei.cmn.model.MeasurePart with _root_.meiall.mei.mensural.model.staffpart.Mensural with _root_.meiall.mei.neumes.model.SyllablePart with _root_.meiall.mei.critapp.model.rdgpart.Music with _root_.meiall.mei.edittrans.model.edittranspart.Music {

} 
