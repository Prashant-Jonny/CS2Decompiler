package com.wycody.cs2d.script.inst.swtch;

import com.wycody.utils.DynamicArray;

/**
 * The switch block represent bunch of if coniditions but in other format
 * switch(CONDITION) { case EXPRESSION: goto SUCCESS; }
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 */
public class SwitchBlock {

	/**
	 * The epxression of the node
	 */
	private Object expression;

	/**
	 * The switch block cases represented as nodes
	 */
	private DynamicArray<CaseNode> cases;

	/**
	 * The block address
	 */
	private int address;

	/**
	 * Construct a new {@link SwitchBlock}
	 * 
	 * @param casesCount
	 *            the total amount of cases found in the block
	 */
	public SwitchBlock(int casesCount) {
		this.cases = new DynamicArray<CaseNode>(CaseNode.class);
	}

	/**
	 * Add an case to the block
	 * 
	 * @param node
	 *            the case to add
	 */
	public void addCase(CaseNode node) {
		cases.add(node);
	}

	/**
	 * Get you an case from specified index
	 * 
	 * @param index
	 *            the index of the node you want to get
	 * @return the node
	 */
	public CaseNode getCase(int index) {
		return cases.get(index);
	}

	/**
	 * Set an case node to index
	 * 
	 * @param index
	 *            the index of the node
	 * @param node
	 *            the node
	 */
	public void setCase(int index, CaseNode node) {
		cases.set(index, node);
	}

	/**
	 * Remove the node at index
	 * 
	 * @param index
	 *            the index to remove at
	 */

	public void removeCase(int index) {
		cases.remove(index);
	}

	/**
	 * Find the node's index and perform remove action
	 * 
	 * @param node
	 *            the node to remove
	 */
	public void removeCase(CaseNode node) {
		cases.remove(cases.indexOf(node));
	}

	/**
	 * @return the expression
	 */
	public Object getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(Object expression) {
		this.expression = expression;
	}

	/**
	 * @return the cases
	 */
	public DynamicArray<CaseNode> getCases() {
		return cases;
	}

	/**
	 * @param cases
	 *            the cases to set
	 */
	public void setCases(DynamicArray<CaseNode> cases) {
		this.cases = cases;
	}

	/**
	 * @return the address
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(int address) {
		this.address = address;
	}

	public DynamicArray<CaseNode> getAllCases() {
		DynamicArray<CaseNode> array = new DynamicArray<CaseNode>(CaseNode.class);
		for (CaseNode node : cases) {
			array.add(node);
			for (CaseNode child : node.getChilds())
				array.add(child);
		}
		return array;
	}


}
