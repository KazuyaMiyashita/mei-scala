package meibasic.mei.visual.att.chord

/**
 * Visual domain attributes for chord. The slur, slur.dir, slur.rend, tie, tie.dir, and tie.rend attributes here are syntactic sugar for these attributes on each of the chord's individual notes. The values here apply to all the notes in the chord. If some notes are slurred or tied while others aren't, then the individual note attributes must be used.
 * 
 * Ident: att.chord.vis
 * Module: MEI.visual
 */
trait Vis  extends meibasic.mei.shared.att.Color with meibasic.mei.shared.att.EnclosingChars with meibasic.mei.externalsymbols.att.ExtSym with meibasic.mei.shared.att.Stems with meibasic.mei.shared.att.visualoffset.Ho with meibasic.mei.cmn.att.chord.vis.Cmn {

} 
