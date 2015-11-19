package com.jagex.maths;

import net.openrs.io.WrappedByteBuffer;

public class Vector3f {
	public float x;
	public float z;
	public float y;
	static int anInt2464;
	static int anInt2469;
	static Vector3f[] aVector3fArray2466;

	static {
		new Vector3f(0.0F, 0.0F, 0.0F);
		aVector3fArray2466 = new Vector3f[0];
	}

	public Vector3f() {
		/* empty */
	}

	Vector3f(WrappedByteBuffer rsbytebuffer) {
		x = rsbytebuffer.getFloat();
		y = rsbytebuffer.getFloat();
		z = rsbytebuffer.getFloat();
	}

	public Vector3f(Vector3f vector3f_0_) {
		x = vector3f_0_.x;
		y = vector3f_0_.y;
		z = vector3f_0_.z;
	}

	public Vector3f(float f, float f_1_, float f_2_) {
		x = f;
		y = f_1_;
		z = f_2_;
	}

	public void method4236() {
		synchronized (aVector3fArray2466) {
			if (anInt2469 < anInt2464 - 1)
				aVector3fArray2466[anInt2469++] = this;
		}
	}

	public final void method4243() {
		x = -x;
		y = -y;
		z = -z;
	}

	public final float method4254() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public final void method4255() {
		if (x < 0.0F)
			x *= -1.0F;
		if (y < 0.0F)
			y *= -1.0F;
		if (z < 0.0F)
			z *= -1.0F;
	}

	public String toString() {
		return x + ", " + y + ", " + z;
	}

	public final void method4294() {
		float f = 1.0F / method4254();
		x *= f;
		y *= f;
		z *= f;
	}

	public final void method4330() {
		z = 0.0F;
		y = 0.0F;
		x = 0.0F;
	}

	public void method4238(WrappedByteBuffer rsbytebuffer) {
		x = rsbytebuffer.getFloat();
		y = rsbytebuffer.getFloat();
		z = rsbytebuffer.getFloat();
	}

	public boolean method4242(Vector3f vector3f_3_) {
		if (x != vector3f_3_.x || y != vector3f_3_.y || z != vector3f_3_.z)
			return false;
		return true;
	}

	public final void method4244(float f) {
		x /= f;
		y /= f;
		z /= f;
	}

	public final void method4245(Vector3f vector3f_4_) {
		x += vector3f_4_.x;
		y += vector3f_4_.y;
		z += vector3f_4_.z;
	}

	public final void method4248(Vector3f vector3f_5_) {
		x -= vector3f_5_.x;
		y -= vector3f_5_.y;
		z -= vector3f_5_.z;
	}

	public final void method4258(float f) {
		x *= f;
		y *= f;
		z *= f;
	}

	public final void method4264(Matrix3x4 matrix3x4) {
		float f = x;
		float f_6_ = y;
		x = matrix3x4.m00 * f + matrix3x4.m01 * f_6_ + matrix3x4.m02 * z + matrix3x4.m03;
		y = matrix3x4.m10 * f + matrix3x4.m11 * f_6_ + matrix3x4.m12 * z + matrix3x4.m13;
		z = matrix3x4.m20 * f + matrix3x4.m21 * f_6_ + matrix3x4.m22 * z + matrix3x4.m23;
	}

	public final void method4265(Matrix3x4 matrix3x4) {
		float f = x;
		float f_7_ = y;
		x = matrix3x4.m00 * f + matrix3x4.m01 * f_7_ + matrix3x4.m02 * z;
		y = matrix3x4.m10 * f + matrix3x4.m11 * f_7_ + matrix3x4.m12 * z;
		z = matrix3x4.m20 * f + matrix3x4.m21 * f_7_ + matrix3x4.m22 * z;
	}

	public final void method4297(Quaternion quaternion) {
		Quaternion quaternion_8_ = Quaternion.method5826(x, y, z, 0.0F);
		Quaternion quaternion_9_ = Quaternion.method5837(quaternion);
		Quaternion quaternion_10_ = Quaternion.method5844(quaternion_9_, quaternion_8_);
		quaternion_10_.multiply(quaternion);
		set(quaternion_10_.i, quaternion_10_.j, quaternion_10_.k);
		quaternion_8_.method5841();
		quaternion_9_.method5841();
		quaternion_10_.method5841();
	}

	public final float method4327(Vector3f vector3f_11_) {
		return x * vector3f_11_.x + y * vector3f_11_.y + z * vector3f_11_.z;
	}

	public void set(Vector3f vector3f_12_) {
		set(vector3f_12_.x, vector3f_12_.y, vector3f_12_.z);
	}

	public final void method4232(Vector3f vector3f_13_, float f) {
		x += vector3f_13_.x * f;
		y += vector3f_13_.y * f;
		z += vector3f_13_.z * f;
	}

	public final void method4266(Vector3f vector3f_14_, float f) {
		method4258(1.0F - f);
		method4245(method4289(vector3f_14_, f));
	}

	public void set(float f, float f_15_, float f_16_) {
		x = f;
		y = f_15_;
		z = f_16_;
	}

	final void method4252(Vector3f vector3f_17_) {
		set(y * vector3f_17_.z - z * vector3f_17_.y, z * vector3f_17_.x - x * vector3f_17_.z, x * vector3f_17_.y - y * vector3f_17_.x);
	}

	final void method4256(Vector3f vector3f_18_) {
		x *= vector3f_18_.x;
		y *= vector3f_18_.y;
		z *= vector3f_18_.z;
	}

	final void method4259(Vector3f vector3f_19_) {
		x /= vector3f_19_.x;
		y /= vector3f_19_.y;
		z /= vector3f_19_.z;
	}

	public static Vector3f method4284() {
		synchronized (aVector3fArray2466) {
			if (anInt2469 == 0) {
				Vector3f vector3f = new Vector3f();
				Vector3f vector3f_20_ = vector3f;
				return vector3f_20_;
			}
			aVector3fArray2466[--anInt2469].method4330();
			Vector3f vector3f = aVector3fArray2466[anInt2469];
			Vector3f vector3f_21_ = vector3f;
			return vector3f_21_;
		}
	}

	public static void method4241(int i) {
		anInt2464 = i;
		aVector3fArray2466 = new Vector3f[i];
		anInt2469 = 0;
	}

	public static Vector3f method4250(Vector3f vector3f) {
		synchronized (aVector3fArray2466) {
			if (anInt2469 == 0) {
				Vector3f vector3f_22_ = new Vector3f(vector3f);
				Vector3f vector3f_23_ = vector3f_22_;
				return vector3f_23_;
			}
			aVector3fArray2466[--anInt2469].set(vector3f);
			Vector3f vector3f_24_ = aVector3fArray2466[anInt2469];
			Vector3f vector3f_25_ = vector3f_24_;
			return vector3f_25_;
		}
	}

	public static Vector3f method4324(WrappedByteBuffer rsbytebuffer) {
		synchronized (aVector3fArray2466) {
			if (anInt2469 == 0) {
				Vector3f vector3f = new Vector3f(rsbytebuffer);
				Vector3f vector3f_26_ = vector3f;
				return vector3f_26_;
			}
			aVector3fArray2466[--anInt2469].method4238(rsbytebuffer);
			Vector3f vector3f = aVector3fArray2466[anInt2469];
			Vector3f vector3f_27_ = vector3f;
			return vector3f_27_;
		}
	}

	public static final Vector3f method4247(Vector3f vector3f, Vector3f vector3f_28_) {
		Vector3f vector3f_29_ = method4250(vector3f);
		vector3f_29_.method4245(vector3f_28_);
		return vector3f_29_;
	}

	public static final Vector3f method4249(Vector3f vector3f, Vector3f vector3f_30_) {
		Vector3f vector3f_31_ = method4250(vector3f);
		vector3f_31_.method4248(vector3f_30_);
		return vector3f_31_;
	}

	public static final float method4251(Vector3f vector3f, Vector3f vector3f_32_) {
		return vector3f.method4327(vector3f_32_);
	}

	public static final Vector3f method4257(Vector3f vector3f, Vector3f vector3f_33_) {
		Vector3f vector3f_34_ = method4250(vector3f);
		vector3f_34_.method4256(vector3f_33_);
		return vector3f_34_;
	}

	public static final Vector3f method4260(Vector3f vector3f, Vector3f vector3f_35_) {
		Vector3f vector3f_36_ = method4250(vector3f);
		vector3f_36_.method4259(vector3f_35_);
		return vector3f_36_;
	}

	public static final Vector3f method4289(Vector3f vector3f, float f) {
		Vector3f vector3f_37_ = method4250(vector3f);
		vector3f_37_.method4258(f);
		return vector3f_37_;
	}

	public static final Vector3f method4328(Vector3f vector3f, Vector3f vector3f_38_) {
		Vector3f vector3f_39_ = method4250(vector3f);
		vector3f_39_.method4252(vector3f_38_);
		return vector3f_39_;
	}

	public static Vector3f method4263(float f, float f_40_, float f_41_) {
		synchronized (aVector3fArray2466) {
			if (anInt2469 == 0) {
				Vector3f vector3f = new Vector3f(f, f_40_, f_41_);
				Vector3f vector3f_42_ = vector3f;
				return vector3f_42_;
			}
			aVector3fArray2466[--anInt2469].set(f, f_40_, f_41_);
			Vector3f vector3f = aVector3fArray2466[anInt2469];
			Vector3f vector3f_43_ = vector3f;
			return vector3f_43_;
		}
	}
}
