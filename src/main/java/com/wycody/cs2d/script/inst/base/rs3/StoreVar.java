package com.wycody.cs2d.script.inst.base.rs3;

import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class StoreVar extends Instruction {//TODO is this being done correct?

	String variable;
	Object value;
	
	public StoreVar(int id, int address) {
		super(id, address, InstructionType.STORE_VAR);
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
		boolean usePlayerMap = integerOperand == 1;
		BaseVarType baseVar = type.dataType.baseType;
		VarDomainType domain = type.getDomain();
        String outcome = "%var";
        //this stuff was in a class becuse it gets used for other instructsions
        if(domain == null) {
        	outcome += "bit";
        } else {
        	outcome += domain.name().toLowerCase().replace("_", "");
        }
        
        outcome += type.getId();
        variable = outcome;
        
		if(baseVar == BaseVarType.INTEGER) {
			value = pop(StackType.INT);
		} else if(baseVar == BaseVarType.LONG) {
			value = pop(StackType.LONG);
		} else if(baseVar == BaseVarType.STRING) {
			value = pop(StackType.OBJECT);
		} else {
			throw new RuntimeException("Invalid BaseVarType found in PUSH_VAR: " + baseVar);
		}
        
	}

	@Override
	public void print(Context context, ScriptPrinter printer) {
		printer.println(variable + " = " + value + ";");
	}

	@Override
	public int getPushCount(StackType type) {
		return -1;
	}

	@Override
	public int getPopCount(StackType type) {
		return -1;
	}
}
