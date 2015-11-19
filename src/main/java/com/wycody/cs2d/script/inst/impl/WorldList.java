package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public class WorldList {
    
    private static final String PREFIX = "getWorldList().";
    
    /**
     * Sends a request to fetch the world list from the server. Returns 1 if the request could not be sent, 0 if the request was successful.
     */
    public static Supplier<CallMethodInstruction> FETCH = () ->
            new CallMethodInstruction(InstructionType.WORLDLIST_FETCH)
                    .setName(PREFIX+"fetch")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the first sorted world list entry and increases the entry position by one.
     * Format: nodeId, flags, activity, countryId, countryName, playerCount, ping, hostname
     */
    public static Supplier<CallMethodInstruction> PUSH_FIRSTENTRY = () ->
            new CallMethodInstruction(InstructionType.PUSH_WORLDLIST_FIRSTENTRY)
                    .setName(PREFIX+"getFirstEntry")
                    .setPushTypes(StackType.INT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.INT, StackType.OBJECT);
    
    /**
     * Returns the next sorted world list entry and increases the entry position by one. 
     * Format: nodeId, flags, activity, countryId, countryName, playerCount, ping, hostname
     */
    public static Supplier<CallMethodInstruction> PUSH_NEXTENTRY = () ->
            new CallMethodInstruction(InstructionType.PUSH_WORLDLIST_NEXTENTRY)
                    .setName(PREFIX+"getNextEntry")
                    .setPushTypes(StackType.INT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.INT, StackType.OBJECT);
    
    /**
     * Sets the specified world as the current active world. Returns 1 if the world was set, 0 otherwise. Format: (nodeId, hostname)
     */
    public static Supplier<CallMethodInstruction> SETACTIVE = () ->
            new CallMethodInstruction(InstructionType.WORLDLIST_SETACTIVE)
                    .setFormattedName(PREFIX+"setSelectedWorld(%1, %2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT)
                    .setPushType(StackType.INT);
    /**
     * Returns the specified sorted world list entry. 
     * Format: nodeId, flags, activity, countryId, countryName, playerCount, ping, hostname
     */
    public static Supplier<CallMethodInstruction> PUSH_ENTRY = () ->
            new CallMethodInstruction(InstructionType.PUSH_WORLDLIST_ENTRY)
                    .setFormattedName(PREFIX+"getEntry(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushTypes(StackType.INT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.OBJECT, StackType.INT, StackType.INT, StackType.OBJECT);
    
    /**
     * Sorts the world list using the format (primaryMode, primaryReversed, secondaryMode, secondaryReversed)
     * Modes:
     * 1 = Player count
     * 2 = Country name
     * 3 = Activity
     * 4 = Lootshare
     * 5 = ? (flag 0x2)
     * 6 = ? (flag 0x4)
     * 7 = Members
     * 8 = Ping
     * Default = Node ID (world ID)
     */
    public static Supplier<CallMethodInstruction> SORT = () ->
            new CallMethodInstruction(InstructionType.WORLDLIST_SORT)
                    .setFormattedName(PREFIX+"sort(%1, %2b, %3, %4b)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Resets the active world to the default (sent with the lobby login)
     */
    public static Supplier<CallMethodInstruction> RESETACTIVE = () ->
            new CallMethodInstruction(InstructionType.WORLDLIST_RESETACTIVE)
                    .setName(PREFIX+"resetActiveWorld");
    
    /**
     * Sets whether ping should be fetched for worlds on the world list (1=fetch ping, 0=don't fetch ping)
     */
    public static Supplier<CallMethodInstruction> SETFETCHPING = () ->
            new CallMethodInstruction(InstructionType.WORLDLIST_SETFETCHPING)
                    .setName(PREFIX+"setFetchPing(%1b)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Returns the flags for the active world.
     * Flags may have any combination of the following values:
     * Bit 0 (0x1) = Members world
     * Bit 3 (0x8) = Lootshare world
     * Bit 4 (0x10) = Activity world?
     * Bit 10 (0x400) = High risk wilderness world
     * Bit 16 (0x10000) = Beta world
     * Bit 20 (0x100000) = VIP world
     */
    public static Supplier<CallMethodInstruction> PUSH_ACTIVEFLAGS = () ->
            new CallMethodInstruction(InstructionType.PUSH_ACTIVEWORLD_FLAGS)
                    .setName("getActiveWorld().getFlags()")
                    .setPushType(StackType.INT);
    
}
