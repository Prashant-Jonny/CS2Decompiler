package com.thedoge.Blocks;

import com.google.common.collect.ImmutableList;
import com.wycody.cs2d.script.CS2Script;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents one or multiple blocks
 *
 */
public class MultiBlock extends BaseBlock {
    private List<BaseBlock> nodes;

    public MultiBlock(CS2Script script, Integer flow) {
        super(script, flow);
        this.nodes = new LinkedList<>();
    }

    public void addNode(BaseBlock node) {
        this.nodes.add(node);
        node.addRangesTo(this);

        assert this.getFlowTarget() == null || isAFlowTarget(this.getFlowTarget());
    }

    public boolean isAFlowTarget(int target){
        Integer wrap = Integer.valueOf(target);
        for(BaseBlock part : nodes)
            if(wrap.equals(part.getFlowTarget()))
                return true;
        return false;
    }



    public List<BaseBlock> getNodes(){
        return ImmutableList.copyOf(this.nodes);
    }

    public BaseBlock getNodeFor(int instructionIndex) {
        for(BaseBlock node : nodes)
            if(node.contains(instructionIndex))
                return node;
        return null;
    }

    @Override
    public void print(int indent){
        BaseBlock[] parts = nodes.toArray(new BaseBlock[nodes.size()]);
        String base = getIndentString(indent);
        System.out.println(base + "===MultiBlock===");
        for(int i=0; i < parts.length; i++){
            System.out.println(base+"  "+"[Node #"+(1+i)+"]");
            parts[i].print(indent +2);
        }
    }

    @Override
    public Set<Integer> getAllJumpTargets() {
        Set<Integer> set = new TreeSet<>();
        this.nodes.forEach(x -> set.addAll(x.getAllJumpTargets()));
        return set;
    }

    @Override
    public boolean isDeadEnd() {
        return false; //TODO: Need to figure out how to implement thi.
    }

    public void remove(BaseBlock block){
        nodes.remove(block);
        //TODO: remove lines!!!
    }
}