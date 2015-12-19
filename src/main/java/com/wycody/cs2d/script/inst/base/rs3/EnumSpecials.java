package com.wycody.cs2d.script.inst.base.rs3;

import com.jagex.core.constants.SerialEnum;
import com.jagex.game.runetek5.config.vartype.constants.ScriptVarType;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

public interface EnumSpecials {
    class EnumValueKeyGetterInstruction extends Instruction {
        public EnumValueKeyGetterInstruction() {
            super(InstructionType.METHOD_CALL_WITH_PUSH);
        }

        @Override
        public void process(Context context) {
            Object slot = pop(StackType.INT);
            Object value = pop(StackType.INT);
            Object enumId = pop(StackType.INT);
            int keyType = (Integer) pop(StackType.INT);
            int valueType = (Integer) pop(StackType.INT);


            ScriptVarType valueVarType;
            ScriptVarType keyVarType = null;
            valueVarType = SerialEnum.forID(ScriptVarType.values(), valueType);
            keyVarType = SerialEnum.forID(ScriptVarType.values(), keyType);
            String call = "enum<" + keyVarType.name() + "," + valueVarType.name() + ">("+enumId+").getKeysForValue("+value+").get("+slot+"))";
            push(StackType.INT, call);
        }

        @Override
        public int getPushCount(StackType type) {
            return type == StackType.INT ? 1 : 0;
        }

        @Override
        public int getPopCount(StackType type) {
            return type == StackType.INT ? 5 : 0;
        }

        @Override
        public void print(Context context, ScriptPrinter printer) {

        }

        @Override
        public String toString() {
            return String.format("%4d:(%d) => %s<%s>", this.address, this.id, "ENUM_VALUE_KEYS_KEY", this.type);
        }
    }
}
