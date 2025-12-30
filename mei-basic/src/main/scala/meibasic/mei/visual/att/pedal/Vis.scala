package meibasic.mei.visual.att.pedal

/**
 * Visual domain attributes. The place attribute captures the placement of the pedal marking with respect to the staff with which it is associated. Modern publishing standards require the place to bebelow; however, for transcriptions of manuscript works, this attribute class allows the full range of values.
 * 
 * Ident: att.pedal.vis
 * Module: MEI.visual
 */
trait Vis  extends _root_.meibasic.mei.shared.att.Color with _root_.meibasic.mei.externalsymbols.att.ExtSym with _root_.meibasic.mei.shared.att.LineRend with _root_.meibasic.mei.shared.att.PlacementRelStaff with _root_.meibasic.mei.shared.att.Typography with _root_.meibasic.mei.shared.att.VisualOffset {

} 
