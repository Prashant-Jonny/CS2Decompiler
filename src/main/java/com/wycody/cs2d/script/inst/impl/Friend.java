package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Friend {
    Function<Object,Object> friendFormatter = o -> "getFriend("+o+")";
    Function<Object,Object> ignoreFormatter = o -> "getIgnore("+o+")";
    Function<Object,Object> friendChatUserFormatter = o -> "getFriendChatUser("+o+")";
    
    /**
     * Returns the number of users on the active player's friend list, or -2 if connecting to friend server, -1 if loading. 
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDLIST_SIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDLIST_SIZE)
                    .setName("getFriendCount")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the names of the specified friend, in the format (name, prevName).
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_NAMES = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_NAMES)
                    .setName("getNames")
                    .setArgumentTypes(StackType.INT)
                    .setPushTypes(StackType.OBJECT, StackType.OBJECT)
                    .setPrefixFormatters(friendFormatter); 
    
    /**
     * Returns the world ID of the specified friend.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_NODEID = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_NODEID)
                    .setName("getWorldId")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendFormatter);
    
    /**
     * Returns the rank of the specified friend.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_RANK = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_RANK)
                    .setName("getRank")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendFormatter);
    
    /**
     * Returns the flags of the world the specified friend is currently on.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_WORLDFLAGS = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_WORLDFLAGS)
                    .setName("getWorldFlags")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendFormatter);
    
    /**
     * Requests the rank of the specified friend be changed to the specified value.
     */
    Supplier<CallMethodInstruction> FRIEND_SETRANK = () ->
            new CallMethodInstruction(InstructionType.FRIEND_SETRANK)
                    .setName("setFriendRank")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT);
    
    /**
     * Requests the specified name be added the player's friends list.
     */
    Supplier<CallMethodInstruction> FRIENDLIST_ADD = () ->
            new CallMethodInstruction(InstructionType.FRIENDLIST_ADD)
                    .setName("addFriend")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Removes the specified name from the player's friends list.
     */
    Supplier<CallMethodInstruction> FRIENDLIST_DEL = () ->
            new CallMethodInstruction(InstructionType.FRIENDLIST_DEL)
                    .setName("removeFriend")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Requests the specified name be added the player's ignore list.
     */
    Supplier<CallMethodInstruction> IGNORELIST_ADD = () ->
            new CallMethodInstruction(InstructionType.IGNORELIST_ADD)
                    .setName("addIgnore")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Removes the specified name from the player's ignore list.
     */
    Supplier<CallMethodInstruction> IGNORELIST_DEL = () ->
            new CallMethodInstruction(InstructionType.IGNORELIST_DEL)
                    .setName("removeIgnore")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Returns whether the specified name is on the player's friends list.
     */
    Supplier<CallMethodInstruction> PUSH_IS_FRIEND = () ->
            new CallMethodInstruction(InstructionType.PUSH_IS_FRIEND)
                    .setName("isFriend")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the name of the world the specified friend is currently on.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_WORLDNAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_WORLDNAME)
                    .setName("getWorldName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(friendFormatter);
    
    /**
     * Returns the name of the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_NAME)
                    .setName("getFriendChatName")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the number of users in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USERCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USERCOUNT)
                    .setName("getFriendChatUserCount")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the name of the specified user in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_NAME)
                    .setName("getName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the world ID of the specified user in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_NODEID = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_NODEID)
                    .setName("getWorldId")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the rank of the specified user in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_RANK = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_RANK)
                    .setName("getRank")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the minimum rank needed to kick users from the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_RANKKICK = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_RANKKICK)
                    .setName("getFriendChatKickRank")
                    .setPushType(StackType.INT);
    
    /**
     * Requests the specified user be kicked from the active friends chat.
     */
    Supplier<CallMethodInstruction> FRIENDCHAT_KICKUSER = () ->
            new CallMethodInstruction(InstructionType.FRIENDCHAT_KICKUSER)
                    .setName("kickFriendsChatUser")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Returns the rank of the active player within the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_ACTIVEPLAYERRANK = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_ACTIVEPLAYERRANK)
                    .setName("getFriendChatActivePlayerRank")
                    .setPushType(StackType.INT);
    
    /**
     * Requests the active player joins the specified friends chat.
     */
    Supplier<CallMethodInstruction> FRIENDCHAT_JOIN = () ->
            new CallMethodInstruction(InstructionType.FRIENDCHAT_JOIN)
                    .setName("joinFriendsChat")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Requests the active player leaves the active friends chat.
     */
    Supplier<CallMethodInstruction> FRIENDCHAT_LEAVE = () ->
            new CallMethodInstruction(InstructionType.FRIENDCHAT_LEAVE)
                    .setName("leaveFriendsChat");
    
    /**
     * Returns the number of users on the active player's ignore list. 
     */
    Supplier<CallMethodInstruction> PUSH_IGNORELIST_SIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_IGNORELIST_SIZE)
                    .setName("getIgnoreCount")
                    .setPushType(StackType.INT);   
    
    /**
     * Returns the names of the specified ignore, in the format (name, prevName).
     */
    Supplier<CallMethodInstruction> PUSH_IGNORE_NAMES = () ->
            new CallMethodInstruction(InstructionType.PUSH_IGNORE_NAMES)
                    .setName("getNames")
                    .setArgumentTypes(StackType.INT)
                    .setPushTypes(StackType.OBJECT, StackType.OBJECT)
                    .setPrefixFormatters(ignoreFormatter);
    
    /**
     * Returns whether the specified name is on the player's ignore list.
     */
    Supplier<CallMethodInstruction> PUSH_IS_IGNORED = () ->
            new CallMethodInstruction(InstructionType.PUSH_IS_IGNORED)
                    .setName("isIgnored")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns whether the specified user in the active friends chat is the active player.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_IS_ACTIVEPLAYER = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_IS_ACTIVEPLAYER)
                    .setName("isActivePlayer")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the name of the active friends chat owner.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_OWNER = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_OWNER)
                    .setName("getFriendChatOwner")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the world name of the specified user in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_WORLDNAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_WORLDNAME)
                    .setName("getWorldName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the ID of the game the specified friend is currently playing (0=RuneScape).
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_GAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_GAME)
                    .setName("getGame")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendFormatter);
    
    /**
     * Returns the position of the specified name in the active player's friends list.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_SLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_SLOT)
                    .setName("getFriendSlotByDisplayName")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the position of the specified name in the active player's ignore list.
     */
    Supplier<CallMethodInstruction> PUSH_IGNORE_SLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_IGNORE_SLOT)
                    .setName("getIgnoreSlotByDisplayName")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Requests the specified name be added the player's ignore list until they log out.
     */
    Supplier<CallMethodInstruction> IGNORELIST_ADDTEMP = () ->
            new CallMethodInstruction(InstructionType.IGNORELIST_ADDTEMP)
                    .setName("addTempIgnore")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Returns whether the specified ignore list entry is temporary.
     */
    Supplier<CallMethodInstruction> PUSH_IGNORE_ISTEMP = () ->
            new CallMethodInstruction(InstructionType.PUSH_IGNORE_ISTEMP)
                    .setName("isTemporary")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(ignoreFormatter);
    
    /**
     * Returns the unfiltered name of the specified user in the active friends chat.
     */
    Supplier<CallMethodInstruction> PUSH_FRIENDCHAT_USER_NAMEUNFILTERED = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIENDCHAT_USER_NAMEUNFILTERED)
                    .setName("getNameUnfiltered")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(friendChatUserFormatter);
    
    /**
     * Returns the name of the specified ignore list entry.
     */
    Supplier<CallMethodInstruction> PUSH_IGNORE_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_IGNORE_NAME)
                    .setName("getName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(ignoreFormatter);
    
    /**
     * Returns whether the specified friend was recruited by the player.
     */
    Supplier<CallMethodInstruction> PUSH_FRIEND_RECRUITED = () ->
            new CallMethodInstruction(InstructionType.PUSH_FRIEND_RECRUITED)
                    .setName("isRecruited")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(friendFormatter);
}
