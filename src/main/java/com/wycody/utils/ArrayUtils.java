package com.wycody.utils;

public class ArrayUtils {

	public static Object[] flip(Object... array) {
		Object[] clone = array.clone();

		int offset2 = 0;
		for (int offset = array.length - 1; offset >= 0; offset--) {
			clone[offset] = array[offset2++];
		}
		for(int index = 0; index < array.length; index++) {
			array[index] = clone[index];
		}
		return clone;

	}

	public static Object[] merge(Object[]... arrays) {
		int size = size(arrays);
		Object[] merged = new Object[size];
		int index = 0;
		for (Object[] array : arrays) {
			for (Object element : array) {
				merged[index++] = element;
			}
		}
		return merged;
	}

	public static int size(Object[]... arrays) {
		int size = 0;
		for (Object[] o : arrays) {
			size += o.length;
		}
		return size;

	}
}
