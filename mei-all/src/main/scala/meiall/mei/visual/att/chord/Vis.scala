package meiall.mei.visual.att.chord

/**
 * Visual domain attributes for chord. The slur, slur.dir, slur.rend, tie, tie.dir, and tie.rend attributes here are syntactic sugar for these attributes on each of the chord's individual notes. The values here apply to all the notes in the chord. If some notes are slurred or tied while others aren't, then the individual note attributes must be used.
 * 
 * Ident: att.chord.vis
 * Module: MEI.visual
 */
trait Vis  extends meiall.mei.usersymbols.att.AltSym with meiall.mei.shared.att.Color with meiall.mei.shared.att.EnclosingChars with meiall.mei.externalsymbols.att.ExtSym with meiall.mei.shared.att.Stems with meiall.mei.shared.att.Typography with meiall.mei.shared.att.Visibility with meiall.mei.shared.att.visualoffset.Ho with meiall.mei.shared.att.visualoffset.To with meiall.mei.shared.att.Xy with meiall.mei.cmn.att.chord.vis.Cmn {

} 
