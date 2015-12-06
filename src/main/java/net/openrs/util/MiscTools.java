package net.openrs.util;

public class MiscTools {
	
	public static int[] sine = new int[16384];
	public static int[] cosine = new int[16384];
	public static float[] SIN = new float[16384];
	public static float[] COS = new float[16384];

	static {
		double d = 3.834951969714103E-4;
		for (int i = 0; i < 16384; i++) {
			sine[i] = (int) (Math.sin(i * d) * 16384.0);
			cosine[i] = (int) (Math.cos(i * d) * 16384.0);
			SIN[i] = (float) Math.sin((double) i * d);
			COS[i] = (float) Math.cos((double) i * d);
		}
	}
	
	/*
	 * Converts String array to String, with optional
	 * var cement operator
	 * Ex:
	 * 		
	 * 		String[] myTempStr = { "Mew", null, "Pow", "Go Go Gadget Frosty" }
	 * 		join(myTempStr, ", ")
	 * return:
	 * 		String = "Mew, null, Pow, Go Go Gadget Frosty"
	 */
	public static <T> String join(T[] array, String cement) {
	    StringBuilder builder = new StringBuilder();

	    if(array == null || array.length == 0) {
	        return null;
	    }

	    for (T t : array) {
	        builder.append(t).append(cement);
	    }

	    builder.delete(builder.length() - cement.length(), builder.length());

	    return builder.toString();
	}
	
	/*
	 * Converts String to int array
	 * Ex: 
	 * 		String: [ 2, 2, 53, 1 ]
	 * return: 
	 * 		int[] = { 2, 2, 53, 1 };
	 */
	public static int[] toIntArray(String input) {
	    String beforeSplit = input.replaceAll("\\[|\\]|\\s", "");
	    String[] split = beforeSplit.split("\\,");
	    int[] result = new int[split.length];
	    for (int i = 0; i < split.length; i++) {
	        result[i] = Integer.parseInt(split[i]);
	    }
	    return result;
	}
	
	/*
	 * Converts String to short array
	 * Ex: 
	 * 		String: [ 2, 2, 53, 1 ]
	 * return: 
	 * 		short[] = { 2, 2, 53, 1 };
	 */
	public static short[] toShortArray(String input) {
	    String beforeSplit = input.replaceAll("\\[|\\]|\\s", "");
	    String[] split = beforeSplit.split("\\,");
	    short[] result = new short[split.length];
	    for (int i = 0; i < split.length; i++) {
	        result[i] = Short.parseShort(split[i]);
	    }
	    return result;
	}
	
	public static final void set(int[] array, int off, int len, int val) {
		len = off + len - 7;
		while (off < len) {
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
			array[off++] = val;
		}
		len += 7;
		while (off < len) {
			array[off++] = val;
		}
	}
	public static final void intArrayCopy(int[] array1, int i, int[] array2, int j,
			int k) {
		if (array1 == array2) {
			if (i == j) {
				return;
			}
			if (j > i && j < i + k) {
				k--;
				i += k;
				j += k;
				k = i - k;
				k += 7;
				while (i >= k) {
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
					array2[j--] = array1[i--];
				}
				k -= 7;
				while (i >= k) {
					array2[j--] = array1[i--];
				}
				return;
			}
		}
		k += i;
		k -= 7;
		while (i < k) {
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
			array2[j++] = array1[i++];
		}
		k += 7;
		while (i < k) {
			array2[j++] = array1[i++];
		}
	}
}
