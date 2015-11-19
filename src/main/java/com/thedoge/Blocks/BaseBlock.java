package com.thedoge.Blocks;

import com.google.common.collect.ImmutableList;
import com.wycody.cs2d.script.CS2Script;
import org.apache.commons.lang.math.IntRange;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 * Represents a block of code. 
 * This represents the collection of other blocks. Instructions can 
 * be wrapped in an other block.
 * 
 * @author Stefan
 */
public abstract class BaseBlock {
    //All index ranges held by this container and the children within this container.
    private List<IntRange> ranges;
    protected CS2Script script;
    private Integer flowTarget;

    public BaseBlock(CS2Script script, Integer flowTarget){
        this.script = script;
        this.ranges = new LinkedList<>();
        this.flowTarget = flowTarget;
    }
    
    protected void addRange(IntRange range) {
        if(this.ranges.isEmpty()){
            this.ranges.add(range);
        }else{
            for(IntRange checkRange : this.ranges){
                if(checkRange.containsInteger(range.getMinimumInteger() - 1) || checkRange.containsInteger(range.getMinimumInteger())) {
                    int newMin = Math.min(range.getMinimumInteger(),checkRange.getMinimumInteger());
                    int newMax = Math.max(range.getMaximumInteger(),checkRange.getMaximumInteger());
                    IntRange merged = new IntRange(newMin,newMax);
                    ranges.remove(checkRange);
                    this.addRange(merged);
                    return;
                }

                if(checkRange.containsInteger(range.getMaximumInteger() + 1) || checkRange.containsInteger(range.getMaximumInteger())) {
                    int newMin = Math.min(range.getMinimumInteger(),checkRange.getMinimumInteger());
                    int newMax = Math.max(range.getMaximumInteger(),checkRange.getMaximumInteger());
                    IntRange merged = new IntRange(newMin,newMax);
                    ranges.remove(checkRange);
                    this.addRange(merged);
                    return;
                }
            }
            this.ranges.add(range);
        }
    }

    protected void addRange(int start, int end) {
        IntRange range = new IntRange(start,end);
        addRange(range);
    }

    protected void addRangesTo(BaseBlock destination){
        this.ranges.forEach(destination::addRange);
    }

    public String getIndentString(int level){
        String s = "";
        for(int i=0; i < level; i++)
            s += "  ";
        return s;
    }

    public List<IntRange> getRanges(){
        return ImmutableList.copyOf(this.ranges);
    }

    public abstract void print(int indent);

    public boolean contains(int instructionIndex) {
        for(IntRange range : this.ranges)
            if(range.containsInteger(instructionIndex))
                return true;
        return false;
    }

    public CS2Script getScript() {
        return this.script;
    }

    public abstract Set<Integer> getAllJumpTargets();

    public Integer getFlowTarget(){
        return this.flowTarget;
    }

    public void setFlowTarget(Integer target){
        this.flowTarget = target;
    }

    public String getLines() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(IntRange range : this.getRanges()){
            if(!first)
                sb.append(",");
            sb.append(range.getMinimumInteger()).append("-").append(range.getMaximumInteger());
            first=false;
        }
        return sb.toString();
    }


    public abstract boolean isDeadEnd();
}
