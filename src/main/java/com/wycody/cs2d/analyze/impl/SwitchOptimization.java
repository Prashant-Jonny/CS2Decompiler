package com.wycody.cs2d.analyze.impl;

import com.wycody.cs2d.analyze.Analyzer;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;

/**
 * 
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class SwitchOptimization extends Analyzer {

	private SwitchBlock[] blocks;

	public SwitchOptimization(CS2Script script) {
		super(script);

	}

	@Override
	public void initialize() {
		blocks = script.getSwitchBlocks();
	}

	@Override
	public void process() {
//		a:for (SwitchBlock block : blocks) {
//			int sameAddr = -1;
//			boolean same = true;
//			System.out.println("----------------");
//			b:for (int caseID = 0; caseID < block.getCases().size(); caseID++) {
//				boolean isLast= caseID == block.getCases().size() -1;
//				CaseNode node = block.getCases().get(caseID);
//				BasicBlock nodeBlock = script.getBlockAt(block.getAddress() + 1 + node.getJumpTarget());
//				Instruction caseInstr = nodeBlock.getInstructions().last();
//				if (!(caseInstr instanceof JumpInstruction)) {
//					if(!isLast) {
//						same = false;
//						break b;
//					}
//					continue b;
//					
//				}
//				int lastCheck = -1;
//				JumpInstruction caseJump = (JumpInstruction) caseInstr;
//				if(caseJump instanceof JumpInstruction) {
//					JumpInstruction lastJump = caseJump;
//					if(lastCheck == -1)  {
//						lastCheck = lastJump.getJumpTarget();
//					}
//					if(lastCheck != lastJump.getJumpTarget()) {
//						same = false;
//						break b;
//					}
//				}
//				
//			}
//			if(same) {
//				b:for (int caseID = 0; caseID < block.getCases().size(); caseID++) {
//					boolean isLast= caseID == block.getCases().size() -1;
//					CaseNode node = block.getCases().get(caseID);
//					BasicBlock nodeBlock = script.getBlockAt(block.getAddress() + 1 + node.getJumpTarget());
//					Instruction caseInstr = nodeBlock.getInstructions().last();
//					if (!(caseInstr instanceof JumpInstruction)) {
//						if(!isLast) {
//							same = false;
//							break b;
//						}
//						continue b;
//						
//					}
//					
//					JumpInstruction caseJump = (JumpInstruction) caseInstr;
//					if(caseJump instanceof JumpInstruction) {
//						System.out.println("Remvoing: " + caseJump.getAddress());
//						caseJump.getHolder().removeInstruction(caseJump);
//					}
//					
//				}
//			}
//			System.out.println(same);
//		}
	}

	@Override
	public void finalyze() {
		// TODO Auto-generated method stub

	}

}
