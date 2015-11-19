package com.jagex.game.runetek5.config.vartype.bit;

import com.jagex.core.constants.SerialEnum;

public enum VarBitTypeEncodingKey implements SerialEnum {
	BASEVAR(1),
	BITS(2),
	WARNONDECREASE(3),
	MASTERQUEST(4),
	QUESTPOINTS(5),
	WEALTHEQUIVALENT(6),
	SETVARALLOWED(7),
	SENDTOGAMELOGWORLD(8),
	TRANSMITLEVEL(9),
	TRANSMITLEVELOTHER(10),
	DEBUGNAME(11),
	IGNOREOVERLAP(12),
	VARBITNAME_HASH32(13),
	KEY_14(14),
	KEY_15(15);
	
	private int serialID;
	
	private VarBitTypeEncodingKey (int id) {
		this.serialID = id;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
}
