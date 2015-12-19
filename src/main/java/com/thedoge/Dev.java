package com.thedoge;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.MissingInstruction;
//import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

/**
 * Created by Stefan on 12/16/2015.
 */
public class Dev {


    /*public static void printMissingInstructions(Context context) throws IOException {
        List<Pair<Integer,Integer>> list = findMissingInstructions(context);
        list.forEach(x -> System.out.printf("%4d (%dx)\n",x.getKey(),x.getValue()));
    }

    public static List<Pair<Integer,Integer>> findMissingInstructions(Context context) throws IOException {
        final Map<Integer,Integer> results = new HashMap<>();

        context.getDisassembledIterator().forEachRemaining(t -> {
            if(t == null)
                return;

            HashSet<Integer> added = new HashSet<>();
            for(Instruction ins : t.getInstructions()) {
                if(!(ins instanceof MissingInstruction))
                    continue;

                int opcode = ins.getId();
                int count = 1;
                if(results.containsKey(ins.getId()))
                    count += results.get(ins.getId());
                results.put(opcode,count);
            }
        });

        int count = results.size();
        List<Pair<Integer,Integer>> sorted = new ArrayList<>(count);
        results.forEach((a,b) -> sorted.add(new Pair<>(a,b)));
        sorted.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return sorted;
    }*/

    public static void findInstructions(Context context, int ... instructions) throws IOException {
        context.getDisassembledIterator().forEachRemaining(t -> {
            if(t == null)
                return;

            for(Instruction ins : t.getInstructions()) {
                for(int find : instructions)
                    if(ins.getId() == find)
                        System.out.printf("Script %d contains opcode: %d [total ins=%d] \n", t.getId(), find, t.getInstructions().length);
            }
        });
    }

//    public static void findStructUse(Context context, int ... structs) throws IOException {
//        context.getDisassembledIterator().forEachRemaining(t -> {
//            if(t == null)
//                return;
//
//            Instruction[] instructions = t.getInstructions();
//            for (int i = 0; i < instructions.length; i++) {
//                Instruction ins = instructions[i];
//
//                if(ins.getType() == )
//
//                if (ins.getId() == find)
//                    System.out.printf("Script %d contains opcode: %d [total ins=%d] \n", t.getId(), find, t.getInstructions().length);
//            }
//        });
//    }
}