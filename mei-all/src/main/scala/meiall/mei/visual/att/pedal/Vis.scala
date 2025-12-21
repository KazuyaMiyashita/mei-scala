package meiall.mei.visual.att.pedal

/**
 * Visual domain attributes. The place attribute captures the placement of the pedal marking with respect to the staff with which it is associated. Modern publishing standards require the place to bebelow; however, for transcriptions of manuscript works, this attribute class allows the full range of values.
 * 
 * Ident: att.pedal.vis
 * Module: MEI.visual
 */
trait Vis  extends meiall.mei.usersymbols.att.AltSym with meiall.mei.shared.att.Color with meiall.mei.externalsymbols.att.ExtSym with meiall.mei.shared.att.LineRend with meiall.mei.shared.att.PlacementRelStaff with meiall.mei.shared.att.Typography with meiall.mei.shared.att.VerticalGroup with meiall.mei.shared.att.VisualOffset with meiall.mei.shared.att.Xy {

} 
