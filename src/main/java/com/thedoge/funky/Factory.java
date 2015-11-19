package com.thedoge.funky;

import com.thedoge.Blocks.DummyBlock;
import com.thedoge.Blocks.InstructionBlock;
import com.thedoge.Blocks.BaseBlock;
import com.thedoge.Blocks.MultiBlock;
import com.thedoge.Container;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.ReturnInstruction;
import com.wycody.cs2d.script.inst.base.branch.BranchInstruction;
import com.wycody.cs2d.script.inst.base.branch.ConditionalInstruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;
import com.wycody.cs2d.script.inst.swtch.SwitchInstruction;
import org.apache.commons.lang.math.IntRange;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Stefan
 */
public class Factory {
    
    public Factory() {
        
    }
    
    public MultiBlock breakDown(CS2Script script) {
        Set<Integer> startingPoints = new TreeSet<>();
        Set<Integer> endPoints = new TreeSet<>();

        calculatePoints(script,startingPoints,endPoints);

        endPoints.remove(0); // Just in case.

        MultiBlock rootBlock = new MultiBlock(script,null);
        Instruction[] instructions = script.getInstructions();
        int currentStart=0;
        //TODO: could be optimized?
        for(int i=0; i < instructions.length; i++){
            if(endPoints.contains(i) || startingPoints.contains(i+1)){
                // Last instruction for this block...
                IntRange range = new IntRange(currentStart,i);
                InstructionBlock block = new InstructionBlock(script,range);
                rootBlock.addNode(block);
                currentStart = i+1;
            }
        }

        if(currentStart < instructions.length){
            IntRange range = new IntRange(currentStart,instructions.length-1);
            InstructionBlock block = new InstructionBlock(script,range);
            rootBlock.addNode(block);
        }

        Container container = new Container(rootBlock);
        container.show();
        return rootBlock;
    }
    
    private void calculatePoints(CS2Script script, Set<Integer> start, Set<Integer> end) {
        start.add(0);// 0 is always a start.

        Instruction[] instructions = script.getInstructions();
        for(Instruction i : instructions) {
            if(i instanceof BranchInstruction) {
                if(i instanceof JumpInstruction){
                    end.add(i.getAddress());
                    start.add(((JumpInstruction)i).getJumpTarget());
                }else if(i instanceof ConditionalInstruction){
                    ConditionalInstruction cnd = (ConditionalInstruction)i;
                    end.add(i.getAddress());
                    start.add(cnd.getJumpTarget());
                }
            }else if(i instanceof ReturnInstruction) {
                ReturnInstruction ret = (ReturnInstruction)i;
                end.add(ret.getAddress());
            }else if(i instanceof SwitchInstruction){
                SwitchInstruction sw = (SwitchInstruction)i;
                SwitchBlock block = sw.getBlock();
                block.setAddress(sw.getAddress());//FIXME: this shouldn't be done here...
                CaseNode[] nodes = block.getCases().getData();
                for(CaseNode node : nodes){
                    start.add(node.getJumpTarget());
                }
            }
        }

        //A script should always end with return.. but just in case
        end.add(instructions[instructions.length-1].getAddress());
    }

    
    public void detectWhile(MultiBlock block){
        List<BaseBlock> nodes = block.getNodes();
        CS2Script script = block.getScript();

        //First, try to find all major while loops.
        //Later, we can try to narrow it down.
        for(int i=nodes.size()-1; i >= 0; i--) {
            BaseBlock part = nodes.get(i);
            if(part instanceof InstructionBlock) {
                InstructionBlock ib = (InstructionBlock)part;
                IntRange range = ib.getRange();
                for(int instructionIndex = range.getMaximumInteger(); instructionIndex >= range.getMinimumInteger(); instructionIndex++){
                    Instruction ins = script.getInstruction(instructionIndex);
                    if(ins instanceof JumpInstruction){
                        //This means we have a block with a jump :p
                        int target = ((JumpInstruction) ins).getJumpTarget();
                        /*
                                ASSUMPTION.......
                                 EVERYTHING
                                  Inside the MultiBlock, can not jump outside of it
                                  Unless.... its to the end-destination / flow target.
                                So, if we calculate the set of destinations of a block A
                                and none of these go outside of block A except for the natural flow
                                destination of A or a return
                                Then A is a Scope.

                                Hence, if we have a JUMP instruction with
                                    (1) A target
                                    (2) This JUMP can only be reached through the target
                                    (3) The discrete graph between the two points only have branches in between
                                        each other, return, or natural flow.
                                Then, we can define this as a while block.
                        */

                    }
                }
            }
        }




    }
}
