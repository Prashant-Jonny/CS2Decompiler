package com.wycody.cs2d.script.inst.impl;

import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.CallMethodInstruction;
import com.wycody.cs2d.script.inst.base.EventBindInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.types.StackType;
import java.util.function.Supplier;

public class ActiveWidget {
    
    public static final String AC_PREFIX = "getActiveWidget().";
    
    /**
     * Sets the position of the current active component, in the format (xoff, yoff, xPosMode, yPosMode)
     */
    public static Supplier<CallMethodInstruction> SETPOS = () ->
            new CallMethodInstruction(InstructionType.AC_SETPOS)
                    .setFormattedName(AC_PREFIX+"setPosition(%1, %2, %3, %4)")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.INT,StackType.INT);
    
    /**
     * Sets the width of the current active component, in the format (width, height, xSizeMode, ySizeMode)
     */
    public static Supplier<CallMethodInstruction> SETSIZE = () ->
            new CallMethodInstruction(InstructionType.AC_SETSIZE)
                    .setFormattedName(AC_PREFIX+"setSize(%1, %2, %3, %4)")
                    .setArgumentTypes(StackType.INT,StackType.INT,StackType.INT,StackType.INT);
    
    /**
     * Sets the hidden status of the current active component (1=hidden, 0=visible)
     */
    public static Supplier<CallMethodInstruction> SETHIDE = () ->
            new CallMethodInstruction(InstructionType.AC_SETHIDE)
                    .setFormattedName(AC_PREFIX+"setHidden(%1b)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the aspect ratio of the current active component (format: width, height)
     */
    public static Supplier<CallMethodInstruction> SETASPECTRATIO = () ->
            new CallMethodInstruction(InstructionType.AC_SETASPECTRATIO)
                    .setName(AC_PREFIX+"setAspectRatio")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the solid status of the current active component (0=can click through, 1=solid)
     */
    public static Supplier<CallMethodInstruction> SETSOLID = () ->
            new CallMethodInstruction(InstructionType.AC_SETSOLID)
                    .setFormattedName(AC_PREFIX+"setSolid(%1b)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the current scroll position of the active component (format: xpos, ypos)
     */
    public static Supplier<CallMethodInstruction> SETSCROLLPOS = () ->
            new CallMethodInstruction(InstructionType.AC_SETSCROLLPOS)
                    .setName(AC_PREFIX+"setScrollPosition")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the colour of the active component
     */
    public static Supplier<CallMethodInstruction> SETCOLOUR = () ->
            new CallMethodInstruction(InstructionType.AC_SETCOLOUR)
                    .setFormattedName(AC_PREFIX+"setColour(%1c)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets whether the active box component is filled or just an outline (1=filled, 0=outline)
     */
    public static Supplier<CallMethodInstruction> SETFILLED = () ->
            new CallMethodInstruction(InstructionType.AC_SETFILLED)
                    .setName(AC_PREFIX+"setFilled")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the alpha (transparency) of the active component
     */
    public static Supplier<CallMethodInstruction> SETALPHA = () ->
            new CallMethodInstruction(InstructionType.AC_SETALPHA)
                    .setName(AC_PREFIX+"setAlpha")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the weight of the active line component
     */
    public static Supplier<CallMethodInstruction> SETLINEWEIGHT = () ->
            new CallMethodInstruction(InstructionType.AC_SETLINEWEIGHT)
                    .setName(AC_PREFIX+"setLineWeight")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the graphic (sprite) of the active component
     */
    public static Supplier<CallMethodInstruction> SETGRAPHIC = () ->
            new CallMethodInstruction(InstructionType.AC_SETGRAPHIC)
                    .setName(AC_PREFIX+"setGraphic")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the graphic (sprite) rotation on the active component
     */
    public static Supplier<CallMethodInstruction> SETGRAPHICROTATION = () ->
            new CallMethodInstruction(InstructionType.AC_SETGRAPHICROT)
                    .setName(AC_PREFIX+"setGraphicRotation")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets whether the graphic (sprite) on the active component should be repeated (1=repeat, 0=no repeat)
     */
    public static Supplier<CallMethodInstruction> SETGRAPHICREPEAT = () ->
            new CallMethodInstruction(InstructionType.AC_SETGRAPHICREPEAT)
                    .setName(AC_PREFIX+"setGraphicRepeat")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the model on the active component
     */
    public static Supplier<CallMethodInstruction> SETMODEL = () ->
            new CallMethodInstruction(InstructionType.AC_SETMODEL)
                    .setName(AC_PREFIX+"setModel")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the model constraints on the active component (format: unknown1, unknown2, rotateX, rotateY, rotateZ, zoom)
     */
    public static Supplier<CallMethodInstruction> SETMODELCONSTRAINTS = () ->
            new CallMethodInstruction(InstructionType.AC_SETMODELCONSTRAINTS)
                    .setName(AC_PREFIX+"setModelConstraints")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets the model animation on the active component
     */
    public static Supplier<CallMethodInstruction> SETANIMATION = () ->
            new CallMethodInstruction(InstructionType.AC_SETANIMATION)
                    .setName(AC_PREFIX+"setAnimation")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the text on the active component
     */
    public static Supplier<CallMethodInstruction> SETTEXT = () ->
            new CallMethodInstruction(InstructionType.AC_SETTEXT)
                    .setName(AC_PREFIX+"setText")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sets the text font on the active component
     */
    public static Supplier<CallMethodInstruction> SETFONT = () ->
            new CallMethodInstruction(InstructionType.AC_SETFONT)
                    .setName(AC_PREFIX+"setFont")
                    .setArgumentTypes(StackType.INT);
    
    ////////////////// bookmark
    
    
    /**
     * Sets the text alignment on the active component (format: xalignmode, yalignmode, lineheight)
     */
    public static Supplier<CallMethodInstruction> SETTEXTALIGN = () ->
            new CallMethodInstruction(InstructionType.AC_SETTEXTALIGN)
                    .setName(AC_PREFIX+"setTextAlign")
                    .setArgumentTypes(StackType.INT, StackType.INT, StackType.INT);
    
    /**
     * Sets whether the text on the active component has a shadow (1=shadow, 0=no shadow)
     */
    public static Supplier<CallMethodInstruction> SETSHADED = () ->
            new CallMethodInstruction(InstructionType.AC_SETSHADED)
                    .setName(AC_PREFIX+"setTextShaded")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the background colour of the active component
     */
    public static Supplier<CallMethodInstruction> SETBACKGROUNDCOL = () ->
            new CallMethodInstruction(InstructionType.AC_SETBACKGROUNDCOL)
                    .setName(AC_PREFIX+"setBackgroundColour")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets whether the graphic on the active component should be flipped along the y-axis
     */
    public static Supplier<CallMethodInstruction> SETFLIPY = () ->
            new CallMethodInstruction(InstructionType.AC_SETFLIPY)
                    .setName(AC_PREFIX+"setGraphicFlipY")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets whether the graphic on the active component should be flipped along the x-axis
     */
    public static Supplier<CallMethodInstruction> SETFLIPX = () ->
            new CallMethodInstruction(InstructionType.AC_SETFLIPX)
                    .setName(AC_PREFIX+"setGraphicFlipX")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the scroll boundaries of the active component (format: xbound, ybound)
     */
    public static Supplier<CallMethodInstruction> SETSCROLLBOUNDS = () ->
            new CallMethodInstruction(InstructionType.AC_SETSCROLLBOUNDS)
                    .setName(AC_PREFIX+"setScrollBounds")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the zoom of the active model component
     */
    public static Supplier<CallMethodInstruction> SETMODELZOOM = () ->
            new CallMethodInstruction(InstructionType.AC_SETMODELZOOM)
                    .setName(AC_PREFIX+"setModelZoom")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets whether the active line component is mirrored
     */
    public static Supplier<CallMethodInstruction> SETLINEMIRRORED = () ->
            new CallMethodInstruction(InstructionType.AC_SETLINEMIRRORED)
                    .setName(AC_PREFIX+"setLineMirrored")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the offset of the model within the active component
     */
    public static Supplier<CallMethodInstruction> SETMODELOFFSET = () ->
            new CallMethodInstruction(InstructionType.AC_SETMODELOFFSET)
                    .setName(AC_PREFIX+"setModelOffset")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the number of lines within the active text component
     */
    public static Supplier<CallMethodInstruction> SETTEXTLINECOUNT = () ->
            new CallMethodInstruction(InstructionType.AC_SETTEXTLINECOUNT)
                    .setName(AC_PREFIX+"setTextLineCount")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets an integer parameter for the active component
     */
    public static Supplier<CallMethodInstruction> SETPARAM_INT = () ->
            new CallMethodInstruction(InstructionType.AC_SETPARAM_INT)
                    .setName(AC_PREFIX+"setParam")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets a string parameter for the active component
     */
    public static Supplier<CallMethodInstruction> SETPARAM_STR = () ->
            new CallMethodInstruction(InstructionType.AC_SETPARAM_STR)
                    .setName(AC_PREFIX+"setParam")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT);
    
    /**
     * Sets whether the active component font should be monochrome
     */
    public static Supplier<CallMethodInstruction> SETMONOCHROMEFONT = () ->
            new CallMethodInstruction(InstructionType.AC_SETMONOCHROMEFONT)
                    .setName(AC_PREFIX+"setMonchromeFont")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the object (item) on the active model component
     */
    public static Supplier<CallMethodInstruction> SETOBJECT = () ->
            new CallMethodInstruction(InstructionType.AC_SETOBJECT)
                    .setName(AC_PREFIX+"setObject")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the option text at the given slot for the active component
     */
    public static Supplier<CallMethodInstruction> SETOP = () ->
            new CallMethodInstruction(InstructionType.AC_SETOP)
                    .setName(AC_PREFIX+"setOption")
                    .setArgumentTypes(StackType.INT, StackType.OBJECT);
    
    /**
     * Sets the default slot component for the active component
     */
    public static Supplier<CallMethodInstruction> SETDEFAULTSLOT = () ->
            new CallMethodInstruction(InstructionType.AC_SETDEFAULTSLOT)
                    .setName(AC_PREFIX+"setDefaultSlot")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the content type for the active component
     */
    public static Supplier<CallMethodInstruction> SETCONTENTTYPE = () ->
            new CallMethodInstruction(InstructionType.AC_SETCONTENTTYPE)
                    .setName(AC_PREFIX+"setContentType")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the apply text for the active component, which appears when the object is "used" with another object
     */
    public static Supplier<CallMethodInstruction> SETAPPLYTEXT = () ->
            new CallMethodInstruction(InstructionType.AC_SETAPPLYTEXT)
                    .setName(AC_PREFIX+"setApplyText")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sets the "use" option text for the active component
     */
    public static Supplier<CallMethodInstruction> SETUSEOP = () ->
            new CallMethodInstruction(InstructionType.AC_SETUSEOP)
                    .setName(AC_PREFIX+"setUseOption")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Removes all current options on the active component
     */
    public static Supplier<CallMethodInstruction> CLEAROPS = () ->
            new CallMethodInstruction(InstructionType.AC_CLEAROPS)
                    .setName(AC_PREFIX+"clearOptions");
    
    /**
     * Sets the "use" option cursor for the active component (format: default, validTarget)
     */
    public static Supplier<CallMethodInstruction> SETUSETARGETCUR = () ->
            new CallMethodInstruction(InstructionType.AC_SETUSETARGETCUR)
                    .setFormattedName(AC_PREFIX+"setUseTargetCursors(%1, %2)")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the option cursor at the specified slot for the active component
     */
    public static Supplier<CallMethodInstruction> SETOPCUR = () ->
            new CallMethodInstruction(InstructionType.AC_SETOPCUR)
                    .setName(AC_PREFIX+"setOptionCursor")
                    .setArgumentTypes(StackType.INT, StackType.INT);
    
    /**
     * Sets the default option for the active component
     */
    public static Supplier<CallMethodInstruction> SETDEFAULTOP = () ->
            new CallMethodInstruction(InstructionType.AC_SETDEFAULTOP)
                    .setName(AC_PREFIX+"setDefaultOption")
                    .setArgumentTypes(StackType.OBJECT);
    
    /**
     * Sets the "use" option cursor for the active component
     */
    public static Supplier<CallMethodInstruction> SETUSEOPCUR = () ->
            new CallMethodInstruction(InstructionType.AC_SETUSEOPCUR)
                    .setFormattedName(AC_PREFIX+"setUseOptionCursor(%2)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the default cursor for the active component
     */
    public static Supplier<CallMethodInstruction> SETDEFAULTCUR = () ->
            new CallMethodInstruction(InstructionType.AC_SETDEFAULTCUR)
                    .setFormattedName(AC_PREFIX+"setDefaultCursor(%2)")
                    .setArgumentTypes(StackType.INT);
    
    /**
     * Sets the mouse press event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEPRESS_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEPRESS_HANDLER, "MousePress", true);
    
    /**
     * Sets the mouse drag past event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEDRAGPAST_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEDRAGPAST_HANDLER, "MouseDragPast", true);
    
    /**
     * Sets the mouse release event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSERELEASE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSERELEASE_HANDLER, "MouseRelease", true);
    
    /**
     * Sets the mouse over event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEOVER_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEOVER_HANDLER, "MouseOver", true);
    
    /**
     * Sets the mouse out event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEOUT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEOUT_HANDLER, "MouseOut", true);
    
    /**
     * Sets the drag release event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_DRAGRELEASE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_DRAGRELEASE_HANDLER, "DragRelease", true);
    
    /**
     * Sets the deselect event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_DESELECT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_DESELECT_HANDLER, "Deselect", true);
    
    /**
     * Sets the varp change event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_VARP_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_VARP_HANDLER, "VarpChange", true);
    
    /**
     * Sets the update event handler (always called once per event cycle) for the active component
     */
    public static Supplier<EventBindInstruction> BIND_UPDATE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_UPDATE_HANDLER, "Update", true);
    
    /**
     * Sets the context menu option event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_OPTION_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_OPTION_HANDLER, "Option", true);
    
    /**
     * Sets the interface drag event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_DRAG_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_DRAG_HANDLER, "Drag", true);
    
    /**
     * Sets the mouse drag event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEDRAG_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEDRAG_HANDLER, "MouseDrag", true);
    
    /**
     * Sets the mouse move event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSEMOVE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSEMOVE_HANDLER, "MouseMove", true);
    
    /**
     * Sets the inventory update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_INV_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_INV_HANDLER, "InventoryChange", true);
    
    /**
     * Sets the stat (skill) update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_STAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_STAT_HANDLER, "StatChange", true);
    
    /**
     * Sets the interface select event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_SELECT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_SELECT_HANDLER, "Select", true);
    
    /**
     * Sets the mouse scroll event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_MOUSESCROLL_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_MOUSESCROLL_HANDLER, "MouseScroll", true);
    
    /**
     * Sets the chat update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_CHAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_CHAT_HANDLER, "ChatUpdate", true);
    
    /**
     * Sets the key press event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_KEYPRESS_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_KEYPRESS_HANDLER, "KeyPress", true);
    
    /**
     * Sets the friend list update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_FRIENDLIST_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_FRIENDLIST_HANDLER, "FriendListUpdate", true);
    
    /**
     * Sets the friend chat update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_FRIENDCHAT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_FRIENDCHAT_HANDLER, "FriendChatUpdate", true);
    
    /**
     * Sets the status update (run energy, run weight, system update) event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_STATUS_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_STATUS_HANDLER, "StatusUpdate", true);
    
    /**
     * Sets the attachment event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_ATTACHMENT_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_ATTACHMENT_HANDLER, "Attachment", true);
    
    /**
     * Sets the exchange update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_EXCHANGE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_EXCHANGE_HANDLER, "ExchangeUpdate", true);
    
    /**
     * Sets the resize event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_RESIZE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_RESIZE_HANDLER, "Resize", true);
    
    /**
     * Sets the varc (var client) change event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_VARC_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_VARC_HANDLER, "VarcChange", true);
    
    /**
     * Sets the varcstr (var client string) change event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_VARCSTR_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_VARCSTR_HANDLER, "VarcstrChange", true);
    
    /**
     * Sets the use (component "use with" component) event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_USE_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_USE_HANDLER, "Use", true);
    
    /**
     * Sets the clan settings update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_CLANSETTINGS_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_CLANSETTINGS_HANDLER, "ClanSettingsUpdate", true);
    
    /**
     * Sets the clan channel update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_CLANCHANNEL_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_CLANCHANNEL_HANDLER, "ClanChannelUpdate", true);
    
    /**
     * Sets the var clan change event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_VARCLAN_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_VARCLAN_HANDLER, "VarClanChange", true);    
       
    /**
     * Sets the group channel update event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_GROUPCHANNEL_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_GROUPCHANNEL_HANDLER, "GroupChannelUpdate", true);
    
    /**
     * Sets the var group change event handler for the active component
     */
    public static Supplier<EventBindInstruction> BIND_VARGROUP_HANDLER = () ->
            new EventBindInstruction(InstructionType.AC_SET_VARGROUP_HANDLER, "VarGroupChange", true);    
                
    /**
     * Removes all event bindings on the active component
     */
    public static Supplier<CallMethodInstruction> CLEAR_HANDLERS = () ->
            new CallMethodInstruction(InstructionType.AC_CLEAR_HANDLERS)
                    .setName(AC_PREFIX+"clearEventHandlers");
    
    /**
     * Gets the x-position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_POSX = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_POSX)
                    .setName(AC_PREFIX+"getPositionX")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the y-position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_POSY = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_POSY)
                    .setName(AC_PREFIX+"getPositionY")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the width of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_WIDTH = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_WIDTH)
                    .setName(AC_PREFIX+"getWidth")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the height of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_HEIGHT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_HEIGHT)
                    .setName(AC_PREFIX+"getHeight")
                    .setPushType(StackType.INT);
    
    /**
     * Gets whether the active component is hidden (1=hidden, 0=visible)
     */
    public static Supplier<CallMethodInstruction> PUSH_HIDE = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_HIDE)
                    .setName(AC_PREFIX+"isHidden")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the parent hash of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_PARENT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_PARENT)
                    .setName(AC_PREFIX+"getParent")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the hash of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_HASH = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_HASH)
                    .setName(AC_PREFIX+"getHash")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the colour of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_COL = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_COL)
                    .setName(AC_PREFIX+"getColour")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the horizontal scroll position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_SCROLLX = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_SCROLLX)
                    .setName(AC_PREFIX+"getScrollX")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the vertical scroll position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_SCROLLY = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_SCROLLY)
                    .setName(AC_PREFIX+"getScrollY")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the text of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_TEXT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_TEXT)
                    .setName(AC_PREFIX+"getText")
                    .setPushType(StackType.OBJECT);
    
    /**
     * Gets the maximum horizontal scroll position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_SCROLLWID = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_SCROLLWID)
                    .setName(AC_PREFIX+"getScrollWidth")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the maximum vertical scroll position of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_SCROLLHEI = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_SCROLLHEI)
                    .setName(AC_PREFIX+"getScrollHeight")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the model zoom of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_MODELZOOM = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_MODELZOOM)
                    .setName(AC_PREFIX+"getModelZoom")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the model x-rotation of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_MODELROT_X = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_MODELROT_X)
                    .setName(AC_PREFIX+"getModelRotationX")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the model z-rotation of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_MODELROT_Z = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_MODELROT_Z)
                    .setName(AC_PREFIX+"getModelRotationZ")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the model y-rotation of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_MODELROT_Y = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_MODELROT_Y)
                    .setName(AC_PREFIX+"getModelRotationY")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the alpha (transparency) of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_ALPHA = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_ALPHA)
                    .setName(AC_PREFIX+"getAlpha")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the graphic ID of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_GRAPHIC = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_GRAPHIC)
                    .setName(AC_PREFIX+"getGraphic")
                    .setPushType(StackType.INT);
    
    /**
     * Gets a parameter of the active component. Returns either string or int depending on the parameter type
     */
    public static Supplier<PushParamInstruction> PUSH_PARAM = () ->
            new PushParamInstruction(InstructionType.PUSH_AC_PARAM, x -> AC_PREFIX);
    
    /**
     * Gets the graphic rotation of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_GRAPHICROT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_GRAPHICROT)
                    .setName(AC_PREFIX+"getGraphicRotation")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the model of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_MODEL = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_MODEL)
                    .setName(AC_PREFIX+"getModel")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the font of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_FONT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_FONT)
                    .setName(AC_PREFIX+"getFont")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the slot of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_SLOT = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_SLOT)
                    .setName(AC_PREFIX+"getSlot")
                    .setPushType(StackType.INT);
    
    /**
     * Gets the option at the given slot of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_OP = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_OP)
                    .setName(AC_PREFIX+"getOption")
                    .setArgumentTypes(StackType.INT)
                    .setPushType(StackType.OBJECT);
    
    /**
     * Gets the use name of the active component
     */
    public static Supplier<CallMethodInstruction> PUSH_APPLYNAME = () ->
            new CallMethodInstruction(InstructionType.PUSH_AC_APPLYNAME)
                    .setName(AC_PREFIX+"getApplyName")
                    .setPushType(StackType.INT);
    
}
