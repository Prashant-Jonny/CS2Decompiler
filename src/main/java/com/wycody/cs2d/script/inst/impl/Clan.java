package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Clan {
    Function<Object,Object> memberFormatter = o -> "getActiveClanSettings().getMember("+o+")";
    Function<Object,Object> banFormatter = o -> "getActiveClanSettings().getBan("+o+")";
    Function<Object,Object> userFormatter = o -> "getActiveClanChannel().getUser("+o+")";
    
    /**
     * Sets the active clan settings to the guest clan. Returns 1 if the active clan settings was set, 0 otherwise.
     */
    Supplier<CallMethodInstruction> SETTINGS_SETGUEST = () ->
            new CallMethodInstruction(InstructionType.CLANSETTINGS_SETGUEST)
                    .setName("setGuestClanSettings")
                    .setPushType(StackType.INT);
    
    /**
     * Sets the active clan settings to the main clan (clan which the active player belongs to). Returns 1 if the active clan settings was set, 0 otherwise.
     */
    Supplier<CallMethodInstruction> SETTINGS_SETMAIN = () ->
            new CallMethodInstruction(InstructionType.CLANSETTINGS_SETMAIN)
                    .setName("setMainClanSettings")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the name of the active clan settings.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_NAME)
                    .setName("getActiveClanSettings().getName")
                    .setPushType(StackType.OBJECT);    
    
    /**
     * Returns whether non members are allowed into the active clan. 1 if true, 0 if false.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_ALLOWNONMEMBERS = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_ALLOWNONMEMBERS)
                    .setName("getActiveClanSettings().allowNonMembers")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the minimum rank required to talk in the active clan. 
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_RANKTALK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_RANKTALK)
                    .setName("getActiveClanSettings().getTalkRank")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the minimum rank required to kick guests from the active clan. 
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_RANKKICK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_RANKKICK)
                    .setName("getActiveClanSettings().getKickRank")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the minimum rank required to share loot within the active clan. Not actually used, but identified in a leaked client.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_RANKLOOTSHARE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_RANKLOOTSHARE)
                    .setName("getActiveClanSettings().getLootShareRank")
                    .setPushType(StackType.INT);
        
    /**
     * Returns the number of members belonging to the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBERCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBERCOUNT)
                    .setName("getActiveClanSettings().getMemberCount")
                    .setPushType(StackType.INT);
    /**
     * Returns the name of the member at the specified slot within the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_NAME)
                    .setName("getName")
                    .setPrefixFormatters(memberFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    /**
     * Returns the rank of the member at the specified slot within the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_RANK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_RANK)
                    .setName("getRank")
                    .setPrefixFormatters(memberFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
        
    /**
     * Returns the number of players banned from the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_BANCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_BANCOUNT)
                    .setName("getActiveClanSettings().getBanCount")
                    .setPushType(StackType.INT);
    /**
     * Returns the name of the ban at the specified slot within the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_BAN_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_BAN_NAME)
                    .setName("getName")
                    .setPrefixFormatters(banFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns a part of the member's variable data at the specified slot joined the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_VARBIT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_VARBIT)
                    .setName("getVarBit")
                    .setPrefixFormatters(memberFormatter)
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT);
        
    /**
     * Returns slot of the member who is the active clan owner.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_OWNERSLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_OWNERSLOT)
                    .setName("getActiveClanSettings().getOwnerSlot")
                    .setPushType(StackType.INT);
        
    /**
     * Returns slot of the member who will replace the active clan owner if they leave the clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_REPLACEMENTOWNERSLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_REPLACEMENTOWNERSLOT)
                    .setName("getActiveClanSettings().getReplacementOwnerSlot")
                    .setPushType(StackType.INT);
        
    /**
     * Returns slot of the member with the given display name, or -1 if the member is not in the clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_SLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_SLOT)
                    .setName("getActiveClanSettings().getMemberSlot")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
        
    /**
     * Returns position of the member at the given slot within the alphabetically sorted member list.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_SORTEDSLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_SORTEDSLOT)
                    .setName("getActiveClanSettings().getSortedMemberSlot")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    /**
     * Returns the runeday on which the member at the specified slot joined the active clan.
     */
    Supplier<CallMethodInstruction> PUSH_SETTINGS_MEMBER_JOINDAY = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANSETTINGS_MEMBER_JOINDAY)
                    .setName("getJoinDay")
                    .setPrefixFormatters(memberFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Sets the active clan channel to the guest channel. Returns 1 if the active clan channel was set, 0 otherwise.
     */
    Supplier<CallMethodInstruction> CHANNEL_SETGUEST = () ->
            new CallMethodInstruction(InstructionType.CLANCHANNEL_SETGUEST)
                    .setName("setGuestClanChannel")
                    .setPushType(StackType.INT);
    
    /**
     * Sets the active clan channel to the main channel (clan which the active player belongs to). Returns 1 if the active clan channel was set, 0 otherwise.
     */
    Supplier<CallMethodInstruction> CHANNEL_SETMAIN = () ->
            new CallMethodInstruction(InstructionType.CLANCHANNEL_SETMAIN)
                    .setName("setMainClanChannel")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the name of the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_NAME)
                    .setName("getActiveClanChannel().getName")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the minimum rank required to kick guests from the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_RANKKICK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_RANKKICK)
                    .setName("getActiveClanChannel().getKickRank")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the minimum rank required to talk in the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_RANKTALK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_RANKTALK)
                    .setName("getActiveClanChannel().getTalkRank")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the number of users currently in the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USERCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USERCOUNT)
                    .setName("getActiveClanChannel().getUserCount")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the name of the user at the specified slot within the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USER_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USER_NAME)
                    .setName("getName")
                    .setPrefixFormatters(userFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the name of the user at the specified slot within the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USER_RANK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USER_RANK)
                    .setName("getRank")
                    .setPrefixFormatters(userFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the world ID of the user at the specified slot within the active clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USER_NODEID = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USER_NODEID)
                    .setName("getWorldId")
                    .setPrefixFormatters(userFormatter)
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Sends a request to kick the user at the specified slot from the active clan channel.
     */
    Supplier<CallMethodInstruction> CHANNEL_KICK_USER = () ->
            new CallMethodInstruction(InstructionType.CLANCHANNEL_KICKUSER)
            .setName("kickClanChannelUser").setArgumentTypes(StackType.INT);
        
    /**
     * Returns slot of the user with the given display name, or -1 if the user is not in the clan channel.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USER_SLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USER_SLOT)
                    .setName("getActiveClanChannel().getMemberSlot")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
        
    /**
     * Returns position of the user at the given slot within the alphabetically sorted user list.
     */
    Supplier<CallMethodInstruction> PUSH_CHANNEL_USER_SORTEDSLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_CLANCHANNEL_USER_SORTEDSLOT)
                    .setName("getActiveClanChannel().getSortedMemberSlot")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
        
    /**
     * Returns 1 if varclan is enabled or 0 otherwise.
     */
    Supplier<CallMethodInstruction> PUSH_VARCLAN_ENABLED = () ->
            new CallMethodInstruction(InstructionType.PUSH_VARCLAN_ENABLED)
                    .setName("isVarclanEnabled")
                    .setPushType(StackType.INT);
    
    
}
