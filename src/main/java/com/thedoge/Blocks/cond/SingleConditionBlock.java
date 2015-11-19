package com.thedoge.Blocks.cond;

import com.thedoge.Blocks.BaseBlock;
import com.wycody.cs2d.script.CS2Script;
import org.apache.commons.lang.math.IntRange;

import java.util.Set;

public class SingleConditionBlock extends BaseBlock {

    public SingleConditionBlock(CS2Script script, IntRange instructionRange, Integer flowTarget) {
        super(script, flowTarget);
        this.addRange(instructionRange);
    }

    @Override
    public void print(int indent) {
        //TODO
    }

    @Override
    public Set<Integer> getAllJumpTargets() {
        return null;
    }

    @Override
    public boolean isDeadEnd() {
        return false;
    }

    public String toString() {
        return "Solo-Condition [" + this.getLines() + "]";
    }


}
