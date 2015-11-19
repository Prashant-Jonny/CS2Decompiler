package com.jagex.core.constants;

import java.util.Locale;

public enum Language implements SerialEnum {
	EN("en", "English", ModeWhere.LIVE, 0, "GB"),
	DE("de", "German", ModeWhere.LIVE, 1, "DE"),
	FR("fr", "French", ModeWhere.LIVE, 2, "FR"),
	PT("pt", "Portuguese", ModeWhere.LIVE, 3, "BR"),
	NL("nl", "Dutch", ModeWhere.WTWIP, 4, "NL"),
	ES("es", "Spanish", ModeWhere.LIVE, 5, "ES"),
	ES_MX("es-mx", "Spanish (Latin American)", ModeWhere.WTWIP, 6, "MX");

	public final int id;
	public static final int field_831 = 7;
	private final String name;
	private final ModeWhere modeWhere;
	private static final Language[] languageList;
	private final Locale locale;
	private final String internalName;

	public static Language method_1388(int var0) {
		return var0 >= 0 && var0 < languageList.length ? languageList[var0]
				: null;
	}

	private Language(String var3, String var4, ModeWhere var5, int var6,
			String var7) {
		this.internalName = var3;
		this.name = var4;
		this.modeWhere = var5;
		this.id = var6;
		if (var7 != null) {
			this.locale = new Locale(var3.substring(0, 2), var7);
		} else {
			this.locale = new Locale(var3.substring(0, 2));
		}

	}

	public static Language getByInternalName(String var0) {
		String var1 = var0.toLowerCase(Locale.ENGLISH);
		Language[] var2 = languageList;
		int var3 = var2.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			Language var5 = var2[var4];
			if (var5 != null && getInternalName(var5).equals(var1)) {
				return var5;
			}
		}

		return null;
	}

	public static String getInternalName(Language var0) {
		return var0.internalName;
	}

	@Override
	public int getSerialId() {
		return this.id;
	}

	public static Language getById(int var0) {
		return var0 >= 0 && var0 < languageList.length ? languageList[var0]
				: null;
	}

	public String toString() {
		return getInternalName(this).toLowerCase(Locale.ENGLISH);
	}

	static {
		Language[] var0 = values();
		languageList = new Language[var0.length];
		Language[] var1 = var0;
		int var2 = var0.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			Language var4 = var1[var3];
			if (languageList[var4.id] != null) {
				throw new IllegalStateException("Clashing IDs for "
						+ languageList[var4.id] + " and " + var4);
			}

			languageList[var4.id] = var4;
		}

	}
	public String getLowerCaseInternalName() {
		return getInternalName(this).toLowerCase(Locale.ENGLISH);
	}
}
