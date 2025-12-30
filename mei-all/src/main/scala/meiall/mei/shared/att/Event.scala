package meiall.mei.shared.att

/**
 * Attributes that apply to all written events,e.g., note, chord, rest, etc.
 * 
 * Ident: att.event
 * Module: MEI.shared
 */
trait Event  extends _root_.meiall.mei.performance.att.Alignment with _root_.meiall.mei.shared.att.LayerIdent with _root_.meiall.mei.shared.att.StaffIdent with _root_.meiall.mei.gestural.att.timestamp.Ges with _root_.meiall.mei.shared.att.timestamp.Log {

} 
