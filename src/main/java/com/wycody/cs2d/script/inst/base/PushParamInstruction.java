package com.wycody.cs2d.script.inst.base;

import java.util.function.Function;

import com.jagex.game.runetek5.config.paramtype.ParamType;
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
    private final Function<PushParamInstruction, Object> prefixFormatter;
    private int paramId = -1;

    public PushParamInstruction(InstructionType type, Function<PushParamInstruction, Object> prefixFormatter) {
        super(type);
        this.prefixFormatter = prefixFormatter;
    }

    @Override
    public void process(Context context) {
        int paramId = (Integer) pop(StackType.INT);
        ParamType paramType = (ParamType) Main.paramTypeList.list(paramId);
        String call = prefixFormatter.apply(this)+"getParam("+paramId+")";        
        
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
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        if(type == StackType.INT)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"PUSH_PARAM",this.type);
    }
}
