package com.wycody.io;

/**
 * The buffer class is a class which contains a I/O reading for a byte array,
 * with a pointer increase after reading a byte
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class Buffer {

	/**
	 * The data to read from or to write to
	 */
	private byte[] data;

	/**
	 * The pointer of {@link #data}
	 */
	private int offset;

	/**
	 * Construct a {@link Buffer} type
	 * 
	 * @param data
	 *            the data for the buffer in this case this will be input type
	 *            mostly
	 * @param offset
	 *            of the {@code data}
	 */
	public Buffer(byte[] data, int offset) {
		this.data = data;
		this.offset = offset;
	}

	/**
	 * Construct a {@link Buffer} type
	 * 
	 * @param length
	 *            the length of the data (This will create a new byte
	 *            array[@{code length}] as data)
	 * @param the
	 *            offset of the {@code data}
	 */
	public Buffer(int length, int offset) {
		this(new byte[length], offset);
	}

	public Buffer(int length) {
		this(new byte[length], 0);
	}
	/**
	 * Construct a {@link Buffer} type
	 * 
	 * @param length
	 *            the length of the data (This will create a new byte
	 *            array[@{code length}] as data)
	 * @param the
	 *            offset of the {@code data}
	 */
	public Buffer(byte[] data) {
		this(data, 0);
	}

	/**
	 * Read an single signed byte
	 * 
	 * @return the byte
	 */
	public int readByte() {
		return data[offset++];
	}

	/**
	 * Read a single unsigned byte
	 * 
	 * @return the byte
	 */
	public int readUnsignedByte() {
		return readByte() & 0xff;
	}

	/**
	 * Write a single byte
	 * 
	 * @param value
	 *            the value of the byte
	 */
	public void writeByte(int value) {
		checkCapacity(offset, 1);
		data[offset++] = (byte) value;
	}

	/**
	 * Read as unsigned short, then convert it to signed
	 * 
	 * @return the signed short
	 */
	public int readShort() {
		int unsigned = readUnsignedShort();
		if (unsigned > Short.MAX_VALUE) {
			unsigned -= 0x10000;
		}
		return unsigned;
	}

	/**
	 * Read two unsigned bytes (unsigned short)
	 * 
	 * @return
	 */
	public int readUnsignedShort() {
		return (readUnsignedByte() << 8) + readUnsignedByte();
	}

	/**
	 * Write two bytes (short)
	 * 
	 * @param value
	 *            the short to write
	 */
	public void writeShort(int value) {
		writeByte((byte) (value >> 8));
		writeByte((byte) (value));
	}

	/**
	 * Read 4 signed bytes (unsigned integer)
	 * 
	 * @return the integer
	 */
	public int readInt() {
		return (readUnsignedByte() << 24) + (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + readUnsignedByte();
	}

	/**
	 * Read as signed integer, then convert it to unsigned
	 * 
	 * @return the unsigned integer
	 */
	public long readUnsignedInt() {
		return readInt() & 0xFFFFFFFFL;
	}

	/**
	 * Write an integer
	 * 
	 * @param value
	 *            the integer to write
	 */
	public void writeInt(int value) {
		writeByte((byte) (value >> 24));
		writeByte((byte) (value >> 16));
		writeByte((byte) (value >> 8));
		writeByte((byte) (value));
	}

	/**
	 * Read 8 bytes (long)
	 * 
	 * @return the long
	 */
	public long readLong() {
		return ((readInt() & 0xffffffffL) << 32) + readInt() & 0xffffffffL;
	}

	/**
	 * Write 8 bytes (long)
	 * 
	 * @param value
	 *            the long to write
	 */
	public void writeLong(long value) {
		writeByte((byte) (value >> 56));
		writeByte((byte) (value >> 48));
		writeByte((byte) (value >> 32));
		writeByte((byte) (value >> 24));
		writeByte((byte) (value >> 16));
		writeByte((byte) (value >> 8));
		writeByte((byte) (value));
	}

	/**
	 * Keep reading byte until we hit a byte with value 0, then collecting the
	 * bytes as string
	 * 
	 * @return the string
	 */
	public String readString() {
		StringBuilder builder = new StringBuilder();
		int character = 0;
		while ((character = readByte()) != 0) {
			builder.append((char) character);
		}
		return builder.toString();
	}

	/**
	 * Write a simple string
	 * 
	 * @param value
	 *            the value of the string
	 */
	public void writeString(String value) {
		checkCapacity(offset + value.length() + 1, value.length() + 1);
		System.arraycopy(value.getBytes(), 0, data, offset, value.length());
		offset += value.length();
		writeByte((byte) 0);
	}

	public String readNullableString() {
		if (readByte() == 0)
			return null;
		return readString();
	}

	public void writeNullableString(String value) {
		if (value == null) {
			writeByte(0);
		} else {
			writeString(value);
		}

	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Increment the current offset by {@code modifier}
	 * 
	 * @param modifier
	 */
	public void incrOffset(int modifier) {
		offset += modifier;
	}

	/**
	 * Decrement the current offset by {@code modifier}
	 * 
	 * @param modifier
	 */
	public void decrOffset(int modifier) {
		offset -= modifier;
	}

	/**
	 * Return the length of the {@code data}
	 * 
	 * @return the length
	 */
	public int getLength() {
		return data.length;
	}

	public void trim() {
		byte[] data = new byte[offset];
		System.arraycopy(this.data, 0, data, 0, data.length);
		this.data = data;
	}

	public byte[] trimAndGet() {
		trim();
		return data;
	}

	public void checkCapacity(int target, int expandBy) {
		if (target >= data.length) {
			byte[] newData = new byte[target + expandBy];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
	}
}
