package com.jagex.game.runetek5.config.vartype;

import net.openrs.cache.Cache;

import com.jagex.game.runetek5.config.ConfigTypeList;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;


public class VarTypeList extends ConfigTypeList {
	
	protected VarDomainType variableDomain;

	public VarTypeList (Cache cache, Class<? extends VarType> clazz, VarDomainType domain) {
		super(cache, clazz, domain);
		this.variableDomain = domain;
		//this.varTypes = new VarType[archive.size()];
		//this.archive = archive;
	}
}
