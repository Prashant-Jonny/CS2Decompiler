package com.jagex.maths;

import net.openrs.io.WrappedByteBuffer;

public class Vector3 {
	public int level;
	public int y;
	public int x;
	public int z;
	static int anInt9194;
	static Vector3[] aVector3Array9193 = new Vector3[0];

	public Vector3() {
		level = -1;
	}

	Vector3(Vector3 vector) {
		level = vector.level;
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}

	Vector3(int i, boolean bool) {
		if (i == -1)
			level = -1;
		else {
			level = i >> 28 & 0x3;
			x = (i >> 14 & 0x3fff) << 9;
			y = 0;
			z = (i & 0x3fff) << 9;
			if (bool) {
				x += 256;
				z += 256;
			}
		}
	}

	public Vector3(int i, int i_1_, int i_2_, int i_3_) {
		level = i;
		x = i_1_;
		y = i_2_;
		z = i_3_;
	}

	@Override
	public String toString() {
		return "Level: " + level + " Coord: " + x + "," + y + "," + z + " Coord Split: " + (x >> 15) + "," + (z >> 15) + "," + (x >> 9 & 0x3f) + "," + (z >> 9 & 0x3f) + "," + (x & 0x1ff) + "," + (z & 0x1ff);
	}

	public Vector3f method15115() {
		return new Vector3f(x, y, z);
	}

	public int len() {
		return 13;
	}

	public void decode(WrappedByteBuffer buffer) {
		level = buffer.getUnsignedByte();
		x = buffer.getInt();
		y = buffer.getInt();
		z = buffer.getInt();
	}

	public void encode(WrappedByteBuffer buffer) {
		buffer.putByte((byte) level);
		buffer.putInt(x);
		buffer.putInt(y);
		buffer.putInt(z);
	}

	void set(Vector3 vector) {
		level = vector.level;
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}

	void set(int i, boolean bool) {
		if (i == -1)
			level = -1;
		else {
			level = i >> 28 & 0x3;
			x = (i >> 14 & 0x3fff) << 9;
			y = 0;
			z = (i & 0x3fff) << 9;
			if (bool) {
				x += 256;
				z += 256;
			}
		}
	}

	void set(int i, int i_5_, int i_6_, int i_7_) {
		level = i;
		x = i_5_;
		y = i_6_;
		z = i_7_;
	}
}
