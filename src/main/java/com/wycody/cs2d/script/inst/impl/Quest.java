package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.types.StackType;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Quest {
	
	Function<Object, Object> questFormatter = o -> "getQuest(" + o + ")";
    
    /**
     * Returns the name of the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_NAME = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_NAME)
            .setName("getName")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.OBJECT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the category of the specified quest (normal or holiday-only)
     */
    Supplier<CallMethodInstruction> PUSH_CATEGORY = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_CATEGORY)
            .setName("getCategory")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the difficulty of the specified quest (easy, moderate, experience, master, grandmaster)
     */
    Supplier<CallMethodInstruction> PUSH_DIFFICULTY = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_DIFFICULTY)
            .setName("getDifficulty")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the specified quest is members-only
     */
    Supplier<CallMethodInstruction> PUSH_MEMBERS = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEMBERS)
            .setName("isMembers")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of quest points awarded when the specified quest is completed
     */
    Supplier<CallMethodInstruction> PUSH_REWARD_POINTS = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_REWARD_POINTS)
            .setName("getRewardPoints")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of quests required to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_QUESTREQ_COUNT = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_QUESTREQ_COUNT)
            .setName("getQuestRequirementCount")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the ID of the quest requirement at the specified slot needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_QUESTREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_QUESTREQ)
            .setName("getQuestRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets the specified quest requirement for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_QUESTREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_QUESTREQ)
            .setName("meetsQuestRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of quest points needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_POINTSREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_POINTSREQ)
            .setName("getQuestPointRequirement")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets the quest point requirement for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_POINTSREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_POINTSREQ)
            .setName("meetsQuestPointRequirement")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of skills needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_STATREQ_COUNT = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_STATREQ_COUNT)
            .setName("getSkillRequirementCount")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the id of the skill at the specified slot needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_STATREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_STATREQ)
            .setName("getSkillRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the minimum level of the skill at the specified slot needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_STATREQ_LEVEL = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_STATREQ_LEVEL)
            .setName("getSkillRequirementLevel")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets the specified skill requirement for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_STATREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_STATREQ)
            .setName("meetsSkillRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of player variables (varps) considered when listing requirements for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_VARPREQ_COUNT = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_VARPREQ_COUNT)
            .setName("getVarpRequirementCount")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the name of the variable at the specified slot needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_VARPREQ_NAME = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_VARPREQ_NAME)
            .setName("getVarpRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.OBJECT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets the specified varp requirement for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_VARPREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_VARPREQ)
            .setName("meetsVarpRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the number of player variables bits (varbits) considered when listing requirements for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_VARBITREQ_COUNT = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_VARBITREQ_COUNT)
            .setName("getVarbitRequirementCount")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns the name of the variable bit at the specified slot needed to complete the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_VARBITREQ_NAME = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_VARBITREQ_NAME)
            .setName("getVarbitRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.OBJECT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets the specified varbit requirement for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_VARBITREQ = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_VARBITREQ)
            .setName("meetsVarbitRequirement")
            .setArgumentTypes(StackType.INT, StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player meets all requirements for the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_MEETS_REQS = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_MEETS_REQS)
            .setName("meetsRequirements")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player has started the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_STARTED = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_STARTED)
            .setName("hasStarted")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Returns whether the active player has completed the specified quest
     */
    Supplier<CallMethodInstruction> PUSH_COMPLETED = () ->
        new CallMethodInstruction(InstructionType.PUSH_QUEST_COMPLETED)
            .setName("hasCompleted")
            .setArgumentTypes(StackType.INT)
            .setPushType(StackType.INT)
            .setPrefixFormatters(questFormatter);
    
    /**
     * Gets the value of the specified quest config parameter. Returns either string or int depending on the parameter type
     */
    Supplier<PushParamInstruction> PUSH_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_QUEST_PARAM, "getQuest", StackType.INT);
}
