package com.wycody.cs2d.node;

/**
 * This class will be extended by various types, such as: FlowBlock, Instruction
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class Node {

	/**
	 * The next node by this node
	 */
	private Node next;

	/**
	 * The previous node by this node
	 */
	private Node previous;

	/**
	 * Unlink the node
	 */
	public void unlink() {
		if (previous != null) {
			previous.next = next;
			if(next != null) {
				next.previous = previous;
			}
			next = null;
			previous = null;
		}
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * @return the previous
	 */
	public Node getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Node previous) {
		this.previous = previous;
	}

}
