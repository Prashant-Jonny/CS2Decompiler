package com.thedoge.Blocks;

import com.wycody.cs2d.script.CS2Script;

import java.util.Set;
import java.util.TreeSet;

public class DummyBlock extends BaseBlock {
    private String name;
    private boolean isDeadEnd;
    public DummyBlock(boolean isDeadEnd, String name, CS2Script script) {
        super(script,0);
        this.name = name;
        this.isDeadEnd=isDeadEnd;
    }

    @Override
    public void print(int indent) {
        System.out.println(this.getIndentString(indent) + name);
    }

    @Override
    public Set<Integer> getAllJumpTargets() {
        return new TreeSet<>();
    }

    @Override
    public boolean isDeadEnd() {
        return this.isDeadEnd;
    }


    public String toString(){
        return this.name;
    }


}
