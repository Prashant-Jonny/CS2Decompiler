package com.wycody.cs2d.script.inst.base;

import com.jagex.game.runetek5.config.paramtype.ParamType;
import com.jagex.game.runetek5.config.vartype.constants.BaseVarType;
import com.wycody.Main;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class GroupVarInstruction extends Instruction {
    private StackType resultType = null;
    private String prefix;

    public GroupVarInstruction(String prefix) {
        super(InstructionType.PUSH_STRUCT_PARAM);
        this.prefix=prefix;
    }

    @Override
    public void preprocess(Context ctx){
        //Attempt to resolve the type statically.
/*        for(int i=0; i < 5; i++){
            int addr=this.address-1-i;
            if(addr >= 0) {
                Instruction ins = this.script.getInstruction(addr);
                int pushCount = ins.getPushCount(StackType.INT);
                int popCount = ins.getPopCount(StackType.INT);

                // If we don't know... we can' know!
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
                    }
                }else if(pushCount > 1)
                    return; // Resolve failed :<
            }
        }*/
        //FIXME: make this work :)
    }

    @Override
    public void process(Context context) {
        int paramId = (Integer) pop(StackType.INT);
        int bool = (Integer) pop(StackType.INT);
        Object memberId = pop(StackType.INT);

        if(bool != 1) {
            ParamType paramType = (ParamType) Main.paramTypeList.list(paramId);
            String call = prefix+"(" + memberId + ").getParam(" + paramId + ")";

            if(paramType.scriptVarType.baseType == BaseVarType.INTEGER){
                push(StackType.INT, call);
            }else if(paramType.scriptVarType.baseType == BaseVarType.LONG) {
                push(StackType.LONG, call);
            }else{
                assert paramType.scriptVarType.baseType == BaseVarType.STRING;
                push(StackType.OBJECT, call);
            }
        }else{
            String call = prefix+"(" + memberId + ").getVarbit(" + paramId + ")";
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
            return 3;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"PUSH_PARAM",this.type);
    }
}
