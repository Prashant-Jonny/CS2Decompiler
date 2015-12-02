package com.wycody.cs2d.script.inst.impl;

import java.util.function.Function;
import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.GIComponentType;

public interface WidgetContainer {
    
    Function<Object,Object> typeFormatter = o -> GIComponentType.forObj(o);
    /**
     * Creates a new component within the specified component container
     */
    Supplier<CallMethodInstruction> CREATE = () -> 
            new CallMethodInstruction(InstructionType.CC_CREATE)
                    .setName("createGIComponent")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setArgumentFormatters(null, typeFormatter);
;
    
    /**
     * Removes the active component from its parent component container
     */
    Supplier<CallMethodInstruction> DELETE = () -> 
            new CallMethodInstruction(InstructionType.CC_DELETE)
                    .setName("deleteGIComponent");
    
    Supplier<CallMethodInstruction> CLEAR = () -> 
            new CallMethodInstruction(InstructionType.CC_CLEAR)
                    .setFormattedName("%1.resetChilds()")
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
