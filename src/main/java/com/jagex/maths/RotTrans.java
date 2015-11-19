package com.jagex.maths;

public final class RotTrans {
	public Quaternion rot;
	public Vector3f trans;

	public RotTrans() {
		rot = new Quaternion();
		trans = new Vector3f();
	}

	public RotTrans(RotTrans rotTrans_0_) {
		rot = new Quaternion();
		trans = new Vector3f();
		set(rotTrans_0_);
	}

	@Override
	public String toString() {
		return String.format("{ ScaleRotTrans: rot=%.3f,%.3f,%.3f,%.3f | trans=%.3f,%.3f,%.3f }", new Object[]{Float.valueOf(this.rot.i), Float.valueOf(this.rot.j), Float.valueOf(this.rot.k), Float.valueOf(this.rot.scalar), Float.valueOf(this.trans.x), Float.valueOf(this.trans.y), Float.valueOf(this.trans.z)});
	}

	public final void method5627() {
		rot.conjugate();
		trans.method4243();
		trans.method4297(rot);
	}

	public void set(RotTrans rotTrans) {
		rot.set(rotTrans.rot);
		trans.set(rotTrans.trans);
	}

	public final void method5626(RotTrans rotTrans) {
		rot.multiply(rotTrans.rot);
		trans.method4297(rotTrans.rot);
		trans.method4245(rotTrans.trans);
	}
}

