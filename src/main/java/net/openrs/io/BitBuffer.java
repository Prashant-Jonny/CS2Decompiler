package net.openrs.io;

import java.nio.ByteBuffer;

public class BitBuffer extends WrappedByteBuffer {

	private int bitPosition;
	private static int[] BIT_MASKS = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023,
			2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287,
			1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863,
			134217727, 268435455, 536870911, 1073741823, 2147483647, -1 };

	public BitBuffer(byte[] data) {
		super(data);
	}

	public BitBuffer(ByteBuffer data) {
		super(data);
	}

	public BitBuffer(WrappedByteBuffer data) {
		super(data.getBuffer());
	}
	
	public static BitBuffer wrap(ByteBuffer data) {
		BitBuffer buffer = new BitBuffer(data);
		return buffer;
	}
	
	public static BitBuffer wrap(WrappedByteBuffer data) {
		BitBuffer buffer = new BitBuffer(data);
		return buffer;
	}

	public static BitBuffer wrap(byte[] data) {
		BitBuffer buffer = new BitBuffer(data);
		return buffer;
	}

	public void startBitAccess() {
		setBitPosition(getPosition() * 8);
	}
	
	public void endBitAccess() {
		setPosition((7 + bitPosition) / 8);
	}

	public void setBitPosition(int i) {
		bitPosition = i;
	}

	public int getBitPosition() {
		return bitPosition;
	}
	
	public int readBits(int i) {
		int i_6_ = bitPosition >> 3;
		int i_7_ = 8 - (bitPosition & 0x7);
		int i_8_ = 0;
		bitPosition += i;
		for (; i > i_7_; i_7_ = 8) {
			i_8_ += ((getData()[i_6_++] & BIT_MASKS[i_7_]) << i - i_7_);
			i -= i_7_;
		}
		if (i_7_ == i)
			i_8_ += getData()[i_6_] & BIT_MASKS[i_7_];
		else
			i_8_ += getData()[i_6_] >> i_7_ - i & BIT_MASKS[i];
		return i_8_;
	}

}
