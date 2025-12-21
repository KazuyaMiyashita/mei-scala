package meiall.mei.shared.att.chord

/**
 * Logical domain attributes for chord. The artic, dots, and dur attributes encode the written articulations, augmentation dots, and duration values. The beam, fermata, lv, slur, syl, tie, and tuplet attributes may be used to indicate the attachment of these things to this chord. If visual information about these things needs to be recorded, then either the elements corresponding to these attributes or the attributes available in the att.vis.chord class should be employed.
 * 
 * Ident: att.chord.log
 * Module: MEI.shared
 */
trait Log  extends meiall.mei.shared.att.Articulation with meiall.mei.shared.att.AugmentDots with meiall.mei.cmn.att.chord.log.Cmn with meiall.mei.shared.att.Cue with meiall.mei.shared.att.duration.Log with meiall.mei.shared.att.Event with meiall.mei.shared.att.SylText {

} 
