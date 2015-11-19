package com.jagex.game.runetek5.config.vartype.player;

import com.jagex.core.constants.SerialEnum;

public enum VarPlayerTypeEncodingKey implements SerialEnum {
	PROTECTEDACCESSREQUIRED(100),
	SETVARALLOWED(101),
	WEALTHEQUIVALENT(102),
	SENDTOGAMELOGWORLD(103),
	WARNONDECREASE(104),
	HISCOREDATA(105),
	PLOGDATA(106),
	CLANDATA(107),
	TRANSMITLEVELOTHER(108),
	GENERALPURPOSE(109),
	CLIENTCODE(110),
	MASTERQUEST(111),
	QUESTPOINTS(112),
	LEGACYID(113),
	UNKNOWN1(114),
	UNKNOWN2(115),
	UNKNOWN3(116),
	UNKNOWN4(117);
	
	private int serialID;
	
	private VarPlayerTypeEncodingKey (int id) {
		this.serialID = id;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
}
