package com.jagex.game.runetek5.config.vartype.general;

import net.openrs.cache.Cache;

import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.VarTypeList;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;

public class VarBasicTypeListClient extends VarTypeList {

	public VarDomainType type;
	
	public VarBasicTypeListClient(Cache cache, VarDomainType domain) {
		super(cache, VarType.class, domain);
		type = domain;
	}

}
