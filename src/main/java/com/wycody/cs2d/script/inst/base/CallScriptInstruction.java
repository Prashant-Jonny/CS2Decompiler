package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.ReturnType;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.CS2Utils;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 13, 2015
 */
public class CallScriptInstruction extends Instruction {

	/**
	 * The called script
	 */
	private CS2Script target;

	/**
	 * The call text
	 */
	private String callText;

	/**
	 * The object array name if found
	 */
	private String arrayName;
	private int[] args;

	/**
	 * Construct a new {@link CallScriptInstruction}
	 */
	public CallScriptInstruction() {
		super(InstructionType.CALL_SCRIPT);
		this.args = null;
	}

	@Override
	public void preprocess(Context context) {
		CS2Decompiler decompiler = context.getDecompiler();
		int scriptId = integerOperand;
		if (scriptId == getHolder().getHolder().getId()) {
			target = getHolder().getHolder();
		} else {
			target = decompiler.decompile(scriptId);
		}
		args = new int[StackType.values().length];
		args[StackType.INT.ordinal()] = target.getIntegerParameters().length;
		args[StackType.OBJECT.ordinal()] = target.getObjectParameters().length;
		args[StackType.LONG.ordinal()] = target.getLongParameters().length;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#process(com.wycody.cs2d.Context)
	 */
	@Override
	public void process(Context context) {
		if (args == null) {
			preprocess(context);
		}
		callText = "~" + target.getName() + "(" + target.generateParameters(CS2Utils.getParameters(target, script)) + ")";

		assert target.getReturnTypes() != null : "Target returns are null";

		Object wtf = target.getReturnTypes();

		if(target.getId() == 8701) {
			target.setReturnTypes(new StackType[]{StackType.INT,StackType.INT,StackType.INT,StackType.INT,StackType.INT,StackType.INT,StackType.INT,StackType.INT});
			target.setType(ReturnType.OBJECT_ARRAY);
		}
		switch (target.getType()) {
			case BOOLEAN:
			case INTEGER:
				setPrintable(false);
				push(StackType.INT, callText);
				break;
			case OBJECT:
				setPrintable(false);
				push(StackType.OBJECT, callText);
				break;
			case LONG:
				setPrintable(false);
				push(StackType.LONG, callText);
				break;
			case OBJECT_ARRAY:
				setPrintable(false);
				arrayName = "result";
				for (int typeIndex = 0; typeIndex < target.getReturnTypes().length; typeIndex++) {
					StackType type = target.getReturnTypes()[typeIndex];
					switch (type) {
						case INT:
							push(StackType.INT, arrayName + "[" + typeIndex + "]");
							break;
						case LONG:
							push(StackType.LONG, arrayName + "[" + typeIndex + "]");
							break;
						case OBJECT:
							push(StackType.OBJECT, arrayName + "[" + typeIndex + "]");
							break;
						default:
							throw new UnsupportedOperationException("The '" + type + "' is not supported in object array yet!!");

					}
				}
				break;
			case VOID:

				break;

			default:
				throw new UnsupportedOperationException("The '" + target.getType() + "' call type is not supported yet!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wycody.cs2d.script.inst.Instruction#print(com.wycody.cs2d.Context,
	 * com.wycody.cs2d.print.ScriptPrinter)
	 */
	@Override
	public void print(Context context, ScriptPrinter printer) {
		if (target.getType() == ReturnType.VOID) {
			printer.println(callText + ";");
		} else if (target.getType() == ReturnType.OBJECT_ARRAY) {
			printer.println("Object[] " + arrayName + " = " + callText + ";");
		}

	}

	@Override
	public int getPushCount(StackType type) {
		StackType[] types = target.getReturnTypes();
		if (types == null) {
			return 0;
		}
		int cnt = 0;
		for (StackType cmp : types) {
			if (cmp == type) {
				cnt++;
			}
		}
		return cnt;
	}

	@Override
	public int getPopCount(StackType type) {
		return args[type.ordinal()];
	}

	/**
	 * @return the target
	 */
	public CS2Script getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(CS2Script target) {
		this.target = target;
	}

	/**
	 * @return the callText
	 */
	public String getCallText() {
		return callText;
	}

	/**
	 * @param callText
	 *            the callText to set
	 */
	public void setCallText(String callText) {
		this.callText = callText;
	}

	/**
	 * @return the arrayName
	 */
	public String getArrayName() {
		return arrayName;
	}

	/**
	 * @param arrayName
	 *            the arrayName to set
	 */
	public void setArrayName(String arrayName) {
		this.arrayName = arrayName;
	}

	@Override
	public String toString() {
		return String.format("%4d:(%d) => %s<script_%d>", this.address, this.id, "CALL_SCRIPT", this.integerOperand);
	}
}
