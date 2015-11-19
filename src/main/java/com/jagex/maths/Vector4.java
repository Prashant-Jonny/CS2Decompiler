package com.jagex.maths;

import net.openrs.io.WrappedByteBuffer;


public final class Vector4 {

   public final float y;
   public final float w;
   public final float z;
   public final float x;
   public static final Vector4 g = new Vector4(0.0F, 0.0F, 0.0F, 0.0F);


   public String toString() {
      return this.w + "," + this.x + "," + this.y + "," + this.z;
   }

   public Vector4(WrappedByteBuffer var1) {
      this.w = var1.getFloat();
      this.x = var1.getFloat();
      this.y = var1.getFloat();
      this.z = var1.getFloat();
   }

   public static void g(Vector4 var0, WrappedByteBuffer var1) {
      var1.putFloat(var0.w);
      var1.putFloat(var0.x);
      var1.putFloat(var0.y);
      var1.putFloat(var0.z);
   }

   public static Vector4 d(Vector4 var0) {
      return var0;
   }

   public Vector4(float var1, float var2, float var3, float var4) {
      this.w = var1;
      this.x = var2;
      this.y = var3;
      this.z = var4;
   }
}
