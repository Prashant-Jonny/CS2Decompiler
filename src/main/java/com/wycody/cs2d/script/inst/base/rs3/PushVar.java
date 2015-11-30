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

	@Override
	public void process(Context context) {
		VarType type = (VarType) objectOperand;
		boolean usePlayerMap = integerOperand == 1;
		BaseVarType baseVar = type.dataType.baseType;

		System.out.flush();
		System.err.flush();
		System.err.println(address + " => " + baseVar);

		VarDomainType domain = type.getDomain();
        String outcome = "";
        //this stuff was in a class becuse it gets used for other instructsions
        if(domain == null) {
        	outcome = "varbits";
        } else {
	        if(domain == VarDomainType.CLIENT) {
	        	outcome = "varcs";
	        } else if(domain == VarDomainType.CLAN) {
	        	outcome = "varclans";
	        } else if(domain == VarDomainType.CLAN_SETTINGS) {
	        	outcome = "varclansettings";
	        } else if(domain == VarDomainType.PLAYER) {
	        	outcome = "varps";
	        } else if(domain == VarDomainType.GROUP) {
	        	outcome = "vargroups";
	        } else if(domain == VarDomainType.NPC) {
	        	outcome = "varnpcs";
	        } else if(domain == VarDomainType.OBJECT) {
	        	outcome = "varobjs";
	        } else if(domain == VarDomainType.WORLD) {
	        	outcome = "varworlds";
	        } else if(domain == VarDomainType.REGION) {
	        	outcome = "varregions";
	        } else if(domain == VarDomainType.GLOBAL) {
	        	outcome = "var8s";
	        } else if(domain == VarDomainType.EXCHANGE) {
	        	outcome = "var10s";
	        }
        }
        
        outcome += "[" + type.getId() + "]";
        
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
}
