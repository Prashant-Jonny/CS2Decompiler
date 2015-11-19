package com.jagex.game.runetek5.config;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;

import net.openrs.io.WrappedByteBuffer;

public abstract class ConfigType {

	protected abstract void decode(WrappedByteBuffer var1, int var2);

	public void decode(WrappedByteBuffer buffer) {
		while (true) {
			int opcode = buffer.getUnsignedByte();
			if (opcode == 0) {
				return;
			}

			decode(buffer, opcode);
		}
	}

	public abstract void postDecode();
	
	
    public static String replace(final String text, final String searchString, final String replacement) {
        return replace(text, searchString, replacement, -1);
    }
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static String replace(final String text, final String searchString, final String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        final int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase < 0 ? 0 : increase;
        increase *= max < 0 ? 16 : max > 64 ? 64 : max;
        final StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }	
    
	public Object get(Field field) throws Throwable {
		field.setAccessible(true);
		Class<?> type = field.getType();
		if(type == String.class) {
			return "\"" + replace((String)field.get(this), "\"", "\\\"") + "\"";
		} if(type == char.class) {
			return "\'" + field.get(this) + "\'";
		} else if (type == int[].class) {
			String array = Arrays.toString((int[]) field.get(this));
			return "new int[] " + array.replace("[", "{ ").replace("]", " }");
		} else if (type == int[][].class) {
			return "new int[][] " + Arrays.deepToString((int[][]) field.get(this)).replace('[', '{').replace(']', '}');
		}  else if (type == short[][].class) {
			return "new short[][] " + Arrays.deepToString((short[][]) field.get(this)).replace('[', '{').replace(']', '}');
		} else if (type == byte[].class) {
			String array = Arrays.toString((byte[]) field.get(this));
			return "new byte[] " + array.replace("[", "{ ").replace("]", " }");
		} else if (type == short[].class) {
			String array = Arrays.toString((short[]) field.get(this));
			return "new short[] " + array.replace("[", "{ ").replace("]", " }");
		} else if (type == double[].class) {
			return "new double[] " + Arrays.toString((double[]) field.get(this)).replace("[", "{ ").replace("]", " }");
		} else if (type == boolean[].class) {
			return "new boolean[] " + Arrays.toString((boolean[]) field.get(this)).replace("[", "{ ").replace("]", " }");
		} else if (type == float[].class) {
			return "new float[] " + Arrays.toString((float[]) field.get(this)).replace("[", "{ ").replace("]", " }");
		} else if (type == String[].class) {
			String[] array = (String[]) field.get(this);
			if(array != null) {
				StringBuilder sb = new StringBuilder();
				sb.append("new String[] { ");
				for (int ind = 0; ind < array.length; ind++) {
					if(array[ind] != null)
						sb.append("\"");
					sb.append(array[ind]);
					if(array[ind] != null)
						sb.append("\"");
					if(ind == array.length-1)
						sb.append(" }");
					else
						sb.append(", ");
				}
				return sb.toString();
			} else {
				return null;
			}
		} else if (type == char[].class) {
			char[] array = (char[]) field.get(this);
			if(array != null) {
				StringBuilder sb = new StringBuilder();
				sb.append("new char[] { ");
				for (int ind = 0; ind < array.length; ind++) {
					if(array[ind] != '\u0000')
						sb.append("\'");
					sb.append(array[ind]);
					if(array[ind] != '\u0000')
						sb.append("\'");
					if(ind == array.length-1)
						sb.append(" }");
					else
						sb.append(", ");
				}
				return sb.toString();
			} else {
				return null;
			}
		} else if (type == Object[].class) {
			return Arrays.toString((Object[]) field.get(this));
		}
		return field.get(this);
	}
	
}
