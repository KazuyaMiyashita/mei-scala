package meiall.mei.gestural.att.scoredef

/**
 * Gestural domain attributes for scoreDef. The values set in these attributes act as score-wide defaults for attributes that are not set in descendant elements. For example, the grace attribute value here applies to all the grace attribute values in the score (or, more accurately, until the nextscoreDefelement) without having to individually set each note’s grace attribute value. The midi.* attributes function as default values when creating sounding output. The tune.* attributes provide the capability of recording a tuning reference pitch.
 * 
 * Ident: att.scoreDef.ges
 * Module: MEI.gestural
 */
trait Ges  extends meiall.mei.midi.att.Channelized with meiall.mei.midi.att.TimeBase with meiall.mei.shared.att.Tuning with meiall.mei.midi.att.MidiTempo with meiall.mei.shared.att.MmTempo {

} 
