package com.wycody.cs2d.script.inst.impl;

import java.util.function.Function;
import java.util.function.Supplier;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.EventBindInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import com.wycody.cs2d.utils.CS2Utils;

public interface Widget {
    Function<Object,Object> widgetFormatter = CS2Utils::getWidget;

    /**
     * Sets the specified component to the active component. Returns 1 if successful, 0 otherwise
     */
    Supplier<CallMethodInstruction> SETACTIVE = () ->
            new CallMethodInstruction(InstructionType.COMP_SETACTIVE)
                    .setName("setActive")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT)
                    .setPrefixFormatters(Widget.widgetFormatter);
    
    
    Supplier<CallMethodInstruction> SHOW_SELECTED_COMPONENT = () ->
        new CallMethodInstruction(InstructionType.SHOW_SELECTED_COMPONENT)
                .setName("showSelectedComponent")
                .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the position of the specified component, in the format (xoff, yoff, xPosMode, yPosMode)
     */
    Supplier<CallMethodInstruction> SETPOS = () ->
            new CallMethodInstruction(InstructionType.COMP_SETPOS)
                    .setFormattedName("%1.setPosition(%2, %3, %4, %5)")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the size of the specified component, in the format (width, height, xSizeMode, ySizeMode)
     */
    Supplier<CallMethodInstruction> SETSIZE = () ->
            new CallMethodInstruction(InstructionType.COMP_SETSIZE)
                    .setFormattedName("%1.setSize(%2, %3, %4, %5)")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.INT,StackType.INT,StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the hidden status of the specified component (1=hidden, 0=visible)
     */
    Supplier<CallMethodInstruction> SETHIDDEN = () ->
            new CallMethodInstruction(InstructionType.COMP_SETHIDE)
                    .setFormattedName("%1.setHidden(%2b)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the aspect ratio of the specified component (format: width, height)
     */
    Supplier<CallMethodInstruction> SETASPECTRATIO = () ->
            new CallMethodInstruction(InstructionType.COMP_SETASPECTRATIO)
                    .setName("setAspectRatio")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the solid status of the specified component (0=can click through, 1=solid)
     */
    Supplier<CallMethodInstruction> SETSOLID = () ->
            new CallMethodInstruction(InstructionType.COMP_SETSOLID)
                    .setFormattedName("%1.setSolid(%2b)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the current scroll position of the specified component (format: xpos, ypos)
     */
    Supplier<CallMethodInstruction> SETSCROLLPOS = () ->
            new CallMethodInstruction(InstructionType.COMP_SETSCROLLPOS)
                    .setName("setScrollPosition")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)            
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the colour of the specified component
     */
    Supplier<CallMethodInstruction> SETCOL = () ->
            new CallMethodInstruction(InstructionType.COMP_SETCOL)
                    .setFormattedName("%1.setColour(%2c)")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the specified box component is filled or just an outline (1=filled, 0=outline)
     */
    Supplier<CallMethodInstruction> SETFILLED = () ->
            new CallMethodInstruction(InstructionType.COMP_SETFILLED)
                    .setFormattedName("%1.setFilled(%2b)")
                    .setArgumentTypes(StackType.INT,StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the alpha (transparency) of the specified component
     */
    Supplier<CallMethodInstruction> SETALPHA = () ->
            new CallMethodInstruction(InstructionType.COMP_SETALPHA)
                    .setFormattedName("%1.setAlpha(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the weight of the specified line component
     */
    Supplier<CallMethodInstruction> SETLINEWEIGHT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETLINEWEIGHT)
                    .setFormattedName("%1.setLineWeight(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the graphic (sprite) of the specified component
     */
    Supplier<CallMethodInstruction> SETGRAPHIC = () ->
            new CallMethodInstruction(InstructionType.COMP_SETGRAPHIC)
                    .setName("setGraphic")
                    .setReverseArgs(false)
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);


    /**
     * Sets the graphic (sprite) rotation on the specified component
     */
    Supplier<CallMethodInstruction> SETGRAPHICROTATION = () ->
            new CallMethodInstruction(InstructionType.COMP_SETGRAPHICROT)
                    .setName("setGraphicRotation")
                    .setReverseArgs(false)
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the graphic (sprite) on the specified component should be repeated (1=repeat, 0=no repeat)
     */
    Supplier<CallMethodInstruction> SETGRAPHICREPEAT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETGRAPHICREPEAT)
                    .setName("setGraphicRepeat")
                    .setReverseArgs(false)
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the model on the specified component
     */
    Supplier<CallMethodInstruction> SETMODEL = () ->
            new CallMethodInstruction(InstructionType.COMP_SETMODEL)
                    .setFormattedName("%1w.setModel(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the model constraints on the specified component (format: unknown1, unknown2, rotateX, rotateY, rotateZ, zoom)
     */
    Supplier<CallMethodInstruction> SETMODELCONSTRAINTS = () ->
            new CallMethodInstruction(InstructionType.COMP_SETMODELCONSTRAINTS)
                    .setFormattedName("%1w.setModelConstraints(%2, %3, %4, %5, %6, %7)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the model animation on the specified component
     */
    Supplier<CallMethodInstruction> SETANIMATION = () ->
            new CallMethodInstruction(InstructionType.COMP_SETANIMATION)
                    .setName("setAnimation")
                    .setReverseArgs(false)
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the text on the specified component
     */
    Supplier<CallMethodInstruction> SETTEXT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETTEXT)
                    .setFormattedName("%1.setText(%2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the text font on the specified component
     */
    Supplier<CallMethodInstruction> SETFONT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETFONT)
                    .setFormattedName("%1.setFont(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);    
    
    /**
     * Sets the text alignment on the specified component (format: xalignmode, yalignmode, lineheight)
     */
    Supplier<CallMethodInstruction> SETTEXTALIGN = () ->
            new CallMethodInstruction(InstructionType.COMP_SETTEXTALIGN)
                    .setFormattedName("%1.setTextAlign(%2, %3, %4)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the text on the specified component has a shadow (1=shadow, 0=no shadow)
     */
    Supplier<CallMethodInstruction> SETSHADED = () ->
            new CallMethodInstruction(InstructionType.COMP_SETSHADED)
                    .setFormattedName("%1.setTextShaded(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the background colour of the specified component
     */
    Supplier<CallMethodInstruction> SETBACKGROUNDCOL = () ->
            new CallMethodInstruction(InstructionType.COMP_SETBACKGROUNDCOL)
                    .setFormattedName("%1.setBackgroundColour(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the graphic on the specified component should be flipped along the y-axis
     */
    Supplier<CallMethodInstruction> SETFLIPY = () ->
            new CallMethodInstruction(InstructionType.COMP_SETFLIPY)
                    .setFormattedName("%1.setGraphicFlipY(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the graphic on the specified component should be flipped along the x-axis
     */
    Supplier<CallMethodInstruction> SETFLIPX = () ->
            new CallMethodInstruction(InstructionType.COMP_SETFLIPX)
                    .setFormattedName("%1.setGraphicFlipX(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the scroll boundaries of the specified component (format: xbound, ybound)
     */
    Supplier<CallMethodInstruction> SETSCROLLBOUNDS = () ->
            new CallMethodInstruction(InstructionType.COMP_SETSCROLLBOUNDS)
                    .setFormattedName("%1.setScrollBounds(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the zoom of the specified model component
     */
    Supplier<CallMethodInstruction> SETMODELZOOM = () ->
            new CallMethodInstruction(InstructionType.COMP_SETMODELZOOM)
                    .setFormattedName("%1.setModelZoom(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the specified line component is mirrored
     */
    Supplier<CallMethodInstruction> SETLINEMIRRORED = () ->
            new CallMethodInstruction(InstructionType.COMP_SETLINEMIRRORED)
                    .setName("setLineMirrored")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the offset of the model within the specified component
     */
    Supplier<CallMethodInstruction> SETMODELOFFSET = () ->
            new CallMethodInstruction(InstructionType.COMP_SETMODELOFFSET)
                    .setName("setModelOffset")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the number of lines within the specified text component
     */
    Supplier<CallMethodInstruction> SETTEXTLINECOUNT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETTEXTLINECOUNT)
                    .setName("setTextLineCount")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets an integer parameter for the specified component
     */
    Supplier<CallMethodInstruction> SETPARAM_INT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETPARAM_INT)
                    .setName("setParam")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets a string parameter for the specified component
     */
    Supplier<CallMethodInstruction> SETPARAM_STR = () ->
            new CallMethodInstruction(InstructionType.COMP_SETPARAM_STR)
                    .setName("setParam")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.OBJECT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets whether the specified component font should be monochrome
     */
    Supplier<CallMethodInstruction> SETMONOCHROMEFONT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETMONOCHROMEFONT)
                    .setFormattedName("%1.setMonchromeFont(%2b)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the object (item) on the specified model component
     */
    Supplier<CallMethodInstruction> SETOBJECT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETOBJECT)
                    .setFormattedName("%1w.setObject(%3, %2)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the context menu option at the given slot for the specified component
     */
    Supplier<CallMethodInstruction> SETOP = () ->
            new CallMethodInstruction(InstructionType.COMP_SETOP)
                    .setFormattedName("%1w.setOption(%2, %3)")
                    .setReverseArgs(false)
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.OBJECT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the default slot component for the specified component
     */
    Supplier<CallMethodInstruction> SETDEFAULTSLOT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETDEFAULTSLOT)
                    .setFormattedName("%1w.setDefaultSlot(%2w, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the content type for the specified component
     */
    Supplier<CallMethodInstruction> SETCONTENTTYPE = () ->
            new CallMethodInstruction(InstructionType.COMP_SETCONTENTTYPE)
                    .setFormattedName("%1w.setContentType(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the apply text for the specified component, which appears when the object is "used" with another object
     */
    Supplier<CallMethodInstruction> SETAPPLYTEXT = () ->
            new CallMethodInstruction(InstructionType.COMP_SETAPPLYTEXT)
                    .setFormattedName("%1w.setApplyText(%2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT);
    
    /**
     * Sets the "use" option text for the specified component
     */
    Supplier<CallMethodInstruction> SETUSEOP = () ->
            new CallMethodInstruction(InstructionType.COMP_SETUSEOP)
                    .setFormattedName("%1w.setUseOption(%2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT);
    
    /**
     * Removes all current options on the specified component
     */
    Supplier<CallMethodInstruction> CLEAROPS = () ->
            new CallMethodInstruction(InstructionType.COMP_CLEAROPS)
                    .setFormattedName("%1w.clearOptions()")
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Sets the "use" option cursor for the specified component (format: default, validTarget)
     */
    Supplier<CallMethodInstruction> SETUSETARGETCUR = () ->
            new CallMethodInstruction(InstructionType.COMP_SETUSETARGETCUR)
                    .setFormattedName("%1w.setUseTargetCursors(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the option cursor at the specified slot for the specified component
     */
    Supplier<CallMethodInstruction> SETOPCUR = () ->
            new CallMethodInstruction(InstructionType.COMP_SETOPCUR)
                    .setFormattedName("%1w.setOptionCursor(%2, %3)")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the default option for the specified component
     */
    Supplier<CallMethodInstruction> SETDEFAULTOP = () ->
            new CallMethodInstruction(InstructionType.COMP_SETDEFAULTOP)
                    .setFormattedName("%1w.setDefaultOption(%2)")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT);
    
    /**
     * Sets the "use" option cursor for the specified component
     */
    Supplier<CallMethodInstruction> SETUSEOPCUR = () ->
            new CallMethodInstruction(InstructionType.COMP_SETUSEOPCUR)
                    .setFormattedName("%1w.setUseOptionCursor(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the default cursor for the specified component
     */
    Supplier<CallMethodInstruction> SETDEFAULTCUR = () ->
            new CallMethodInstruction(InstructionType.COMP_SETDEFAULTCUR)
                    .setFormattedName("%1w.setDefaultCursor(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    /**
     * Sets the mouse press event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSEPRESS_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSEPRESS_HANDLER, "MousePress", false);
    
    /**
     * Sets the mouse drag past event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSEDRAGPAST_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSEDRAGPAST_HANDLER, "MouseDragPast", false);
    
    /**
     * Sets the mouse release event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSERELEASE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSERELEASE_HANDLER, "MouseRelease", false);
    
    /**
     * Sets the mouse over event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSEOVER_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSEOVER_HANDLER, "MouseOver", false);
    
    /**
     * Sets the mouse out event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSE_HOVER_OUT_HANDLER = () ->
 new EventBindInstruction(InstructionType.COMP_SET_MOUSEOUT_HANDLER, "MouseHoverOut", false);
    
    /**
     * Sets the drag release event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_DRAGRELEASE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_DRAGRELEASE_HANDLER, "DragRelease", true);
    
    /**
     * Sets the deselect event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_DESELECT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_DESELECT_HANDLER, "Deselect", false);
    
    /**
     * Sets the varp change event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_VARP_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_VARP_HANDLER, "VarpChange", false);
    
    /**
     * Sets the update event handler (always called once per event cycle) for the specified component
     */
    Supplier<EventBindInstruction> BIND_UPDATE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_UPDATE_HANDLER, "Update", false);
    
    /**
     * Sets the context menu option event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_OPTION_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_OPTION_HANDLER, "Option", false);
    
    /**
     * Sets the interface drag event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_DRAG_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_DRAG_HANDLER, "Drag", false);
    
    /**
     * Sets the mouse drag event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSEDRAG_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSEDRAG_HANDLER, "MouseDrag", false);
    
    /**
     * Sets the mouse move event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSE_HOVER_IN_HANDLER = () ->
 new EventBindInstruction(InstructionType.COMP_SET_MOUSEHOVER_HANDLER, "MouseHoverIn", false);
    
    /**
     * Sets the inventory update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_INV_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_INV_HANDLER, "InventoryChange", false);
    
    /**
     * Sets the stat (skill) update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_STAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_STAT_HANDLER, "StatChange", false);
    
    /**
     * Sets the interface select event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_SELECT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_SELECT_HANDLER, "Select", false);
    
    /**
     * Sets the mouse scroll event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_MOUSESCROLL_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_MOUSESCROLL_HANDLER, "MouseScroll", false);
    
    /**
     * Sets the chat update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_CHAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_CHAT_HANDLER, "ChatUpdate", false);
    
    /**
     * Sets the key press event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_KEYPRESS_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_KEYPRESS_HANDLER, "KeyPress", false);
    
    /**
     * Sets the friend list update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_FRIENDLIST_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_FRIENDLIST_HANDLER, "FriendListUpdate", false);
    
    /**
     * Sets the friend chat update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_FRIENDCHAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_FRIENDCHAT_HANDLER, "FriendChatUpdate", false);
    
    /**
     * Sets the status update (run energy, run weight, system update) event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_STATUS_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_STATUS_HANDLER, "StatusUpdate", false);
    
    /**
     * Sets the attachment event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_ATTACHMENT_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_ATTACHMENT_HANDLER, "Attachment", false);
    
    /**
     * Sets the exchange update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_EXCHANGE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_EXCHANGE_HANDLER, "ExchangeUpdate", false);
    
    /**
     * Sets the resize event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_RESIZE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_RESIZE_HANDLER, "Resize", false);
    
    /**
     * Sets the varc (var client) change event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_VARC_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_VARC_HANDLER, "VarcChange", false);
    
    /**
     * Sets the varcstr (var client string) change event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_VARCSTR_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_VARCSTR_HANDLER, "VarcstrChange", false);
    
    /**
     * Sets the use (component "use with" component) event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_USE_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_USE_HANDLER, "Use", false);
    
    /**
     * Sets the clan settings update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_CLANSETTINGS_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_CLANSETTINGS_HANDLER, "ClanSettingsUpdate", false);
    
    /**
     * Sets the clan channel update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_CLANCHANNEL_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_CLANCHANNEL_HANDLER, "ClanChannelUpdate", false);
    
    /**
     * Sets the var clan change event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_VARCLAN_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_VARCLAN_HANDLER, "VarClanChange", false);    
    
    /**
     * Sets the group channel update event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_GROUPCHANNEL_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_GROUPCHANNEL_HANDLER, "GroupChannelUpdate", false);
    
    /**
     * Sets the var group change event handler for the specified component
     */
    Supplier<EventBindInstruction> BIND_VARGROUP_HANDLER = () ->
            new EventBindInstruction(InstructionType.COMP_SET_VARGROUP_HANDLER, "VarGroupChange", false);    
    
    /**
     * Removes all event bindings on the specified component
     */
    Supplier<CallMethodInstruction> CLEAR_HANDLERS = () ->
            new CallMethodInstruction(InstructionType.COMP_CLEAR_HANDLERS)
                    .setName("clearEventHandlers")
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the x-position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_POSX = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_POSX)
                    .setName("getPositionX")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the y-position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_POSY = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_POSY)
                    .setName("getPositionY")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the width of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_WIDTH = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_WIDTH)
                    .setName("getWidth")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the height of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_HEIGHT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_HEIGHT)
                    .setName("getHeight")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets whether the specified component is hidden (1=hidden, 0=visible)
     */
    Supplier<CallMethodInstruction> PUSH_HIDE = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_HIDE)
                    .setName("isHidden")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the parent hash of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_PARENT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_PARENT)
                    .setName("getParent")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the hash of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_HASH = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_HASH)
                    .setName("getHash")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the colour of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_COL = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_COL)
                    .setName("getColour")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the horizontal scroll position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_SCROLLX = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_SCROLLX)
                    .setName("getScrollX")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the vertical scroll position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_SCROLLY = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_SCROLLY)
                    .setName("getScrollY")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the text of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_TEXT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_TEXT)
                    .setName("getText")
                    .setPushType(StackType.OBJECT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the maximum horizontal scroll position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_SCROLLWID = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_SCROLLWID)
                    .setName("getScrollWidth")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the maximum vertical scroll position of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_SCROLLHEI = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_SCROLLHEI)
                    .setName("getScrollHeight")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the model zoom of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_MODELZOOM = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_MODELZOOM)
                    .setName("getModelZoom")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the model x-rotation of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_MODELROT_X = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_MODELROT_X)
                    .setName("getModelRotationX")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the model z-rotation of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_MODELROT_Z = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_MODELROT_Z)
                    .setName("getModelRotationZ")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the model y-rotation of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_MODELROT_Y = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_MODELROT_Y)
                    .setName("getModelRotationY")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the alpha (transparency) of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_ALPHA = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_ALPHA)
                    .setName("getAlpha")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the graphic ID of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_GRAPHIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_GRAPHIC)
                    .setName("getGraphic")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the graphic rotation of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_GRAPHICROT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_GRAPHICROT)
                    .setName("getGraphicRotation")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the model of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_MODEL = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_MODEL)
                    .setName("getModel")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the font of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_FONT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_FONT)
                    .setName("getFontId")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the size of the specified graphic component, in the format (height, width)
     */
    Supplier<CallMethodInstruction> PUSH_GRAPHICSIZE = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_GRAPHICSIZE)
                    .setName("getGraphicSize")
                    .setPushTypes(StackType.INT, StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the ID of the object on the specified media component
     */
    Supplier<CallMethodInstruction> PUSH_OBJECTID = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_OBJECT)
                    .setName("getObject")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the stack size of the object on the specified media component
     */
    Supplier<CallMethodInstruction> PUSH_OBJCOUNT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_OBJCOUNT)
                    .setName("getObjectCount")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the first empty slot ID on the specified component
     */
    Supplier<CallMethodInstruction> PUSH_EMPTYSLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_EMPTYSLOT)
                    .setName("getFirstEmptySlot")
                    .setPushType(StackType.INT)
                    .setArgumentTypes(StackType.INT)
                    .setPrefixFormatters(widgetFormatter);
    
    /**
     * Gets the target event flags for the specified component
     */
    Supplier<CallMethodInstruction> PUSH_TARGETFLAGS = () ->
            new CallMethodInstruction(InstructionType.PUSH_COMP_TARGETFLAGS)
                    .setFormattedName("%1w.getTargetFlags()")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
    /**
     * Gets the option at the given slot of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_OP = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_OP)
                    .setFormattedName("%1w.getOption(%2)")
                    .setArgumentTypes(StackType.INT, StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Gets the use name of the specified component
     */
    Supplier<CallMethodInstruction> PUSH_APPLYNAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_APPLYNAME)
                    .setFormattedName("%1w.getApplyName()")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.INT);
    
}
