package com.wycody.cs2d.rev;

import java.util.HashMap;
import java.util.Map;

import com.jagex.game.runetek5.config.vartype.VarTypeList;
import com.jagex.game.runetek5.config.vartype.bit.VarBitTypeList;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.jagex.game.runetek5.config.vartype.general.VarBasicTypeListClient;
import com.jagex.game.runetek5.config.vartype.player.VarPlayerTypeList;

import net.openrs.cache.Cache;

public abstract class RS3Revision extends Revision {

	public VarBitTypeList varBitTypeList;
	public VarPlayerTypeList varpTypeList;
	public VarBasicTypeListClient varcListClient;
	public VarBasicTypeListClient varclanListClient;
	public VarBasicTypeListClient varClanSettingsListClient;
	public VarBasicTypeListClient varnListClient;
	public VarBasicTypeListClient vargroupListClient;
	public VarBasicTypeListClient varWorldListClient;
	public VarBasicTypeListClient varRegionListClient;
	public VarBasicTypeListClient varObjListClient;
	public VarBasicTypeListClient varExchangeListClient;
	public VarBasicTypeListClient varGlobalListClient;
	public Map<VarDomainType, VarTypeList> varTypeDomain = new HashMap<>();

	public RS3Revision(Cache cache) {
		varcListClient = new VarBasicTypeListClient(cache, VarDomainType.CLIENT);
		varclanListClient = new VarBasicTypeListClient(cache, VarDomainType.CLAN);
		varClanSettingsListClient = new VarBasicTypeListClient(cache, VarDomainType.CLAN_SETTINGS);
		vargroupListClient = new VarBasicTypeListClient(cache, VarDomainType.GROUP);
		varnListClient = new VarBasicTypeListClient(cache, VarDomainType.NPC);
		varRegionListClient = new VarBasicTypeListClient(cache, VarDomainType.REGION);
		varWorldListClient = new VarBasicTypeListClient(cache, VarDomainType.WORLD);
		varObjListClient = new VarBasicTypeListClient(cache, VarDomainType.OBJECT);
		varExchangeListClient = new VarBasicTypeListClient(cache, VarDomainType.GLOBAL);
		varGlobalListClient = new VarBasicTypeListClient(cache, VarDomainType.EXCHANGE);
		varBitTypeList = new VarBitTypeList(cache);
		varpTypeList = new VarPlayerTypeList(cache);
		varTypeDomain.put(varcListClient.type, varcListClient);
		varTypeDomain.put(varnListClient.type, varnListClient);
		varTypeDomain.put(varclanListClient.type, varclanListClient);
		varTypeDomain.put(varClanSettingsListClient.type, varClanSettingsListClient);
		varTypeDomain.put(vargroupListClient.type, vargroupListClient);
		varTypeDomain.put(VarDomainType.PLAYER, varpTypeList);
	}

}
