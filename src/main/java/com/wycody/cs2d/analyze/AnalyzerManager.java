package com.wycody.cs2d.analyze;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.wycody.cs2d.script.CS2Script;

/**
 * A class that's manage to run the analyzers correctly
 * @author Walied-Yassen
 * @date Nov 12, 2015
 */
public class AnalyzerManager {

	/**
	 * The script to run for
	 */
	private CS2Script script;
	
	
	/**
	 * The analyzers list to run
	 */
	private ArrayList<Class<? extends Analyzer>> analyzers;

	/**
	 * Construct a new {@link AnalyzerManager}
	 * @param script the script to run for
	 * @param analyzers the list of analyzers to run
	 */
	public AnalyzerManager(CS2Script script, ArrayList<Class<? extends Analyzer>> analyzers) {
		this.script = script;
		this.analyzers = analyzers;
	}

	/**
	 * Run the analyzers to the script
	 */
	public void run() {
		for(Class<? extends Analyzer> analyzerClass : analyzers) {
			try {
				Analyzer analyzer = analyzerClass.getConstructor(CS2Script.class).newInstance(script);
				analyzer.initialize();
				analyzer.process();
				analyzer.finalyze();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return the script
	 */
	public CS2Script getScript() {
		return script;
	}


	/**
	 * @param script the script to set
	 */
	public void setScript(CS2Script script) {
		this.script = script;
	}


	/**
	 * @return the analyzers
	 */
	public ArrayList<Class<? extends Analyzer>> getAnalyzers() {
		return analyzers;
	}


	/**
	 * @param analyzers the analyzers to set
	 */
	public void setAnalyzers(ArrayList<Class<? extends Analyzer>> analyzers) {
		this.analyzers = analyzers;
	}
	
	
	
}
