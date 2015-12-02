package com.wycody.cs2d.analyze;

import com.wycody.cs2d.script.CS2Script;

/**
 * Analyzer is a process that come after finish generating in-purpose to make the basic blocks better, ex "While Loop Detector"
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public abstract class Analyzer {

	/**
	 * The script to run for 
	 */
	protected CS2Script script;
	
	/**
	 * Construct a new {@link Analyzer}
	 * @param script the script to run for
	 */
	public Analyzer(CS2Script script) {
		this.script = script;
	}
	
	/**
	 * Initialize the analyzer
	 */
	public abstract void initialize();
	
	/**
	 * Process the analyzer
	 */
	public abstract void process();
	
	/**
	 * Finalize the analyzer, I know i've spell it wrong, but i dont want override {@link #finalize()} method
	 */
	public abstract void finalyze();
}
