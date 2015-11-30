package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Chat {
    Function<Object,Object> chatFormatter = o -> "getChatLine("+o+")";
    Function<Object,Object> quickChatCategoryFormatter = o -> "getQuickChatCategory("+o+")";
    Function<Object,Object> quickChatPhraseFormatter = o -> "getQuickChatPhrase("+o+")";
    
    /**
     * Returns current public chat filter status. 
     */
    Supplier<CallMethodInstruction> PUSH_PUBLICFILTERSTATUS = () ->
            new CallMethodInstruction(InstructionType.PUSH_PUBLICFILTERSTATUS)
                    .setName("getPublicChatStatus")
                    .setPushType(StackType.INT);
    
    /**
     * Sets the current chat filter status (format: public, private, trade)
     */
    Supplier<CallMethodInstruction> SET_CHATFILTERSTATUS = () ->
            new CallMethodInstruction(InstructionType.SET_CHATFILTERSTATUS)
                    .setFormattedName("setChatFilterStatus(%1, %2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sends a snapshot (player abuse report) to the server (format: name, rule, mute, unknown)
     */
    Supplier<CallMethodInstruction> SEND_SNAPSHOT = () ->
            new CallMethodInstruction(InstructionType.SEND_SNAPSHOT)
                    .setFormattedName("setChatFilterStatus(%1, %2, %3, %4)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT, StackType.INT, StackType.OBJECT);
    
    /**
     * Gets the message of the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_MESSAGE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_MESSAGE)
                    .setName("getMessage")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the message type of the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_TYPE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_TYPE)
                    .setName("getType")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Returns current private chat filter status (aka online status). 
     */
    Supplier<CallMethodInstruction> PUSH_PRIVATEFILTERSTATUS = () ->
            new CallMethodInstruction(InstructionType.PUSH_PRIVATEFILTERSTATUS)
                    .setName("getPrivateChatStatus")
                    .setPushType(StackType.INT);
    
    /**
     * Sets the current chat mode (0=public, 1=friend, 2=clan, 3=guest clan, 4=group, 5=group(team))
     */
    Supplier<CallMethodInstruction> SETMODE = () ->
            new CallMethodInstruction(InstructionType.CHAT_SETMODE)
                    .setFormattedName("setChatMode(%1)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sends the specified message (the channel depends on the previously set chat mode)
     */
    Supplier<CallMethodInstruction> MESSAGE_PUBLIC = () ->
            new CallMethodInstruction(InstructionType.SEND_MES_PUBLIC)
                    .setFormattedName("sendPublicMessage(%1)")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sends the specified private message to the specified player
     */
    Supplier<CallMethodInstruction> MESSAGE_PRIVATE = () ->
            new CallMethodInstruction(InstructionType.SEND_MES_PRIVATE)
                    .setFormattedName("sendPrivateMessage(%1, %2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.OBJECT);
    
    /**
     * Gets the name of the user who sent the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_NAME)
                    .setName("getName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the clan prefix of the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_CLAN = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_CLAN)
                    .setName("getClan")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the id of the quick chat message represented by the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_QUICKCHATID = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_QUICKCHATID)
                    .setName("getQuickChatID")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Returns formatted name (including prefix and suffix titles) of the active player. 
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_NAME)
                    .setName("getActivePlayer().getName")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns current private chat filter status (aka online status). 
     */
    Supplier<CallMethodInstruction> PUSH_TRADEFILTERSTATUS = () ->
            new CallMethodInstruction(InstructionType.PUSH_TRADEFILTERSTATUS)
                    .setName("getTradeFilterStatus")
                    .setPushType(StackType.INT);
    
    /**
     * Returns the number of chat lines currently stored in chat history. 
     */
    Supplier<CallMethodInstruction> PUSH_HISTORYSIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_HISTORYSIZE)
                    .setName("getChatHistorySize")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the arguments of the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_ARGS = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_ARGS)
                    .setName("getArgs")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the unfiltered name of the user who sent the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_NAMEUNFILTERED = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_NAMEUNFILTERED)
                    .setName("getNameUnfiltered")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Returns unformated unfiltered name of the active player. 
     */
    Supplier<CallMethodInstruction> PUSH_PLAYER_NAMESIMPLE = () ->
            new CallMethodInstruction(InstructionType.PUSH_PLAYER_NAMESIMPLE)
                    .setName("getActivePlayer().getNameSimple")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Gets the id of the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_ID = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_ID)
                    .setName("getId")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the time the specified chat line was received
     */
    Supplier<CallMethodInstruction> PUSH_TIME = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_TIME)
                    .setName("getTime")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the simple name of the user who sent the specified chat line
     */
    Supplier<CallMethodInstruction> PUSH_NAMESIMPLE = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAT_NAMESIMPLE)
                    .setName("getNameSimple")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(chatFormatter);
    
    /**
     * Gets the name of the specified quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_NAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_NAME)
                    .setName("getName")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the number of subcategories within the specified quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_SUBCATCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_SUBCATCOUNT)
                    .setName("getSubcategoryCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the subcategory at the specified slot within the specified quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_SUBCAT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_SUBCAT)
                    .setFormattedName("%1.getSubcategory(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the number of phrases within the specified quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_PHRASECOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_PHRASECOUNT)
                    .setName("getPhraseCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the phrase at the specified slot within the specified quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_PHRASE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_PHRASE)
                    .setFormattedName("%1.getPhrase(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the template for the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_TEMPLATE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_TEMPLATE)
                    .setName("getTemplate")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Gets the number of possible responses for the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_RESPONSECOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_RESPONSECOUNT)
                    .setName("getResponseCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Gets the response at the specified slot within the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_RESPONSE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_RESPONSE)
                    .setFormattedName("%1.getResponse(%2)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Sets the active quick chat phrase to the specified phrase
     */
    Supplier<CallMethodInstruction> QC_PHRASE_SETACTIVE = () ->
            new CallMethodInstruction(InstructionType.QC_PHRASE_SETACTIVE)
                    .setName("setActive")
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Sends the active quick chat phrase as a public message
     */
    Supplier<CallMethodInstruction> AQC_SENDPUBLIC = () ->
            new CallMethodInstruction(InstructionType.AQC_SENDPUBLIC)
                    .setName("getActiveQuickChat().sendAsPublicMessage");
    
    /**
     * Sends the active quick chat phrase as a private message
     */
    Supplier<CallMethodInstruction> AQC_SENDPRIVATE = () ->
            new CallMethodInstruction(InstructionType.AQC_SENDPRIVATE)
                    .setFormattedName("getActiveQuickChat().sendAsPrivateMessage(%1)")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sends the active quick chat phrase as a friend chat message
     */
    Supplier<CallMethodInstruction> AQC_SENDFRIENDCHAT = () ->
            new CallMethodInstruction(InstructionType.AQC_SENDFRIENDCHAT)
                    .setName("getActiveQuickChat().sendAsFriendChatMessage");
    
    /**
     * Gets the key code for the subcategory at the specified slot within the quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_SUBCAT_KEYCODE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_SUBCAT_KEYCODE)
                    .setFormattedName("%1.getSubCategoryKeyCode(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the key code for the phrase at the specified slot within the quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_PHRASE_KEYCODE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_PHRASE_KEYCODE)
                    .setFormattedName("%1.getPhraseKeyCode(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the subcategory for the specified character key code within the quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_SUBCAT_FORKEYCODE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_SUBCAT_FORKEYCODE)
                    .setFormattedName("%1.getSubCategoryForKeyCode(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the phrase for the specified character key code within the quick chat category
     */
    Supplier<CallMethodInstruction> PUSH_QC_CATEGORY_PHRASE_FORKEYCODE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_CATEGORY_PHRASE_FORKEYCODE)
                    .setFormattedName("%1.getPhraseForKeyCode(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatCategoryFormatter);
    
    /**
     * Gets the number of arguments for the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_ARGCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_ARGCOUNT)
                    .setName("getArgumentCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Gets the argument type at the specified slot for the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_ARGTYPE = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_ARGTYPE)
                    .setFormattedName("%1.getArgumentType(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Sets the specified argument on the active quick chat phrase
     */
    Supplier<CallMethodInstruction> AQC_SETARG = () ->
            new CallMethodInstruction(InstructionType.AQC_SETARG)
                    .setFormattedName("getActiveQuickChat().setArgument(%1, %2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the specified object argument on the active quick chat phrase
     */
    Supplier<CallMethodInstruction> AQC_SETARG_OBJ = () ->
            new CallMethodInstruction(InstructionType.AQC_SETARG_OBJ)
                    .setFormattedName("getActiveQuickChat().setArgument(%1, %2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Gets the specified argument key at the specified slot for the specified quick chat phrase
     */
    Supplier<CallMethodInstruction> PUSH_QC_PHRASE_ARGKEY = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_PHRASE_ARGKEY)
                    .setFormattedName("%1.getArgumentKey(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(quickChatPhraseFormatter);
    
    /**
     * Triggers a search for quick chat phrases containing the specified text (format: searchTerm, isGlobal)
     */
    Supplier<CallMethodInstruction> QC_SEARCH = () ->
            new CallMethodInstruction(InstructionType.QC_SEARCH)
                    .setFormattedName("quickChatSearch(%1, %2b)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the next result of the last quick chat search and increments the search result pointer
     */
    Supplier<CallMethodInstruction> PUSH_QC_SEARCH_NEXTRESULT = () ->
            new CallMethodInstruction(InstructionType.PUSH_QC_SEARCH_NEXTRESULT)
                    .setName("nextQuickChatSearchResult")
                    .setPushType(StackType.INT);
    
    
    /**
     * Returns the quick chat search result pointer to the start.
     */
    Supplier<CallMethodInstruction> QC_SEARCH_RESET = () ->
            new CallMethodInstruction(InstructionType.QC_SEARCH_RESET)
                    .setName("resetQuickChatSearch");
    
    /**
     * Sends the active quick chat phrase as a clan channel message
     */
    Supplier<CallMethodInstruction> AQC_SENDCLANCHANNEL = () ->
            new CallMethodInstruction(InstructionType.AQC_SENDCLANCHANNEL)
                    .setFormattedName("getActiveQuickChat().sendAsClanChannelMessage(%1)")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sends the active quick chat phrase as a guest clan channel message
     */
    Supplier<CallMethodInstruction> AQC_SENDGUESTCLANCHANNEL = () ->
            new CallMethodInstruction(InstructionType.AQC_SENDGUESTCLANCHANNEL)
                    .setFormattedName("getActiveQuickChat().sendAsGuestClanChannelMessage(%1)")
                    .setArgumentTypes(StackType.OBJECT);
    
}
