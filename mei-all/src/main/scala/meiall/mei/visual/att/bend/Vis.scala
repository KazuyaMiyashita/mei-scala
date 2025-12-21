package meiall.mei.visual.att.bend

/**
 * Visual domain attributes. If the bulge or bezier attributes are present, the bend should be rendered as a curve. Otherwise, it should be rendered using lines. The ho and vo attributes describe the visual offset of the entire rendered bend. The endho, endvo and startho, startvo attribute pairs may be used to encode start and end points relative to their programmatic placement. For exact placement of the endpoints of the bend, use the x and y attributes.
 * 
 * Ident: att.bend.vis
 * Module: MEI.visual
 */
trait Vis  extends meiall.mei.shared.att.Color with meiall.mei.shared.att.Curvature with meiall.mei.shared.att.linerend.Base with meiall.mei.shared.att.VerticalGroup with meiall.mei.shared.att.VisualOffset with meiall.mei.shared.att.VisualOffset2 with meiall.mei.shared.att.Xy with meiall.mei.shared.att.Xy2 {

} 
