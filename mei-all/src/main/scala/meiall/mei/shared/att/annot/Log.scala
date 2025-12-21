package meiall.mei.shared.att.annot

/**
 * Logical domain attributes for annot. Values for the type attribute can be taken from any convenient typology of annotation suitable to the work in hand;e.g., annotation, gloss, citation, digression, preliminary, temporary, etc.
 * 
 * Ident: att.annot.log
 * Module: MEI.shared
 */
trait Log  extends meiall.mei.performance.att.Alignment with meiall.mei.shared.att.duration.Additive with meiall.mei.shared.att.LayerIdent with meiall.mei.shared.att.PartIdent with meiall.mei.shared.att.StaffIdent with meiall.mei.shared.att.StartEndId with meiall.mei.shared.att.timestamp.Log with meiall.mei.shared.att.timestamp2.Log {

} 
