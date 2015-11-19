package com.jagex.core.constants;

public enum ModeGameType implements SerialEnum {

	RUNESCAPE(0, "runescape", "RuneScape"),
	FUNORB(1, "funorb", "Fun Orb"),
	WAR_OF_LEGENDS(2, "waroflegends", "War of Legends"),
	STELLAR_DAWN(3, "stellardawn", "Stellar Dawn"),
	EIGHT_REALMS(4, "8realms", "8 Realms"),
	TRANSFORMERS(5, "transformers", "Transformers Universe"),
	SCRATCH(6, "scratch", "Scratch"),
	LEGACY(-1, "legacy", "Legacy", true, new ModeGameType[]{ RUNESCAPE, FUNORB, WAR_OF_LEGENDS, EIGHT_REALMS, STELLAR_DAWN });

	public final int id;
	public final ModeGameType[] supportedLegacyGames;
	public final String internalName;
	public final String name;
	public final boolean isLegacy;

	private ModeGameType(int var3, String var4, String var5) {
		this.id = var3;
		this.internalName = var4;
		this.name = var5;
		this.isLegacy = false;
		this.supportedLegacyGames = new ModeGameType[0];
	}

	@Override
	public int getSerialId() {
		return this.id;
	}

	public String toString() {
		return this.name;
	}

	private ModeGameType(int var3, String var4, String var5, boolean var6, ModeGameType[] var7) {
		this.id = var3;
		this.internalName = var4;
		this.name = var5;
		this.isLegacy = var6;
		this.supportedLegacyGames = var7;
	}
}

