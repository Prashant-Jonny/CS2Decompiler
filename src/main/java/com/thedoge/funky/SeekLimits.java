package com.thedoge.funky;

public interface SeekLimits {

    /**
     * The maximum amount of instructions the parser will attempt to read in order to find the flow target of a while
     * loop.
     */
    int WHILE_LOOP_FIND_CONDITIONAL_LIMIT = 10;
}
