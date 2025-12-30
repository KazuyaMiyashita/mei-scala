package meiall.mei.shared.att.scoredef

/**
 * Logical domain attributes for scoreDef in the CMN repertoire. The values set in these attributes act as score-wide defaults for attributes that are not set in descendant elements.
 * 
 * Ident: att.scoreDef.log
 * Module: MEI.shared
 */
trait Log  extends _root_.meiall.mei.shared.att.cleffing.Log with _root_.meiall.mei.shared.att.duration.Default with _root_.meiall.mei.shared.att.keysigdefault.Log with _root_.meiall.mei.shared.att.metersigdefault.Log with _root_.meiall.mei.shared.att.OctaveDefault with _root_.meiall.mei.shared.att.Transposition with _root_.meiall.mei.cmn.att.scoredef.log.Cmn with _root_.meiall.mei.mensural.att.scoredef.log.Mensural {

} 
