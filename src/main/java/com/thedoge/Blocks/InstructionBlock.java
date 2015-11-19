package com.thedoge.Blocks;

import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;
import org.apache.commons.lang.math.IntRange;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a block of Instructions.
 * Any of these instructions have not been identified as larger
 * structured groups of instructions.
 * It does however normally consist of instructions that have been broken down into nodes.
 *
 * Because of this, this should never be confused with a scope! Since
 * this may contain jump destination and sources outside of this block and outside
 * of the normal flow!
 * 
 * @author Stefan
 */
public class InstructionBlock extends BaseBlock {
    /**
     * Instructions should be straight after each other.
     */
    public InstructionBlock(CS2Script script, IntRange range){
        this(script, range, -1);
    }

    public InstructionBlock(CS2Script script, IntRange range, Integer flowTarget){
        super(script, flowTarget);
        if(script == null || range == null)
            throw new IllegalArgumentException();

        this.addRange(range);

        if(flowTarget != null && flowTarget < 0)
            setFlowTarget(detectFlowTarget());

        assert flowTarget == null || !this.contains(flowTarget);
    }

    public Integer detectFlowTarget(){
        Instruction last = this.getLastInstruction();
        if(!(last instanceof ReturnInstruction) && !(last instanceof JumpInstruction))
            return last.getAddress()+1;
        return null;
    }



    public IntRange getRange() {
        List<IntRange> ranges = super.getRanges();
        assert ranges.size() == 1;
        IntRange range = ranges.get(0);
        assert range != null;
        return range;
    }

    @Override
    public void print(int indent) {
        IntRange range = getRange();
        for(int i=range.getMinimumInteger(); i <= range.getMaximumInteger(); i++) {
            System.out.println(getIndentString(indent) + script.getInstruction(i));
        }
    }

    @Override
    public String toString() {
        return "Code [" + getLines() + "]";
    }

    public Instruction getLastInstruction(){
        return script.getInstruction(this.getRange().getMaximumInteger());
    }

    @Override
    public Set<Integer> getAllJumpTargets() {
        IntRange range = getRange();
        Set<Integer> targets = new HashSet<>();
        for(int i=range.getMinimumInteger(); i <= range.getMaximumInteger(); i++) {
            Instruction is = script.getInstruction(i);
            if(is instanceof JumpInstruction){
                targets.add(((JumpInstruction) is).getJumpTarget());
            }else if(is instanceof ConditionalInstruction){
                targets.add(is.getAddress()+1);
                targets.add(((ConditionalInstruction) is).getJumpTarget());
            }else if(is instanceof SwitchInstruction){
                SwitchInstruction sw = (SwitchInstruction)is;
                SwitchBlock block = sw.getBlock();
                block.setAddress(sw.getAddress());//FIXME: this shouldn't be done here...
                CaseNode[] nodes = block.getCases().getData();
                for(CaseNode node : nodes)
                    targets.add(node.getJumpTarget());
                //FIXME: anything else needed here?
            }
        }

        //If last instruction is not a jump or return. Then, we have also the natural flow.
        if(this.getFlowTarget() != null)
            targets.add(this.getFlowTarget());

        return targets;
    }

    @Override
    public boolean isDeadEnd() {
        return (this.getLastInstruction() == null && (this.getFlowTarget() == null || this.getFlowTarget() <0)) || this.getLastInstruction() instanceof ReturnInstruction;
    }


}
