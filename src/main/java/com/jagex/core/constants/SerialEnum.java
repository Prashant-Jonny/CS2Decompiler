package com.jagex.core.constants;

public interface SerialEnum {
	int getSerialId();

	public static <T extends SerialEnum> T forID(T[] identifiables,
			int id) {
		for (T identifiable : identifiables) {
			if (id == identifiable.getSerialId()) {
				return identifiable;
			}
		}

		return null;
	}
}
