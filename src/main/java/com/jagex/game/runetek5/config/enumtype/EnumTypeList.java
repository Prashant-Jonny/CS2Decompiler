package com.jagex.game.runetek5.config.enumtype;

import net.openrs.cache.Cache;

import com.jagex.game.runetek5.config.ConfigTypeList;
import com.jagex.game.runetek5.config.constants.Js5Archive;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;

public class EnumTypeList extends ConfigTypeList {

	public EnumTypeList(Cache cache) {
		super(cache, EnumType.class, Js5Archive.CONFIG_ENUM.id, Js5ConfigGroup.ENUMTYPE);
	}
}
