package meiall.mei.shared.att

/**
 * Attributes shared by events which rely on other events for their existence. For example, a slur/phrase marking must be drawn between or over a group of notes. The slur is therefore a control event.
 * 
 * Ident: att.controlEvent
 * Module: MEI.shared
 */
trait ControlEvent  extends meiall.mei.performance.att.Alignment with meiall.mei.shared.att.LayerIdent with meiall.mei.shared.att.PartIdent with meiall.mei.shared.att.Plist with meiall.mei.shared.att.StaffIdent with meiall.mei.shared.att.TargetEval with meiall.mei.shared.att.timestamp.Log with meiall.mei.gestural.att.timestamp.Ges {

} 
