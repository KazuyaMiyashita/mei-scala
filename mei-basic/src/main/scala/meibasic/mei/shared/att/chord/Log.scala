package meibasic.mei.shared.att.chord

/**
 * Logical domain attributes for chord. The artic, dots, and dur attributes encode the written articulations, augmentation dots, and duration values. The beam, fermata, lv, slur, syl, tie, and tuplet attributes may be used to indicate the attachment of these things to this chord. If visual information about these things needs to be recorded, then either the elements corresponding to these attributes or the attributes available in the att.vis.chord class should be employed.
 * 
 * Ident: att.chord.log
 * Module: MEI.shared
 */
trait Log  extends meibasic.mei.shared.att.AugmentDots with meibasic.mei.cmn.att.chord.log.Cmn with meibasic.mei.shared.att.Cue with meibasic.mei.shared.att.duration.Log with meibasic.mei.shared.att.Event {

} 
