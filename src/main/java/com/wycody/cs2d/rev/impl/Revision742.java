package com.wycody.cs2d.rev.impl;

import java.util.ArrayList;

import com.wycody.cs2d.Context;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.script.CS2Field;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.impl.ActiveWidget;
import com.wycody.cs2d.script.inst.impl.Array;
import com.wycody.cs2d.script.inst.impl.Branch;
import com.wycody.cs2d.script.inst.impl.Camera;
import com.wycody.cs2d.script.inst.impl.Chat;
import com.wycody.cs2d.script.inst.impl.Clan;
import com.wycody.cs2d.script.inst.impl.ClientGeneral;
import com.wycody.cs2d.script.inst.impl.Config;
import com.wycody.cs2d.script.inst.impl.Exchange;
import com.wycody.cs2d.script.inst.impl.Friend;
import com.wycody.cs2d.script.inst.impl.Input;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.Player;
import com.wycody.cs2d.script.inst.impl.Pop;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.Quest;
import com.wycody.cs2d.script.inst.impl.ScriptEnum;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Var;
import com.wycody.cs2d.script.inst.impl.Widget;
import com.wycody.cs2d.script.inst.impl.WidgetContainer;
import com.wycody.cs2d.script.inst.impl.WindowMode;
import com.wycody.cs2d.script.inst.impl.WorldList;
import com.wycody.cs2d.script.inst.swtch.CaseNode;
import com.wycody.cs2d.script.inst.swtch.SwitchBlock;

import net.openrs.cache.Cache;
import net.openrs.io.WrappedByteBuffer;

/************************************
 * 
 * 
 * GET OUT OF THIS FUCKIN CLASS IF YOU're NOT SUNDAYS OR WALIED, JUST TO YOUR REVISION NUB  SRSLY
 * 
 * 
 * 
 * @author Walied-Yassen
 * @date Nov 10, 2015
 */
public class Revision742 extends Revision {
    
    public Revision742(){}

    public Revision742(Cache c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerInstructions() {
        registerInstruction(-1, Unsorted.MISSING);
        
        // Control flow
        registerInstruction(606, Push.PUSH_INT);
        registerInstruction(1044, Var.PUSH_VARP);
        registerInstruction(122, Var.STORE_VARP);
        registerInstruction(145, Push.PUSH_OBJECT);
        registerInstruction(323, Branch.GOTO);        
        registerInstruction(362, Branch.INT_NE);
        registerInstruction(703, Branch.INT_EQ);
        registerInstruction(588, Branch.INT_LT);
        registerInstruction(573, Branch.INT_GT);   
        registerInstruction(2, Unsorted.RETURN);
        registerInstruction(541, Var.PUSH_VARP_BIT);
        registerInstruction(326, Var.STORE_VARP_BIT);
        registerInstruction(443, Branch.INT_LE);
        registerInstruction(1019, Branch.INT_GE);
        registerInstruction(611, Push.LOAD_INT);
        registerInstruction(497, Store.STORE_INT);
        registerInstruction(320, Push.LOAD_OBJ);
        registerInstruction(568, Store.STORE_OBJ);
        registerInstruction(471, Unsorted.CONCAT_STRS);
        registerInstruction(1037, Pop.POP_INT);
        registerInstruction(16, Pop.POP_OBJ);
        registerInstruction(3, Unsorted.CALL_SCRIPT);
        registerInstruction(176, Var.PUSH_VARC);
        registerInstruction(874, Var.STORE_VARC);
        registerInstruction(960, Array.CREATE);
        registerInstruction(32, Push.ARRAY);
        registerInstruction(260, Store.ARRAY);
        registerInstruction(679, Var.PUSH_VARC_STR);
        registerInstruction(671, Var.STORE_VARC_STR);
        registerInstruction(623, Branch.SWITCH);
        registerInstruction(63, Push.PUSH_LONG);
        registerInstruction(285, Pop.POP_LONG);
        registerInstruction(9, Push.LOAD_LONG);
        registerInstruction(99, Store.STORE_LONG);
        registerInstruction(138, Branch.LONG_NE);
        registerInstruction(731, Branch.LONG_EQ);
        registerInstruction(558, Branch.LONG_LT);
        registerInstruction(141, Branch.LONG_GT);
        registerInstruction(30, Branch.LONG_LE);
        registerInstruction(118, Branch.LONG_GE); 
        //TODO: True (1) and false (0) branch instructions        
        registerInstruction(282, Var.PUSH_VAR_CLAN);
        registerInstruction(522, Var.PUSH_VARBIT_CLAN);
        registerInstruction(794, Var.PUSH_VAR_CLAN_LONG);
        registerInstruction(310, Var.PUSH_VAR_CLAN_STRING);        
        registerInstruction(639, Var.PUSH_VARCLANSETTING);
        registerInstruction(41, Var.PUSH_VARCLANSETTING_BIT);
        registerInstruction(95, Var.PUSH_VARCLANSETTING_LONG);
        registerInstruction(556, Var.PUSH_VARCLANSETTING_STR);

        // Component container instructions
        registerInstruction(512, WidgetContainer.CREATE);
        registerInstruction(82, WidgetContainer.DELETE);
        registerInstruction(528, WidgetContainer.CLEAR);
        registerInstruction(532, WidgetContainer.SETACTIVE);
        registerInstruction(371, Widget.SETACTIVE);
        //5x unknown
                
        //Active player instructions
        registerInstruction(240, Player.SET_IDK);
        registerInstruction(22, Player.SET_COLOUR);
        registerInstruction(911, Player.SET_GENDER);
        registerInstruction(47, Player.SET_WORN_OBJECT);
        
        //Active component - position
        registerInstruction(925, ActiveWidget.SETPOS);
        registerInstruction(885, ActiveWidget.SETSIZE);
        registerInstruction(812, ActiveWidget.SETHIDE);
        registerInstruction(431, ActiveWidget.SETASPECTRATIO);
        registerInstruction(511, ActiveWidget.SETSOLID);
        
        //Active component - attribute setters
        registerInstruction(327, ActiveWidget.SETSCROLLPOS);
        registerInstruction(704, ActiveWidget.SETCOLOUR);
        registerInstruction(638, ActiveWidget.SETFILLED);
        registerInstruction(493, ActiveWidget.SETALPHA);
        registerInstruction(672, ActiveWidget.SETLINEWEIGHT);
        registerInstruction(494, ActiveWidget.SETGRAPHIC);
        registerInstruction(689, ActiveWidget.SETGRAPHICROTATION);
        registerInstruction(551, ActiveWidget.SETGRAPHICREPEAT);
        registerInstruction(788, ActiveWidget.SETMODEL);
        registerInstruction(951, ActiveWidget.SETMODELCONSTRAINTS);
        registerInstruction(972, ActiveWidget.SETANIMATION);
        //2x unknown
        registerInstruction(370, ActiveWidget.SETTEXT);
        registerInstruction(829, ActiveWidget.SETFONT);
        registerInstruction(435, ActiveWidget.SETTEXTALIGN);
        registerInstruction(789, ActiveWidget.SETSHADED);
        //2x unknown
        registerInstruction(580, ActiveWidget.SETBACKGROUNDCOL);
        registerInstruction(754, ActiveWidget.SETFLIPY);
        registerInstruction(273, ActiveWidget.SETFLIPX);
        registerInstruction(633, ActiveWidget.SETSCROLLBOUNDS);
        //1x unknown
        registerInstruction(680, ActiveWidget.SETMODELZOOM);
        registerInstruction(739, ActiveWidget.SETLINEMIRRORED);
        registerInstruction(75, ActiveWidget.SETMODELOFFSET);
        registerInstruction(907, ActiveWidget.SETTEXTLINECOUNT);
        registerInstruction(165, ActiveWidget.SETPARAM_INT);
        registerInstruction(537, ActiveWidget.SETPARAM_STR);
        //4x unknown
        registerInstruction(173, ActiveWidget.SETMONOCHROMEFONT);
        //2x unknown
        registerInstruction(462, ActiveWidget.SETOBJECT);
        
        //Active component - interaction setters
        registerInstruction(163, ActiveWidget.SETOP);
        registerInstruction(665, ActiveWidget.SETDEFAULTSLOT);
        registerInstruction(275, ActiveWidget.SETCONTENTTYPE);
        //2x unknown
        registerInstruction(1036, ActiveWidget.SETAPPLYTEXT);
        registerInstruction(1008, ActiveWidget.SETUSEOP);
        registerInstruction(677, ActiveWidget.CLEAROPS);
        registerInstruction(177, ActiveWidget.SETUSETARGETCUR);
        registerInstruction(475, ActiveWidget.SETOPCUR);
        registerInstruction(629, ActiveWidget.SETDEFAULTOP);
        registerInstruction(288, ActiveWidget.SETUSEOPCUR);
        //4x unknown
        registerInstruction(826, ActiveWidget.SETDEFAULTCUR);
        
        //Active component - event binding
        registerInstruction(246, ActiveWidget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(530, ActiveWidget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(442, ActiveWidget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(143, ActiveWidget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(621, ActiveWidget.BIND_MOUSEOUT_HANDLER);
        registerInstruction(889, ActiveWidget.BIND_DRAGRELEASE_HANDLER);
        registerInstruction(647, ActiveWidget.BIND_DESELECT_HANDLER);
        registerInstruction(108, ActiveWidget.BIND_VARP_HANDLER);
        registerInstruction(88, ActiveWidget.BIND_UPDATE_HANDLER);
        registerInstruction(294, ActiveWidget.BIND_OPTION_HANDLER);
        registerInstruction(250, ActiveWidget.BIND_DRAG_HANDLER);
        registerInstruction(134, ActiveWidget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(26, ActiveWidget.BIND_MOUSEMOVE_HANDLER);
        registerInstruction(1043, ActiveWidget.BIND_INV_HANDLER);
        registerInstruction(111, ActiveWidget.BIND_STAT_HANDLER);
        registerInstruction(899, ActiveWidget.BIND_SELECT_HANDLER);
        registerInstruction(152, ActiveWidget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(436, ActiveWidget.BIND_CHAT_HANDLER);
        registerInstruction(805, ActiveWidget.BIND_KEYPRESS_HANDLER);
        registerInstruction(830, ActiveWidget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(170, ActiveWidget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(584, ActiveWidget.BIND_STATUS_HANDLER);
        registerInstruction(957, ActiveWidget.BIND_ATTACHMENT_HANDLER);
        //1x unknown
        registerInstruction(928, ActiveWidget.BIND_EXCHANGE_HANDLER);
        //1x unknown
        registerInstruction(947, ActiveWidget.BIND_RESIZE_HANDLER);
        registerInstruction(437, ActiveWidget.BIND_VARC_HANDLER);
        registerInstruction(424, ActiveWidget.BIND_VARCSTR_HANDLER);
        registerInstruction(878, ActiveWidget.BIND_USE_HANDLER);
        registerInstruction(1025, ActiveWidget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(54, ActiveWidget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(277, ActiveWidget.BIND_VARCLAN_HANDLER);
        registerInstruction(7, ActiveWidget.CLEAR_HANDLERS);
        
        //Active component - layout getters
        registerInstruction(401, ActiveWidget.PUSH_POSX);
        registerInstruction(147, ActiveWidget.PUSH_POSY);
        registerInstruction(868, ActiveWidget.PUSH_WIDTH);
        registerInstruction(127, ActiveWidget.PUSH_HEIGHT);
        registerInstruction(851, ActiveWidget.PUSH_HIDE);
        registerInstruction(823, ActiveWidget.PUSH_PARENT);
        registerInstruction(183, ActiveWidget.PUSH_HASH);
        registerInstruction(835, ActiveWidget.PUSH_COL);
        
        //Active component - attribute getters
        registerInstruction(483, ActiveWidget.PUSH_SCROLLX);
        registerInstruction(1010, ActiveWidget.PUSH_SCROLLY);
        registerInstruction(87, ActiveWidget.PUSH_TEXT);
        registerInstruction(792, ActiveWidget.PUSH_SCROLLWID);
        registerInstruction(244, ActiveWidget.PUSH_SCROLLHEI);
        registerInstruction(461, ActiveWidget.PUSH_MODELZOOM);
        registerInstruction(559, ActiveWidget.PUSH_MODELROT_X);
        registerInstruction(844, ActiveWidget.PUSH_MODELROT_Z);
        registerInstruction(964, ActiveWidget.PUSH_MODELROT_Y);
        registerInstruction(15, ActiveWidget.PUSH_ALPHA);
        //2x unknown
        registerInstruction(1, ActiveWidget.PUSH_GRAPHIC);
        registerInstruction(350, ActiveWidget.PUSH_PARAM);
        registerInstruction(590, ActiveWidget.PUSH_GRAPHICROT);
        registerInstruction(40, ActiveWidget.PUSH_MODEL);
        registerInstruction(976, ActiveWidget.PUSH_FONT);
        //1x unknown
        registerInstruction(154, ActiveWidget.PUSH_FONT);//Not sure why Jagex duplicates this, but whatever...
        //2x unknown
        registerInstruction(49, ActiveWidget.PUSH_SLOT);
        registerInstruction(603, ActiveWidget.PUSH_OP);
        registerInstruction(469, ActiveWidget.PUSH_APPLYNAME);
        
        //Component - position
        registerInstruction(843, Widget.SETPOS);
        registerInstruction(617, Widget.SETSIZE);
        registerInstruction(902, Widget.SETHIDDEN);
        registerInstruction(1011, Widget.SETASPECTRATIO);
        registerInstruction(12, Widget.SETSOLID);
        
        //Component - attribute setters
        registerInstruction(841, Widget.SETSCROLLPOS);
        registerInstruction(654, Widget.SETCOL);
        registerInstruction(761, Widget.SETFILLED);
        registerInstruction(302, Widget.SETALPHA);
        registerInstruction(931, Widget.SETLINEWEIGHT);
        registerInstruction(18, Widget.SETGRAPHIC);
        registerInstruction(408, Widget.SETGRAPHICROTATION);
        registerInstruction(419, Widget.SETGRAPHICREPEAT);
        registerInstruction(520, Widget.SETMODEL);
        registerInstruction(156, Widget.SETMODELCONSTRAINTS);
        registerInstruction(970, Widget.SETANIMATION);
        //2x unknown
        registerInstruction(445, Widget.SETTEXT);
        registerInstruction(308, Widget.SETFONT);
        registerInstruction(913, Widget.SETTEXTALIGN);
        registerInstruction(297, Widget.SETSHADED);
        //2x unknown
        registerInstruction(135, Widget.SETBACKGROUNDCOL);
        registerInstruction(702, Widget.SETFLIPY);
        registerInstruction(139, Widget.SETFLIPX);
        registerInstruction(316, Widget.SETSCROLLBOUNDS);
        //1x unknown
        registerInstruction(1027, Widget.SETMODELZOOM);
        registerInstruction(735, Widget.SETLINEMIRRORED);
        registerInstruction(895, Widget.SETMODELOFFSET);
        registerInstruction(238, Widget.SETTEXTLINECOUNT);
        registerInstruction(355, Widget.SETPARAM_INT);
        registerInstruction(269, Widget.SETPARAM_STR);
        //4x unknown
        registerInstruction(364, Widget.SETMONOCHROMEFONT);
        //1x unknown
        registerInstruction(251, Widget.SETOBJECT);
        
        //Component - interaction
        registerInstruction(641, Widget.SETOP);
        registerInstruction(993, Widget.SETDEFAULTSLOT);
        registerInstruction(85, Widget.SETCONTENTTYPE);
        //2x unknown
        registerInstruction(562, Widget.SETAPPLYTEXT);
        registerInstruction(915, Widget.SETUSEOP);
        registerInstruction(593, Widget.CLEAROPS);
        registerInstruction(876, Widget.SETUSETARGETCUR);
        registerInstruction(510, Widget.SETOPCUR);
        registerInstruction(674, Widget.SETDEFAULTOP);
        registerInstruction(288, Widget.SETUSEOPCUR);
        //4x unknown
        registerInstruction(826, Widget.SETDEFAULTCUR);
        
        //Component - event binding
        registerInstruction(777, Widget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(893, Widget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(564, Widget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(816, Widget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(942, Widget.BIND_MOUSEOUT_HANDLER);
        registerInstruction(900, Widget.BIND_DRAGRELEASE_HANDLER);
        registerInstruction(706, Widget.BIND_DESELECT_HANDLER);
        registerInstruction(1016, Widget.BIND_VARP_HANDLER);
        registerInstruction(328, Widget.BIND_UPDATE_HANDLER);
        registerInstruction(191, Widget.BIND_OPTION_HANDLER);
        registerInstruction(648, Widget.BIND_DRAG_HANDLER);
        registerInstruction(994, Widget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(472, Widget.BIND_MOUSEHOVER_HANDLER);
        registerInstruction(398, Widget.BIND_INV_HANDLER);
        registerInstruction(935, Widget.BIND_STAT_HANDLER);
        registerInstruction(946, Widget.BIND_SELECT_HANDLER);
        registerInstruction(905, Widget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(741, Widget.BIND_CHAT_HANDLER);
        registerInstruction(448, Widget.BIND_KEYPRESS_HANDLER);
        registerInstruction(561, Widget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(325, Widget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(394, Widget.BIND_STATUS_HANDLER);
        registerInstruction(614, Widget.BIND_ATTACHMENT_HANDLER);
        //1x unknown
        registerInstruction(353, Widget.BIND_EXCHANGE_HANDLER);
        //1x unknown
        registerInstruction(89, Widget.BIND_RESIZE_HANDLER);
        registerInstruction(459, Widget.BIND_VARC_HANDLER);
        registerInstruction(241, Widget.BIND_VARCSTR_HANDLER);
        registerInstruction(507, Widget.BIND_USE_HANDLER);
        registerInstruction(71, Widget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(1030, Widget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(927, Widget.BIND_VARCLAN_HANDLER);
        registerInstruction(548, Widget.CLEAR_HANDLERS);
        
        //Component - layout getters
        registerInstruction(213, Widget.PUSH_POSX);
        registerInstruction(775, Widget.PUSH_POSY);
        registerInstruction(452, Widget.PUSH_WIDTH);
        registerInstruction(470, Widget.PUSH_HEIGHT);
        registerInstruction(391, Widget.PUSH_HIDE);
        registerInstruction(422, Widget.PUSH_PARENT);
        registerInstruction(543, Widget.PUSH_HASH);
        registerInstruction(824, Widget.PUSH_COL);
        
        //Component - attribute getters
        registerInstruction(554, Widget.PUSH_SCROLLX);
        registerInstruction(585, Widget.PUSH_SCROLLY);
        registerInstruction(336, Widget.PUSH_TEXT);
        registerInstruction(333, Widget.PUSH_SCROLLWID);
        registerInstruction(406, Widget.PUSH_SCROLLHEI);
        registerInstruction(577, Widget.PUSH_MODELZOOM);
        registerInstruction(23, Widget.PUSH_MODELROT_X);
        registerInstruction(949, Widget.PUSH_MODELROT_Z);
        registerInstruction(334, Widget.PUSH_MODELROT_Y);
        registerInstruction(194, Widget.PUSH_ALPHA);
        //2x unknown
        registerInstruction(1006, Widget.PUSH_GRAPHIC);
        registerInstruction(504, Widget.PUSH_GRAPHICROT);
        registerInstruction(859, Widget.PUSH_MODEL);
        registerInstruction(786, Widget.PUSH_FONT);
        registerInstruction(652, Widget.PUSH_GRAPHICSIZE);
        
        //Component - container getters
        registerInstruction(1003, Widget.PUSH_OBJECTID);
        registerInstruction(107, Widget.PUSH_OBJCOUNT);
        //1x unknown
        registerInstruction(1028, Widget.PUSH_EMPTYSLOT);
        
        //Component - interaction getters
        registerInstruction(810, Widget.PUSH_TARGETFLAGS);
        registerInstruction(423, Widget.PUSH_OP);
        registerInstruction(765, Widget.PUSH_APPLYNAME);
        
        //General action executors
        registerInstruction(420, ClientGeneral.SEND_MESSAGE);
        //1x unknown
        registerInstruction(496, ClientGeneral.CLOSE_MODAL);
        registerInstruction(727, ClientGeneral.RESUME_P_COUNTDIALOG);
        registerInstruction(305, ClientGeneral.RESUME_P_NAMEDIALOG);
        registerInstruction(673, ClientGeneral.RESUME_P_STRINGDIALOG);
        registerInstruction(676, ClientGeneral.INVOKE_PLAYER_OPTION);
        //2x unknown
        registerInstruction(694, ClientGeneral.RESUME_P_OBJDIALOG);
        //1x unknown
        registerInstruction(92, ClientGeneral.IF_CLOSESUB);
        registerInstruction(357, ClientGeneral.INVOKE_USE_ON_PLAYER);
        registerInstruction(661, ClientGeneral.SEND_MESSAGE_TYPED);
        //1x unknown
        registerInstruction(604, ClientGeneral.RESUME_P_HSLDIALOG);
        registerInstruction(332, ClientGeneral.RESUME_P_CLANFORUMQFCDIALOG);
        
        //General getter instructions
        registerInstruction(59, ClientGeneral.PUSH_CLIENT_CYCLE);
        registerInstruction(287, ClientGeneral.PUSH_INV_SLOTOBJ);
        registerInstruction(142, ClientGeneral.PUSH_INV_SLOTCOUNT);
        registerInstruction(206, ClientGeneral.PUSH_INV_OBJCOUNT);
        registerInstruction(189, ClientGeneral.PUSH_INV_CAPACITY);
        registerInstruction(457, ClientGeneral.PUSH_INV_STOCKCOUNT);
        registerInstruction(690, ClientGeneral.PUSH_STAT_LEVEL);
        registerInstruction(169, ClientGeneral.PUSH_STAT_BASE);
        registerInstruction(961, ClientGeneral.PUSH_STAT_EXPERIENCE);
        registerInstruction(365, ClientGeneral.PUSH_PLAYER_COORD);
        registerInstruction(366, ClientGeneral.PUSH_COORD_X);
        registerInstruction(174, ClientGeneral.PUSH_COORD_LEVEL);
        registerInstruction(190, ClientGeneral.PUSH_COORD_Y);
        registerInstruction(123, ClientGeneral.PUSH_MAP_MEMBERS);
        registerInstruction(489, ClientGeneral.PUSH_INV_OTHERPLAYER_SLOTOBJ);
        registerInstruction(377, ClientGeneral.PUSH_INV_OTHERPLAYER_SLOTCOUNT);
        registerInstruction(104, ClientGeneral.PUSH_INV_OTHERPLAYER_OBJCOUNT);
        registerInstruction(664, ClientGeneral.PUSH_RIGHTS);
        registerInstruction(158, ClientGeneral.PUSH_REBOOT_TIMER);
        registerInstruction(159, ClientGeneral.PUSH_WORLDID);
        registerInstruction(84, ClientGeneral.PUSH_RUNENERGY);
        registerInstruction(854, ClientGeneral.PUSH_RUNWEIGHT);
        //2x unknown
        registerInstruction(776, ClientGeneral.PUSH_PLAYER_ISMEMBER);
        registerInstruction(681, ClientGeneral.PUSH_PLAYER_COMBATLEVEL);
        registerInstruction(464, ClientGeneral.PUSH_PLAYER_ISFEMALE);
        registerInstruction(237, ClientGeneral.PUSH_CHAT_RESTRICTED);
        registerInstruction(660, ClientGeneral.PUSH_INV_FREESLOTCOUNT);
        registerInstruction(774, ClientGeneral.PUSH_INV_WEIGHTEDSLOTCOUNT);
        registerInstruction(186, ClientGeneral.PUSH_INV_WEIGHTEDSLOTCOUNT_STACKS);
        registerInstruction(346, ClientGeneral.PUSH_LANGUAGE);
        
        //Enum instructions
        registerInstruction(14, ScriptEnum.PUSH_VALUE_STR);
        registerInstruction(467, ScriptEnum.LEGACY_PUSH_VALUE);
        registerInstruction(57, ScriptEnum.PUSH_CONTAINS_INT);
        registerInstruction(628, ScriptEnum.PUSH_CONTAINS_STR);
        registerInstruction(779, ScriptEnum.PUSH_SIZE);
        registerInstruction(736, ScriptEnum.PUSH_INTVALKEYS_COUNT);
        registerInstruction(550, ScriptEnum.PUSH_STRVALKEYS_COUNT);
        registerInstruction(981, ScriptEnum.PUSH_INTVALKEY);
        registerInstruction(787, ScriptEnum.PUSH_STRVALKEY);
        
        //Friend (includes ignore and friendchat)
        registerInstruction(601, Friend.PUSH_FRIENDLIST_SIZE);
        registerInstruction(714, Friend.PUSH_FRIEND_NAMES);
        registerInstruction(281, Friend.PUSH_FRIEND_NODEID);
        registerInstruction(968, Friend.PUSH_FRIEND_RANK);
        registerInstruction(441, Friend.PUSH_FRIEND_WORLDFLAGS);
        registerInstruction(526, Friend.FRIEND_SETRANK);
        registerInstruction(43, Friend.FRIENDLIST_ADD);
        registerInstruction(1014, Friend.FRIENDLIST_DEL);
        registerInstruction(1009, Friend.IGNORELIST_ADD);
        registerInstruction(733, Friend.IGNORELIST_DEL);
        registerInstruction(806, Friend.PUSH_IS_FRIEND);
        registerInstruction(193, Friend.PUSH_FRIEND_WORLDNAME);
        registerInstruction(712, Friend.PUSH_FRIENDCHAT_NAME);
        registerInstruction(324, Friend.PUSH_FRIENDCHAT_USERCOUNT);
        registerInstruction(814, Friend.PUSH_FRIENDCHAT_USER_NAME);
        registerInstruction(790, Friend.PUSH_FRIENDCHAT_USER_NODEID);
        registerInstruction(875, Friend.PUSH_FRIENDCHAT_USER_RANK);
        registerInstruction(196, Friend.PUSH_FRIENDCHAT_RANKKICK);
        registerInstruction(259, Friend.FRIENDCHAT_KICKUSER);
        registerInstruction(817, Friend.PUSH_FRIENDCHAT_ACTIVEPLAYERRANK);
        registerInstruction(861, Friend.FRIENDCHAT_JOIN);
        registerInstruction(986, Friend.FRIENDCHAT_LEAVE);
        registerInstruction(172, Friend.PUSH_IGNORELIST_SIZE);
        registerInstruction(634, Friend.PUSH_IGNORE_NAMES);
        registerInstruction(688, Friend.PUSH_IS_IGNORED);
        registerInstruction(912, Friend.PUSH_FRIENDCHAT_USER_IS_ACTIVEPLAYER);
        registerInstruction(877, Friend.PUSH_FRIENDCHAT_OWNER);
        registerInstruction(68, Friend.PUSH_FRIENDCHAT_USER_WORLDNAME);
        registerInstruction(153, Friend.PUSH_FRIEND_GAME);
        registerInstruction(83, Friend.PUSH_FRIEND_SLOT);
        registerInstruction(815, Friend.PUSH_IGNORE_SLOT);
        //1x unknown (client paramater)
        registerInstruction(53, Friend.IGNORELIST_ADDTEMP);
        registerInstruction(962, Friend.PUSH_IGNORE_ISTEMP);
        registerInstruction(827, Friend.PUSH_FRIENDCHAT_USER_NAMEUNFILTERED);
        registerInstruction(495, Friend.PUSH_IGNORE_NAME);
        registerInstruction(460, Friend.PUSH_FRIEND_RECRUITED);
        
        //Clan (settings and channel)
        registerInstruction(216, Clan.SETTINGS_SETGUEST);
        registerInstruction(219, Clan.SETTINGS_SETMAIN);
        registerInstruction(745, Clan.PUSH_SETTINGS_NAME);
        registerInstruction(349, Clan.PUSH_SETTINGS_ALLOWNONMEMBERS);
        registerInstruction(249, Clan.PUSH_SETTINGS_RANKTALK);
        registerInstruction(352, Clan.PUSH_SETTINGS_RANKKICK);
        registerInstruction(626, Clan.PUSH_SETTINGS_RANKLOOTSHARE);
        registerInstruction(586, Clan.PUSH_SETTINGS_MEMBERCOUNT);
        registerInstruction(607, Clan.PUSH_SETTINGS_MEMBER_NAME);
        registerInstruction(914, Clan.PUSH_SETTINGS_MEMBER_RANK);
        registerInstruction(932, Clan.PUSH_SETTINGS_BANCOUNT);
        registerInstruction(503, Clan.PUSH_SETTINGS_BAN_NAME);
        registerInstruction(339, Clan.PUSH_SETTINGS_MEMBER_VARBIT);
        registerInstruction(411, Clan.PUSH_SETTINGS_OWNERSLOT);
        registerInstruction(317, Clan.PUSH_SETTINGS_REPLACEMENTOWNERSLOT);
        registerInstruction(980, Clan.PUSH_SETTINGS_MEMBER_SLOT);
        registerInstruction(685, Clan.PUSH_SETTINGS_MEMBER_SORTEDSLOT);
        registerInstruction(992, Clan.PUSH_SETTINGS_MEMBER_JOINDAY);
        registerInstruction(389, Clan.CHANNEL_SETGUEST);
        registerInstruction(380, Clan.CHANNEL_SETMAIN);
        registerInstruction(361, Clan.PUSH_CHANNEL_NAME);
        registerInstruction(644, Clan.PUSH_CHANNEL_RANKKICK);
        registerInstruction(640, Clan.PUSH_CHANNEL_RANKTALK);
        registerInstruction(898, Clan.PUSH_CHANNEL_USERCOUNT);
        registerInstruction(553, Clan.PUSH_CHANNEL_USER_NAME);
        registerInstruction(430, Clan.PUSH_CHANNEL_USER_RANK);
        registerInstruction(373, Clan.PUSH_CHANNEL_USER_NODEID);
        registerInstruction(753, Clan.CHANNEL_KICK_USER);
        registerInstruction(214, Clan.PUSH_CHANNEL_USER_SLOT);
        registerInstruction(449, Clan.PUSH_CHANNEL_USER_SORTEDSLOT);
        registerInstruction(239, Clan.PUSH_VARCLAN_ENABLED);
        
        //Exchange instructions
        registerInstruction(490, Exchange.PUSH_ISSELL);
        registerInstruction(545, Exchange.PUSH_OBJID);
        registerInstruction(466, Exchange.PUSH_PRICE);
        registerInstruction(491, Exchange.PUSH_COUNT);
        registerInstruction(155, Exchange.PUSH_COMPLETECOUNT);
        registerInstruction(381, Exchange.PUSH_COMPLETEGOLD);
        registerInstruction(215, Exchange.PUSH_ISEMPTY);
        //1x unknown
        registerInstruction(298, Exchange.PUSH_ISFINISHED);
        registerInstruction(181, Exchange.PUSH_ISSUBMITTING);
        
                
        //Arithmatic (math) instructions
        registerInstruction(998, Math.SUM);
        registerInstruction(212, Math.SUBTRACT);
        registerInstruction(870, Math.MULTIPLY);
        registerInstruction(487, Math.DIVIDE);
        registerInstruction(909, Math.RANDOM);
        registerInstruction(939, Math.SET_BIT);
        registerInstruction(882, Math.RESET_BIT);
        registerInstruction(207, Math.TEST_BIT);
        registerInstruction(752, Math.MODULO);
        registerInstruction(897, Math.POWER);
        registerInstruction(128, Math.ROOT);
        registerInstruction(1040, Math.BITWISE_AND);
        registerInstruction(283, Math.BITWISE_OR);
        registerInstruction(477, Math.MIN);
        registerInstruction(384, Math.MAX);
        registerInstruction(309, Math.FRAC_MULTIPLY);
        registerInstruction(114, Math.HSL_TO_RGB);
        registerInstruction(901, Math.BITWISE_NOT);

        //Text (string) instructions
        registerInstruction(991, Text.CONCAT_INT);
        registerInstruction(715, Text.CONCAT);
        registerInstruction(271, Text.COLOR_TO_CHAT_STR);
        registerInstruction(31, Text.TO_LOWER);
        registerInstruction(579, Text.SUBSTR);
        
        registerInstruction(221, Text.RUNEDATE_TO_STRING);
        registerInstruction(79, Text.GENDER);
        registerInstruction(819, Text.INT_TO_STR);
        registerInstruction(130, Text.COMPARE);
        registerInstruction(944, Text.LINE_COUNT);
        registerInstruction(255, Text.RENDER_WIDTH);
        registerInstruction(958, Text.CHOICE);
        registerInstruction(218, Text.ENCODE_STR);
        registerInstruction(1026, Text.CONCAT_CHAR);
        registerInstruction(686, Text.IS_BREAK);
        registerInstruction(798, Text.IS_ALPHA_NUMERIC);
        registerInstruction(228, Text.IS_ALPHA);
        registerInstruction(896, Text.IS_NUMERIC);
        registerInstruction(105, Text.STRLEN);
        registerInstruction(579, Text.SUBSTR);
        registerInstruction(399, Text.STRIPCODE);
        registerInstruction(563, Text.INDEXOF_CHAR);
        registerInstruction(137, Text.INDEXOF_STR);
        registerInstruction(315, Text.CHAR_TOLOWER);
        registerInstruction(121, Text.CHAR_TOUPPER);
        registerInstruction(682, Text.INT_FORMATTEDSTR);
        //1x unknown
        registerInstruction(405, Text.UTC_TIMESTAMP);
        registerInstruction(953, Text.LONG_TO_BASE36);
        
        //Object config instructions
        registerInstruction(304, Config.PUSH_OBJ_NAME);
        registerInstruction(722, Config.PUSH_OBJ_OP);
        registerInstruction(513, Config.PUSH_OBJ_IOP);
        registerInstruction(921, Config.PUSH_OBJ_COST);
        registerInstruction(645, Config.PUSH_OBJ_STACKABLE);
        registerInstruction(881, Config.PUSH_OBJ_FROMCERT);
        registerInstruction(974, Config.PUSH_OBJ_TOCERT);
        registerInstruction(567, Config.PUSH_OBJ_WEARPOS);
        registerInstruction(185, Config.PUSH_OBJ_WEARTYPE);
        registerInstruction(730, Config.PUSH_OBJ_HIDEWEARPOS);
        registerInstruction(842, Config.PUSH_OBJ_MEMBERS);
        registerInstruction(455, Config.PUSH_OBJ_PARAM);
        registerInstruction(1005, Config.PUSH_OBJ_IOPCUR);
        registerInstruction(658, Config.OBJ_SEARCH);
        registerInstruction(910, Config.PUSH_OBJ_SEARCH_NEXTRESULT);
        registerInstruction(560, Config.OBJ_SEARCH_RESET);
        registerInstruction(1007, Config.PUSH_OBJ_MULTISTACKSIZE);
        registerInstruction(918, Config.OBJ_SEARCH_INTPARAM);
        registerInstruction(959, Config.OBJ_SEARCH_STRPARAM);
        registerInstruction(917, Config.PUSH_OBJ_HASIOPCOL);
        registerInstruction(433, Config.PUSH_OBJ_IOPCOL);
        
        //NPC config instructions
        registerInstruction(270, Config.PUSH_NPC_PARAM);
        
        //Location config instructions
        registerInstruction(242, Config.PUSH_LOC_PARAM);
        
        //Struct config instructions
        registerInstruction(235, Config.PUSH_STRUCT_PARAM);
        
        //Base (render) config instructions
        registerInstruction(720, Config.PUSH_BASE_IDLEANIM);
        
        //Chat instructions
        registerInstruction(254, Chat.PUSH_PUBLICFILTERSTATUS);
        registerInstruction(42, Chat.SET_CHATFILTERSTATUS);
        registerInstruction(718, Chat.SEND_SNAPSHOT);
        registerInstruction(622, Chat.PUSH_MESSAGE);
        registerInstruction(481, Chat.PUSH_TYPE);
        registerInstruction(233, Chat.PUSH_PRIVATEFILTERSTATUS);
        registerInstruction(940, Chat.SETMODE);
        registerInstruction(758, Chat.MESSAGE_PUBLIC);
        registerInstruction(880, Chat.MESSAGE_PRIVATE);
        registerInstruction(61, Chat.PUSH_NAME);
        registerInstruction(923, Chat.PUSH_CLAN);
        registerInstruction(924, Chat.PUSH_QUICKCHATID);
        registerInstruction(1042, Chat.PUSH_PLAYER_NAME);
        registerInstruction(72, Chat.PUSH_TRADEFILTERSTATUS);
        registerInstruction(666, Chat.PUSH_HISTORYSIZE);
        registerInstruction(372, Chat.PUSH_ARGS);
        registerInstruction(257, Chat.PUSH_NAMEUNFILTERED);
        registerInstruction(988, Chat.PUSH_PLAYER_NAMESIMPLE);
        registerInstruction(646, Chat.PUSH_ID);
        registerInstruction(535, Chat.PUSH_TIME);
        registerInstruction(262, Chat.PUSH_NAMESIMPLE);
        registerInstruction(34, Chat.PUSH_QC_CATEGORY_NAME);
        registerInstruction(979, Chat.PUSH_QC_CATEGORY_SUBCATCOUNT);
        registerInstruction(416, Chat.PUSH_QC_CATEGORY_SUBCAT);
        registerInstruction(62, Chat.PUSH_QC_CATEGORY_PHRASECOUNT);
        registerInstruction(785, Chat.PUSH_QC_CATEGORY_PHRASE);
        registerInstruction(222, Chat.PUSH_QC_PHRASE_TEMPLATE);
        registerInstruction(597, Chat.PUSH_QC_PHRASE_RESPONSECOUNT);
        registerInstruction(368, Chat.PUSH_QC_PHRASE_RESPONSE);
        registerInstruction(417, Chat.QC_PHRASE_SETACTIVE);
        registerInstruction(508, Chat.AQC_SENDPUBLIC);
        registerInstruction(762, Chat.AQC_SENDPRIVATE);
        registerInstruction(1039, Chat.AQC_SENDFRIENDCHAT);
        registerInstruction(428, Chat.PUSH_QC_CATEGORY_SUBCAT_KEYCODE);
        registerInstruction(198, Chat.PUSH_QC_CATEGORY_PHRASE_KEYCODE);
        registerInstruction(863, Chat.PUSH_QC_CATEGORY_SUBCAT_FORKEYCODE);
        registerInstruction(266, Chat.PUSH_QC_CATEGORY_PHRASE_FORKEYCODE);
        registerInstruction(412, Chat.PUSH_QC_PHRASE_ARGCOUNT);
        registerInstruction(468, Chat.PUSH_QC_PHRASE_ARGTYPE);
        registerInstruction(1035, Chat.AQC_SETARG);
        registerInstruction(319, Chat.AQC_SETARG_OBJ);
        registerInstruction(73, Chat.PUSH_QC_PHRASE_ARGKEY);
        registerInstruction(106, Chat.QC_SEARCH);
        registerInstruction(600, Chat.PUSH_QC_SEARCH_NEXTRESULT);
        registerInstruction(746, Chat.QC_SEARCH_RESET);
        registerInstruction(90, Chat.AQC_SENDCLANCHANNEL);
        registerInstruction(756, Chat.AQC_SENDGUESTCLANCHANNEL);
        
        //Window mode instructions
        registerInstruction(729, WindowMode.FS_SET_DIMENSIONS);
        registerInstruction(514, WindowMode.PUSH_MODE);
        
        //World list instructions
        registerInstruction(70, WorldList.FETCH);
        registerInstruction(13, WorldList.PUSH_FIRSTENTRY);
        registerInstruction(80, WorldList.PUSH_NEXTENTRY);
        registerInstruction(117, WorldList.SETACTIVE);
        registerInstruction(157, WorldList.PUSH_ENTRY);
        registerInstruction(542, WorldList.SORT);
        registerInstruction(343, WorldList.RESETACTIVE);
        registerInstruction(675, WorldList.SETFETCHPING);
        registerInstruction(710, WorldList.PUSH_ACTIVEFLAGS);
        
        // Input
        registerInstruction(847, Input.PUSH_PRESSED_MOUSE_BUTTONS);
        // Camera
        registerInstruction(884, Camera.SETZOOM);
        registerInstruction(301, Camera.GET_CAMERA_ROT_X);
        registerInstruction(236, Camera.GET_CAMERA_ROT_Y);
        registerInstruction(524, Camera.CAMERA_IS_ANINT1621_EQUALS_4);
        registerInstruction(209, Camera.UNKNOWN_METHOD);
        
        // Quest instructions
        registerInstruction(618, Quest.PUSH_NAME);
        //1x unknown
        registerInstruction(253, Quest.PUSH_CATEGORY);
        registerInstruction(552, Quest.PUSH_DIFFICULTY);
        registerInstruction(782, Quest.PUSH_MEMBERS);
        registerInstruction(832, Quest.PUSH_REWARD_POINTS);
        registerInstruction(945, Quest.PUSH_QUESTREQ_COUNT);
        registerInstruction(66, Quest.PUSH_QUESTREQ);
        registerInstruction(583, Quest.PUSH_MEETS_QUESTREQ);
        registerInstruction(822, Quest.PUSH_POINTSREQ);
        registerInstruction(724, Quest.PUSH_MEETS_POINTSREQ);
        registerInstruction(971, Quest.PUSH_STATREQ_COUNT);
        registerInstruction(570, Quest.PUSH_STATREQ);
        registerInstruction(291, Quest.PUSH_STATREQ_LEVEL);
        registerInstruction(444, Quest.PUSH_MEETS_STATREQ);
        registerInstruction(86, Quest.PUSH_VARPREQ_COUNT);
        registerInstruction(119, Quest.PUSH_VARPREQ_NAME);
        registerInstruction(400, Quest.PUSH_MEETS_VARPREQ);
        registerInstruction(743, Quest.PUSH_VARBITREQ_COUNT);
        registerInstruction(650, Quest.PUSH_VARBITREQ_NAME);
        registerInstruction(625, Quest.PUSH_MEETS_VARBITREQ);
        registerInstruction(793, Quest.PUSH_MEETS_REQS);
        registerInstruction(1017, Quest.PUSH_STARTED);
        registerInstruction(578, Quest.PUSH_COMPLETED);
        registerInstruction(853, Quest.PUSH_PARAM);
        System.out.println("Registered " + getRegisteredInstructions().size() + " instruction.");
        
    }

    @Override
    public void registerLarges() {
        registerLarge(3);
        registerLarge(9);
        registerLarge(30);
        registerLarge(32);
        registerLarge(41);
        registerLarge(63);
        registerLarge(95);
        registerLarge(99);
        registerLarge(118);
        registerLarge(122);
        registerLarge(138);
        registerLarge(141);
        registerLarge(145);
        registerLarge(176);
        registerLarge(260);
        registerLarge(282);
        registerLarge(285);
        registerLarge(310);
        registerLarge(320);
        registerLarge(323);
        registerLarge(326);
        registerLarge(362);
        registerLarge(443);
        registerLarge(471);
        registerLarge(480);
        registerLarge(497);
        registerLarge(522);
        registerLarge(527);
        registerLarge(541);
        registerLarge(556);
        registerLarge(558);
        registerLarge(568);
        registerLarge(573);
        registerLarge(588);
        registerLarge(606);
        registerLarge(611);
        registerLarge(623);
        registerLarge(639);
        registerLarge(671);
        registerLarge(679);
        registerLarge(692);
        registerLarge(703);
        registerLarge(728);
        registerLarge(731);
        registerLarge(794);
        registerLarge(818);
        registerLarge(874);
        registerLarge(960);
        registerLarge(1000);
        registerLarge(1019);
        registerLarge(1044);
    }

    @Override
    public Instruction decode(CS2Script script, Context context, WrappedByteBuffer buffer, int id, int address) {
        int instKey = registeredInstructions.containsKey(id) ? id : -1;
        Instruction instr = registeredInstructions.get(instKey).get();
        instr.setBaseData(id, address);

        if (instr.getType() == InstructionType.PUSH_OBJ) {
            instr.setObjectOperand(buffer.getString().intern());
        } else if (instr.getType() == InstructionType.PUSH_LONG) {
            instr.setLongOperand(buffer.getLong());
        } else {
            instr.setIntegerOperand(isLarge(id) ? buffer.getInt() : buffer.getUnsignedByte());
        }
        instr.setScript(script);
        return instr;
    }

    @Override
    public CS2Script disassemble(Context context, WrappedByteBuffer buffer) {

        // Construct an empty CS2Script
        CS2Script script = new CS2Script();

        // Read the header offset
        buffer.setPosition(buffer.length() - 2);
        int headerLength = buffer.getUnsignedShort();
        int headerOffset = buffer.length() - 18 - headerLength;
        buffer.setPosition(headerOffset);
        buffer.getInt();//Instruction count (not used)

        // Read the fields lengths and assign them to the script
        int size = buffer.getUnsignedShort();
        
        script.setIntegerFields(new CS2Field[size]);
        script.setObjectFields(new CS2Field[buffer.getUnsignedShort()]);
        script.setLongFields(new CS2Field[buffer.getUnsignedShort()]);

        // Read the parameters lengths and assign them to the script
        size = buffer.getUnsignedShort();

        script.setIntegerParameters(new CS2Field[size]);
        script.setObjectParameters(new CS2Field[buffer.getUnsignedShort()]);
        script.setLongParameters(new CS2Field[buffer.getUnsignedShort()]);

        // Read the switch blocks and assign them to the script
        SwitchBlock[] blocks = new SwitchBlock[buffer.getUnsignedByte()];
        for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {
            int casesCount = buffer.getUnsignedShort();
            SwitchBlock block = blocks[blockIndex] = new SwitchBlock(casesCount);
            // Loop through cases and store them
            CaseNode previousCase = null;

            for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
                CaseNode currentCase = new CaseNode(buffer.getInt(), buffer.getInt());
                if (previousCase != null) {
                    previousCase.setNext(currentCase);
                }
                currentCase.setPrevious(previousCase);
                block.addCase(currentCase);
            }

        }
        script.setSwitchBlocks(blocks);
        buffer.setPosition(0);

        buffer.getNullString();
        // Read the instructions and assign them to the script
        int instructionsEnd = buffer.length() - 18 - headerLength;
        int instrAddress = 0;
        ArrayList<Instruction> tempInstructions = new ArrayList<>();
        while (buffer.getPosition() < instructionsEnd) {
            tempInstructions.add(context.getInstructionDecoder().decode(script, context, buffer, buffer.getUnsignedShort(), instrAddress));
            instrAddress++;
        }
        script.setInstructions(tempInstructions.toArray(new Instruction[tempInstructions.size()]));
        script.initializeFields();
        return script;
    }

}
