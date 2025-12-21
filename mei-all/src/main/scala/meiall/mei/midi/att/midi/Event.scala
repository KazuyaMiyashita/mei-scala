package meiall.mei.midi.att.midi

/**
 * Attributes common to MIDI events.
 * 
 * Ident: att.midi.event
 * Module: MEI.midi
 */
trait Event  extends meiall.mei.shared.att.LayerIdent with meiall.mei.shared.att.PartIdent with meiall.mei.shared.att.StaffIdent with meiall.mei.shared.att.timestamp.Log with meiall.mei.gestural.att.timestamp.Ges {

} 
