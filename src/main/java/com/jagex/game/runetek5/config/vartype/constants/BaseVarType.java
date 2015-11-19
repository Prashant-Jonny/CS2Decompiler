package com.jagex.game.runetek5.config.vartype.constants;

import com.jagex.core.constants.SerialEnum;
import com.jagex.maths.Vector3;

public enum BaseVarType implements SerialEnum {
	INTEGER("INTEGER", 0, Integer.class),
	LONG("LONG", 1, Long.class),
	STRING("STRING", 2, String.class), 
    VECTOR3("VECTOR3", 3, Vector3.class),
    ERROR("ERROR", 4, Error.class);
	

	public final Class<?> javaClass;
	private int id;

	private BaseVarType(String var1, int ord, Class<?> var4) {
		this.id = ord;
		this.javaClass = var4;
	}

	@Override
	public int getSerialId() {
		return id;
	}
}
