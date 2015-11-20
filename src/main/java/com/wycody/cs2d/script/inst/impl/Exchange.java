package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Function;
import java.util.function.Supplier;

public class Exchange {
    private static final Function<Object,Object> exchangeFormatter = o -> "getExchangeOffer("+o+")";
    
    
    /**
     * Returns whether the specified grand exchange offer is sell (1) or buy (0).
     */
    public static Supplier<CallMethodInstruction> PUSH_ISSELL = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_ISSELL)
                    .setName("isSell")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns the object (item) ID for the specified grand exchange offer.
     */
    public static Supplier<CallMethodInstruction> PUSH_OBJID = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_OBJ)
                    .setName("getObjectId")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns the offer price for the specified grand exchange offer.
     */
    public static Supplier<CallMethodInstruction> PUSH_PRICE = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_PRICE)
                    .setName("getPrice")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns the number of objects requested to buy/sell on the specified grand exchange offer.
     */
    public static Supplier<CallMethodInstruction> PUSH_COUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_COUNT)
                    .setName("getCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns the number of objects currently bought/sold on the specified grand exchange offer.
     */
    public static Supplier<CallMethodInstruction> PUSH_COMPLETECOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_COMPLETECOUNT)
                    .setName("getCompletedCount")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns the number of coins currently received for the specified grand exchange offer.
     */
    public static Supplier<CallMethodInstruction> PUSH_COMPLETEGOLD = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_COMPLETEGOLD)
                    .setName("getCompletedGold")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns whether the specified grand exchange offer slot is currently empty.
     */
    public static Supplier<CallMethodInstruction> PUSH_ISEMPTY = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_ISEMPTY)
                    .setName("isEmpty")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns whether the specified grand exchange offer has completed.
     */
    public static Supplier<CallMethodInstruction> PUSH_ISFINISHED = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_ISFINISHED)
                    .setName("isFinished")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
    
    /**
     * Returns whether the specified grand exchange offer is being submitted.
     */
    public static Supplier<CallMethodInstruction> PUSH_ISSUBMITTING = () ->
            new CallMethodInstruction(InstructionType.PUSH_EXCHANGE_OFFER_ISSUBMITTING)
                    .setName("isSubmitting")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(exchangeFormatter);
    
}
