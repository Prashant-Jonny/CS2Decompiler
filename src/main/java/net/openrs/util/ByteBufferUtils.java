package net.openrs.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import net.openrs.util.crypto.Whirlpool;

import com.jagex.core.stringtools.general.StringTools;


/**
 * Contains {@link ByteBuffer}-related utility methods.
 * @author Graham
 * @author `Discardedx2
 */
public final class ByteBufferUtils {


	public static int getUShortA(ByteBuffer buffer) {
		
		return ((buffer.get() & 0xFF) << 8) + (buffer.get() - 128 & 0xFF);
	}
	public static int getUShortLEA(ByteBuffer buffer) {
		
		return (buffer.get() - 128 & 0xFF) + ((buffer.get() & 0xFF) << 8);
	}
	/**
	 * Calculates the CRC32 checksum of the specified buffer.
	 * @param buffer The buffer.
	 * @return The CRC32 checksum.
	 */
	public static int getCrcChecksum(ByteBuffer buffer) {
		Checksum crc = new CRC32();
		for (int i = 0; i < buffer.limit(); i++) {
			crc.update(buffer.get(i));
		}
		return (int) crc.getValue();
	}

	/**
	 * Gets a null-terminated string from the specified buffer, using a
	 * modified ISO-8859-1 character set.
	 * @param buf The buffer.
	 * @return The decoded string.
	 */
	public static String getJagexString(ByteBuffer buf) {
		StringBuilder bldr = new StringBuilder();
		int b;
		while ((b = buf.get()) != 0) {
			if (b >= 127 && b < 160) {
				char curChar = StringTools.CHARACTERS[b - 128];
				if (curChar != 0) {
					bldr.append(curChar);
				}
			} else {
				bldr.append((char) b);
			}
		}
		return bldr.toString();
	}
	
	public static String getCheckedJagexString(ByteBuffer buf) {
		byte check = buf.get();
		if (check != 0)
		    throw new IllegalStateException("");
		StringBuilder bldr = new StringBuilder();
		int b;
		while ((b = buf.get()) != 0) {
			if (b >= 127 && b < 160) {
				char curChar = StringTools.CHARACTERS[b - 128];
				if (curChar != 0) {
					bldr.append(curChar);
				}
			} else {
				bldr.append((char) b);
			}
		}
		return bldr.toString();
	}
	/**
	 * Gets a smart integer from the buffer.
	 * @param buffer The buffer.
	 * @return The value.
	 */
	public static int getSmartInt(ByteBuffer buffer) {
		if (buffer.get(buffer.position()) < 0)
			return buffer.getInt() & 0x7fffffff;
		int f = buffer.getShort() & 0xFFFF;
		return f == 32767?-1:f;
	}

	public static void writeSmartInt(DataOutputStream dos, int value) {
		try {
			if (value >= Short.MAX_VALUE && value >= 0)
				dos.writeInt(value - Integer.MAX_VALUE - 1);
			else
				dos.writeShort(value >= 0 ? value : 32767);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write24BitInt(DataOutputStream dos, int i) {
		try {
			dos.writeByte(i >> 16);
			dos.writeByte(i >> 8);
			dos.writeByte(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getSmartShort(ByteBuffer buffer) {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return buffer.get() & 0xFF;
		return (buffer.getShort() & 0xFFFF) - 32768;
	}
	public static int getSmart1(ByteBuffer buffer) {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return (buffer.get() & 0xFF) - 64;
		return (buffer.getShort() & 0xFFFF) - 49152;
	}
	public static int getSmartShortMinusOne(ByteBuffer buffer) {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return (buffer.get() & 0xFF) - 1;
		return (buffer.getShort() & 0xFFFF) - 32769;
	}
	/**
	 * Reads a 'tri-byte' from the specified buffer.
	 * @param buffer The buffer.
	 * @return The value.
	 */
	public static int getMedium(ByteBuffer buffer) {
		return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
	}

	/**
	 * Calculates the whirlpool digest of the specified buffer.
	 * @param buf The buffer.
	 * @return The 64-byte whirlpool digest.
	 */
	public static byte[] getWhirlpoolDigest(ByteBuffer buf) {
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);
		return Whirlpool.whirlpool(bytes, 0, bytes.length);
	}


	/**
	 * Converts the contents of the specified byte buffer to a string, which is
	 * formatted similarly to the output of the {@link Arrays#toString()}
	 * method.
	 * @param buffer The buffer.
	 * @return The string.
	 */
	public static String toString(ByteBuffer buffer) {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < buffer.limit(); i++) {
			String hex = Integer.toHexString(buffer.get(i) & 0xFF).toUpperCase();
			if (hex.length() == 1)
				hex = "0" + hex;

			builder.append("0x").append(hex);
			if (i != buffer.limit() - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	public static String readFastString(ByteBuffer buffer) {
		byte value = buffer.get(buffer.position());
		if (value == 0) {
			buffer.position(buffer.position() + 1);
			return null;
		}
		return readString(buffer);
	}
	public static void writeString(DataOutputStream dos, String toWrite) {
		try {
			dos.write(toWrite.getBytes());
			dos.writeByte(0);		
		} catch(IOException ioe){
			
		}

	}
	public static String readString(ByteBuffer buf) {
		StringBuilder bldr = new StringBuilder();
		byte b;
		while ((b = buf.get()) != 0) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	public static String readLine(ByteBuffer buf) {
		StringBuilder bldr = new StringBuilder();
		byte b;
		while ((b = buf.get()) != 10) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}
	public static String method16053(ByteBuffer buffer) {
		byte i_31_ = buffer.get();
		if (i_31_ != 0)
			throw new IllegalStateException("");
		int i_32_ = buffer.position();
		while (buffer.get() != 0) {
			/* empty */
		}
		int i_33_ = buffer.position() - i_32_ - 1;
		if (0 == i_33_)
			return "";
		return getStringFromBytes(buffer.array(), i_32_, i_33_);
	}
	public static String getStringFromBytes(byte[] is, int i, int i_0_) {
		char[] cs = new char[i_0_];
		int i_2_ = 0;
		for (int i_3_ = 0; i_3_ < i_0_; i_3_++) {
			int i_4_ = is[i_3_ + i] & 0xff;
			if (0 != i_4_) {
				if (i_4_ >= 128 && i_4_ < 160) {
					int i_5_ = StringTools.CHARACTERS[i_4_ - 128];
					if (i_5_ == 0)
						i_5_ = 63;
					i_4_ = i_5_;
				}
				cs[i_2_++] = (char) i_4_;
			}
		}
		return new String(cs, 0, i_2_);
	}
	/**
	 * Default private constructor to prevent instantiation.
	 */
	private ByteBufferUtils() {

	}

	/**
	 * Writes a 'tri-byte' to the specified buffer.
	 * @param buffer The buffer.
	 * @param value The value.
	 */
	public static void putMedium(ByteBuffer buffer, int value) {
		buffer.put((byte) (value >> 16));
		buffer.put((byte) (value >> 8));
		buffer.put((byte) value);
	}
	public static int getIMEInt(ByteBuffer buffer) {
		// TODO Auto-generated method stub
		return ((buffer.get() & 0xFF) << 16) + ((buffer.get() & 0xFF) << 24) + (buffer.get() & 0xFF) + ((buffer.get() & 0xFF) << 8);
	}


}
