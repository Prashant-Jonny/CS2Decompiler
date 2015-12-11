package com.wycody.cs2d.script.inst.impl;

import java.util.function.Supplier;

import com.wycody.cs2d.script.CS2Operator;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.InfixInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

public interface Text {
    Supplier<InfixInstruction> CONCAT_INT = () ->
            new InfixInstruction(InstructionType.STRING_INT_CONCAT,false,CS2Operator.SUM,StackType.OBJECT,StackType.INT,StackType.OBJECT);
    
    Supplier<InfixInstruction> CONCAT = () ->
            new InfixInstruction(InstructionType.STRING_STRING_CONCAT,false,CS2Operator.SUM,StackType.OBJECT,StackType.OBJECT,StackType.OBJECT);
    
    Supplier<CallMethodInstruction> COLOR_TO_CHAT_STR = () ->
            new CallMethodInstruction(InstructionType.INT_COL_STRING)
                    .setName("colorToString")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
     
    
    Supplier<CallMethodInstruction> TO_LOWER = () ->
            new CallMethodInstruction(InstructionType.STRING_LOWER)
                    .setName("toLower")
                    .setFormattedName("%1.toLower()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    Supplier<CallMethodInstruction> IS_ALPHA = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_ALPHA)
                    .setName("isAlpha")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> IS_ALPHA_NUMERIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_ALPHANUMERIC)
                    .setName("isAlphaNumeric")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> IS_BREAK = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_BREAK)
                    .setName("isBreak")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> IS_NUMERIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_IS_NUMERIC)
                    .setName("isNumeric")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> ENCODE_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_ENCODED_STRING)
                    .setName("encode")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    Supplier<CallMethodInstruction> LINE_COUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_TEXT_LINE_COUNT)
                    .setName("countLines")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the width of the provided text line if it was rendered in the given font. Format: (text, maxwidth)
     */
    Supplier<CallMethodInstruction> RENDER_WIDTH_WX = () ->
            new CallMethodInstruction(InstructionType.PUSH_TEXT_RENDER_WIDTH)
                    .setFormattedName("getFont(%1).getRenderWidth(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
            Supplier<CallMethodInstruction> RENDER_WIDTH = () ->
            new CallMethodInstruction(InstructionType.PUSH_TEXT_RENDER_WIDTH)
                    .setFormattedName("getFont(%1).getRenderWidth(%2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT)
                    .setPushType(StackType.INT);
    
        
    Supplier<CallMethodInstruction> RUNEDATE_TO_STRING = () ->
            new CallMethodInstruction(InstructionType.RUNEDAY_STR)
                    .setName("runedayToString")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    //TODO: should make this '+' again.
    Supplier<InfixInstruction> CONCAT_CHAR = () ->
            new InfixInstruction(InstructionType.STRING_CHAR_CONCAT,false,CS2Operator.SUM,StackType.OBJECT,StackType.INT,StackType.OBJECT);
    
    Supplier<CallMethodInstruction> COMPARE = () ->
            new CallMethodInstruction(InstructionType.STR_COMPARE)
                    .setName("strcmp")
                    .setArgumentTypes(StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    Supplier<CallMethodInstruction> CHOICE = () ->
            new CallMethodInstruction(InstructionType.TEXT_CHOICE)
                    .setName("text_choice")
                    .setArgumentTypes(StackType.INT,StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    Supplier<CallMethodInstruction> GENDER = () ->
            new CallMethodInstruction(InstructionType.TEXT_GENDER)
                    .setName("text_gender")
                    .setArgumentTypes(StackType.OBJECT,StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    Supplier<CallMethodInstruction> INT_TO_STR = () ->
            new CallMethodInstruction(InstructionType.INT_STR)
                    .setName("toString")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the length of the given string
     */
    Supplier<CallMethodInstruction> STRLEN = () ->
            new CallMethodInstruction(InstructionType.PUSH_STRLEN)
                    .setName("strlen")
                    .setFormattedName("%1.length()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the substring of the specified string, between the specified positions
     */
    Supplier<CallMethodInstruction> SUBSTR = () ->
            new CallMethodInstruction(InstructionType.PUSH_SUBSTRING)
                    .setFormattedName("%1.substring(%2, %3)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Removes all code tags (anything surrounded by &gt; and &lt;) from the given string
     */
    Supplier<CallMethodInstruction> STRIPCODE = () ->
            new CallMethodInstruction(InstructionType.STRING_STRIPCODE)
                    .setFormattedName("%1.stripCode()")
                    .setArgumentTypes(StackType.OBJECT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the index of the given character within the given string
     */
    Supplier<CallMethodInstruction> INDEXOF_CHAR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INDEXOF_CHAR)
                    .setFormattedName("%1.indexOf(%2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns the index of the given substring within the given string
     */
    Supplier<CallMethodInstruction> INDEXOF_STR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INDEXOF_STRING)
                    .setFormattedName("%1.indexOf(%2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.OBJECT)
                    .setPushType(StackType.INT);
     
    /**
     * Returns the lowercase version of the given character
     */
    Supplier<CallMethodInstruction> CHAR_TOLOWER = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_TOLOWER)
                    .setFormattedName("charToLower(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
     
    /**
     * Returns the uppercase version of the given character
     */
    Supplier<CallMethodInstruction> CHAR_TOUPPER = () ->
            new CallMethodInstruction(InstructionType.PUSH_CHAR_TOUPPER)
                    .setFormattedName("charToUpper(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns a formatted version of the given integer as a string. Format: (number, useSeparators)
     */
    Supplier<CallMethodInstruction> INT_FORMATTEDSTR = () ->
            new CallMethodInstruction(InstructionType.PUSH_INT_FORMATTEDSTR)
                    .setFormattedName("toFormattedString(%2, %1)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Returns the width of the specified string when rendered using the given font. Format: (string, font)
     */
    Supplier<CallMethodInstruction> PUSH_STR_WIDTH = () ->
            new CallMethodInstruction(InstructionType.PUSH_STR_WIDTH)
                    .setFormattedName("getStringWidth(%1, %2)")
                    .setArgumentTypes(StackType.OBJECT, StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Returns a utc timestamp based off the provided time
     */
    Supplier<CallMethodInstruction> UTC_TIMESTAMP = () ->
            new CallMethodInstruction(InstructionType.PUSH_UTC_TIMESTAMP)
                    .setFormattedName("toUTCTimestamp(%1)")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Converts the provided long value into a base-36 string
     */
    Supplier<CallMethodInstruction> LONG_TO_BASE36 = () ->
            new CallMethodInstruction(InstructionType.PUSH_LONG_TO_BASE36STR)
                    .setFormattedName("toBase36string(%1)")
                    .setArgumentTypes(StackType.LONG)
                    .setPushType(StackType.OBJECT);
}