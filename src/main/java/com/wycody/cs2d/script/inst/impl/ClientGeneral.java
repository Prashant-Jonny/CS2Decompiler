package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ClientGeneral {
    Function<Object,Object> invFormatter = o -> "getInventory("+o+")";
    Function<Object,Object> otherPlayerInvFormatter = o -> "getOtherPlayerInventory("+o+")";
    Function<Object,Object> statFormatter = o -> "getSkill("+o+")";
        
    /**
     * Adds the specified message to the chatbox as an unfilterable game message.
     */
    Supplier<CallMethodInstruction> SEND_MESSAGE = () ->
            new CallMethodInstruction(InstructionType.MES)
                    .setFormattedName("showMessage(%1)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Closes the currently open modal (dialog interface).
     */
    Supplier<CallMethodInstruction> CLOSE_MODAL = () ->
            new CallMethodInstruction(InstructionType.CLOSE_MODAL)
                    .setName("closeDialog"); 
    
    /**
     * Sends the number (count) entered by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_COUNTDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_COUNTDIALOG)
                    .setFormattedName("resumePauseCountDialog(%1)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Sends the name entered by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_NAMEDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_NAMEDIALOG)
                    .setFormattedName("resumePauseNameDialog(%1)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Sends the string entered by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_STRINGDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_STRINGDIALOG)
                    .setFormattedName("resumePauseStringDialog(%1)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Sends the specified player option to the server, where it can be handled.
     */
    Supplier<CallMethodInstruction> INVOKE_PLAYER_OPTION = () ->
            new CallMethodInstruction(InstructionType.INVOKE_PLAYER_OPTION)
                    .setFormattedName("invokePlayerOption(%1, %2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT); 
    
    /**
     * Sends the object (item) selected by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_OBJDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_OBJDIALOG)
                    .setFormattedName("resumePauseObjectDialog(%1)")
                    .setArgumentTypes(StackType.INT); 
    
    /**
     * Closes the interface which is a sub of the specified component
     */
    Supplier<CallMethodInstruction> IF_CLOSESUB = () ->
            new CallMethodInstruction(InstructionType.IF_CLOSESUB)
                    .setFormattedName("%1w.closeSub()")
                    .setArgumentTypes(StackType.INT); 
    
    /**
     * Simulates the selected component used on the specified player, triggering any server-side actions.
     */
    Supplier<CallMethodInstruction> INVOKE_USE_ON_PLAYER = () ->
            new CallMethodInstruction(InstructionType.USE_ON_PLAYER)
                    .setFormattedName("invokeUseOnPlayer(%2)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Adds the specified typed message to the chatbox. Format: (type, args, message)
     */
    Supplier<CallMethodInstruction> SEND_MESSAGE_TYPED = () ->
            new CallMethodInstruction(InstructionType.MES_TYPED)
                    .setFormattedName("showTypedMessage(%2, %1, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.OBJECT);  
    
    /**
     * Sends the colour (in hsl format) selected by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_HSLDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_HSLDIALOG)
                    .setFormattedName("resumePauseHslDialog(%1)")
                    .setArgumentTypes(StackType.INT);   
    
    /**
     * Sends the clan forum QFC selected by the user to the server, and causes them to resume whatever they were doing.
     */
    Supplier<CallMethodInstruction> RESUME_P_CLANFORUMQFCDIALOG = () ->
            new CallMethodInstruction(InstructionType.RESUME_P_CLANFORUMQFCDIALOG)
                    .setFormattedName("resumePauseClanForumQfcDialog(%1)")
                    .setArgumentTypes(StackType.OBJECT); 
    
    /**
     * Returns the current client cycle (number of update cycles progressed since the client was launched).
     */
    Supplier<CallMethodInstruction> PUSH_CLIENT_CYCLE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLIENT_CYCLE)
                    .setName("getClientCycle")
                    .setPushType(StackType.INT); 
    
    /**
     * Returns the item ID at the specified slot in the specified inventory, or -1 if no item is in that slot.
     */
    Supplier<CallMethodInstruction> PUSH_INV_SLOTOBJ = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_SLOTOBJ)
                    .setName("getSlotObject")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    /**
     * Returns the number of items at the specified slot in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_SLOTCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_SLOTCOUNT)
                    .setName("getSlotCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    /**
     * Returns the number of items there are of a specified category in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_CATCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_CATCOUNT)
                    .setName("getCategoryCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    /**
     * Returns the number of items with the specified ID in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_OBJCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_OBJCOUNT)
                    .setName("getObjectCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    /**
     * Returns the maximum number of slots available in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_CAPACITY = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_CAPACITY)
                    .setName("getCapacity")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    /**
     * Returns the number of items in the default stock with the specified ID in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_STOCKCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_STOCKCOUNT)
                    .setName("getStockCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    /**
     * Returns the current level (which can be boosted or drained) of the specified skill for the active player.
     */
    Supplier<CallMethodInstruction> PUSH_STAT_LEVEL = () ->
            new CallMethodInstruction(InstructionType.PUSH_STAT_LEVEL)
                    .setName("getLevel")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(statFormatter); 
    
    /**
     * Returns the base level (which is based off xp only) of the specified skill for the active player.
     */
    Supplier<CallMethodInstruction> PUSH_STAT_BASE = () ->
            new CallMethodInstruction(InstructionType.PUSH_STAT_BASE)
                    .setName("getBaseLevel")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(statFormatter);
    
    /**
     * Returns the base level (which is based off xp only) of the specified skill without the f2p cap for the active player.
     */
    Supplier<CallMethodInstruction> PUSH_STAT_NOCAPBASE = () ->
            new CallMethodInstruction(InstructionType.PUSH_STAT_NOCAPBASE)
                    .setName("getNoCapBaseLevel")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(statFormatter);
    
    /**
     * Returns the experience (xp) gained in the specified skill for the active player.
     */
    Supplier<CallMethodInstruction> PUSH_STAT_EXPERIENCE = () ->
            new CallMethodInstruction(InstructionType.PUSH_STAT_EXPERIENCE)
                    .setName("getExperience")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(statFormatter); 
    
    /**
     * Returns the current coordinate of the active player.
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_COORD = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_COORD)
                    .setName("getActivePlayer().getCoord")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the x-component of the specified coordinate.
     */
    Supplier<CallMethodInstruction> PUSH_COORD_X = () ->
            new CallMethodInstruction(InstructionType.PUSH_COORD_X)
                    .setName("getCoordX")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the level-component of the specified coordinate.
     */
    Supplier<CallMethodInstruction> PUSH_COORD_LEVEL = () ->
            new CallMethodInstruction(InstructionType.PUSH_COORD_LEVEL)
                    .setName("getCoordLevel")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the y-component of the specified coordinate.
     */
    Supplier<CallMethodInstruction> PUSH_COORD_Y = () ->
            new CallMethodInstruction(InstructionType.PUSH_COORD_Y)
                    .setName("getCoordY")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns whether the current world (map) is members-only.
     */
    Supplier<CallMethodInstruction> PUSH_MAP_MEMBERS = () ->
            new CallMethodInstruction(InstructionType.PUSH_MAP_MEMBERS)
                    .setName("isMemberWorld")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the item ID at the specified slot in the target player's specified inventory, or -1 if no item is in that slot.
     */
    Supplier<CallMethodInstruction> PUSH_INV_OTHERPLAYER_SLOTOBJ = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_OTHERPLAYER_SLOTOBJ)
                    .setName("getSlotObject")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(otherPlayerInvFormatter); 
    
    /**
     * Returns the number of items at the specified slot in the target player's specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_OTHERPLAYER_SLOTCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_OTHERPLAYER_SLOTCOUNT)
                    .setName("getSlotCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(otherPlayerInvFormatter);
    
    /**
     * Returns the number of items with the specified ID in the target player's specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_OTHERPLAYER_OBJCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_OTHERPLAYER_OBJCOUNT)
                    .setName("getObjectCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(otherPlayerInvFormatter);
    
    /**
     * Returns the rights level (0=normal player, 1=mod, 2> = jmod) for the active player.
     */
    Supplier<CallMethodInstruction> PUSH_RIGHTS = () ->
            new CallMethodInstruction(InstructionType.PUSH_RIGHTS)
                    .setName("getActivePlayer().getRights")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the number of server cycles (600ms) until the next server reboot (system update).
     */
    Supplier<CallMethodInstruction> PUSH_REBOOT_TIMER = () ->
            new CallMethodInstruction(InstructionType.PUSH_REBOOT_TIMER)
                    .setName("getSystemUpdateTime")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the ID of the world the active player is on.
     */
    Supplier<CallMethodInstruction> PUSH_WORLDID = () ->
            new CallMethodInstruction(InstructionType.PUSH_WORLDID)
                    .setName("getWorldId")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the current run energy of the active player.
     */
    Supplier<CallMethodInstruction> PUSH_RUNENERGY = () ->
            new CallMethodInstruction(InstructionType.PUSH_RUNENERGY)
                    .setName("getActivePlayer().getRunEnergy")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the current run weight of the active player.
     */
    Supplier<CallMethodInstruction> PUSH_RUNWEIGHT = () ->
            new CallMethodInstruction(InstructionType.PUSH_RUNWEIGHT)
                    .setName("getActivePlayer().getRunWeight")
                    .setPushType(StackType.INT);
    
    /**
     * Returns whether the active player is a member.
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_ISMEMBER = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_ISMEMBER)
                    .setName("getActivePlayer().isMember")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the combat level of the active player.
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_COMBATLEVEL = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_COMBATLEVEL)
                    .setName("getActivePlayer().getCombatLevel")
                    .setPushType(StackType.INT);
    
    /**
     * Returns whether the active player is female.
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_ISFEMALE = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_ISFEMALE)
                    .setName("getActivePlayer().isFemale")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns whether chat has been restricted (to quick chat only) within the client.
     */
    Supplier<CallMethodInstruction> PUSH_CHAT_RESTRICTED = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_RESTRICTED)
                    .setName("isChatRestricted()")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the number of free slots available in the specified inventory.
     */
    Supplier<CallMethodInstruction> PUSH_INV_FREESLOTCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_FREESLOTCOUNT)
                    .setName("getFreeSlots")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    
    /**
     * Returns the weighted number objects in the specified inventory, where the weight is determined by the specified parameter.
     */
    Supplier<CallMethodInstruction> PUSH_INV_WEIGHTEDSLOTCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_WEIGHTEDSLOTCOUNT)
                    .setName("getWeightedSlotCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter); 
    
    
    /**
     * Returns the weighted number objects in the specified inventory, where the weight is determined by the specified parameter. Stacks count for multiple objects.
     */
    Supplier<CallMethodInstruction> PUSH_INV_WEIGHTEDSLOTCOUNT_STACKS = () ->
            new CallMethodInstruction(InstructionType.PUSH_INV_WEIGHTEDSLOTCOUNT_STACKS)
                    .setName("getWeightedStackedSlotCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(invFormatter);
    
    
    /**
     * Returns the language ID of the client.
     */
    Supplier<CallMethodInstruction> PUSH_LANGUAGE = () ->
            new CallMethodInstruction(InstructionType.PUSH_LANGUAGE)
                    .setName("getLanguage()")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns whether the game applet currently has focus.
     */
    Supplier<CallMethodInstruction> PUSH_APPLET_FOCUSED = () ->
            new CallMethodInstruction(InstructionType.PUSH_APPLET_FOCUSED)
                    .setName("isAppletFocused()")
                    .setPushType(StackType.INT);
    
    
}
