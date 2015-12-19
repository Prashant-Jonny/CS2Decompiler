package com.wycody.cs2d.script.inst.base.rs3;

import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * Specifically for 745+ var system
 * pushes specific var onto stack.
 * for example it could push 'varc[1020]'
 */
public class PushVar extends Instruction {

	public PushVar(int id, int address) {
		super(id, address, InstructionType.PUSH_VAR);
	}


	public String getTypeName(){
		VarType type = (VarType) objectOperand;
		boolean usePlayerMap = integerOperand == 1;

		VarDomainType domain = type.getDomain();
		String outcome = "%var";
		//this stuff was in a class becuse it gets used for other instructsions
		if(domain == null) {
			outcome += "bit";
		} else {
			outcome += domain.name().toLowerCase().replace("_", "");
		}

		outcome += type.getId();
		return outcome;
	}

	@Override
	public void process(Context context) {
		VarType type = (VarType) objectOperand;
		BaseVarType baseVar = type.dataType.baseType;
		String outcome = this.getTypeName();
        
		if(baseVar == BaseVarType.INTEGER) {
			push(StackType.INT, outcome);
		} else if(baseVar == BaseVarType.LONG) {
			push(StackType.LONG, outcome);
		} else if(baseVar == BaseVarType.STRING) {
            push(StackType.OBJECT, outcome);
		} else {
			throw new RuntimeException("Invalid BaseVarType found in PUSH_VAR: " + baseVar);
		}
        
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		
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
	public String toString(){
		VarType type = (VarType) objectOperand;
		boolean usePlayerMap = integerOperand == 1;
		BaseVarType baseVar = type.dataType.baseType;

		return "PushVar - " + baseVar;
	}
}
