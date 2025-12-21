package meiall.mei.shared.att

/**
 * Attributes that apply to all written events,e.g., note, chord, rest, etc.
 * 
 * Ident: att.event
 * Module: MEI.shared
 */
trait Event  extends meiall.mei.performance.att.Alignment with meiall.mei.shared.att.LayerIdent with meiall.mei.shared.att.StaffIdent with meiall.mei.gestural.att.timestamp.Ges with meiall.mei.shared.att.timestamp.Log {

} 
