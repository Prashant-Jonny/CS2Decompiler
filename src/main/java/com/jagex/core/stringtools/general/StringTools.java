package com.jagex.core.stringtools.general;

public final class StringTools {
	public static boolean method12844(char c) {
		return (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a'
				&& c <= 'z');
	}

	static int method12845(CharSequence charsequence, char c) {
		int i = 0;
		int i_0_ = charsequence.length();
		for (int i_1_ = 0; i_1_ < i_0_; i_1_++) {
			if (charsequence.charAt(i_1_) == c)
				i++;
		}
		return i;
	}

	static int method12846(CharSequence charsequence, char c) {
		int i = 0;
		int i_2_ = charsequence.length();
		for (int i_3_ = 0; i_3_ < i_2_; i_3_++) {
			if (charsequence.charAt(i_3_) == c)
				i++;
		}
		return i;
	}

	public static int method12847(CharSequence charsequence) {
		int i = charsequence.length();
		int i_4_ = 0;
		for (int i_5_ = 0; i_5_ < i; i_5_++)
			i_4_ = (i_4_ << 5) - i_4_ + charsequence.charAt(i_5_);
		return i_4_;
	}

	static int method12848(CharSequence charsequence, char c) {
		int i = 0;
		int i_6_ = charsequence.length();
		for (int i_7_ = 0; i_7_ < i_6_; i_7_++) {
			if (charsequence.charAt(i_7_) == c)
				i++;
		}
		return i;
	}

	static int method12849(CharSequence charsequence, char c) {
		int i = 0;
		int i_8_ = charsequence.length();
		for (int i_9_ = 0; i_9_ < i_8_; i_9_++) {
			if (charsequence.charAt(i_9_) == c)
				i++;
		}
		return i;
	}

	public static String method12850(Object[] objects, int i, int i_10_) {
		if (0 == i_10_)
			return "";
		if (1 == i_10_) {
			CharSequence charsequence = (CharSequence) objects[i];
			if (charsequence == null)
				return "null";
			return charsequence.toString();
		}
		int i_11_ = i + i_10_;
		int i_12_ = 0;
		for (int i_13_ = i; i_13_ < i_11_; i_13_++) {
			CharSequence charsequence = (CharSequence) objects[i_13_];
			if (charsequence == null)
				i_12_ += 4;
			else
				i_12_ += charsequence.length();
		}
		StringBuilder stringbuilder = new StringBuilder(i_12_);
		for (int i_14_ = i; i_14_ < i_11_; i_14_++) {
			CharSequence charsequence = (CharSequence) objects[i_14_];
			if (null == charsequence)
				stringbuilder.append("null");
			else
				stringbuilder.append(charsequence);
		}
		return stringbuilder.toString();
	}

	public static boolean method12851(CharSequence charsequence) {
		return StringTools.isNumber(charsequence, 10, true, (byte) -110);
	}

	public static String[] method12852(String string, char c) {
		int i = method10116(string, c, (byte) 127);
		String[] strings = new String[1 + i];
		int i_15_ = 0;
		int i_16_ = 0;
		for (int i_17_ = 0; i_17_ < i; i_17_++) {
			int i_18_;
			for (i_18_ = i_16_; string.charAt(i_18_) != c; i_18_++) {
				/* empty */
			}
			strings[i_15_++] = string.substring(i_16_, i_18_);
			i_16_ = i_18_ + 1;
		}
		strings[i] = string.substring(i_16_);
		return strings;
	}

	public static boolean method12853(CharSequence charsequence) {
		return StringTools.isNumber(charsequence, 10, true, (byte) -74);
	}

	public static boolean method12854(CharSequence charsequence) {
		return StringTools.isNumber(charsequence, 10, true, (byte) -89);
	}

	public static boolean method12855(CharSequence charsequence) {
		return StringTools.isNumber(charsequence, 10, true, (byte) -77);
	}

	public static String[] method12856(String string, char c) {
		int i = method10116(string, c, (byte) 127);
		String[] strings = new String[1 + i];
		int i_19_ = 0;
		int i_20_ = 0;
		for (int i_21_ = 0; i_21_ < i; i_21_++) {
			int i_22_;
			for (i_22_ = i_20_; string.charAt(i_22_) != c; i_22_++) {
				/* empty */
			}
			strings[i_19_++] = string.substring(i_20_, i_22_);
			i_20_ = i_22_ + 1;
		}
		strings[i] = string.substring(i_20_);
		return strings;
	}

	static int method12858(CharSequence charsequence, int i, boolean bool) {
		if (i < 2 || i > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(i).toString());
		boolean bool_23_ = false;
		boolean bool_24_ = false;
		int i_25_ = 0;
		int i_26_ = charsequence.length();
		for (int i_27_ = 0; i_27_ < i_26_; i_27_++) {
			int i_28_ = charsequence.charAt(i_27_);
			if (0 == i_27_) {
				if (45 == i_28_) {
					bool_23_ = true;
					continue;
				}
				if (43 == i_28_ && bool)
					continue;
			}
			if (i_28_ >= 48 && i_28_ <= 57)
				i_28_ -= 48;
			else if (i_28_ >= 65 && i_28_ <= 90)
				i_28_ -= 55;
			else if (i_28_ >= 97 && i_28_ <= 122)
				i_28_ -= 87;
			else
				throw new NumberFormatException();
			if (i_28_ >= i)
				throw new NumberFormatException();
			if (bool_23_)
				i_28_ = -i_28_;
			int i_29_ = i_28_ + i_25_ * i;
			if (i_29_ / i != i_25_)
				throw new NumberFormatException();
			i_25_ = i_29_;
			bool_24_ = true;
		}
		if (!bool_24_)
			throw new NumberFormatException();
		return i_25_;
	}

	public static String method12859(int i, boolean bool) {
		if (!bool || i < 0)
			return Integer.toString(i);
		return method1322(i, 10, bool, 113890686);
	}

	public static String method12860(int i, boolean bool) {
		if (!bool || i < 0)
			return Integer.toString(i);
		return method1322(i, 10, bool, -115533439);
	}

	static String method1322(int i, int i_18_, boolean bool, int i_19_) {
		if (i_18_ < 2 || i_18_ > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(i_18_).toString());
		if (!bool || i < 0)
			return Integer.toString(i, i_18_);
		int i_20_ = 2;
		int i_21_ = i / i_18_;
		while (i_21_ != 0) {
			i_21_ /= i_18_;
			i_20_++;
		}
		char[] cs = new char[i_20_];
		cs[0] = '+';
		for (int i_22_ = i_20_ - 1; i_22_ > 0; i_22_--) {
			int i_23_ = i;
			i /= i_18_;
			int i_24_ = i_23_ - i_18_ * i;
			if (i_24_ >= 10)
				cs[i_22_] = (char) (87 + i_24_);
			else
				cs[i_22_] = (char) (i_24_ + 48);
		}
		return new String(cs);
	}

	public static int method12861(CharSequence charsequence) {
		return parseInt(charsequence, 10, true);
	}

	static String method12862(int i, int i_30_, boolean bool) {
		if (i_30_ < 2 || i_30_ > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(i_30_).toString());
		if (!bool || i < 0)
			return Integer.toString(i, i_30_);
		int i_31_ = 2;
		int i_32_ = i / i_30_;
		while (i_32_ != 0) {
			i_32_ /= i_30_;
			i_31_++;
		}
		char[] cs = new char[i_31_];
		cs[0] = '+';
		for (int i_33_ = i_31_ - 1; i_33_ > 0; i_33_--) {
			int i_34_ = i;
			i /= i_30_;
			int i_35_ = i_34_ - i_30_ * i;
			if (i_35_ >= 10)
				cs[i_33_] = (char) (87 + i_35_);
			else
				cs[i_33_] = (char) (i_35_ + 48);
		}
		return new String(cs);
	}

	public static int method12863(CharSequence charsequence) {
		int i = charsequence.length();
		int i_36_ = 0;
		for (int i_37_ = 0; i_37_ < i; i_37_++)
			i_36_ = ((i_36_ << 5) - i_36_ + getByteFromCharacter(charsequence
					.charAt(i_37_)));
		return i_36_;
	}

	public static String method12864(String string, char c, String string_38_) {
		int i = string.length();
		int i_39_ = string_38_.length();
		int i_40_ = i;
		int i_41_ = i_39_ - 1;
		if (i_41_ != 0) {
			int i_42_ = 0;
			for (;;) {
				i_42_ = string.indexOf(c, i_42_);
				if (i_42_ < 0)
					break;
				i_42_++;
				i_40_ += i_41_;
			}
		}
		StringBuilder stringbuilder = new StringBuilder(i_40_);
		int i_43_ = 0;
		for (;;) {
			int i_44_ = string.indexOf(c, i_43_);
			if (i_44_ < 0)
				break;
			stringbuilder.append(string.substring(i_43_, i_44_));
			stringbuilder.append(string_38_);
			i_43_ = 1 + i_44_;
		}
		stringbuilder.append(string.substring(i_43_));
		return stringbuilder.toString();
	}

	public static int method12865(CharSequence charsequence) {
		int i = charsequence.length();
		int i_45_ = 0;
		for (int i_46_ = 0; i_46_ < i; i_46_++)
			i_45_ = ((i_45_ << 5) - i_45_ + getByteFromCharacter(charsequence
					.charAt(i_46_)));
		return i_45_;
	}

	static String method12866(int i, int i_47_, boolean bool) {
		if (i_47_ < 2 || i_47_ > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(i_47_).toString());
		if (!bool || i < 0)
			return Integer.toString(i, i_47_);
		int i_48_ = 2;
		int i_49_ = i / i_47_;
		while (i_49_ != 0) {
			i_49_ /= i_47_;
			i_48_++;
		}
		char[] cs = new char[i_48_];
		cs[0] = '+';
		for (int i_50_ = i_48_ - 1; i_50_ > 0; i_50_--) {
			int i_51_ = i;
			i /= i_47_;
			int i_52_ = i_51_ - i_47_ * i;
			if (i_52_ >= 10)
				cs[i_50_] = (char) (87 + i_52_);
			else
				cs[i_50_] = (char) (i_52_ + 48);
		}
		return new String(cs);
	}

	public static int method12867(CharSequence charsequence,
			CharSequence charsequence_53_) {
		int i = charsequence.length();
		int i_54_ = charsequence_53_.length();
		if (i == 0)
			return i_54_;
		if (0 == i_54_)
			return i;
		int[] is = new int[i + 1];
		int[] is_55_ = new int[i + 1];
		for (int i_56_ = 0; i_56_ <= i; i_56_++)
			is[i_56_] = i_56_;
		for (int i_57_ = 1; i_57_ <= i_54_; i_57_++) {
			is_55_[0] = i_57_;
			char c = charsequence_53_.charAt(i_57_ - 1);
			for (int i_58_ = 1; i_58_ <= i; i_58_++)
				is_55_[i_58_] = Math.min(Math.min(1 + is_55_[i_58_ - 1],
						is[i_58_] + 1), (is[i_58_ - 1] + (charsequence
						.charAt(i_58_ - 1) == c ? 0 : 1)));
			int[] is_59_ = is;
			is = is_55_;
			is_55_ = is_59_;
		}
		return is[i];
	}

	public static int method12868(CharSequence charsequence,
			CharSequence charsequence_60_) {
		int i = charsequence.length();
		int i_61_ = charsequence_60_.length();
		if (i == 0)
			return i_61_;
		if (0 == i_61_)
			return i;
		int[] is = new int[i + 1];
		int[] is_62_ = new int[i + 1];
		for (int i_63_ = 0; i_63_ <= i; i_63_++)
			is[i_63_] = i_63_;
		for (int i_64_ = 1; i_64_ <= i_61_; i_64_++) {
			is_62_[0] = i_64_;
			char c = charsequence_60_.charAt(i_64_ - 1);
			for (int i_65_ = 1; i_65_ <= i; i_65_++)
				is_62_[i_65_] = Math.min(Math.min(1 + is_62_[i_65_ - 1],
						is[i_65_] + 1), (is[i_65_ - 1] + (charsequence
						.charAt(i_65_ - 1) == c ? 0 : 1)));
			int[] is_66_ = is;
			is = is_62_;
			is_62_ = is_66_;
		}
		return is[i];
	}

	public static int method12869(CharSequence charsequence,
			CharSequence charsequence_67_) {
		int i = charsequence.length();
		int i_68_ = charsequence_67_.length();
		if (i == 0)
			return i_68_;
		if (0 == i_68_)
			return i;
		int[] is = new int[i + 1];
		int[] is_69_ = new int[i + 1];
		for (int i_70_ = 0; i_70_ <= i; i_70_++)
			is[i_70_] = i_70_;
		for (int i_71_ = 1; i_71_ <= i_68_; i_71_++) {
			is_69_[0] = i_71_;
			char c = charsequence_67_.charAt(i_71_ - 1);
			for (int i_72_ = 1; i_72_ <= i; i_72_++)
				is_69_[i_72_] = Math.min(Math.min(1 + is_69_[i_72_ - 1],
						is[i_72_] + 1), (is[i_72_ - 1] + (charsequence
						.charAt(i_72_ - 1) == c ? 0 : 1)));
			int[] is_73_ = is;
			is = is_69_;
			is_69_ = is_73_;
		}
		return is[i];
	}

	StringTools() throws Throwable {
		throw new Error();
	}

	static int method12870(CharSequence charsequence, char c) {
		int i = 0;
		int i_74_ = charsequence.length();
		for (int i_75_ = 0; i_75_ < i_74_; i_75_++) {
			if (charsequence.charAt(i_75_) == c)
				i++;
		}
		return i;
	}

	public static long method12871(CharSequence charsequence) {
		int i = charsequence.length();
		long l = 0L;
		for (int i_76_ = 0; i_76_ < i; i_76_++)
			l = (l << 5) - l + (long) charsequence.charAt(i_76_);
		return l;
	}

	public static String method12872(String string, char c, String string_77_) {
		int i = string.length();
		int i_78_ = string_77_.length();
		int i_79_ = i;
		int i_80_ = i_78_ - 1;
		if (i_80_ != 0) {
			int i_81_ = 0;
			for (;;) {
				i_81_ = string.indexOf(c, i_81_);
				if (i_81_ < 0)
					break;
				i_81_++;
				i_79_ += i_80_;
			}
		}
		StringBuilder stringbuilder = new StringBuilder(i_79_);
		int i_82_ = 0;
		for (;;) {
			int i_83_ = string.indexOf(c, i_82_);
			if (i_83_ < 0)
				break;
			stringbuilder.append(string.substring(i_82_, i_83_));
			stringbuilder.append(string_77_);
			i_82_ = 1 + i_83_;
		}
		stringbuilder.append(string.substring(i_82_));
		return stringbuilder.toString();
	}

	public static String method12873(String string, char c, String string_84_) {
		int i = string.length();
		int i_85_ = string_84_.length();
		int i_86_ = i;
		int i_87_ = i_85_ - 1;
		if (i_87_ != 0) {
			int i_88_ = 0;
			for (;;) {
				i_88_ = string.indexOf(c, i_88_);
				if (i_88_ < 0)
					break;
				i_88_++;
				i_86_ += i_87_;
			}
		}
		StringBuilder stringbuilder = new StringBuilder(i_86_);
		int i_89_ = 0;
		for (;;) {
			int i_90_ = string.indexOf(c, i_89_);
			if (i_90_ < 0)
				break;
			stringbuilder.append(string.substring(i_89_, i_90_));
			stringbuilder.append(string_84_);
			i_89_ = 1 + i_90_;
		}
		stringbuilder.append(string.substring(i_89_));
		return stringbuilder.toString();
	}

	public static String[] method12874(String string, char c) {
		int i = method10116(string, c, (byte) 127);
		String[] strings = new String[1 + i];
		int i_91_ = 0;
		int i_92_ = 0;
		for (int i_93_ = 0; i_93_ < i; i_93_++) {
			int i_94_;
			for (i_94_ = i_92_; string.charAt(i_94_) != c; i_94_++) {
				/* empty */
			}
			strings[i_91_++] = string.substring(i_92_, i_94_);
			i_92_ = i_94_ + 1;
		}
		strings[i] = string.substring(i_92_);
		return strings;
	}

	public static boolean method12875(char c) {
		return (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a'
				&& c <= 'z');
	}

	public static boolean method12876(CharSequence charsequence) {
		return StringTools.isNumber(charsequence, 10, true, (byte) -102);
	}

	public static String[] split/* Class633_Sub1.method16572() */(
			String string, char c, byte i) {
		int i_13_ = method10116(string, c, (byte) 127);
		String[] strings = new String[1 + i_13_];
		int i_14_ = 0;
		int i_15_ = 0;
		for (int i_16_ = 0; i_16_ < i_13_; i_16_++) {
			int i_17_;
			for (i_17_ = i_15_; string.charAt(i_17_) != c; i_17_++) {
				/* empty */
			}
			strings[i_14_++] = string.substring(i_15_, i_17_);
			i_15_ = i_17_ + 1;
		}
		strings[i_13_] = string.substring(i_15_);
		return strings;
	}

	public static int parseInt(CharSequence string) {
		return parseInt(string, 10, true);
	}

	public static int parseInt(CharSequence string,
			int radix) {
		return parseInt(string, radix, true);
	}

	public static boolean isCount/* Class349.method5863() */(
			CharSequence charsequence, int i) {
		return isNumber(charsequence, 10, true, (byte) -110);
	}

	static boolean isNumber/* Class507.method8491() */(
			CharSequence charsequence, int radix, boolean bool, byte i_40_) {
		if (radix < 2 || radix > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(radix).toString());
		boolean bool_41_ = false;
		boolean bool_42_ = false;
		int i_43_ = 0;
		int i_44_ = charsequence.length();
		for (int i_45_ = 0; i_45_ < i_44_; i_45_++) {
			int i_46_ = charsequence.charAt(i_45_);
			if (i_45_ == 0) {
				if (i_46_ == 45) {
					bool_41_ = true;
					continue;
				}
				if (43 == i_46_ && bool)
					continue;
			}
			if (i_46_ >= 48 && i_46_ <= 57)
				i_46_ -= 48;
			else if (i_46_ >= 65 && i_46_ <= 90)
				i_46_ -= 55;
			else if (i_46_ >= 97 && i_46_ <= 122)
				i_46_ -= 87;
			else
				return false;
			if (i_46_ >= radix)
				return false;
			if (bool_41_)
				i_46_ = -i_46_;
			int i_47_ = i_46_ + radix * i_43_;
			if (i_47_ / radix != i_43_)
				return false;
			i_43_ = i_47_;
			bool_42_ = true;
		}
		return bool_42_;
	}

	static int parseInt(CharSequence charsequence,
			int i, boolean bool) {
		if (i < 2 || i > 36)
			throw new IllegalArgumentException(new StringBuilder().append("")
					.append(i).toString());
		boolean bool_9_ = false;
		boolean bool_10_ = false;
		int i_11_ = 0;
		int i_12_ = charsequence.length();
		for (int i_13_ = 0; i_13_ < i_12_; i_13_++) {
			int i_14_ = charsequence.charAt(i_13_);
			if (0 == i_13_) {
				if (45 == i_14_) {
					bool_9_ = true;
					continue;
				}
				if (43 == i_14_ && bool)
					continue;
			}
			if (i_14_ >= 48 && i_14_ <= 57)
				i_14_ -= 48;
			else if (i_14_ >= 65 && i_14_ <= 90)
				i_14_ -= 55;
			else if (i_14_ >= 97 && i_14_ <= 122)
				i_14_ -= 87;
			else
				throw new NumberFormatException();
			if (i_14_ >= i)
				throw new NumberFormatException();
			if (bool_9_)
				i_14_ = -i_14_;
			int i_15_ = i_14_ + i_11_ * i;
			if (i_15_ / i != i_11_)
				throw new NumberFormatException();
			i_11_ = i_15_;
			bool_10_ = true;
		}
		if (!bool_10_)
			throw new NumberFormatException();
		return i_11_;
	}

	static int method10116/* Class603.method10116() */(CharSequence charsequence,
			char c, byte i) {
		int i_0_ = 0;
		int i_1_ = charsequence.length();
		for (int i_2_ = 0; i_2_ < i_1_; i_2_++) {
			if (charsequence.charAt(i_2_) == c)
				i_0_++;
		}
		return i_0_;
	}

	public static int method2303/* Class130.method2303() */(
			CharSequence charsequence, int i) {
		int i_14_ = charsequence.length();
		int i_15_ = 0;
		for (int i_16_ = 0; i_16_ < i_14_; i_16_++)
			i_15_ = ((i_15_ << 5) - i_15_ + getByteFromCharacter(charsequence
					.charAt(i_16_)));
		return i_15_;
	}

	/**
	 * The modified set of 'extended ASCII' characters used by the client.
	 */
	public static char CHARACTERS[] = { '\u20AC', '\0', '\u201A', '\u0192',
			'\u201E', '\u2026', '\u2020', '\u2021', '\u02C6', '\u2030',
			'\u0160', '\u2039', '\u0152', '\0', '\u017D', '\0', '\0', '\u2018',
			'\u2019', '\u201C', '\u201D', '\u2022', '\u2013', '\u2014',
			'\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\0', '\u017E',
			'\u0178' };

	public static char getCharacterFromByte(byte var0) {
		int var1 = var0 & 255;
		if (var1 == 0) {
			throw new IllegalArgumentException("Non cp1252 character 0x"
					+ Integer.toString(var1, 16) + " provided");
		} else {
			if (var1 >= 128 && var1 < 160) {
				char var2 = CHARACTERS[var1 - 128];
				if (var2 == 0) {
					var2 = 63;
				}

				var1 = var2;
			}

			return (char) var1;
		}
	}

	public static byte getByteFromCharacter(char c) {
		byte i_12_;
		if (c > 0 && c < '\u0080' || c >= '\u00a0' && c <= '\u00ff')
			i_12_ = (byte) c;
		else if ('\u20ac' == c)
			i_12_ = (byte) -128;
		else if (c == '\u201a')
			i_12_ = (byte) -126;
		else if (c == '\u0192')
			i_12_ = (byte) -125;
		else if ('\u201e' == c)
			i_12_ = (byte) -124;
		else if ('\u2026' == c)
			i_12_ = (byte) -123;
		else if (c == '\u2020')
			i_12_ = (byte) -122;
		else if ('\u2021' == c)
			i_12_ = (byte) -121;
		else if ('\u02c6' == c)
			i_12_ = (byte) -120;
		else if ('\u2030' == c)
			i_12_ = (byte) -119;
		else if (c == '\u0160')
			i_12_ = (byte) -118;
		else if (c == '\u2039')
			i_12_ = (byte) -117;
		else if (c == '\u0152')
			i_12_ = (byte) -116;
		else if (c == '\u017d')
			i_12_ = (byte) -114;
		else if ('\u2018' == c)
			i_12_ = (byte) -111;
		else if (c == '\u2019')
			i_12_ = (byte) -110;
		else if ('\u201c' == c)
			i_12_ = (byte) -109;
		else if (c == '\u201d')
			i_12_ = (byte) -108;
		else if (c == '\u2022')
			i_12_ = (byte) -107;
		else if ('\u2013' == c)
			i_12_ = (byte) -106;
		else if ('\u2014' == c)
			i_12_ = (byte) -105;
		else if ('\u02dc' == c)
			i_12_ = (byte) -104;
		else if (c == '\u2122')
			i_12_ = (byte) -103;
		else if (c == '\u0161')
			i_12_ = (byte) -102;
		else if ('\u203a' == c)
			i_12_ = (byte) -101;
		else if ('\u0153' == c)
			i_12_ = (byte) -100;
		else if (c == '\u017e')
			i_12_ = (byte) -98;
		else if (c == '\u0178')
			i_12_ = (byte) -97;
		else
			i_12_ = (byte) 63;
		return i_12_;
	}

}
