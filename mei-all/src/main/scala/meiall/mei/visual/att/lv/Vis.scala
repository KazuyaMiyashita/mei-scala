package meiall.mei.visual.att.lv

/**
 * Visual domain attributes. The vo attribute is the vertical offset (from its normal position) of the entire rendered tie. The startho, startvo, endho, and endvo attributes describe the horizontal and vertical offsets of the start and end points of the sign in terms of staff interline distance; that is, in units of 1/2 the distance between adjacent staff lines. Startto and endto describe the start and end points in terms of time; that is, beats.
 * 
 * Ident: att.lv.vis
 * Module: MEI.visual
 */
trait Vis  extends meiall.mei.shared.att.Color with meiall.mei.shared.att.Curvature with meiall.mei.shared.att.linerend.Base with meiall.mei.shared.att.VisualOffset with meiall.mei.shared.att.VisualOffset2 with meiall.mei.shared.att.Xy with meiall.mei.shared.att.Xy2 {

} 
