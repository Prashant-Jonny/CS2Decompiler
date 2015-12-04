package com.wycody.cs2d.script.inst.base;

import java.util.Arrays;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.utils.ArrayUtils;

/**
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class ReturnInstruction extends Instruction {
	private ReturnType retType;
	private Object[] retObjs;

	public ReturnInstruction() {
		super(InstructionType.RETURN);
	}

	@Override
	public void process(Context context) {
//		System.out.println("==========================");
		int total = script.getIntegerStack().size() + script.getObjectStack().size() + script.getLongStack().size();
	//	System.out.println("IntegerTotal:" + script.getIntegerStack().size() + ", ObjectTotal: " + script.getObjectStack().size() + ", LongTotal: " + script.getLongStack().size());
	//	retObjs = new Object[total];
		if (total == 0) {
			retType = ReturnType.VOID;
		} else if (total == 1) {
			if (script.getIntegerStack().size() == 1) {
				retType = ReturnType.INTEGER;
			} else if (script.getObjectStack().size() == 1) {
				retType = ReturnType.OBJECT;
			} else {
				retType = ReturnType.LONG;
			}
		} else {
			retType = ReturnType.OBJECT_ARRAY;
		}
	//	System.out.println("Defined type as: "+ retType);
		int c = 0, c2 = 0;;
		StackType[] types = new StackType[total];
		Object[] ints = new Object[script.getIntegerStack().size()];
		Object[] objs = new Object[script.getObjectStack().size()];
		Object[] longs = new Object[script.getLongStack().size()];
		
		while(script.getIntegerStack().size() > 0) {
			types[c++] = StackType.INT;
			ints[c2++] = script.popInteger(address);
		//	System.out.println("Adding integer: " +  retObjs[c-1]);
		}
		c2 = 0;
		while(script.getObjectStack().size() > 0) {
			types[c++] = StackType.OBJECT;
		//	retObjs[c++] = script.popObject(this.address);
		//	System.out.println("Adding object: " +  retObjs[c-1]);
			objs[c2++] = script.popObject(address);
		}
		c2 = 0;
		while(script.getLongStack().size() > 0) {
			types[c++] = StackType.LONG;
		//	retObjs[c++] = script.popLong(this.address);
			//System.out.println("Adding long: " +  retObjs[c-1]);
			longs[c2++] = script.popLong(address);
		}
		//System.out.println("Attaching the result: " + Arrays.toString(retObjs));
		script.setReturnTypes(types);
		//System.out.println("Generating return types: " + Arrays.toString(types));
		script.setType(retType);
		ints = ArrayUtils.flip(ints);
		objs = ArrayUtils.flip(objs);
		longs = ArrayUtils.flip(longs);
		retObjs = new Object[total];
		c = 0;
		for(Object o : ints) {
			retObjs[c++] = o;
		}
		for(Object o : objs) {
			retObjs[c++] = o;
		}
		for(Object o : longs) {
			retObjs[c++] = o;
		}
		
	//	System.out.println("====================================");
	}

	public String getReturnText() {
		String temp = Arrays.toString(retObjs);
		if (retType == ReturnType.VOID) {
			return ("return;");
		} else {
			return (retType.getReturnFormat().replace("{value}", temp.substring(1, temp.length() - 1)));
		}
	}
	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println(getReturnText());
	}

	@Override
	public int getPushCount(StackType type) {
		return 0;
	}

	@Override
	public int getPopCount(StackType type) {
		return 0;
	}

	@Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"RETURN",this.type);
    }
}
