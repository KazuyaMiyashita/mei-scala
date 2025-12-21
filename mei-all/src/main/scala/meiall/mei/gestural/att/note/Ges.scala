package meiall.mei.gestural.att.note

/**
 * Gestural domain attributes.
 * 
 * Ident: att.note.ges
 * Module: MEI.gestural
 */
trait Ges  extends meiall.mei.gestural.att.accidental.Ges with meiall.mei.gestural.att.articulation.Ges with meiall.mei.gestural.att.duration.Ges with meiall.mei.midi.att.InstrumentIdent with meiall.mei.midi.att.MidiVelocity with meiall.mei.mensural.att.note.ges.Mensural with meiall.mei.gestural.att.pitch.Ges with meiall.mei.stringtab.att.Stringtab {

} 
