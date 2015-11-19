package com.jagex.game.runetek5.config.vartype;

import com.jagex.core.constants.SerialEnum;

public enum VarTypeEncodingKey implements SerialEnum {
	DEBUGNAME(1),
	DOMAIN(2),
	TYPE(3),
	LIFETIME(4),
	TRANSMITLEVEL(5),
	VARNAME_HASH32(6),
	NOT_TEMPORARY(7);
	
	private int serialID;
	
	private VarTypeEncodingKey (int id) {
		this.serialID = id;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
}
