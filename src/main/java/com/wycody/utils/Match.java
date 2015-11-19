package com.wycody.utils;

/**
 * Handles any match of objects, like <code>T</code> is matching another
 * <code>T</code> by address
 * 
 * @author Walied-Yassen
 * @date Nov 19, 2015
 * @param <T>
 *            the type of the match element
 */
public class Match<T> {

	/**
	 * The first element
	 */
	private T first;

	/**
	 * The matched element
	 */
	private T second;

	/**
	 * Construct an empty {@link Match}
	 */
	public Match() {
		this(null, null);
	}

	/**
	 * Construct a new {@link Match}
	 * 
	 * @param first
	 *            the first element
	 * @param second
	 *            the second elment
	 */
	public Match(T first, T second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public T getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(T first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public T getSecond() {
		return second;
	}

	/**
	 * @param second
	 *            the second to set
	 */
	public void setSecond(T second) {
		this.second = second;
	}

}
