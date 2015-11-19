package com.jagex.core.constants;

import java.util.EnumSet;

public enum ModeWhere implements SerialEnum {
	LOCAL("LOCAL", 4),
	WTWIP("WTWIP", 3, new ModePermission[]{ ModePermission.OFFICE }),
	UNKNOWN("UNKNOWN", 11, new ModePermission[]{ ModePermission.OFFICE }),
	WTI("WTI", 5, new ModePermission[]{ ModePermission.OFFICE, ModePermission.HAS_EXTERNAL_ACCESS }),
	WTITU("WTITU", 8, new ModePermission[]{ ModePermission.OFFICE, ModePermission.HAS_EXTERNAL_ACCESS }),
	WTQA("WTQA", 2, new ModePermission[]{ ModePermission.OFFICE, ModePermission.QA }),
	WTQA2("WTQA2", 9, new ModePermission[]{ ModePermission.OFFICE, ModePermission.QA }),
	WTRC("WTRC", 1, new ModePermission[]{ ModePermission.OFFICE, ModePermission.HAS_EXTERNAL_ACCESS }),
	INTBETA("INTBETA", 6, new ModePermission[]{ ModePermission.HAS_EXTERNAL_ACCESS }),
	LIVE("LIVE", 0, new ModePermission[]{ ModePermission.HAS_EXTERNAL_ACCESS }),
	DEMO("DEMO", 10, new ModePermission[]{ ModePermission.OFFICE });

	private String suffix = "";
	private final int id;
	private String name;
	private final EnumSet<ModePermission> permissions = EnumSet.noneOf(ModePermission.class);

	private ModeWhere(String var3, int var4) {
		this.name = var3;
		this.id = var4;
	}

	private ModeWhere(String var3, int var4, ModePermission ... var5) {
		this.name = var3;
		this.id = var4;
		ModePermission[] var6 = var5;
		int var7 = var5.length;

		for(int var8 = 0; var8 < var7; ++var8) {
			ModePermission var9 = var6[var8];
			this.permissions.add(var9);
		}

	}

	public static boolean isAllowed(ModeWhere var0, ModePermission var1) {
		return var0.permissions.contains(var1);
	}

	@Override
	public int getSerialId() {
		return this.id;
	}

	public String toString() {
		return this.name().toLowerCase() + this.suffix;
	}

	public String getName() {
		return name;
	}
}

