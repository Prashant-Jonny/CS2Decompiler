package com.wycody.cs2d.print;

/**
 * Print the output of the decompile process to the console
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 */
public class ConsolePrinter extends ScriptPrinter {
    private boolean hasIdent=false;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.wycody.cache.print.ScriptPrinter#newLine()
     */
    @Override
    public void newLine() {
        System.out.println();
        hasIdent=false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.wycody.cache.print.ScriptPrinter#print(java.lang.Object)
     */
    @Override
    public void print(Object object) {
        if(!hasIdent){
            System.out.print(getIndentString());
            hasIdent=true;
        }
        System.out.print(object);
    }
}
