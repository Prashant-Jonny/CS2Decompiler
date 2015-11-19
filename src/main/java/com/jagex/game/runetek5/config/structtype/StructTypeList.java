package com.jagex.game.runetek5.config.structtype;

import com.jagex.game.runetek5.config.ConfigTypeList;
import com.jagex.game.runetek5.config.constants.Js5Archive;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;

import net.openrs.cache.Cache;

public class StructTypeList extends ConfigTypeList {

	public StructTypeList(Cache cache) {
		super(cache, StructType.class, Js5Archive.CONFIG_STRUCT.id, Js5ConfigGroup.STRUCTTYPE);
	}
}

