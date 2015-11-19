package com.jagex.maths;

public final class ScaleRotTrans {

	public static final ScaleRotTrans g = new ScaleRotTrans();
	public float scale;
	public Quaternion rot;
	public Vector3f trans;

	public ScaleRotTrans(RotTrans var1, float var2) {
		this.rot = new Quaternion();
		this.trans = new Vector3f();
		this.scale = 1.0F;
		q(this, var1, var2);
	}

	public static void d(ScaleRotTrans var0, RotTrans var1, boolean var2) {
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
		if (var2) {
			var0.scale = 1.0F;
		}

	}

	public ScaleRotTrans(RotTrans var1) {
		this.rot = new Quaternion();
		this.trans = new Vector3f();
		this.scale = 1.0F;
		d(this, var1, true);
	}

	public static void j(ScaleRotTrans var0, ScaleRotTrans var1) {
		var0.scale = var1.scale;
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
	}

	public ScaleRotTrans(ScaleRotTrans var1) {
		this.rot = new Quaternion();
		this.trans = new Vector3f();
		this.scale = 1.0F;
		g(this, var1);
	}

	public static void g(ScaleRotTrans var0, ScaleRotTrans var1) {
		var0.scale = var1.scale;
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
	}

	public ScaleRotTrans(float var1, Quaternion var2, Vector3f var3) {
		this.scale = var1;
		this.rot = new Quaternion(var2);
		this.trans = new Vector3f(var3);
	}

	public static void q(ScaleRotTrans var0, RotTrans var1, float var2) {
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
		var0.scale = var2;
	}

	@Override
	public String toString() {
		return String
				.format("{ ScaleRotTrans: %.3f | rot=%.3f,%.3f,%.3f,%.3f | trans=%.3f,%.3f,%.3f }",
						new Object[] { Float.valueOf(this.scale),
								Float.valueOf(this.rot.i),
								Float.valueOf(this.rot.j),
								Float.valueOf(this.rot.k),
								Float.valueOf(this.rot.scalar),
								Float.valueOf(this.trans.x),
								Float.valueOf(this.trans.y),
								Float.valueOf(this.trans.z) });
	}

	public ScaleRotTrans() {
		this.scale = 1.0F;
		this.rot = new Quaternion();
		this.trans = new Vector3f();
	}

	public static void e(ScaleRotTrans var0, RotTrans var1, boolean var2) {
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
		if (var2) {
			var0.scale = 1.0F;
		}

	}

	public static void h(ScaleRotTrans var0, RotTrans var1, boolean var2) {
		var0.rot.set(var1.rot);
		var0.trans.set(var1.trans);
		if (var2) {
			var0.scale = 1.0F;
		}

	}
}
