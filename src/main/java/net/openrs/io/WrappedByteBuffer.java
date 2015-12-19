package net.openrs.io;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.jagex.core.stringtools.general.StringTools;

import net.openrs.util.crypto.Whirlpool;

/**
 * Wraps ByteBuffer because it cannot be extended.
 * @author Ethan
 *
 */
public class WrappedByteBuffer {

	private ByteBuffer buffer;

	public WrappedByteBuffer(ByteBuffer data) {
		buffer = data;
	}

	public WrappedByteBuffer(byte[] data) {
		this.buffer = ByteBuffer.wrap(data);
	}
	
	public WrappedByteBuffer(int i) {
		this.buffer = ByteBuffer.allocate(i);
	}

	public static WrappedByteBuffer wrap(ByteBuffer data) {
		WrappedByteBuffer buffer = new WrappedByteBuffer(data);
		return buffer;
	}

	public static WrappedByteBuffer wrap(byte[] data) {
		WrappedByteBuffer buffer = new WrappedByteBuffer(data);
		return buffer;
	}
	
	public byte[] getData() {
		return buffer.array();
	}

	public ByteBuffer getBuffer() {
		return buffer;
	}
	
	public int getPosition() {
		return buffer.position();
	}

	public void setPosition(int pos) {
		buffer.position(pos);
	}
	
	public int getLimit() {
		return buffer.limit();
	}
	
	public int getCapacity() {
		return buffer.capacity();
	}

	public int getRemaining() {
		return buffer.remaining();
	}
	
	public boolean hasRemaining() {
		return buffer.hasRemaining();
	}
	
	public int getInt() {
		return buffer.getInt();
	}
	
	public long getUnsignedInt() {
		return ((long) buffer.getInt() & 0xffffffffL);
	}
	
	public void putInt(int i) {
		buffer.putInt(i);
	}

	public byte getByte() {
		return buffer.get();
	}

	public short getUnsignedByte() {
		return (short) (buffer.get() & 0xFF);
	}
	
	public void putByte(byte value) {
		buffer.put(value);
	}

	public void putByte(int value) {
		buffer.put((byte) value);
	}

	public int getShort() {
		return buffer.getShort();
	}

	public int getUnsignedShort() {
		return buffer.getShort() & 0xFFFF;
	}
	
	public void putShort(short value) {
		buffer.putShort(value);
	}

	public void putShort(int value) {
		buffer.putShort((short) value);
	}

	public int getSmartInt() {
		if (buffer.get(buffer.position()) < 0)
			return buffer.getInt() & 0x7fffffff;
		int f = buffer.getShort() & 0xFFFF;
		return f == 32767?-1:f;
	}

	public void putDecrSmartS(int i)
	{
		if(i < 128)
		{
			putByte(i + 1);
			return;
		}
		if(i < 32768)
		{
			putShort(32769 + i);
			return;
		} else
		{
			System.out.println("Error pdecrsmarts out of range: " + i);
			return;
		}
	}
	public void putSmartInt(int value) {
		if (value >= Short.MAX_VALUE && value >= 0)
			buffer.putInt(value - Integer.MAX_VALUE - 1);
		else
			buffer.putShort((short)(value >= 0 ? value : 32767));
	}

	public int getSmartShort() {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return buffer.get() & 0xFF;
		return (buffer.getShort() & 0xFFFF) - 32768;
	}
	
	public int getSmart1() {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return (buffer.get() & 0xFF) - 64;
		return (buffer.getShort() & 0xFFFF) - 49152;
	}
	
	public void putSmart1(int value) {
		if (value >= -64 && value < 64) {
			putByte((byte) (value + 64));
		} else {
			putShort((short) (0x8000 | (value + 16384)));
		}
	}
	
	public int getSmartShortMinusOne() {
		int i_118_ = buffer.get(buffer.position()) & 0xff;
		if (i_118_ < 128)
			return (buffer.get() & 0xFF) - 1;
		return (buffer.getShort() & 0xFFFF) - 32769;
	}
	
    public int readSmarts() throws IOException {
        int value=0;
        int offset;
        for(offset=getSmartShort(); offset == 32767; offset = getSmartShort())
            value += 32767;
        value += offset;
        return value;
    }
	
	public int getMedium() {
		return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
	}

	public void putMedium(int value) {
		buffer.put((byte) (value >> 16));
		buffer.put((byte) (value >> 8));
		buffer.put((byte) value);
	}
	
	public double getDouble() {
		return buffer.getDouble();
	}
	
	public void putDouble(double value) {
		buffer.putDouble(value);
	}
	
	public float getFloat() {
		return buffer.getFloat();
	}
	
	public float getIntAsFloat() {
		return Float.intBitsToFloat(buffer.getInt());
	}
	
	public void getFloat(float value) {
		buffer.putFloat(value);
	}
	
	public long getLong() {
		return buffer.getLong();
	}
	
	public void putLong(long value) {
		buffer.putLong(value);
	}
	
	public String getNullString() {
		if(buffer.get(buffer.position()) == 0) {
			buffer.position(buffer.position() + 1);
			return null;
		}
		return getString();
	}

	public String getString() {
		StringBuilder bldr = new StringBuilder();
		byte b;
		while ((b = buffer.get()) != 0) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	public void putString(String string) {
		buffer.put(string.getBytes());
		buffer.put((byte)0);
	}

	
	public String readEscapedString() {
		int start = buffer.position();
		while (buffer.get() != 0) {
		}
		int length = buffer.position() - start - 1;
		if (length == 0)
			return "";
		return StringTools.escapeString(buffer.array(), start, length);
	}
	
	public String getCheckedString() {
		byte check = buffer.get();
		if (check != 0)
		    throw new IllegalStateException("");
		StringBuilder bldr = new StringBuilder();
		byte b;
		while ((b = buffer.get()) != 0) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	public void putCheckedString(String string) {
		buffer.put((byte)0);
		buffer.put(string.getBytes());
		buffer.put((byte)0);
	}
	
	public String getLine() {
		StringBuilder bldr = new StringBuilder();
		byte b;
		while ((b = buffer.get()) != 10) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	public void putLine(String string) {
		buffer.put(string.getBytes());
		buffer.put((byte)10);
	}

	/**
	 * Calculates the whirlpool digest of the specified buffer.
	 * @param buffer The buffer.
	 * @return The 64-byte whirlpool digest.
	 */
	public byte[] getWhirlpoolDigest() {
		byte[] bytes = new byte[buffer.limit()];
		buffer.get(bytes);
		return Whirlpool.whirlpool(bytes, 0, bytes.length);
	}

	/**
	 * Retrieves a quickchat parameter from the byte buffer
	 * @param buf The buffer
	 * @param size The size (in bytes) of the parameter. Must be between 1 and 8
	 * @return The parameter
	 */
	public long getQuickchatParam(int size) {
		if (--size < 0 || size > 7) {
			throw new IllegalArgumentException();
		}
		
		long l = 0L;
		for (int shift = 8 * size; shift >= 0; shift -= 8) {
			l |= ((long) getUnsignedByte()) << shift;
		}
		return l;
	}
	
	public void putQuickchatParam (long param, int size) {
		if (--size < 0 || size > 7) {
			throw new IllegalArgumentException();
		}
		for (int i = size * 8; i >= 0; i -= 8) {
			putByte((byte) (param >> i));
		}
	}

	public void flip() {
		buffer.flip();
	}
	
	public int length() {
		return getData().length;
	}
	
	public int method_1886() {
		int var1 = getUnsignedByte();
		int var2 = var1 & 63;
		int var3 = var1 & 64;

		for (int var4 = 6; var1 > 127; var4 += 7) {
			var1 = getUnsignedByte();
			var2 |= (var1 & 127) << var4;
		}

		return var3 == 0 ? var2 : -var2;
	}

	public void putFloat(float value) {
		buffer.putFloat(value);
		
	}
}
