package meibasic.mei.shared.att

/**
 * Attributes shared by events which rely on other events for their existence. For example, a slur/phrase marking must be drawn between or over a group of notes. The slur is therefore a control event.
 * 
 * Ident: att.controlEvent
 * Module: MEI.shared
 */
trait ControlEvent  extends _root_.meibasic.mei.shared.att.LayerIdent with _root_.meibasic.mei.shared.att.Plist with _root_.meibasic.mei.shared.att.StaffIdent with _root_.meibasic.mei.shared.att.timestamp.Log {

} 
