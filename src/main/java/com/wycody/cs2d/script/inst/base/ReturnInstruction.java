package com.wycody.cs2d.script.inst.base;

import java.util.Arrays;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.types.StackType;

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
		retObjs = new Object[total];
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
		int c = 0;
		StackType[] types = new StackType[total];
		while(script.getIntegerStack().size() > 0) {
			types[c] = StackType.INT;
			retObjs[c++] = script.popInteger(this.address);
		//	System.out.println("Adding integer: " +  retObjs[c-1]);
		}
		while(script.getObjectStack().size() > 0) {
			types[c] = StackType.OBJECT;
			retObjs[c++] = script.popObject(this.address);
		//	System.out.println("Adding object: " +  retObjs[c-1]);
		}
		while(script.getLongStack().size() > 0) {
			types[c] = StackType.LONG;
			retObjs[c++] = script.popLong(this.address);
			//System.out.println("Adding long: " +  retObjs[c-1]);
		}
		//System.out.println("Attaching the result: " + Arrays.toString(retObjs));
		script.setReturnTypes(types);
		//System.out.println("Generating return types: " + Arrays.toString(types));
		script.setType(retType);
	//	System.out.println("====================================");
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		String temp = Arrays.toString(retObjs);
		if (retType == ReturnType.VOID) {
			printer.println("return;");
		} else {
			printer.println(retType.getReturnFormat().replace("{value}", temp.substring(1, temp.length() - 1)));
		}
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
