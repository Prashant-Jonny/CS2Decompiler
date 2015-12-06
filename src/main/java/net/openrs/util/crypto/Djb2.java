package net.openrs.util.crypto;

/**
 * A modified implementation of the {@code djb2} hash function.
 * @author Graham
 * @author `Discardedx2
 */
public final class Djb2 {
	
	/**
	 * An implementation of Dan Bernstein's {@code djb2} hash function
	 * which is slightly modified. Instead of the initial hash being 5381, it
	 * is zero.
	 * @param str The string to hash.
	 * @return The hash code.
	 */
	public static int djb2(String str) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + ((hash << 5) - hash);
		}
		return hash;
	}

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private Djb2() {
		
	}
	
	
	/**
	 * Runescapes old hashing + unhasher
	 */
	public static long hashOld(String text) {
		long hash = 0;
		text = text.toLowerCase();
		for (long i = 0; i < text.length(); i++) {
			hash = (hash * 61 + text.charAt((int) i)) - 32;
		}
		return (long) hash;
	}

	private static String unhashOld(long hash) {
		StringBuilder builder = new StringBuilder();
		do {
			long character = (hash - (((hash - 32) / 61) * 61)) + 32;
			builder.append((char) character);
			hash += 32;
			hash -= character;
			hash /= 61;
		} while (hash != 0);
		return builder.reverse().toString();
	}

}