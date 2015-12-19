package com.wycody.cs2d.script.inst.base;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ScriptPrinter;
import com.wycody.cs2d.script.CS2Operator;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.types.StackType;

// TODO:
// This could possibly be replaced by CallMethodInstruction
// e.g. setFormattedName("(%1 + %2)")
public class InfixInstruction extends Instruction{
    private final boolean reverse;
    private final CS2Operator operator;
    private final StackType leftType;
    private final StackType rightType;
    private final StackType resultType;
    
    public InfixInstruction(InstructionType type,boolean isReverse, CS2Operator operator, StackType leftType, StackType rightType, StackType resultType) {
        super(type);
        this.reverse = isReverse;
        this.operator = operator;
        this.leftType = leftType;
        this.rightType = rightType;
        this.resultType = resultType;
    }

    @Override
    public void process(Context context) {
        String result;
        Object right = pop(rightType);
        Object left = pop(leftType);
        
        boolean brackets = true && (this.getType() != InstructionType.STRING_INT_CONCAT) &&
                (this.getType() != InstructionType.MULTISTRING_CONCAT) &&
                (this.getType() != InstructionType.STRING_CHAR_CONCAT) &&
                (this.getType() != InstructionType.STRING_STRING_CONCAT) &&
                (this.getType() != InstructionType.STRING_SIGNINT_CONCAT);
        
        if(!reverse){
            result = (brackets?"(":"") + left + " " + operator.getSymbol() + " " + right + (brackets?")":"");
        }else{
            result = (brackets?"(":"") + right + " " + operator.getSymbol() + " " + left + (brackets?")":"");            
        }
        
        push(resultType,result);
    }

    @Override
    public void print(Context context, ScriptPrinter printer) {
        
    }

    @Override
    public int getPushCount(StackType type) {
        if(this.resultType == type)
            return 1;
        return 0;
    }

    @Override
    public int getPopCount(StackType type) {
        if(type == this.leftType && type == this.rightType)
            return 2;
        else if(type == this.leftType || type == this.rightType)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        if(!this.reverse) {
			return String.format("%4d:(%d) => %s<%s>\t(%s %s %s -> %s)",this.address, this.id,"INFIX",this.type, this.leftType,this.operator,this.rightType,this.resultType);
		}
        return String.format("%4d:(%d) => %s<%s>\t(%s %s %s -> %s)",this.address, this.id,"INFIX",this.type, this.rightType,this.operator,this.leftType,this.resultType);
    }
}
