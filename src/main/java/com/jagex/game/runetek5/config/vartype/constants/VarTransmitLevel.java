package com.jagex.game.runetek5.config.vartype.constants;

import com.jagex.core.constants.SerialEnum;

public enum VarTransmitLevel implements SerialEnum {
	NEVER(0),
	ON_SET_DIFFERENT(1),
	ON_SET_ALWAYS(2);
	
	int serialID;
	
	VarTransmitLevel (int id) {
		this.serialID = id;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
}
