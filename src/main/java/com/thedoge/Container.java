package com.thedoge;

import com.thedoge.Blocks.DummyBlock;
import com.thedoge.Blocks.InstructionBlock;
import com.thedoge.Blocks.BaseBlock;
import com.thedoge.Blocks.MultiBlock;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.base.branch.JumpInstruction;
import org.apache.commons.lang.math.IntRange;
import org.jgraph.JGraph;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Container {
    private InsGraph graph;
    private DummyBlock START,END;
    private MultiBlock root;

    public static class EdgyEdge extends DefaultEdge {
        private String name;
        public EdgyEdge(){
            this("");
        }

        public EdgyEdge(String name){
            this.name=name;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    private class InsGraph extends DefaultDirectedGraph<BaseBlock, EdgyEdge>{
        public InsGraph() {
            super(EdgyEdge.class);
        }
    }


    /*
            NOTE: the root MUST be the entire script in this case.
     */
    public Container(MultiBlock root){
        this.root = root;
        this.graph = new InsGraph();
        START = new DummyBlock(false, "START_NODE",root.getScript());
        END = new DummyBlock(true, "END_NODE",root.getScript());

        List<BaseBlock> nodes = root.getNodes();

        nodes.forEach(this.graph::addVertex);

        this.graph.addVertex(START);
        this.graph.addEdge(START, nodes.get(0));

        nodes.forEach(node -> {
            Set<Integer> jumpDestinations = node.getAllJumpTargets();
            for (int destination : jumpDestinations) {
                if (!node.contains(destination)) {
                    BaseBlock dst = root.getNodeFor(destination);
                    graph.addEdge(node, dst);
                }
            }
        });

        this.removeDeadCode();
        this.graph.addVertex(END);

        //Mark blocks with e.g. return to lead to our END_NODE.
        this.graph.vertexSet().forEach(node -> {
            if (node.getFlowTarget() != null || !node.isDeadEnd())
                return;

            // The node has no flow target. Hence, it must be a dead end.
            // connect it to the END node.
            graph.addEdge(node, END);
        });
        this.optimizeJumps();
        this.detectWhile();
    }

    private void optimizeJumps() {
        Set<BaseBlock> nodeSet = this.graph.vertexSet();
        BaseBlock[] nodes = nodeSet.toArray(new BaseBlock[nodeSet.size()]);

        for(BaseBlock part : nodes) {
            if(part instanceof InstructionBlock){
                InstructionBlock ib = (InstructionBlock) part;
                if(ib.getLastInstruction() instanceof JumpInstruction){
                    IntRange range = ib.getRange();
                    if(range.getMaximumInteger() == range.getMinimumInteger()) {
                        //Hey.. only 1 instruction and a jump.
                        //Let's optimize this away with an edge!

                        List<BaseBlock> sources = new ArrayList<>();
                        BaseBlock dest = null;

                        Set<EdgyEdge> edges = this.graph.edgesOf(part);
                        for(EdgyEdge x : edges) {
                            if (graph.getEdgeTarget(x) == part) {
                                //Incoming, So, Connect to our target later on
                                sources.add(graph.getEdgeSource(x));
                            } else if (graph.getEdgeSource(x) == part) {
                                //Connection from our component to another component.
                                //This should only happen once since it's a jump
                                //So, delete the relation. But remember it! so that we can
                                //Reconnect it.
                                dest = graph.getEdgeTarget(x);
                                graph.removeEdge(x);
                            }
                        }

                        graph.removeVertex(part);

                        for(BaseBlock src : sources)
                            this.graph.addEdge(src,dest);
                    }
                }
            }
        }

    }

    public void show() {
        JFrame frame = new JFrame();

        JGraphModelAdapter a = new JGraphModelAdapter(this.graph);
        JGraph jgraph = new JGraph(a);
        jgraph.setPreferredSize(new Dimension(1000,1000));
        jgraph.show();

        frame.setContentPane(jgraph);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        jgraph.doLayout();


        System.out.println("Mmmk");
    }

    //Vertex = code
    //Edge = relatie
    private void removeDeadCode() {
        ConnectivityInspector<BaseBlock,EdgyEdge> ci = new ConnectivityInspector<BaseBlock,EdgyEdge>(this.graph);
        Set<BaseBlock> connected = ci.connectedSetOf(START);

        BaseBlock[] parts = this.graph.vertexSet().toArray(new BaseBlock[this.graph.vertexSet().size()]);
        for(BaseBlock part : parts) {
            if (!connected.contains(part)){
                this.graph.removeVertex(part);
                this.root.remove(part);
                System.out.println("Removed a piece of deadcode..");
            }
        }
    }



    /**
        Tries to match a jump instruction to a while loop.
     */
    private boolean tryWhileDetect(JumpInstruction ins, ConnectivityInspector<BaseBlock,EdgyEdge> connect) {
        int destination = ((JumpInstruction) ins).getJumpTarget();

        System.out.println("Intruction: " + ins);
        System.out.println("Destination: " + destination);


        BaseBlock footer_block = this.root.getNodeFor(ins.getAddress());
        BaseBlock header_block = this.root.getNodeFor(destination);

        // Most simple while detection that is possible...
        // Strongly connected (Will not get all possibilities)


        System.out.println("footer_block: " + footer_block);
        System.out.println("header_block: " + header_block);


        ArrayList<BaseBlock> whileLoopNodes = new ArrayList<>();
        for(BaseBlock n : this.graph.vertexSet()){
            if(connect.pathExists(header_block,n) //If it is reachable from the header block, it will also be reachable from the footer block.
                    && connect.pathExists(n,footer_block)
                    && connect.pathExists(n,header_block)
                    ) {
                System.out.println(n);
                whileLoopNodes.add(n);
            }
        }


        System.out.println(whileLoopNodes.size());
        return false;
    }

    private void detectWhile(){
        BaseBlock[] parts = this.root.getNodes().toArray(new BaseBlock[0]);
        CS2Script script = this.root.getScript();
        ConnectivityInspector<BaseBlock,EdgyEdge> ci = new ConnectivityInspector<BaseBlock,EdgyEdge>((DirectedGraph)this.graph);
        CycleDetector<BaseBlock,EdgyEdge> cycleDetector = new CycleDetector<>(this.graph);


        for(int i=parts.length-1; i >= 0; i--) {
            BaseBlock part = parts[i];
            if (!(part instanceof InstructionBlock))
                continue;

            Instruction ins = ((InstructionBlock) part).getLastInstruction();
            if (!(ins instanceof JumpInstruction))
                continue;

            tryWhileDetect((JumpInstruction) ins, ci);
        }
    }





}
