package com.jagex.game.runetek5.config.paramtype;

import net.openrs.cache.Cache;

import com.jagex.game.runetek5.config.ConfigTypeList;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;

public class ParamTypeList extends ConfigTypeList {

	public ParamTypeList(Cache cache) {
		super(cache, ParamType.class, Js5ConfigGroup.PARAMTYPE);
	}
}
