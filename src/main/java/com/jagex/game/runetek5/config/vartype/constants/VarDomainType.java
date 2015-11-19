package com.jagex.game.runetek5.config.vartype.constants;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;

public enum VarDomainType implements SerialEnum {
	PLAYER(0, Js5ConfigGroup.VAR_PLAYER, true, true),
	NPC(1, Js5ConfigGroup.VAR_NPC, false, true),
	CLIENT(2, Js5ConfigGroup.VAR_CLIENT, true, true),
	WORLD(3, Js5ConfigGroup.VAR_WORLD, false, false),
	REGION(4, Js5ConfigGroup.VAR_REGION, false, false),
	OBJECT(5, Js5ConfigGroup.VAR_OBJECT, true, false),
	CLAN(6, Js5ConfigGroup.VAR_CLAN, true, true),
	CLAN_SETTINGS(7, Js5ConfigGroup.VAR_CLAN_SETTING, true, false),
	GLOBAL(8, Js5ConfigGroup.VAR_GLOBAL, false, false),
	GROUP(9, Js5ConfigGroup.VAR_GROUP, false, true),
	EXCHANGE(10, Js5ConfigGroup.VAR_EXCHANGE, false, false);
	
	private Js5ConfigGroup js5Group;
	private int serialID;
	private boolean permitPermanentLifetime;
	private boolean permitClientTransmission;
	
	VarDomainType(int id, Js5ConfigGroup js5Group,
			boolean permitPermanentLifetime, boolean permitClientTransmission) {
		this.serialID = id;
		this.js5Group = js5Group;
		this.permitPermanentLifetime = permitPermanentLifetime;
		this.permitClientTransmission = permitClientTransmission;
	}
	
	@Override
	public int getSerialId() {
		return serialID;
	}
	
	public Js5ConfigGroup getJs5GroupID() {
    	return js5Group;
    }
	
	public boolean permitsPermanentLifetime() {
		return permitPermanentLifetime;
	}
	
	public boolean permitsClientTransmission() {
		return permitClientTransmission;
	}
}
