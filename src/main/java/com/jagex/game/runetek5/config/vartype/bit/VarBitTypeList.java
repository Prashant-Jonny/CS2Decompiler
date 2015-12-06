package com.jagex.game.runetek5.config.vartype.bit;

import net.openrs.cache.Cache;
import com.jagex.game.runetek5.config.ConfigTypeList;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;

public class VarBitTypeList extends ConfigTypeList {

	public VarBitTypeList(Cache cache) {
		super(cache, VarBitType.class, Js5ConfigGroup.VAR_BIT);
	}
}
