package com.jagex.maths;

import net.openrs.io.WrappedByteBuffer;

public final class Quaternion {
	public float k;
	public float i;
	public float j;
	public float scalar;
	static int anInt4031;
	static int anInt4032;
	static Quaternion[] aQuaternionArray4035 = new Quaternion[0];

	static {
		new Quaternion();
	}

	public Quaternion() {
		reset();
	}

	public Quaternion(Quaternion quaternion_0_) {
		set(quaternion_0_);
	}

	public Quaternion(float f, float f_1_, float f_2_) {
		method5845(f, f_1_, f_2_);
	}

	public Quaternion(float f, float f_3_, float f_4_, float f_5_) {
		set(f, f_3_, f_4_, f_5_);
	}

	public String toString() {
		return i + "," + j + "," + k + "," + scalar;
	}

	public final void conjugate() {
		i = -i;
		j = -j;
		k = -k;
	}

	public void method5841() {
		synchronized (aQuaternionArray4035) {
			if (anInt4032 < anInt4031 - 1)
				aQuaternionArray4035[anInt4032++] = this;
		}
	}

	public final void method5873() {
		float f = 1.0F / method5860(this);
		i *= f;
		j *= f;
		k *= f;
		scalar *= f;
	}

	public void method5828(WrappedByteBuffer rsbytebuffer) {
		i = rsbytebuffer.getFloat();
		j = rsbytebuffer.getFloat();
		k = rsbytebuffer.getFloat();
		scalar = rsbytebuffer.getFloat();
	}

	public void set(Quaternion quaternion_6_) {
		i = quaternion_6_.i;
		j = quaternion_6_.j;
		k = quaternion_6_.k;
		scalar = quaternion_6_.scalar;
	}

	public final void multiply(Quaternion quat) {
		set(quat.scalar * i + quat.i * scalar + quat.j * k - quat.k * j, quat.scalar * j - quat.i * k + quat.j * scalar + quat.k * i, quat.scalar * k + quat.i * j - quat.j * i + quat.k * scalar, quat.scalar * scalar - quat.i * i - quat.j * j - quat.k * k);
	}

	public final void method5847(Quaternion quaternion_8_, float f) {
		if (method5840(quaternion_8_) < 0.0F)
			invert();
		multiply(1.0F - f);
		Quaternion quaternion_9_ = method5827(quaternion_8_, f);
		add(quaternion_9_);
		quaternion_9_.method5841();
		method5873();
	}

	public void method5875(Vector3f vector3f, float f) {
		method5832(vector3f.x, vector3f.y, vector3f.z, f);
	}

	public void method5845(float f, float f_10_, float f_11_) {
		method5832(0.0F, 1.0F, 0.0F, f);
		Quaternion quaternion_12_ = method5868();
		quaternion_12_.method5832(1.0F, 0.0F, 0.0F, f_10_);
		multiply(quaternion_12_);
		quaternion_12_.method5832(0.0F, 0.0F, 1.0F, f_11_);
		multiply(quaternion_12_);
		quaternion_12_.method5841();
	}

	public void method5832(float f, float f_13_, float f_14_, float f_15_) {
		float f_16_ = (float) Math.sin(f_15_ * 0.5F);
		float f_17_ = (float) Math.cos(f_15_ * 0.5F);
		i = f * f_16_;
		j = f_13_ * f_16_;
		k = f_14_ * f_16_;
		scalar = f_17_;
	}

	final void invert() {
		i = -i;
		j = -j;
		k = -k;
		scalar = -scalar;
	}

	final void reset() {
		k = 0.0F;
		j = 0.0F;
		i = 0.0F;
		scalar = 1.0F;
	}

	final void add(Quaternion quaternion_18_) {
		i += quaternion_18_.i;
		j += quaternion_18_.j;
		k += quaternion_18_.k;
		scalar += quaternion_18_.scalar;
	}

	final float method5840(Quaternion quat) {
		return i * quat.i + j * quat.j + k * quat.k + scalar * quat.scalar;
	}

	final void multiply(float f) {
		i *= f;
		j *= f;
		k *= f;
		scalar *= f;
	}

	void set(float f, float f_20_, float f_21_, float f_22_) {
		i = f;
		j = f_20_;
		k = f_21_;
		scalar = f_22_;
	}

	public static Quaternion method5868() {
		synchronized (aQuaternionArray4035) {
			if (anInt4032 == 0) {
				Quaternion quaternion = new Quaternion();
				Quaternion quaternion_23_ = quaternion;
				return quaternion_23_;
			}
			aQuaternionArray4035[--anInt4032].reset();
			Quaternion quaternion = aQuaternionArray4035[anInt4032];
			Quaternion quaternion_24_ = quaternion;
			return quaternion_24_;
		}
	}

	public static void method5835(int i) {
		anInt4031 = i;
		aQuaternionArray4035 = new Quaternion[i];
		anInt4032 = 0;
	}

	public static final Quaternion method5837(Quaternion quaternion) {
		Quaternion quaternion_25_ = method5865(quaternion);
		quaternion_25_.conjugate();
		return quaternion_25_;
	}

	public static Quaternion method5865(Quaternion quaternion) {
		synchronized (aQuaternionArray4035) {
			if (anInt4032 == 0) {
				Quaternion quaternion_26_ = new Quaternion(quaternion);
				Quaternion quaternion_27_ = quaternion_26_;
				return quaternion_27_;
			}
			aQuaternionArray4035[--anInt4032].set(quaternion);
			Quaternion quaternion_28_ = aQuaternionArray4035[anInt4032];
			Quaternion quaternion_29_ = quaternion_28_;
			return quaternion_29_;
		}
	}

	static final float method5860(Quaternion quaternion) {
		return (float) Math.sqrt(method5834(quaternion, quaternion));
	}

	static final Quaternion method5827(Quaternion quaternion, float f) {
		Quaternion quaternion_30_ = method5865(quaternion);
		quaternion_30_.multiply(f);
		return quaternion_30_;
	}

	static final float method5834(Quaternion quaternion, Quaternion quaternion_31_) {
		return quaternion.method5840(quaternion_31_);
	}

	static final Quaternion method5844(Quaternion quaternion, Quaternion quaternion_32_) {
		Quaternion quaternion_33_ = method5865(quaternion);
		quaternion_33_.multiply(quaternion_32_);
		return quaternion_33_;
	}

	static Quaternion method5826(float f, float f_34_, float f_35_, float f_36_) {
		synchronized (aQuaternionArray4035) {
			if (anInt4032 == 0) {
				Quaternion quaternion = new Quaternion(f, f_34_, f_35_, f_36_);
				Quaternion quaternion_37_ = quaternion;
				return quaternion_37_;
			}
			aQuaternionArray4035[--anInt4032].set(f, f_34_, f_35_, f_36_);
			Quaternion quaternion = aQuaternionArray4035[anInt4032];
			Quaternion quaternion_38_ = quaternion;
			return quaternion_38_;
		}
	}
}

