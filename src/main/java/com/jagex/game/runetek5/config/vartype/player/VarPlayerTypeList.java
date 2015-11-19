package com.jagex.game.runetek5.config.vartype.player;

import net.openrs.cache.Cache;

import com.jagex.game.runetek5.config.vartype.VarTypeList;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;

public class VarPlayerTypeList extends VarTypeList {

	public VarPlayerTypeList(Cache cache) {
		super(cache, VarPlayerType.class, VarDomainType.PLAYER);
	}
}
