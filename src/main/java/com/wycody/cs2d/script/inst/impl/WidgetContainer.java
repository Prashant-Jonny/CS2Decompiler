package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public interface WidgetContainer {
    /**
     * Creates a new component within the specified component container
     */
    Supplier<CallMethodInstruction> CREATE = () ->
            new CallMethodInstruction(InstructionType.CC_CREATE)
                    .setName("componentContainerCreate")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Removes the active component from its parent component container
     */
    Supplier<CallMethodInstruction> DELETE = () ->
            new CallMethodInstruction(InstructionType.CC_DELETE)
                    .setName("componentContainerDelete");
    
    Supplier<CallMethodInstruction> CLEAR = () ->
            new CallMethodInstruction(InstructionType.CC_CLEAR)
                    .setFormattedName("%1.clearComponentContainer()")
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(Widget.widgetFormatter);
    
    /**
     * Sets a component within a component container to the active component. Returns 1 if successful, 0 otherwise
     */
    Supplier<CallMethodInstruction> SETACTIVE = () ->
            new CallMethodInstruction(InstructionType.CC_SETACTIVE)
                    .setName("setActive")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(Widget.widgetFormatter, x -> "getSlot(" + x + ")");
}
