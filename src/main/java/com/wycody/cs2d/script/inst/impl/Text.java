package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.InfixInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public class Text {
    
    public static Supplier<InfixInstruction> CONCAT_INT = () ->
            new InfixInstruction(InstructionType.STRING_INT_CONCAT,false,"+",StackType.OBJECT,StackType.INT,StackType.OBJECT);
    
    public static Supplier<InfixInstruction> CONCAT = () ->
            new InfixInstruction(InstructionType.STRING_STRING_CONCAT,false,"+",StackType.OBJECT,StackType.OBJECT,StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> COLOR_TO_CHAT_STR = () ->
            new CallMethodInstruction(InstructionType.INT_COL_STRING)
                    .setName("colorToString")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
     
    
    public static Supplier<CallMethodInstruction> TO_LOWER = () ->
            new CallMethodInstruction(InstructionType.STRING_LOWER)
                    .setName("toLower")
                    .setFormattedName("%1.toLower()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> IS_ALPHA = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_ALPHA)
                    .setName("isAlpha")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    public static Supplier<CallMethodInstruction> IS_ALPHA_NUMERIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_ALPHANUMERIC)
                    .setName("isAlphaNumeric")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    public static Supplier<CallMethodInstruction> IS_BREAK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_BREAK)
                    .setName("isBreak")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    public static Supplier<CallMethodInstruction> IS_NUMERIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_NUMERIC)
                    .setName("isNumeric")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    public static Supplier<CallMethodInstruction> ENCODE_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENCODED_STRING)
                    .setName("encode")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> LINE_COUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_TEXT_LINE_COUNT)
                    .setName("countLines")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the width of the provided text line if it was rendered in the given font. Format: (text, maxwidth)
     */
    public static Supplier<CallMethodInstruction> RENDER_WIDTH = () ->
            new CallMethodInstruction(InstructionType.PUSH_TEXT_RENDER_WIDTH)
                    .setFormattedName("getFont(%1).getRenderWidth(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    
    public static Supplier<CallMethodInstruction> RUNEDATE_TO_STRING = () ->
            new CallMethodInstruction(InstructionType.RUNEDAY_STR)
                    .setName("runedayToString")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    //TODO: should make this '+' again.
    public static Supplier<InfixInstruction> CONCAT_CHAR = () ->
            new InfixInstruction(InstructionType.STRING_CHAR_CONCAT,false,"+",StackType.OBJECT,StackType.INT,StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> COMPARE = () ->
            new CallMethodInstruction(InstructionType.STR_COMPARE)
                    .setName("strcmp")
                    .setArgumentTypes(StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    public static Supplier<CallMethodInstruction> CHOICE = () ->
            new CallMethodInstruction(InstructionType.TEXT_CHOICE)
                    .setName("text_choice")
                    .setArgumentTypes(StackType.INT,StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> GENDER = () ->
            new CallMethodInstruction(InstructionType.TEXT_GENDER)
                    .setName("text_gender")
                    .setArgumentTypes(StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    public static Supplier<CallMethodInstruction> INT_TO_STR = () ->
            new CallMethodInstruction(InstructionType.INT_STR)
                    .setName("toString")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the length of the given string
     */
    public static Supplier<CallMethodInstruction> STRLEN = () ->
            new CallMethodInstruction(InstructionType.PUSH_STRLEN)
                    .setName("strlen")
                    .setFormattedName("%1.length()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the substring of the specified string, between the specified positions
     */
    public static Supplier<CallMethodInstruction> SUBSTR = () ->
            new CallMethodInstruction(InstructionType.PUSH_SUBSTRING)
                    .setFormattedName("%1.substring(%2, %3)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Removes all code tags (anything surrounded by &gt; and &lt;) from the given string
     */
    public static Supplier<CallMethodInstruction> STRIPCODE = () ->
            new CallMethodInstruction(InstructionType.STRING_STRIPCODE)
                    .setFormattedName("%1.stripCode()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the index of the given character within the given string
     */
    public static Supplier<CallMethodInstruction> INDEXOF_CHAR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INDEXOF_CHAR)
                    .setFormattedName("%1.indexOf(%2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the index of the given substring within the given string
     */
    public static Supplier<CallMethodInstruction> INDEXOF_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INDEXOF_STRING)
                    .setFormattedName("%1.indexOf(%2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.OBJECT)
                    .setPushType(StackType.INT);
     
    /**
     * Returns the lowercase version of the given character
     */
    public static Supplier<CallMethodInstruction> CHAR_TOLOWER = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_TOLOWER)
                    .setFormattedName("charToLower(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
     
    /**
     * Returns the uppercase version of the given character
     */
    public static Supplier<CallMethodInstruction> CHAR_TOUPPER = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_TOUPPER)
                    .setFormattedName("charToUpper(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns a formatted version of the given integer as a string. Format: (number, useSeparators)
     */
    public static Supplier<CallMethodInstruction> INT_FORMATTEDSTR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INT_FORMATTEDSTR)
                    .setFormattedName("toFormattedString(%2, %1)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns a utc timestamp based off the provided time
     */
    public static Supplier<CallMethodInstruction> UTC_TIMESTAMP = () ->
            new CallMethodInstruction(InstructionType.PUSH_UTC_TIMESTAMP)
                    .setFormattedName("toUTCTimestamp(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Converts the provided long value into a base-36 string
     */
    public static Supplier<CallMethodInstruction> LONG_TO_BASE36 = () ->
            new CallMethodInstruction(InstructionType.PUSH_LONG_TO_BASE36STR)
                    .setFormattedName("toBase36string(%1)")
                    .setArgumentTypes(StackType.LONG)
                    .setPushType(StackType.OBJECT);
}