package com.jagex.maths;

import java.util.Arrays;

import net.openrs.util.MiscTools;

public final class Matrix4 {
	
	/** XX: Typically the unrotated X component for scaling, also the cosine of the angle when rotated on the Y and/or Z axis. On
	 * Vector3 multiplication this value is multiplied with the source X component and added to the target X component. */
	public static final int M00 = 0;
	/** XY: Typically the negative sine of the angle when rotated on the Z axis. On Vector3 multiplication this value is multiplied
	 * with the source Y component and added to the target X component. */
	public static final int M01 = 4;
	/** XZ: Typically the sine of the angle when rotated on the Y axis. On Vector3 multiplication this value is multiplied with the
	 * source Z component and added to the target X component. */
	public static final int M02 = 8;
	/** XW: Typically the translation of the X component. On Vector3 multiplication this value is added to the target X component. */
	public static final int M03 = 12;
	/** YX: Typically the sine of the angle when rotated on the Z axis. On Vector3 multiplication this value is multiplied with the
	 * source X component and added to the target Y component. */
	public static final int M10 = 1;
	/** YY: Typically the unrotated Y component for scaling, also the cosine of the angle when rotated on the X and/or Z axis. On
	 * Vector3 multiplication this value is multiplied with the source Y component and added to the target Y component. */
	public static final int M11 = 5;
	/** YZ: Typically the negative sine of the angle when rotated on the X axis. On Vector3 multiplication this value is multiplied
	 * with the source Z component and added to the target Y component. */
	public static final int M12 = 9;
	/** YW: Typically the translation of the Y component. On Vector3 multiplication this value is added to the target Y component. */
	public static final int M13 = 13;
	/** ZX: Typically the negative sine of the angle when rotated on the Y axis. On Vector3 multiplication this value is multiplied
	 * with the source X component and added to the target Z component. */
	public static final int M20 = 2;
	/** ZY: Typical the sine of the angle when rotated on the X axis. On Vector3 multiplication this value is multiplied with the
	 * source Y component and added to the target Z component. */
	public static final int M21 = 6;
	/** ZZ: Typically the unrotated Z component for scaling, also the cosine of the angle when rotated on the X and/or Y axis. On
	 * Vector3 multiplication this value is multiplied with the source Z component and added to the target Z component. */
	public static final int M22 = 10;
	/** ZW: Typically the translation of the Z component. On Vector3 multiplication this value is added to the target Z component. */
	public static final int M23 = 14;
	/** WX: Typically the value zero. On Vector3 multiplication this value is ignored. */
	public static final int M30 = 3;
	/** WY: Typically the value zero. On Vector3 multiplication this value is ignored. */
	public static final int M31 = 7;
	/** WZ: Typically the value zero. On Vector3 multiplication this value is ignored. */
	public static final int M32 = 11;
	/** WW: Typically the value one. On Vector3 multiplication this value is ignored. */
	public static final int M33 = 15;
	public float[] m;
	public static Matrix4 aMatrix4_3998 = new Matrix4();

	public Matrix4() {
		m = new float[16];
		setIdentity();
	}

	public Matrix4(Matrix4 matrix) {
		m = new float[16];
		set(matrix);
	}

	public void setIdentity() {
		m[M00] = 1.0F;
		m[M10] = 0.0F;
		m[M20] = 0.0F;
		m[M30] = 0.0F;
		m[M01] = 0.0F;
		m[M11] = 1.0F;
		m[M21] = 0.0F;
		m[M31] = 0.0F;
		m[M02] = 0.0F;
		m[M12] = 0.0F;
		m[M22] = 1.0F;
		m[M32] = 0.0F;
		m[M03] = 0.0F;
		m[M13] = 0.0F;
		m[M23] = 0.0F;
		m[M33] = 1.0F;
	}

	public void inverse() {
		float inverseDeterminant = 1.0F / determinant();
		float f_1_ = (m[M11] * m[M22] * m[M33] - m[M11] * m[M32] * m[M23] - m[M21] * m[M12] * m[M33] + m[M21] * m[M32] * m[M13] + m[M31] * m[M12] * m[M23] - m[M31] * m[M22] * m[M13]) * inverseDeterminant;
		float f_2_ = (-m[M10] * m[M22] * m[M33] + m[M10] * m[M32] * m[M23] + m[M20] * m[M12] * m[M33] - m[M20] * m[M32] * m[M13] - m[M30] * m[M12] * m[M23] + m[M30] * m[M22] * m[M13]) * inverseDeterminant;
		float f_3_ = (m[M10] * m[M21] * m[M33] - m[M10] * m[M31] * m[M23] - m[M20] * m[M11] * m[M33] + m[M20] * m[M31] * m[M13] + m[M30] * m[M11] * m[M23] - m[M30] * m[M21] * m[M13]) * inverseDeterminant;
		float f_4_ = (-m[M10] * m[M21] * m[M32] + m[M10] * m[M31] * m[M22] + m[M20] * m[M11] * m[M32] - m[M20] * m[M31] * m[M12] - m[M30] * m[M11] * m[M22] + m[M30] * m[M21] * m[M12]) * inverseDeterminant;
		float f_5_ = (-m[M01] * m[M22] * m[M33] + m[M01] * m[M32] * m[M23] + m[M21] * m[M02] * m[M33] - m[M21] * m[M32] * m[M03] - m[M31] * m[M02] * m[M23] + m[M31] * m[M22] * m[M03]) * inverseDeterminant;
		float f_6_ = (m[M00] * m[M22] * m[M33] - m[M00] * m[M32] * m[M23] - m[M20] * m[M02] * m[M33] + m[M20] * m[M32] * m[M03] + m[M30] * m[M02] * m[M23] - m[M30] * m[M22] * m[M03]) * inverseDeterminant;
		float f_7_ = (-m[M00] * m[M21] * m[M33] + m[M00] * m[M31] * m[M23] + m[M20] * m[M01] * m[M33] - m[M20] * m[M31] * m[M03] - m[M30] * m[M01] * m[M23] + m[M30] * m[M21] * m[M03]) * inverseDeterminant;
		float f_8_ = (m[M00] * m[M21] * m[M32] - m[M00] * m[M31] * m[M22] - m[M20] * m[M01] * m[M32] + m[M20] * m[M31] * m[M02] + m[M30] * m[M01] * m[M22] - m[M30] * m[M21] * m[M02]) * inverseDeterminant;
		float f_9_ = (m[M01] * m[M12] * m[M33] - m[M01] * m[M32] * m[M13] - m[M11] * m[M02] * m[M33] + m[M11] * m[M32] * m[M03] + m[M31] * m[M02] * m[M13] - m[M31] * m[M12] * m[M03]) * inverseDeterminant;
		float f_10_ = (-m[M00] * m[M12] * m[M33] + m[M00] * m[M32] * m[M13] + m[M10] * m[M02] * m[M33] - m[M10] * m[M32] * m[M03] - m[M30] * m[M02] * m[M13] + m[M30] * m[M12] * m[M03]) * inverseDeterminant;
		float f_11_ = (m[M00] * m[M11] * m[M33] - m[M00] * m[M31] * m[M13] - m[M10] * m[M01] * m[M33] + m[M10] * m[M31] * m[M03] + m[M30] * m[M01] * m[M13] - m[M30] * m[M11] * m[M03]) * inverseDeterminant;
		float f_12_ = (-m[M00] * m[M11] * m[M32] + m[M00] * m[M31] * m[M12] + m[M10] * m[M01] * m[M32] - m[M10] * m[M31] * m[M02] - m[M30] * m[M01] * m[M12] + m[M30] * m[M11] * m[M02]) * inverseDeterminant;
		float f_13_ = (-m[M01] * m[M12] * m[M23] + m[M01] * m[M22] * m[M13] + m[M11] * m[M02] * m[M23] - m[M11] * m[M22] * m[M03] - m[M21] * m[M02] * m[M13] + m[M21] * m[M12] * m[M03]) * inverseDeterminant;
		float f_14_ = (m[M00] * m[M12] * m[M23] - m[M00] * m[M22] * m[M13] - m[M10] * m[M02] * m[M23] + m[M10] * m[M22] * m[M03] + m[M20] * m[M02] * m[M13] - m[M20] * m[M12] * m[M03]) * inverseDeterminant;
		float f_15_ = (-m[M00] * m[M11] * m[M23] + m[M00] * m[M21] * m[M13] + m[M10] * m[M01] * m[M23] - m[M10] * m[M21] * m[M03] - m[M20] * m[M01] * m[M13] + m[M20] * m[M11] * m[M03]) * inverseDeterminant;
		float f_16_ = (m[M00] * m[M11] * m[M22] - m[M00] * m[M21] * m[M12] - m[M10] * m[M01] * m[M22] + m[M10] * m[M21] * m[M02] + m[M20] * m[M01] * m[M12] - m[M20] * m[M11] * m[M02]) * inverseDeterminant;
		m[M00] = f_1_;
		m[M10] = f_2_;
		m[M20] = f_3_;
		m[M30] = f_4_;
		m[M01] = f_5_;
		m[M11] = f_6_;
		m[M21] = f_7_;
		m[M31] = f_8_;
		m[M02] = f_9_;
		m[M12] = f_10_;
		m[M22] = f_11_;
		m[M32] = f_12_;
		m[M03] = f_13_;
		m[M13] = f_14_;
		m[M23] = f_15_;
		m[M33] = f_16_;
	}

	public boolean method5643() {
		if (m[M00] == 1.0F && m[M10] == 0.0F && m[M20] == 0.0F && m[M30] == 0.0F && m[M01] == 0.0F && m[M11] == 1.0F && m[M21] == 0.0F && m[M31] == 0.0F && m[M02] == 0.0F && m[M12] == 0.0F && m[M22] == 1.0F && m[M32] == 0.0F && m[M03] == 0.0F && m[M13] == 0.0F && m[M23] == 0.0F && m[M33] == 1.0F)
			return true;
		return false;
	}

	public float method5653() {
		return -(m[M33] + m[M23]) / (m[M32] + m[M22]);
	}

	public float method5654() {
		return (m[M23] - m[M33]) / (m[M32] - m[M22]);
	}

	public void method5661() {
		float f = m[M00];
		float f_17_ = m[M01];
		float f_18_ = m[M02];
		float f_19_ = m[M03];
		float f_20_ = m[M10];
		float f_21_ = m[M11];
		float f_22_ = m[M12];
		float f_23_ = m[M13];
		float f_24_ = m[M20];
		float f_25_ = m[M21];
		float f_26_ = m[M22];
		float f_27_ = m[M23];
		float f_28_ = m[M30];
		float f_29_ = m[M31];
		float f_30_ = m[M32];
		float f_31_ = m[M33];
		m[M00] = f;
		m[M10] = f_17_;
		m[M20] = f_18_;
		m[M30] = f_19_;
		m[M01] = f_20_;
		m[M11] = f_21_;
		m[M21] = f_22_;
		m[M31] = f_23_;
		m[M02] = f_24_;
		m[M12] = f_25_;
		m[M22] = f_26_;
		m[M32] = f_27_;
		m[M03] = f_28_;
		m[M13] = f_29_;
		m[M23] = f_30_;
		m[M33] = f_31_;
	}

	@Override
	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			for (int i_32_ = 0; i_32_ < 4; i_32_++) {
				if (i_32_ > 0)
					stringbuffer.append("\t");
				stringbuffer.append(m[i * 4 + i_32_]);
			}
			stringbuffer.append("\n");
		}
		return stringbuffer.toString();
	}

	@Override
	public int hashCode() {
		int i = 1;
		i = i * 31 + Arrays.hashCode(m);
		return i;
	}

	public float[] method5633(float[] fs) {
		float f = m[M30] - m[M10];
		float f_33_ = m[M31] - m[M11];
		float f_34_ = m[M32] - m[M12];
		double d = Math.sqrt(f * f + f_33_ * f_33_ + f_34_ * f_34_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_33_ / d);
		fs[2] = (float) (f_34_ / d);
		fs[3] = (float) ((m[M33] - m[M13]) / d);
		return fs;
	}

	public void set(Matrix4 matrix4_35_) {
		System.arraycopy(matrix4_35_.m, 0, m, 0, 16);
	}

	public void method5638(Matrix4 matrix4_36_) {
		float f = m[M00] * matrix4_36_.m[M00] + m[M10] * matrix4_36_.m[M01] + m[M20] * matrix4_36_.m[M02] + m[M30] * matrix4_36_.m[M03];
		float f_37_ = m[M00] * matrix4_36_.m[M10] + m[M10] * matrix4_36_.m[M11] + m[M20] * matrix4_36_.m[M12] + m[M30] * matrix4_36_.m[M13];
		float f_38_ = m[M00] * matrix4_36_.m[M20] + m[M10] * matrix4_36_.m[M21] + m[M20] * matrix4_36_.m[M22] + m[M30] * matrix4_36_.m[M23];
		float f_39_ = m[M00] * matrix4_36_.m[M30] + m[M10] * matrix4_36_.m[M31] + m[M20] * matrix4_36_.m[M32] + m[M30] * matrix4_36_.m[M33];
		float f_40_ = m[M01] * matrix4_36_.m[M00] + m[M11] * matrix4_36_.m[M01] + m[M21] * matrix4_36_.m[M02] + m[M31] * matrix4_36_.m[M03];
		float f_41_ = m[M01] * matrix4_36_.m[M10] + m[M11] * matrix4_36_.m[M11] + m[M21] * matrix4_36_.m[M12] + m[M31] * matrix4_36_.m[M13];
		float f_42_ = m[M01] * matrix4_36_.m[M20] + m[M11] * matrix4_36_.m[M21] + m[M21] * matrix4_36_.m[M22] + m[M31] * matrix4_36_.m[M23];
		float f_43_ = m[M01] * matrix4_36_.m[M30] + m[M11] * matrix4_36_.m[M31] + m[M21] * matrix4_36_.m[M32] + m[M31] * matrix4_36_.m[M33];
		float f_44_ = m[M02] * matrix4_36_.m[M00] + m[M12] * matrix4_36_.m[M01] + m[M22] * matrix4_36_.m[M02] + m[M32] * matrix4_36_.m[M03];
		float f_45_ = m[M02] * matrix4_36_.m[M10] + m[M12] * matrix4_36_.m[M11] + m[M22] * matrix4_36_.m[M12] + m[M32] * matrix4_36_.m[M13];
		float f_46_ = m[M02] * matrix4_36_.m[M20] + m[M12] * matrix4_36_.m[M21] + m[M22] * matrix4_36_.m[M22] + m[M32] * matrix4_36_.m[M23];
		float f_47_ = m[M02] * matrix4_36_.m[M30] + m[M12] * matrix4_36_.m[M31] + m[M22] * matrix4_36_.m[M32] + m[M32] * matrix4_36_.m[M33];
		float f_48_ = m[M03] * matrix4_36_.m[M00] + m[M13] * matrix4_36_.m[M01] + m[M23] * matrix4_36_.m[M02] + m[M33] * matrix4_36_.m[M03];
		float f_49_ = m[M03] * matrix4_36_.m[M10] + m[M13] * matrix4_36_.m[M11] + m[M23] * matrix4_36_.m[M12] + m[M33] * matrix4_36_.m[M13];
		float f_50_ = m[M03] * matrix4_36_.m[M20] + m[M13] * matrix4_36_.m[M21] + m[M23] * matrix4_36_.m[M22] + m[M33] * matrix4_36_.m[M23];
		float f_51_ = m[M03] * matrix4_36_.m[M30] + m[M13] * matrix4_36_.m[M31] + m[M23] * matrix4_36_.m[M32] + m[M33] * matrix4_36_.m[M33];
		m[M00] = f;
		m[M10] = f_37_;
		m[M20] = f_38_;
		m[M30] = f_39_;
		m[M01] = f_40_;
		m[M11] = f_41_;
		m[M21] = f_42_;
		m[M31] = f_43_;
		m[M02] = f_44_;
		m[M12] = f_45_;
		m[M22] = f_46_;
		m[M32] = f_47_;
		m[M03] = f_48_;
		m[M13] = f_49_;
		m[M23] = f_50_;
		m[M33] = f_51_;
	}

	public void method5639(Matrix3x4 matrix3x4) {
		m[M00] = matrix3x4.m00;
		m[M10] = matrix3x4.m10;
		m[M20] = matrix3x4.m20;
		m[M30] = 0.0F;
		m[M01] = matrix3x4.m01;
		m[M11] = matrix3x4.m11;
		m[M21] = matrix3x4.m21;
		m[M31] = 0.0F;
		m[M02] = matrix3x4.m02;
		m[M12] = matrix3x4.m12;
		m[M22] = matrix3x4.m22;
		m[M32] = 0.0F;
		m[M03] = matrix3x4.m03;
		m[M13] = matrix3x4.m13;
		m[M23] = matrix3x4.m23;
		m[M33] = 1.0F;
	}

	public void method5645(float[] fs) {
		float f = fs[0];
		float f_52_ = fs[1];
		float f_53_ = fs[2];
		fs[0] = m[M00] * f + m[M01] * f_52_ + m[M02] * f_53_ + m[M03];
		fs[1] = m[M10] * f + m[M11] * f_52_ + m[M12] * f_53_ + m[M13];
		fs[2] = m[M20] * f + m[M21] * f_52_ + m[M22] * f_53_ + m[M23];
	}

	public float[] method5658(float[] fs) {
		float f = m[M30] + m[M00];
		float f_54_ = m[M31] + m[M01];
		float f_55_ = m[M32] + m[M02];
		double d = Math.sqrt(f * f + f_54_ * f_54_ + f_55_ * f_55_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_54_ / d);
		fs[2] = (float) (f_55_ / d);
		fs[3] = (float) ((m[M33] + m[M03]) / d);
		return fs;
	}

	public float[] method5659(float[] fs) {
		float f = m[M30] + m[M20];
		float f_56_ = m[M31] + m[M21];
		float f_57_ = m[M32] + m[M22];
		double d = Math.sqrt(f * f + f_56_ * f_56_ + f_57_ * f_57_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_56_ / d);
		fs[2] = (float) (f_57_ / d);
		fs[3] = (float) ((m[M33] + m[M23]) / d);
		return fs;
	}

	public float[] method5660(float[] fs) {
		float f = m[M30] - m[M20];
		float f_58_ = m[M31] - m[M21];
		float f_59_ = m[M32] - m[M22];
		double d = Math.sqrt(f * f + f_58_ * f_58_ + f_59_ * f_59_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_58_ / d);
		fs[2] = (float) (f_59_ / d);
		fs[3] = (float) ((m[M33] - m[M23]) / d);
		return fs;
	}

	public float[] method5663(float[] fs) {
		System.arraycopy(m, 0, fs, 0, 16);
		fs[3] = 0.0F;
		fs[7] = 0.0F;
		fs[11] = 0.0F;
		fs[12] = 0.0F;
		fs[13] = 0.0F;
		fs[14] = 0.0F;
		fs[15] = 1.0F;
		return fs;
	}

	public float[] method5664(float[] fs) {
		fs[0] = m[M00];
		fs[1] = m[M10];
		fs[2] = m[M20];
		fs[3] = m[M01];
		fs[4] = m[M11];
		fs[5] = m[M21];
		fs[6] = m[M02];
		fs[7] = m[M12];
		fs[8] = m[M22];
		return fs;
	}

	public float[] method5665(float[] fs) {
		fs[0] = m[M00];
		fs[1] = m[M01];
		fs[2] = m[M02];
		fs[3] = m[M03];
		fs[4] = m[M10];
		fs[5] = m[M11];
		fs[6] = m[M12];
		fs[7] = m[M13];
		fs[8] = m[M20];
		fs[9] = m[M21];
		fs[10] = m[M22];
		fs[11] = m[M23];
		fs[12] = m[M30];
		fs[13] = m[M31];
		fs[14] = m[M32];
		fs[15] = m[M33];
		return fs;
	}

	public float[] method5666(float[] fs) {
		fs[0] = m[M00];
		fs[1] = m[M10];
		fs[2] = m[M01];
		fs[3] = m[M11];
		fs[4] = m[M02];
		fs[5] = m[M12];
		fs[6] = m[M03];
		fs[7] = m[M13];
		return fs;
	}

	public float[] method5667(float[] fs) {
		fs[0] = m[M00];
		fs[1] = m[M01];
		fs[2] = m[M02];
		fs[3] = m[M03];
		fs[4] = m[M10];
		fs[5] = m[M11];
		fs[6] = m[M12];
		fs[7] = m[M13];
		return fs;
	}

	public float[] method5668(float[] fs) {
		fs[0] = m[M00];
		fs[1] = m[M10];
		fs[2] = 0.0F;
		fs[3] = 0.0F;
		fs[4] = m[M01];
		fs[5] = m[M11];
		fs[6] = 0.0F;
		fs[7] = 0.0F;
		fs[8] = m[M03];
		fs[9] = m[M13];
		fs[10] = m[M23];
		fs[11] = 0.0F;
		fs[12] = 0.0F;
		fs[13] = 0.0F;
		fs[14] = 0.0F;
		fs[15] = 1.0F;
		return fs;
	}

	public void method5669(Matrix4 matrix4_60_) {
		System.arraycopy(matrix4_60_.m, 0, m, 0, 11);
		m[M30] = 0.0F;
		m[M31] = 0.0F;
		m[M32] = 0.0F;
		m[M03] = 0.0F;
		m[M13] = 0.0F;
		m[M23] = 0.0F;
		m[M33] = 1.0F;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Matrix4))
			return false;
		Matrix4 matrix4_61_ = (Matrix4) object;
		for (int i = 0; i < 16; i++) {
			if (m[i] != matrix4_61_.m[i])
				return false;
		}
		return true;
	}

	public float[] method5689(float[] fs) {
		float f = m[M30] - m[M00];
		float f_62_ = m[M31] - m[M01];
		float f_63_ = m[M32] - m[M02];
		double d = Math.sqrt(f * f + f_62_ * f_62_ + f_63_ * f_63_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_62_ / d);
		fs[2] = (float) (f_63_ / d);
		fs[3] = (float) ((m[M33] - m[M03]) / d);
		return fs;
	}

	public float[] method5719(float[] fs) {
		float f = m[M30] + m[M10];
		float f_64_ = m[M31] + m[M11];
		float f_65_ = m[M32] + m[M12];
		double d = Math.sqrt(f * f + f_64_ * f_64_ + f_65_ * f_65_);
		fs[0] = (float) (f / d);
		fs[1] = (float) (f_64_ / d);
		fs[2] = (float) (f_65_ / d);
		fs[3] = (float) ((m[M33] + m[M13]) / d);
		return fs;
	}

	public float[] method5746(float[] fs) {
		System.arraycopy(m, 0, fs, 0, 16);
		return fs;
	}

	public void method5637(Matrix4 matrix4_66_, Matrix4 matrix4_67_) {
		float f = matrix4_66_.m[M00] * matrix4_67_.m[M00] + matrix4_66_.m[M10] * matrix4_67_.m[M01] + matrix4_66_.m[M20] * matrix4_67_.m[M02] + matrix4_66_.m[M30] * matrix4_67_.m[M03];
		float f_68_ = matrix4_66_.m[M00] * matrix4_67_.m[M10] + matrix4_66_.m[M10] * matrix4_67_.m[M11] + matrix4_66_.m[M20] * matrix4_67_.m[M12] + matrix4_66_.m[M30] * matrix4_67_.m[M13];
		float f_69_ = matrix4_66_.m[M00] * matrix4_67_.m[M20] + matrix4_66_.m[M10] * matrix4_67_.m[M21] + matrix4_66_.m[M20] * matrix4_67_.m[M22] + matrix4_66_.m[M30] * matrix4_67_.m[M23];
		float f_70_ = matrix4_66_.m[M00] * matrix4_67_.m[M30] + matrix4_66_.m[M10] * matrix4_67_.m[M31] + matrix4_66_.m[M20] * matrix4_67_.m[M32] + matrix4_66_.m[M30] * matrix4_67_.m[M33];
		float f_71_ = matrix4_66_.m[M01] * matrix4_67_.m[M00] + matrix4_66_.m[M11] * matrix4_67_.m[M01] + matrix4_66_.m[M21] * matrix4_67_.m[M02] + matrix4_66_.m[M31] * matrix4_67_.m[M03];
		float f_72_ = matrix4_66_.m[M01] * matrix4_67_.m[M10] + matrix4_66_.m[M11] * matrix4_67_.m[M11] + matrix4_66_.m[M21] * matrix4_67_.m[M12] + matrix4_66_.m[M31] * matrix4_67_.m[M13];
		float f_73_ = matrix4_66_.m[M01] * matrix4_67_.m[M20] + matrix4_66_.m[M11] * matrix4_67_.m[M21] + matrix4_66_.m[M21] * matrix4_67_.m[M22] + matrix4_66_.m[M31] * matrix4_67_.m[M23];
		float f_74_ = matrix4_66_.m[M01] * matrix4_67_.m[M30] + matrix4_66_.m[M11] * matrix4_67_.m[M31] + matrix4_66_.m[M21] * matrix4_67_.m[M32] + matrix4_66_.m[M31] * matrix4_67_.m[M33];
		float f_75_ = matrix4_66_.m[M02] * matrix4_67_.m[M00] + matrix4_66_.m[M12] * matrix4_67_.m[M01] + matrix4_66_.m[M22] * matrix4_67_.m[M02] + matrix4_66_.m[M32] * matrix4_67_.m[M03];
		float f_76_ = matrix4_66_.m[M02] * matrix4_67_.m[M10] + matrix4_66_.m[M12] * matrix4_67_.m[M11] + matrix4_66_.m[M22] * matrix4_67_.m[M12] + matrix4_66_.m[M32] * matrix4_67_.m[M13];
		float f_77_ = matrix4_66_.m[M02] * matrix4_67_.m[M20] + matrix4_66_.m[M12] * matrix4_67_.m[M21] + matrix4_66_.m[M22] * matrix4_67_.m[M22] + matrix4_66_.m[M32] * matrix4_67_.m[M23];
		float f_78_ = matrix4_66_.m[M02] * matrix4_67_.m[M30] + matrix4_66_.m[M12] * matrix4_67_.m[M31] + matrix4_66_.m[M22] * matrix4_67_.m[M32] + matrix4_66_.m[M32] * matrix4_67_.m[M33];
		float f_79_ = matrix4_66_.m[M03] * matrix4_67_.m[M00] + matrix4_66_.m[M13] * matrix4_67_.m[M01] + matrix4_66_.m[M23] * matrix4_67_.m[M02] + matrix4_66_.m[M33] * matrix4_67_.m[M03];
		float f_80_ = matrix4_66_.m[M03] * matrix4_67_.m[M10] + matrix4_66_.m[M13] * matrix4_67_.m[M11] + matrix4_66_.m[M23] * matrix4_67_.m[M12] + matrix4_66_.m[M33] * matrix4_67_.m[M13];
		float f_81_ = matrix4_66_.m[M03] * matrix4_67_.m[M20] + matrix4_66_.m[M13] * matrix4_67_.m[M21] + matrix4_66_.m[M23] * matrix4_67_.m[M22] + matrix4_66_.m[M33] * matrix4_67_.m[M23];
		float f_82_ = matrix4_66_.m[M03] * matrix4_67_.m[M30] + matrix4_66_.m[M13] * matrix4_67_.m[M31] + matrix4_66_.m[M23] * matrix4_67_.m[M32] + matrix4_66_.m[M33] * matrix4_67_.m[M33];
		m[M00] = f;
		m[M10] = f_68_;
		m[M20] = f_69_;
		m[M30] = f_70_;
		m[M01] = f_71_;
		m[M11] = f_72_;
		m[M21] = f_73_;
		m[M31] = f_74_;
		m[M02] = f_75_;
		m[M12] = f_76_;
		m[M22] = f_77_;
		m[M32] = f_78_;
		m[M03] = f_79_;
		m[M13] = f_80_;
		m[M23] = f_81_;
		m[M33] = f_82_;
	}

	public void method5649(float f, float f_83_, float f_84_) {
		setToOrtho(-10000.0F / f_84_, 10000.0F / f_84_, -10000.0F / f_84_, 10000.0F / f_84_, f, f_83_);
	}

	public void setScale(float x, float y, float z, float w) {
		m[M00] = x;
		m[M10] = 0.0F;
		m[M20] = 0.0F;
		m[M30] = 0.0F;
		m[M01] = 0.0F;
		m[M11] = y;
		m[M21] = 0.0F;
		m[M31] = 0.0F;
		m[M02] = 0.0F;
		m[M12] = 0.0F;
		m[M22] = z;
		m[M32] = 0.0F;
		m[M03] = 0.0F;
		m[M13] = 0.0F;
		m[M23] = 0.0F;
		m[M33] = w;
	}

	public void method5644(float f, float f_88_, float f_89_, float[] fs) {
		fs[0] = m[M00] * f + m[M01] * f_88_ + m[M02] * f_89_ + m[M03];
		fs[1] = m[M10] * f + m[M11] * f_88_ + m[M12] * f_89_ + m[M13];
		fs[2] = m[M20] * f + m[M21] * f_88_ + m[M22] * f_89_ + m[M23];
		if (fs.length > 3)
			fs[3] = m[M30] * f + m[M31] * f_88_ + m[M32] * f_89_ + m[M33];
	}

	public void method5646(float f, float f_90_, float f_91_, float[] fs) {
		fs[0] = m[M00] * f + m[M01] * f_90_ + m[M02] * f_91_;
		fs[1] = m[M10] * f + m[M11] * f_90_ + m[M12] * f_91_;
		fs[2] = m[M20] * f + m[M21] * f_90_ + m[M22] * f_91_;
		if (fs.length > 3)
			fs[3] = m[M30] * f + m[M31] * f_90_ + m[M32] * f_91_;
	}

	public void method5648(float f, float f_92_, float f_93_, float f_94_) {
		float f_95_ = (float) (Math.tan(f_93_ / 2.0F) * f);
		float f_96_ = (float) (Math.tan(f_94_ / 2.0F) * f);
		setToProjection(-f_95_, f_95_, -f_96_, f_96_, f, f_92_);
	}

	public void setToOrtho(float left, float right, float bottom, float top, float near, float far) {
		m[M00] = 2.0F / (right - left);
		m[M10] = 0.0F;
		m[M20] = 0.0F;
		m[M30] = 0.0F;
		m[M01] = 0.0F;
		m[M11] = 2.0F / (top - bottom);
		m[M21] = 0.0F;
		m[M31] = 0.0F;
		m[M02] = 0.0F;
		m[M12] = 0.0F;
		m[M22] = 2.0F / (far - near);
		m[M32] = 0.0F;
		m[M03] = -(right + left) / (right - left);
		m[M13] = -(top + bottom) / (top - bottom);
		m[M23] = -(far + near) / (far - near);
		m[M33] = 1.0F;
	}

	public void method5736(int i, int i_102_, int i_103_, float f, float f_104_, float f_105_) {
		if (i != 0) {
			float f_106_ = MiscTools.COS[i & 0x3fff];
			float f_107_ = MiscTools.SIN[i & 0x3fff];
			m[M00] = f_106_ * i_102_;
			m[M11] = f_106_ * i_103_;
			m[M10] = f_107_ * i_102_;
			m[M01] = -f_107_ * i_103_;
			m[M22] = 1.0F;
			float[] fs = m;
			float[] fs_108_ = m;
			float[] fs_109_ = m;
			m[M12] = 0.0F;
			fs_109_[8] = 0.0F;
			fs_108_[6] = 0.0F;
			fs[2] = 0.0F;
		}
		else {
			m[M00] = i_102_;
			m[M11] = i_103_;
			m[M22] = 1.0F;
			float[] fs = m;
			float[] fs_110_ = m;
			float[] fs_111_ = m;
			float[] fs_112_ = m;
			float[] fs_113_ = m;
			m[M12] = 0.0F;
			fs_113_[8] = 0.0F;
			fs_112_[6] = 0.0F;
			fs_111_[4] = 0.0F;
			fs_110_[2] = 0.0F;
			fs[1] = 0.0F;
		}
		float[] fs = m;
		float[] fs_114_ = m;
		m[M32] = 0.0F;
		fs_114_[7] = 0.0F;
		fs[3] = 0.0F;
		m[M33] = 1.0F;
		m[M03] = f;
		m[M13] = f_104_;
		m[M23] = f_105_;
	}

	public void method5651(float f, float f_115_, float f_116_, float f_117_, float f_118_, float f_119_, float f_120_, float f_121_) {
		m[M00] = f_116_ * 2.0F / f_120_;
		m[M10] = 0.0F;
		m[M20] = 0.0F;
		m[M30] = 0.0F;
		m[M01] = 0.0F;
		m[M11] = f_117_ * 2.0F / f_121_;
		m[M21] = 0.0F;
		m[M31] = 0.0F;
		m[M02] = f * 2.0F / f_120_ - 1.0F;
		m[M12] = f_115_ * 2.0F / f_121_ - 1.0F;
		m[M22] = (f_119_ + f_118_) / (f_119_ - f_118_);
		m[M32] = 1.0F;
		m[M03] = 0.0F;
		m[M13] = 0.0F;
		m[M23] = f_119_ * 2.0F * f_118_ / (f_118_ - f_119_);
		m[M33] = 0.0F;
	}

	public void method5656(float f, float f_122_, float f_123_, float f_124_, float f_125_, float f_126_, float f_127_, float f_128_, float f_129_) {
		setToOrtho(-(f * f_129_) / f_123_, f_129_ * (f_127_ - f) / f_123_, -(f_122_ * f_129_) / f_124_, f_129_ * (f_128_ - f_122_) / f_124_, f_125_, f_126_);
	}

	float determinant() {
		return m[M00] * m[M11] * m[M22] * m[M33] - m[M00] * m[M11] * m[M32] * m[M23] - m[M00] * m[M21] * m[M12] * m[M33] + m[M00] * m[M21] * m[M32] * m[M13] + m[M00] * m[M31] * m[M12] * m[M23] - m[M00] * m[M31] * m[M22] * m[M13] - m[M10] * m[M01] * m[M22] * m[M33] + m[M10] * m[M01] * m[M32] * m[M23] + m[M10] * m[M21] * m[M02] * m[M33] - m[M10] * m[M21] * m[M32] * m[M03] - m[M10] * m[M31] * m[M02] * m[M23] + m[M10] * m[M31] * m[M22] * m[M03] + m[M20] * m[M01] * m[M12] * m[M33] - m[M20] * m[M01] * m[M32] * m[M13] - m[M20] * m[M11] * m[M02] * m[M33] + m[M20] * m[M11] * m[M32] * m[M03] + m[M20] * m[M31] * m[M02] * m[M13] - m[M20] * m[M31] * m[M12] * m[M03] - m[M30] * m[M01] * m[M12] * m[M23] + m[M30] * m[M01] * m[M22] * m[M13] + m[M30] * m[M11] * m[M02] * m[M23] - m[M30] * m[M11] * m[M22] * m[M03] - m[M30] * m[M21] * m[M02] * m[M13] + m[M30] * m[M21] * m[M12] * m[M03];
	}

	void setToProjection(float left, float right, float bottom, float top, float near, float far) {
		m[M00] = near * 2.0F / (right - left);
		m[M10] = 0.0F;
		m[M20] = 0.0F;
		m[M30] = 0.0F;
		m[M01] = 0.0F;
		m[M11] = near * 2.0F / (top - bottom);
		m[M21] = 0.0F;
		m[M31] = 0.0F;
		m[M02] = (right + left) / (right - left);
		m[M12] = (top + bottom) / (top - bottom);
		m[M22] = (far + near) / (far - near);
		m[M32] = 1.0F;
		m[M03] = 0.0F;
		m[M13] = 0.0F;
		m[M23] = -(far * 2.0F * near) / (far - near);
		m[M33] = 0.0F;
	}
}

