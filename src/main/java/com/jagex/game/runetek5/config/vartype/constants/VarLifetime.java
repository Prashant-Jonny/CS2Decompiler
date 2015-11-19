package com.jagex.game.runetek5.config.vartype.constants;

import com.jagex.core.constants.SerialEnum;

public enum VarLifetime implements SerialEnum {
	TEMPORARY(0),
	PERMANENT(1),
	SERVER_PERMANENT(2);
	
	int serialID;
	
	VarLifetime (int id) {
		this.serialID = id;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
}
