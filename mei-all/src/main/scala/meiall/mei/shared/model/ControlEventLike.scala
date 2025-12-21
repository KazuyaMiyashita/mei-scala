package meiall.mei.shared.model

/**
 * Groups elements, such as dynamics, ties, phrase marks, pedal marks, etc., which depend upon other events, such as notes or rests, for their existence.
 * 
 * Ident: model.controlEventLike
 * Module: MEI.shared
 */
trait ControlEventLike  extends meiall.mei.cmn.model.MeasurePart with meiall.mei.mensural.model.staffpart.Mensural with meiall.mei.neumes.model.SyllablePart with meiall.mei.critapp.model.rdgpart.Music with meiall.mei.edittrans.model.edittranspart.Music {

} 
