package com.jagex.core.constants;

public enum ModeGame implements SerialEnum {
	RUNESCAPE("runescape", "RuneScape", 0, ModeGameType.RUNESCAPE),
	STELLARDAWN("stellardawn", "Stellar Dawn", 1, ModeGameType.STELLAR_DAWN),
	TRANSFORMERS("transformers", "Transformers", 3, ModeGameType.TRANSFORMERS),
	ALTERNATEREALITY("alternatereality", "AlternateReality", 2, ModeGameType.RUNESCAPE),
	SCRATCH("scratch", "Scratch", 4, ModeGameType.SCRATCH),
	OLDSCAPE("oldscape", "RuneScape 2007", 5, ModeGameType.RUNESCAPE);

	public final ModeGameType gameType;
	public final int id;
	public final String internalName;
	public final String name;

	private ModeGame(String var3, String var4, int var5, ModeGameType var6) {
		this.internalName = var3;
		this.name = var4;
		this.id = var5;
		this.gameType = var6;
	}

	@Override
	public int getSerialId() {
		return id;
	}
}

