package com.wycody.cs2d.script.inst.base;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public class PushEnumValueInstruction extends Instruction {
    private boolean useLegacyChar;
    private boolean checkKeyType;

    public Object enumId;
    public PushEnumValueInstruction(boolean useLegacyChar, boolean keyType) {
        super(InstructionType.PUSH_ENUM_VAL);
        this.useLegacyChar = useLegacyChar;
        this.checkKeyType = keyType;
    }

    @Override
    public void process(Context context) {
        Object slot = pop(StackType.INT);
        enumId = pop(StackType.INT);
        int valueType = (Integer) pop(StackType.INT);
        int keyType=1;

        if(checkKeyType)
            keyType = (Integer) pop(StackType.INT);

        ScriptVarType valueVarType; 
        ScriptVarType keyVarType = null;
        if (useLegacyChar) {
            valueVarType = ScriptVarType.getByChar((char) valueType);
            if(checkKeyType)
                keyVarType = ScriptVarType.getByChar((char) keyType);
        } else {
            valueVarType = SerialEnum.forID(ScriptVarType.values(), valueType); 
            keyVarType = SerialEnum.forID(ScriptVarType.values(), keyType);
        }
        String call = "enum<" + (checkKeyType ? (""+ keyVarType.name() + ",") : "") + valueVarType.name() + ">("+enumId+", "+slot+")";
        
        if (valueVarType == ScriptVarType.STRING) {
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
        return -1;
    }

    @Override
    public int getPopCount(StackType type) {
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%4d:(%d) => %s<%s>",this.address, this.id,"PUSH_ENUM_VAL",this.type);
    }
}
