package com.jagex.maths;

import net.openrs.util.MiscTools;

public class Matrix3x4 {
	float m22;
	float m10;
	float m12;
	float m01;
	float m23;
	float m21;
	float m02;
	float m11;
	float m00;
	float m03;
	float m13;
	float m20;
	public static Matrix3x4 aMatrix3x4_2417 = new Matrix3x4();

	public Matrix3x4() {
		method4164();
	}

	public Matrix3x4(Matrix3x4 matrix3x4_0_) {
		copyFrom(matrix3x4_0_);
	}

	public void method4110() {
		float f = m03;
		float f_1_ = m13;
		float f_2_ = m01;
		m01 = m10;
		m10 = f_2_;
		float f_3_ = m02;
		m02 = m20;
		m20 = f_3_;
		float f_4_ = m12;
		m12 = m21;
		m21 = f_4_;
		m03 = -(f * m00 + f_1_ * m01 + m23 * m02);
		m13 = -(f * m10 + f_1_ * m11 + m23 * m12);
		m23 = -(f * m20 + f_1_ * m21 + m23 * m22);
	}

	public String toString() {
		return m00 + "," + m01 + "," + m02 + "," + m03 + " - " + m10 + "," + m11 + "," + m12 + "," + m13 + " - " + m20 + "," + m21 + "," + m22 + "," + m23;
	}

	public void method4164() {
		m23 = 0.0F;
		m13 = 0.0F;
		m03 = 0.0F;
		m12 = 0.0F;
		m02 = 0.0F;
		m21 = 0.0F;
		m01 = 0.0F;
		m20 = 0.0F;
		m10 = 0.0F;
		m22 = 1.0F;
		m11 = 1.0F;
		m00 = 1.0F;
	}

	public void copyFrom(Matrix3x4 matrix3x4_5_) {
		m00 = matrix3x4_5_.m00;
		m01 = matrix3x4_5_.m01;
		m02 = matrix3x4_5_.m02;
		m03 = matrix3x4_5_.m03;
		m10 = matrix3x4_5_.m10;
		m11 = matrix3x4_5_.m11;
		m12 = matrix3x4_5_.m12;
		m13 = matrix3x4_5_.m13;
		m20 = matrix3x4_5_.m20;
		m21 = matrix3x4_5_.m21;
		m22 = matrix3x4_5_.m22;
		m23 = matrix3x4_5_.m23;
	}

	public void method4106(Matrix3x4 matrix3x4_6_) {
		if (matrix3x4_6_ == this)
			method4110();
		else {
			m00 = matrix3x4_6_.m00;
			m01 = matrix3x4_6_.m10;
			m02 = matrix3x4_6_.m20;
			m10 = matrix3x4_6_.m01;
			m11 = matrix3x4_6_.m11;
			m12 = matrix3x4_6_.m21;
			m20 = matrix3x4_6_.m02;
			m21 = matrix3x4_6_.m12;
			m22 = matrix3x4_6_.m22;
			m03 = -(matrix3x4_6_.m03 * m00 + matrix3x4_6_.m13 * m01 + matrix3x4_6_.m23 * m02);
			m13 = -(matrix3x4_6_.m03 * m10 + matrix3x4_6_.m13 * m11 + matrix3x4_6_.m23 * m12);
			m23 = -(matrix3x4_6_.m03 * m20 + matrix3x4_6_.m13 * m21 + matrix3x4_6_.m23 * m22);
		}
	}

	public void translate(Vector3f vector3f) {
		translate(vector3f.x, vector3f.y, vector3f.z);
	}

	public void method4116(RotTrans rotTrans) {
		method4135(rotTrans.rot);
		translate(rotTrans.trans);
	}

	public void method4121(float[] fs) {
		float f = fs[0] - m03;
		float f_7_ = fs[1] - m13;
		float f_8_ = fs[2] - m23;
		fs[0] = (int) (m00 * f + m10 * f_7_ + m20 * f_8_);
		fs[1] = (int) (m01 * f + m11 * f_7_ + m21 * f_8_);
		fs[2] = (int) (m02 * f + m12 * f_7_ + m22 * f_8_);
	}

	public void method4122(float[] fs) {
		float f = fs[0];
		float f_9_ = fs[1];
		float f_10_ = fs[2];
		fs[0] = m00 * f + m10 * f_9_ + m20 * f_10_;
		fs[1] = m01 * f + m11 * f_9_ + m21 * f_10_;
		fs[2] = m02 * f + m12 * f_9_ + m22 * f_10_;
	}

	public float[] method4123(float[] fs) {
		fs[0] = m03;
		fs[1] = m13;
		fs[2] = m23;
		return fs;
	}

	public void method4133(RotTrans rotTrans) {
		float f = rotTrans.rot.scalar * rotTrans.rot.scalar;
		float f_11_ = rotTrans.rot.scalar * rotTrans.rot.i;
		float f_12_ = rotTrans.rot.scalar * rotTrans.rot.j;
		float f_13_ = rotTrans.rot.scalar * rotTrans.rot.k;
		float f_14_ = rotTrans.rot.i * rotTrans.rot.i;
		float f_15_ = rotTrans.rot.i * rotTrans.rot.j;
		float f_16_ = rotTrans.rot.i * rotTrans.rot.k;
		float f_17_ = rotTrans.rot.j * rotTrans.rot.j;
		float f_18_ = rotTrans.rot.j * rotTrans.rot.k;
		float f_19_ = rotTrans.rot.k * rotTrans.rot.k;
		m00 = f_14_ + f - f_19_ - f_17_;
		m10 = f_15_ + f_13_ + f_15_ + f_13_;
		m20 = f_16_ - f_12_ - f_12_ + f_16_;
		m01 = f_15_ - f_13_ - f_13_ + f_15_;
		m11 = f_17_ + f - f_14_ - f_19_;
		m21 = f_18_ + f_11_ + f_18_ + f_11_;
		m02 = f_16_ + f_12_ + f_16_ + f_12_;
		m12 = f_18_ - f_11_ - f_11_ + f_18_;
		m22 = f_19_ + f - f_17_ - f_14_;
		m03 = rotTrans.trans.x;
		m13 = rotTrans.trans.y;
		m23 = rotTrans.trans.z;
	}

	public void method4135(Quaternion quaternion) {
		method4117(quaternion.i, quaternion.j, quaternion.k, quaternion.scalar);
	}

	public void method4141(Matrix3x4 matrix3x4_20_) {
		float f = m00;
		float f_21_ = m10;
		float f_22_ = m01;
		float f_23_ = m11;
		float f_24_ = m02;
		float f_25_ = m12;
		float f_26_ = m03;
		float f_27_ = m13;
		float f_28_ = m20;
		float f_29_ = m21;
		float f_30_ = m22;
		float f_31_ = m23;
		m00 = f * matrix3x4_20_.m00 + f_21_ * matrix3x4_20_.m01 + f_28_ * matrix3x4_20_.m02;
		m10 = f * matrix3x4_20_.m10 + f_21_ * matrix3x4_20_.m11 + f_28_ * matrix3x4_20_.m12;
		m20 = f * matrix3x4_20_.m20 + f_21_ * matrix3x4_20_.m21 + f_28_ * matrix3x4_20_.m22;
		m01 = f_22_ * matrix3x4_20_.m00 + f_23_ * matrix3x4_20_.m01 + f_29_ * matrix3x4_20_.m02;
		m11 = f_22_ * matrix3x4_20_.m10 + f_23_ * matrix3x4_20_.m11 + f_29_ * matrix3x4_20_.m12;
		m21 = f_22_ * matrix3x4_20_.m20 + f_23_ * matrix3x4_20_.m21 + f_29_ * matrix3x4_20_.m22;
		m02 = f_24_ * matrix3x4_20_.m00 + f_25_ * matrix3x4_20_.m01 + f_30_ * matrix3x4_20_.m02;
		m12 = f_24_ * matrix3x4_20_.m10 + f_25_ * matrix3x4_20_.m11 + f_30_ * matrix3x4_20_.m12;
		m22 = f_24_ * matrix3x4_20_.m20 + f_25_ * matrix3x4_20_.m21 + f_30_ * matrix3x4_20_.m22;
		m03 = f_26_ * matrix3x4_20_.m00 + f_27_ * matrix3x4_20_.m01 + f_31_ * matrix3x4_20_.m02 + matrix3x4_20_.m03;
		m13 = f_26_ * matrix3x4_20_.m10 + f_27_ * matrix3x4_20_.m11 + f_31_ * matrix3x4_20_.m12 + matrix3x4_20_.m13;
		m23 = f_26_ * matrix3x4_20_.m20 + f_27_ * matrix3x4_20_.m21 + f_31_ * matrix3x4_20_.m22 + matrix3x4_20_.m23;
	}

	public void method4118(Matrix3x4 matrix3x4_32_, Matrix3x4 matrix3x4_33_) {
		m00 = matrix3x4_32_.m00 * matrix3x4_33_.m00 + matrix3x4_32_.m10 * matrix3x4_33_.m01 + matrix3x4_32_.m20 * matrix3x4_33_.m02;
		m10 = matrix3x4_32_.m00 * matrix3x4_33_.m10 + matrix3x4_32_.m10 * matrix3x4_33_.m11 + matrix3x4_32_.m20 * matrix3x4_33_.m12;
		m20 = matrix3x4_32_.m00 * matrix3x4_33_.m20 + matrix3x4_32_.m10 * matrix3x4_33_.m21 + matrix3x4_32_.m20 * matrix3x4_33_.m22;
		m01 = matrix3x4_32_.m01 * matrix3x4_33_.m00 + matrix3x4_32_.m11 * matrix3x4_33_.m01 + matrix3x4_32_.m21 * matrix3x4_33_.m02;
		m11 = matrix3x4_32_.m01 * matrix3x4_33_.m10 + matrix3x4_32_.m11 * matrix3x4_33_.m11 + matrix3x4_32_.m21 * matrix3x4_33_.m12;
		m21 = matrix3x4_32_.m01 * matrix3x4_33_.m20 + matrix3x4_32_.m11 * matrix3x4_33_.m21 + matrix3x4_32_.m21 * matrix3x4_33_.m22;
		m02 = matrix3x4_32_.m02 * matrix3x4_33_.m00 + matrix3x4_32_.m12 * matrix3x4_33_.m01 + matrix3x4_32_.m22 * matrix3x4_33_.m02;
		m12 = matrix3x4_32_.m02 * matrix3x4_33_.m10 + matrix3x4_32_.m12 * matrix3x4_33_.m11 + matrix3x4_32_.m22 * matrix3x4_33_.m12;
		m22 = matrix3x4_32_.m02 * matrix3x4_33_.m20 + matrix3x4_32_.m12 * matrix3x4_33_.m21 + matrix3x4_32_.m22 * matrix3x4_33_.m22;
		m03 = matrix3x4_32_.m03 * matrix3x4_33_.m00 + matrix3x4_32_.m13 * matrix3x4_33_.m01 + matrix3x4_32_.m23 * matrix3x4_33_.m02 + matrix3x4_33_.m03;
		m13 = matrix3x4_32_.m03 * matrix3x4_33_.m10 + matrix3x4_32_.m13 * matrix3x4_33_.m11 + matrix3x4_32_.m23 * matrix3x4_33_.m12 + matrix3x4_33_.m13;
		m23 = matrix3x4_32_.m03 * matrix3x4_33_.m20 + matrix3x4_32_.m13 * matrix3x4_33_.m21 + matrix3x4_32_.m23 * matrix3x4_33_.m22 + matrix3x4_33_.m23;
	}

	public void method4112(float f, float f_34_, float f_35_) {
		m00 = f;
		m01 = 0.0F;
		m02 = 0.0F;
		m03 = 0.0F;
		m10 = 0.0F;
		m11 = f_34_;
		m12 = 0.0F;
		m13 = 0.0F;
		m20 = 0.0F;
		m21 = 0.0F;
		m22 = f_35_;
		m23 = 0.0F;
	}

	public void method4113(float f, float f_36_, float f_37_) {
		m00 *= f;
		m01 *= f;
		m02 *= f;
		m03 *= f;
		m10 *= f_36_;
		m11 *= f_36_;
		m12 *= f_36_;
		m13 *= f_36_;
		m20 *= f_37_;
		m21 *= f_37_;
		m22 *= f_37_;
		m23 *= f_37_;
	}

	public void method4114(float f, float f_38_, float f_39_) {
		m12 = 0.0F;
		m02 = 0.0F;
		m21 = 0.0F;
		m01 = 0.0F;
		m20 = 0.0F;
		m10 = 0.0F;
		m22 = 1.0F;
		m11 = 1.0F;
		m00 = 1.0F;
		m03 = f;
		m13 = f_38_;
		m23 = f_39_;
	}

	public void translate(float f, float f_40_, float f_41_) {
		m03 += f;
		m13 += f_40_;
		m23 += f_41_;
	}

	public void method4107(float f, float f_42_, float f_43_, float f_44_) {
		float f_45_ = (float) Math.cos(f_44_);
		float f_46_ = (float) Math.sin(f_44_);
		m00 = f_45_ + f * f * (1.0F - f_45_);
		m10 = f_43_ * f_46_ + f_42_ * f * (1.0F - f_45_);
		m20 = -f_42_ * f_46_ + f_43_ * f * (1.0F - f_45_);
		m01 = -f_43_ * f_46_ + f * f_42_ * (1.0F - f_45_);
		m11 = f_45_ + f_42_ * f_42_ * (1.0F - f_45_);
		m21 = f * f_46_ + f_43_ * f_42_ * (1.0F - f_45_);
		m02 = f_42_ * f_46_ + f * f_43_ * (1.0F - f_45_);
		m12 = -f * f_46_ + f_42_ * f_43_ * (1.0F - f_45_);
		m22 = f_45_ + f_43_ * f_43_ * (1.0F - f_45_);
		m23 = 0.0F;
		m13 = 0.0F;
		m03 = 0.0F;
	}

	public void method4111(float f, float f_47_, float f_48_, float f_49_) {
		float f_50_ = (float) Math.cos(f_49_);
		float f_51_ = (float) Math.sin(f_49_);
		float f_52_ = f_50_ + f * f * (1.0F - f_50_);
		float f_53_ = f_48_ * f_51_ + f_47_ * f * (1.0F - f_50_);
		float f_54_ = -f_47_ * f_51_ + f_48_ * f * (1.0F - f_50_);
		float f_55_ = -f_48_ * f_51_ + f * f_47_ * (1.0F - f_50_);
		float f_56_ = f_50_ + f_47_ * f_47_ * (1.0F - f_50_);
		float f_57_ = f * f_51_ + f_48_ * f_47_ * (1.0F - f_50_);
		float f_58_ = f_47_ * f_51_ + f * f_48_ * (1.0F - f_50_);
		float f_59_ = -f * f_51_ + f_47_ * f_48_ * (1.0F - f_50_);
		float f_60_ = f_50_ + f_48_ * f_48_ * (1.0F - f_50_);
		float f_61_ = m00;
		float f_62_ = m10;
		float f_63_ = m01;
		float f_64_ = m11;
		float f_65_ = m02;
		float f_66_ = m12;
		float f_67_ = m03;
		float f_68_ = m13;
		m00 = f_61_ * f_52_ + f_62_ * f_55_ + m20 * f_58_;
		m10 = f_61_ * f_53_ + f_62_ * f_56_ + m20 * f_59_;
		m20 = f_61_ * f_54_ + f_62_ * f_57_ + m20 * f_60_;
		m01 = f_63_ * f_52_ + f_64_ * f_55_ + m21 * f_58_;
		m11 = f_63_ * f_53_ + f_64_ * f_56_ + m21 * f_59_;
		m21 = f_63_ * f_54_ + f_64_ * f_57_ + m21 * f_60_;
		m02 = f_65_ * f_52_ + f_66_ * f_55_ + m22 * f_58_;
		m12 = f_65_ * f_53_ + f_66_ * f_56_ + m22 * f_59_;
		m22 = f_65_ * f_54_ + f_66_ * f_57_ + m22 * f_60_;
		m03 = f_67_ * f_52_ + f_68_ * f_55_ + m23 * f_58_;
		m13 = f_67_ * f_53_ + f_68_ * f_56_ + m23 * f_59_;
		m23 = f_67_ * f_54_ + f_68_ * f_57_ + m23 * f_60_;
	}

	public void method4119(float f, float f_69_, float f_70_, float[] fs) {
		fs[0] = m00 * f + m01 * f_69_ + m02 * f_70_ + m03;
		fs[1] = m10 * f + m11 * f_69_ + m12 * f_70_ + m13;
		fs[2] = m20 * f + m21 * f_69_ + m22 * f_70_ + m23;
	}

	public void method4120(float f, float f_71_, float f_72_, float[] fs) {
		f -= m03;
		f_71_ -= m13;
		f_72_ -= m23;
		fs[0] = (int) (m00 * f + m10 * f_71_ + m20 * f_72_);
		fs[1] = (int) (m01 * f + m11 * f_71_ + m21 * f_72_);
		fs[2] = (int) (m02 * f + m12 * f_71_ + m22 * f_72_);
	}

	public void method4105(int i, int i_73_, int i_74_, float f, float f_75_, float f_76_) {
		if (i != 0) {
			float f_77_ = MiscTools.COS[i & 0x3fff];
			float f_78_ = MiscTools.SIN[i & 0x3fff];
			m22 = 1.0F;
			m12 = 0.0F;
			m02 = 0.0F;
			m21 = 0.0F;
			m20 = 0.0F;
			m00 = f_77_ * 2.0F * i_73_;
			m11 = f_77_ * 2.0F * i_74_;
			m10 = f_78_ * 2.0F * i_73_;
			m01 = f_78_ * -2.0F * i_74_;
			m03 = i_73_ * 2 * (f_78_ * 0.5F - f_77_ * 0.5F) + f;
			m13 = i_74_ * 2 * (f_78_ * -0.5F - f_77_ * 0.5F) + f_75_;
			m23 = f_76_;
		}
		else {
			m12 = 0.0F;
			m02 = 0.0F;
			m21 = 0.0F;
			m01 = 0.0F;
			m20 = 0.0F;
			m10 = 0.0F;
			m00 = i_73_ * 2;
			m11 = i_74_ * 2;
			m22 = 1.0F;
			m03 = f - i_73_;
			m13 = f_75_ - i_74_;
			m23 = f_76_;
		}
	}

	public void method4103(double d, double d_79_, double d_80_, double d_81_, double d_82_, double d_83_, float f, float f_84_, float f_85_) {
		float f_86_ = (float) (d_81_ - d);
		float f_87_ = (float) (d_82_ - d_79_);
		float f_88_ = (float) (d_83_ - d_80_);
		float f_89_ = f_84_ * f_88_ - f_85_ * f_87_;
		float f_90_ = f_85_ * f_86_ - f * f_88_;
		float f_91_ = f * f_87_ - f_84_ * f_86_;
		float f_92_ = (float) (1.0 / Math.sqrt(f_89_ * f_89_ + f_90_ * f_90_ + f_91_ * f_91_));
		float f_93_ = (float) (1.0 / Math.sqrt(f_86_ * f_86_ + f_87_ * f_87_ + f_88_ * f_88_));
		m00 = f_89_ * f_92_;
		m01 = f_90_ * f_92_;
		m02 = f_91_ * f_92_;
		m20 = f_86_ * f_93_;
		m21 = f_87_ * f_93_;
		m22 = f_88_ * f_93_;
		m10 = m21 * m02 - m22 * m01;
		m11 = m22 * m00 - m20 * m02;
		m12 = m20 * m01 - m21 * m00;
		m03 = -(float) (d * m00 + d_79_ * m01 + d_80_ * m02);
		m13 = -(float) (d * m10 + d_79_ * m11 + d_80_ * m12);
		m23 = -(float) (d * m20 + d_79_ * m21 + d_80_ * m22);
	}

	public void method4104(float f, float f_94_, float f_95_, float f_96_, float f_97_, float f_98_, float f_99_, float f_100_, float f_101_) {
		m00 = f;
		m01 = f_96_;
		m02 = f_99_;
		m03 = 0.0F;
		m10 = f_94_;
		m11 = f_97_;
		m12 = f_100_;
		m13 = 0.0F;
		m20 = f_95_;
		m21 = f_98_;
		m22 = f_101_;
		m23 = 0.0F;
	}

	void method4117(float f, float f_102_, float f_103_, float f_104_) {
		float f_105_ = f * f;
		float f_106_ = f * f_102_;
		float f_107_ = f * f_103_;
		float f_108_ = f * f_104_;
		float f_109_ = f_102_ * f_102_;
		float f_110_ = f_102_ * f_103_;
		float f_111_ = f_102_ * f_104_;
		float f_112_ = f_103_ * f_103_;
		float f_113_ = f_103_ * f_104_;
		m00 = 1.0F - (f_109_ + f_112_) * 2.0F;
		m01 = (f_106_ - f_113_) * 2.0F;
		m02 = (f_107_ + f_111_) * 2.0F;
		m10 = (f_106_ + f_113_) * 2.0F;
		m11 = 1.0F - (f_105_ + f_112_) * 2.0F;
		m12 = (f_110_ - f_108_) * 2.0F;
		m20 = (f_107_ - f_111_) * 2.0F;
		m21 = (f_110_ + f_108_) * 2.0F;
		m22 = 1.0F - (f_105_ + f_109_) * 2.0F;
		m23 = 0.0F;
		m13 = 0.0F;
		m03 = 0.0F;
	}
}

