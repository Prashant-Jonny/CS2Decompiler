package com.wycody.cs2d.script.inst.base;

import com.jagex.game.runetek5.config.paramtype.ParamType;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.wycody.Main;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

/**
 * An instruction used when a parameter must be returned, such as getItem(x).getParam(y) or getActiveWidget().getParam(y).
 * Since this returns either string or int, depending on the param type, a separate instruction is needed.
 */
public class PushParamInstruction extends Instruction {
    private final String prefix_name;
    private final StackType objectStackType;
    private StackType resultType = null;

    public PushParamInstruction(InstructionType type, String prefix_name, StackType objectStackType) {
        super(type);
        this.prefix_name = prefix_name;
        this.objectStackType = objectStackType;
    }

    @Override
    public void preprocess(Context ctx){
        //Attempt to resolve the type statically.
        for(int i=0; i < 5; i++){
            int addr=this.address-1-i;
            if(addr >= 0) {
                Instruction ins = this.script.getInstruction(addr);
                int pushCount = ins.getPushCount(StackType.INT);
                int popCount = ins.getPopCount(StackType.INT);
                if(pushCount == -1 || popCount == -1)
                    return;

                if(pushCount == 1){
                    if(ins instanceof PushInstruction) {
                        PushInstruction psh = (PushInstruction) ins;
                        if(psh.isStatic()){
                            Integer paramId = (Integer) psh.getStackValue();
                            ParamType paramType = (ParamType) Main.paramTypeList.list(paramId);

                            if(paramType.scriptVarType.baseType == BaseVarType.INTEGER)
                                resultType = StackType.INT;
                            else if(paramType.scriptVarType.baseType == BaseVarType.LONG)
                                resultType = StackType.LONG;
                            else if(paramType.scriptVarType.baseType == BaseVarType.STRING)
                                resultType = StackType.OBJECT;
                            else
                                throw new Error("Unknown type: " + resultType);
                        }
                        break;
                    }
                }else if(pushCount > 1)
                    return; // Resolve failed :<
            }
        }
    }

    @Override
    public void process(Context context) {
        int paramId = (Integer) pop(StackType.INT);

        ParamType paramType = (ParamType) Main.paramTypeList.list(paramId);
        String call = ((prefix_name != null) ?
                    (prefix_name+"("
                        + (objectStackType != null ? pop(objectStackType) : "")
                    + ").") : "")
                + "getParam(" + paramId + ")";
        if (paramType.isStringVarType()) {
            push(StackType.OBJECT, call);
        } else {
            push(StackType.INT, call);
        }
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        
    }

    @Override
    public int getPushCount(StackType type) {
        if(resultType == null)
            return -1;
        return resultType.equals(type) ? 1 : 0;
    }

    @Override
    public int getPopCount(StackType type) {
        if(type == StackType.INT)
            if(type == objectStackType)
                return 2;
            else
                return 1;
        else if(objectStackType == type)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"PUSH_PARAM",this.type);
    }
}
