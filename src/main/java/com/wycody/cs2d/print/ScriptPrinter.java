package com.wycody.cs2d.print;

import com.wycody.cs2d.script.CS2Script;

/**
 * Handles the output of the decompile process and send it to the printer to
 * print it out
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 */
public abstract class ScriptPrinter {
    /**
     * The current tab indention
     */
    private int indent;

    /**
     * Insert a new line and move to it
     */
    public abstract void newLine();

    /**
     * Print an object after converting to string
     * 
     * @param object
     *            the object you want to print
     */
    public abstract void print(Object object);

    
    public abstract void initializeForScript(CS2Script script);
    
    public abstract void finalizeForScript(CS2Script script);
    
    /**
     * Printing by invoking method {@link #print(Object)} then insert new method
     * by invoking method {@link #newLine()}
     * 
     * @param object
     */
    public void println(Object object) {
        print(object);
        newLine();
    }

    /**
     * @return the indent
     */
    public int getIndent() {
        return indent;
    }

    /**
     * @param indent
     *            the indent to set
     */
    public void setIndent(int indent) {
        if (indent < 0) {
                throw new IllegalArgumentException("Tried to indent for a negative value");
        }
        this.indent = indent;
    }

    /**
     * Convert the indent count to tabs and return it as string
     * 
     * @return the indent as string
     */
    public String getIndentString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
                builder.append("\t");
        }
        return builder.toString();
    }

    /**
     * Increase the indent by one as the tab
     */
    public void tab() {
        indent++;
    }

    /**
     * Decrease the indent by one as the tab
     */
    public void untab() {
        if (indent <= 0) {
                throw new Error("You cannot indent a negative");
        }
        indent--;
    }
}
