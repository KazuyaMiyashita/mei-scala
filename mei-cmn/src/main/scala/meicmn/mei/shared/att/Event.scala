package meicmn.mei.shared.att

/**
 * Attributes that apply to all written events,e.g., note, chord, rest, etc.
 * 
 * Ident: att.event
 * Module: MEI.shared
 */
trait Event  extends _root_.meicmn.mei.performance.att.Alignment with _root_.meicmn.mei.shared.att.LayerIdent with _root_.meicmn.mei.shared.att.StaffIdent with _root_.meicmn.mei.gestural.att.timestamp.Ges with _root_.meicmn.mei.shared.att.timestamp.Log {

} 
