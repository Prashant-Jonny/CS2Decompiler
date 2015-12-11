package com.wycody.cs2d.rev.impl;

import com.wycody.cs2d.rev.RS3Revision;
import com.wycody.cs2d.script.inst.impl.ActiveWidget;
import com.wycody.cs2d.script.inst.impl.Array;
import com.wycody.cs2d.script.inst.impl.Branch;
import com.wycody.cs2d.script.inst.impl.Chat;
import com.wycody.cs2d.script.inst.impl.Clan;
import com.wycody.cs2d.script.inst.impl.ClientGeneral;
import com.wycody.cs2d.script.inst.impl.Config;
import com.wycody.cs2d.script.inst.impl.Exchange;
import com.wycody.cs2d.script.inst.impl.Friend;
import com.wycody.cs2d.script.inst.impl.Login;
import com.wycody.cs2d.script.inst.impl.Math;
import com.wycody.cs2d.script.inst.impl.Player;
import com.wycody.cs2d.script.inst.impl.Pop;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.script.inst.impl.Quest;
import com.wycody.cs2d.script.inst.impl.RS3Var;
import com.wycody.cs2d.script.inst.impl.ScriptEnum;
import com.wycody.cs2d.script.inst.impl.Store;
import com.wycody.cs2d.script.inst.impl.Text;
import com.wycody.cs2d.script.inst.impl.Unsorted;
import com.wycody.cs2d.script.inst.impl.Widget;
import com.wycody.cs2d.script.inst.impl.WidgetContainer;
import com.wycody.cs2d.script.inst.impl.WindowMode;
import com.wycody.cs2d.script.inst.impl.WorldList;

import net.openrs.cache.Cache;

public class Revision835 extends RS3Revision {

    public Revision835(Cache c) {
        super(c);
        this.pushIntId = 382;
        this.pushLongId = 1176;
    }

    @Override
    public void registerInstructions() {
        registerInstruction(-1, Unsorted.MISSING);
        
        // Control flow
        registerInstruction(382, Push.PUSH_INT);
        registerInstruction(1042, RS3Var.RS3_PUSH_VAR);
        registerInstruction(539, RS3Var.RS3_STORE_VAR);
        registerInstruction(1040, RS3Var.RS3_PUSH_VARBIT);
        registerInstruction(790, RS3Var.RS3_STORE_VARBIT);
                
        registerInstruction(1129, Push.PUSH_OBJECT);
        registerInstruction(102, Branch.GOTO);        
        registerInstruction(1161, Branch.INT_NE);
        registerInstruction(669, Branch.INT_EQ);
        registerInstruction(258, Branch.INT_LT);
        registerInstruction(498, Branch.INT_GT);
        registerInstruction(1185, Branch.INT_LE);
        registerInstruction(312, Branch.INT_GE);
        registerInstruction(215, Push.LOAD_INT);
        registerInstruction(1032, Store.STORE_INT);
        registerInstruction(613, Push.LOAD_OBJ);
        registerInstruction(101, Store.STORE_OBJ);
        registerInstruction(1080, Unsorted.CONCAT_STRS);
        registerInstruction(838, Pop.POP_INT);
        registerInstruction(1017, Pop.POP_OBJ);
        registerInstruction(449, Unsorted.CALL_SCRIPT);
        registerInstruction(134, Array.CREATE);
        registerInstruction(1188, Push.ARRAY);
        registerInstruction(859, Store.ARRAY);
        registerInstruction(1049, Branch.SWITCH);
        registerInstruction(1176, Push.PUSH_LONG);
        registerInstruction(1187, Pop.POP_LONG);
        registerInstruction(761, Push.LOAD_LONG);
        registerInstruction(49, Store.STORE_LONG);
        registerInstruction(344, Branch.LONG_NE);
        registerInstruction(532, Branch.LONG_EQ);
        registerInstruction(988, Branch.LONG_LT);
        registerInstruction(192, Branch.LONG_GT);
        registerInstruction(662, Branch.LONG_LE);
        registerInstruction(1195, Branch.LONG_GE); 
        //TODO: True (1) and false (0) branch instructions

        // Component container instructions
        registerInstruction(88, WidgetContainer.CREATE);
        registerInstruction(565, WidgetContainer.DELETE);
        registerInstruction(702, WidgetContainer.CLEAR);
        registerInstruction(921, WidgetContainer.SETACTIVE);
        registerInstruction(156, Widget.SETACTIVE);
        //5x unknown
                
        //Active player instructions
        registerInstruction(271, Player.SET_IDK);
        registerInstruction(115, Player.SET_COLOUR);
        registerInstruction(205, Player.SET_GENDER);
        registerInstruction(458, Player.SET_WORN_OBJECT);
        
        //Active component - position
        registerInstruction(514, ActiveWidget.SETPOS);
        registerInstruction(193, ActiveWidget.SETSIZE);
        registerInstruction(1036, ActiveWidget.SETHIDE);
        registerInstruction(24, ActiveWidget.SETASPECTRATIO);
        registerInstruction(43, ActiveWidget.SETSOLID);
        
        //Active component - attribute setters
        registerInstruction(584, ActiveWidget.SETSCROLLPOS);
        registerInstruction(1147, ActiveWidget.SETCOLOUR);
        registerInstruction(1058, ActiveWidget.SETFILLED);
        registerInstruction(807, ActiveWidget.SETALPHA);
        registerInstruction(1171, ActiveWidget.SETLINEWEIGHT);
        registerInstruction(120, ActiveWidget.SETGRAPHIC);
        registerInstruction(710, ActiveWidget.SETGRAPHICROTATION);
        registerInstruction(383, ActiveWidget.SETGRAPHICREPEAT);
        registerInstruction(1199, ActiveWidget.SETMODEL);
        registerInstruction(743, ActiveWidget.SETMODELCONSTRAINTS);
        registerInstruction(595, ActiveWidget.SETANIMATION);
        //4x unknown
        registerInstruction(906, ActiveWidget.SETTEXT);
        registerInstruction(0, ActiveWidget.SETFONT);
        registerInstruction(621, ActiveWidget.SETTEXTALIGN);
        registerInstruction(1154, ActiveWidget.SETSHADED);
        //2x unknown
        registerInstruction(211, ActiveWidget.SETBACKGROUNDCOL);
        registerInstruction(955, ActiveWidget.SETFLIPY);
        registerInstruction(316, ActiveWidget.SETFLIPX);
        registerInstruction(431, ActiveWidget.SETSCROLLBOUNDS);
        //1x unknown
        registerInstruction(652, ActiveWidget.SETMODELZOOM);
        registerInstruction(1149, ActiveWidget.SETLINEMIRRORED);
        registerInstruction(138, ActiveWidget.SETMODELOFFSET);
        registerInstruction(95, ActiveWidget.SETTEXTLINECOUNT);
        registerInstruction(1177, ActiveWidget.SETPARAM_INT);
        registerInstruction(995, ActiveWidget.SETPARAM_STR);
        //4x unknown
        //registerInstruction(-1, ActiveWidget.SETMONOCHROMEFONT);
        //2x unknown
        //registerInstruction(-1, ActiveWidget.SETOBJECT);
        
        //Active component - interaction setters
        registerInstruction(275, ActiveWidget.SETOP);
        registerInstruction(1105, ActiveWidget.SETDEFAULTSLOT);
        registerInstruction(359, ActiveWidget.SETCONTENTTYPE);
        //2x unknown
        registerInstruction(98, ActiveWidget.SETAPPLYTEXT);
        registerInstruction(1004, ActiveWidget.SETUSEOP);
        registerInstruction(16, ActiveWidget.CLEAROPS);
        registerInstruction(961, ActiveWidget.SETUSETARGETCUR);
        registerInstruction(393, ActiveWidget.SETOPCUR);
        registerInstruction(146, ActiveWidget.SETDEFAULTOP);
        //registerInstruction(-1, ActiveWidget.SETUSEOPCUR);
        //4x unknown
        //registerInstruction(-1, ActiveWidget.SETDEFAULTCUR);
        
        //Active component - event binding
        registerInstruction(251, ActiveWidget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(632, ActiveWidget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(939, ActiveWidget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(718, ActiveWidget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(430, ActiveWidget.BIND_MOUSEOUT_HANDLER);
        registerInstruction(624, ActiveWidget.BIND_DRAGRELEASE_HANDLER);
        registerInstruction(1006, ActiveWidget.BIND_DESELECT_HANDLER);
        registerInstruction(1111, ActiveWidget.BIND_VARP_HANDLER);
        registerInstruction(872, ActiveWidget.BIND_UPDATE_HANDLER);
        registerInstruction(147, ActiveWidget.BIND_OPTION_HANDLER);
        registerInstruction(264, ActiveWidget.BIND_DRAG_HANDLER);
        registerInstruction(1135, ActiveWidget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(116, ActiveWidget.BIND_MOUSEMOVE_HANDLER);
        registerInstruction(706, ActiveWidget.BIND_INV_HANDLER);
        registerInstruction(685, ActiveWidget.BIND_STAT_HANDLER);
        registerInstruction(1030, ActiveWidget.BIND_SELECT_HANDLER);
        registerInstruction(72, ActiveWidget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(346, ActiveWidget.BIND_CHAT_HANDLER);
        registerInstruction(926, ActiveWidget.BIND_KEYPRESS_HANDLER);
        //4x unknown
        registerInstruction(75, ActiveWidget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(1130, ActiveWidget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(17, ActiveWidget.BIND_STATUS_HANDLER);
        registerInstruction(626, ActiveWidget.BIND_ATTACHMENT_HANDLER);
        //1x unknown
        registerInstruction(858, ActiveWidget.BIND_EXCHANGE_HANDLER);
        //1x unknown
        registerInstruction(947, ActiveWidget.BIND_RESIZE_HANDLER);
        registerInstruction(471, ActiveWidget.BIND_VARC_HANDLER);
        registerInstruction(591, ActiveWidget.BIND_VARCSTR_HANDLER);
        registerInstruction(152, ActiveWidget.BIND_USE_HANDLER);
        registerInstruction(15, ActiveWidget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(200, ActiveWidget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(605, ActiveWidget.BIND_VARCLAN_HANDLER);
        //registerInstruction(-1, ActiveWidget.CLEAR_HANDLERS);
        
        //Component - position
        registerInstruction(914, Widget.SETPOS);
        registerInstruction(1189, Widget.SETSIZE);
        registerInstruction(691, Widget.SETHIDDEN);
        registerInstruction(679, Widget.SETASPECTRATIO);
        registerInstruction(1083, Widget.SETSOLID);
        
        //Component - attribute setters
        registerInstruction(837, Widget.SETSCROLLPOS);
        registerInstruction(31, Widget.SETCOL);
        registerInstruction(390, Widget.SETFILLED);
        registerInstruction(454, Widget.SETALPHA);
        registerInstruction(111, Widget.SETLINEWEIGHT);
        registerInstruction(567, Widget.SETGRAPHIC);
        registerInstruction(108, Widget.SETGRAPHICROTATION);
        registerInstruction(464, Widget.SETGRAPHICREPEAT);
        registerInstruction(670, Widget.SETMODEL);
        registerInstruction(773, Widget.SETMODELCONSTRAINTS);
        registerInstruction(1005, Widget.SETANIMATION);
        //4x unknown
        registerInstruction(671, Widget.SETTEXT);
        registerInstruction(772, Widget.SETFONT);
        registerInstruction(663, Widget.SETTEXTALIGN);
        registerInstruction(1100, Widget.SETSHADED);
        //2x unknown
        registerInstruction(1166, Widget.SETBACKGROUNDCOL);
        registerInstruction(824, Widget.SETFLIPY);
        registerInstruction(432, Widget.SETFLIPX);
        registerInstruction(366, Widget.SETSCROLLBOUNDS);
        //1x unknown
        registerInstruction(237, Widget.SETMODELZOOM);
        registerInstruction(1173, Widget.SETLINEMIRRORED);
        registerInstruction(887, Widget.SETMODELOFFSET);
        registerInstruction(502, Widget.SETTEXTLINECOUNT);
        registerInstruction(703, Widget.SETPARAM_INT);
        registerInstruction(739, Widget.SETPARAM_STR);
        //4x unknown
        //registerInstruction(-1 Widget.SETMONOCHROMEFONT);
        //1x unknown
        //registerInstruction(-1, Widget.SETOBJECT);
        
        //Component - interaction
        registerInstruction(957, Widget.SETOP);
        registerInstruction(235, Widget.SETDEFAULTSLOT);
        registerInstruction(537, Widget.SETCONTENTTYPE);
        //2x unknown
        registerInstruction(411, Widget.SETAPPLYTEXT);
        registerInstruction(1067, Widget.SETUSEOP);
        registerInstruction(635, Widget.CLEAROPS);
        registerInstruction(451, Widget.SETUSETARGETCUR);
        registerInstruction(689, Widget.SETOPCUR);
        registerInstruction(784, Widget.SETDEFAULTOP);
        //registerInstruction(-1, Widget.SETUSEOPCUR);
        //4x unknown
        //registerInstruction(-1, Widget.SETDEFAULTCUR);
        
        //Component - event binding
        registerInstruction(899, Widget.BIND_MOUSEPRESS_HANDLER);
        registerInstruction(332, Widget.BIND_MOUSEDRAGPAST_HANDLER);
        registerInstruction(348, Widget.BIND_MOUSERELEASE_HANDLER);
        registerInstruction(1019, Widget.BIND_MOUSEOVER_HANDLER);
        registerInstruction(131, Widget.BIND_MOUSE_HOVER_OUT_HANDLER);
        registerInstruction(959, Widget.BIND_DRAGRELEASE_HANDLER);
        registerInstruction(1157, Widget.BIND_DESELECT_HANDLER);
        registerInstruction(189, Widget.BIND_VARP_HANDLER);
        registerInstruction(482, Widget.BIND_UPDATE_HANDLER);
        registerInstruction(854, Widget.BIND_OPTION_HANDLER);
        registerInstruction(1054, Widget.BIND_DRAG_HANDLER);
        registerInstruction(19, Widget.BIND_MOUSEDRAG_HANDLER);
        registerInstruction(813, Widget.BIND_MOUSE_HOVER_IN_HANDLER);
        registerInstruction(475, Widget.BIND_INV_HANDLER);
        registerInstruction(736, Widget.BIND_STAT_HANDLER);
        registerInstruction(648, Widget.BIND_SELECT_HANDLER);
        registerInstruction(976, Widget.BIND_MOUSESCROLL_HANDLER);
        registerInstruction(55, Widget.BIND_CHAT_HANDLER);
        registerInstruction(973, Widget.BIND_KEYPRESS_HANDLER);
        //4x unknown
        registerInstruction(644, Widget.BIND_FRIENDLIST_HANDLER);
        registerInstruction(752, Widget.BIND_FRIENDCHAT_HANDLER);
        registerInstruction(895, Widget.BIND_STATUS_HANDLER);
        registerInstruction(488, Widget.BIND_ATTACHMENT_HANDLER);
        //1x unknown
        registerInstruction(1089, Widget.BIND_EXCHANGE_HANDLER);
        //1x unknown
        registerInstruction(798, Widget.BIND_RESIZE_HANDLER);
        registerInstruction(73, Widget.BIND_VARC_HANDLER);
        registerInstruction(1065, Widget.BIND_VARCSTR_HANDLER);
        registerInstruction(1097, Widget.BIND_USE_HANDLER);
        registerInstruction(928, Widget.BIND_CLANSETTINGS_HANDLER);
        registerInstruction(140, Widget.BIND_CLANCHANNEL_HANDLER);
        registerInstruction(315, Widget.BIND_VARCLAN_HANDLER);
        //registerInstruction(-1, Widget.CLEAR_HANDLERS);
        
        //General action executors
        registerInstruction(82, ClientGeneral.SEND_MESSAGE);
        registerInstruction(945, Unsorted.RETURN);
        registerInstruction(148, ClientGeneral.CLOSE_MODAL);
        registerInstruction(473, ClientGeneral.RESUME_P_COUNTDIALOG);
        registerInstruction(940, ClientGeneral.RESUME_P_NAMEDIALOG);
        registerInstruction(469, ClientGeneral.RESUME_P_STRINGDIALOG);
        //1x unknown
        registerInstruction(127, ClientGeneral.INVOKE_PLAYER_OPTION);
        registerInstruction(783, ClientGeneral.RESUME_P_OBJDIALOG);
        //3x unknown
        registerInstruction(984, ClientGeneral.IF_CLOSESUB);
        registerInstruction(660, ClientGeneral.INVOKE_USE_ON_PLAYER);
        registerInstruction(585, ClientGeneral.SEND_MESSAGE_TYPED);
        //1x unknown
        registerInstruction(209, ClientGeneral.RESUME_P_HSLDIALOG);
        registerInstruction(904, ClientGeneral.RESUME_P_CLANFORUMQFCDIALOG);
        
        //Friend executors
        registerInstruction(1039, Friend.FRIEND_SETRANK);
        registerInstruction(174, Friend.FRIEND_SETNOTE);        
        registerInstruction(343, Friend.FRIENDLIST_ADD);
        registerInstruction(1179, Friend.FRIENDLIST_DEL);
        registerInstruction(238, Friend.IGNORELIST_ADD);
        registerInstruction(141, Friend.IGNORELIST_DEL);
        registerInstruction(749, Friend.IGNORE_SETNOTE); 
        registerInstruction(213, Friend.FRIENDCHAT_KICKUSER);
        registerInstruction(823, Friend.FRIENDCHAT_JOIN);
        registerInstruction(1016, Friend.FRIENDCHAT_LEAVE);
        registerInstruction(412, Friend.IGNORELIST_ADDTEMP);
        
        //Clan executers
        registerInstruction(392, Clan.CHANNEL_KICK_USER); 
        
        //Object config executers
        registerInstruction(857, Config.OBJ_SEARCH_RESET);
        
        //Chat executers
        registerInstruction(1014, Chat.SET_CHATFILTERSTATUS);
        registerInstruction(678, Chat.SEND_SNAPSHOT);
        registerInstruction(1008, Chat.SETMODE);
        registerInstruction(465, Chat.MESSAGE_PUBLIC);
        registerInstruction(105, Chat.MESSAGE_PRIVATE);
        registerInstruction(720, Chat.QC_PHRASE_SETACTIVE);
        registerInstruction(112, Chat.AQC_SENDPUBLIC);
        registerInstruction(347, Chat.AQC_SENDPRIVATE);
        registerInstruction(543, Chat.AQC_SETARG);
        registerInstruction(1075, Chat.AQC_SETARG_OBJ);
        registerInstruction(948, Chat.QC_SEARCH_RESET);
        
        //Active component - layout getters
        registerInstruction(408, ActiveWidget.PUSH_POSX);
        registerInstruction(83, ActiveWidget.PUSH_POSY);
        registerInstruction(958, ActiveWidget.PUSH_WIDTH);
        registerInstruction(994, ActiveWidget.PUSH_HEIGHT);
        registerInstruction(1103, ActiveWidget.PUSH_HIDE);
        registerInstruction(113, ActiveWidget.PUSH_PARENT);
        registerInstruction(776, ActiveWidget.PUSH_HASH);
        registerInstruction(484, ActiveWidget.PUSH_COL);
        
        //Active component - attribute getters
        registerInstruction(157, ActiveWidget.PUSH_SCROLLX);
        registerInstruction(868, ActiveWidget.PUSH_SCROLLY);
        registerInstruction(645, ActiveWidget.PUSH_TEXT);
        registerInstruction(786, ActiveWidget.PUSH_SCROLLWID);
        registerInstruction(831, ActiveWidget.PUSH_SCROLLHEI);
        registerInstruction(1178, ActiveWidget.PUSH_MODELZOOM);
        registerInstruction(18, ActiveWidget.PUSH_MODELROT_X);
        registerInstruction(208, ActiveWidget.PUSH_MODELROT_Z);
        registerInstruction(444, ActiveWidget.PUSH_MODELROT_Y);
        registerInstruction(333, ActiveWidget.PUSH_ALPHA);
        //2x unknown
        registerInstruction(29, ActiveWidget.PUSH_GRAPHIC);
        registerInstruction(38, ActiveWidget.PUSH_PARAM);
        registerInstruction(655, ActiveWidget.PUSH_GRAPHICROT);
        //registerInstruction(-1, ActiveWidget.PUSH_MODEL);
        //registerInstruction(-1, ActiveWidget.PUSH_FONT);
        //1x unknown
        registerInstruction(684, ActiveWidget.PUSH_FONT);//Not sure why Jagex duplicates this, but whatever...
        registerInstruction(421, ActiveWidget.PUSH_OBJECTID);
        registerInstruction(1144, ActiveWidget.PUSH_OBJCOUNT);
        registerInstruction(319, ActiveWidget.PUSH_SLOT);
        //1x unknown
        registerInstruction(763, ActiveWidget.PUSH_OP);
        registerInstruction(129, ActiveWidget.PUSH_APPLYNAME);
        
        //Component - layout getters
        registerInstruction(106, Widget.PUSH_POSX);
        registerInstruction(965, Widget.PUSH_POSY);
        registerInstruction(862, Widget.PUSH_WIDTH);
        registerInstruction(661, Widget.PUSH_HEIGHT);
        registerInstruction(555, Widget.PUSH_HIDDEN);
        registerInstruction(869, Widget.PUSH_PARENT);
        registerInstruction(110, Widget.PUSH_HASH);
        registerInstruction(954, Widget.PUSH_COL);
        
        //Component - attribute getters
        registerInstruction(391, Widget.PUSH_SCROLLX);
        registerInstruction(379, Widget.PUSH_SCROLLY);
        registerInstruction(353, Widget.PUSH_TEXT);
        registerInstruction(896, Widget.PUSH_SCROLLWID);
        registerInstruction(1102, Widget.PUSH_SCROLLHEI);
        registerInstruction(419, Widget.PUSH_MODELZOOM);
        registerInstruction(658, Widget.PUSH_MODELROT_X);
        registerInstruction(74, Widget.PUSH_MODELROT_Z);
        registerInstruction(818, Widget.PUSH_MODELROT_Y);
        registerInstruction(145, Widget.PUSH_ALPHA);
        //2x unknown
        registerInstruction(962, Widget.PUSH_GRAPHIC);
        registerInstruction(840, Widget.PUSH_GRAPHICROT);
        registerInstruction(1033, Widget.PUSH_MODEL);
        registerInstruction(168, Widget.PUSH_FONT);
        registerInstruction(589, Widget.PUSH_GRAPHICSIZE);
        // Duplicated instruction by jagex
        //registerInstruction(-1, Widget.PUSH_FONT);
        
        
        //Component - container getters
        registerInstruction(870, Widget.PUSH_OBJECTID);
        registerInstruction(328, Widget.PUSH_OBJCOUNT);
        //1x unknown
        registerInstruction(467, Widget.PUSH_EMPTYSLOT);
        
        //Component - interaction getters
        registerInstruction(71, Widget.PUSH_TARGETFLAGS);
        registerInstruction(546, Widget.PUSH_OP);
        registerInstruction(241, Widget.PUSH_APPLYNAME);
        
        //General getter instructions
        registerInstruction(769, ClientGeneral.PUSH_CLIENT_CYCLE);
        registerInstruction(811, ClientGeneral.PUSH_INV_SLOTOBJ);
        registerInstruction(698, ClientGeneral.PUSH_INV_SLOTCOUNT);
        registerInstruction(1106, ClientGeneral.PUSH_INV_OBJCOUNT);
        //1x unknown
        registerInstruction(819, ClientGeneral.PUSH_INV_CAPACITY);
        registerInstruction(1096, ClientGeneral.PUSH_INV_STOCKCOUNT);
        registerInstruction(672, ClientGeneral.PUSH_STAT_LEVEL);
        registerInstruction(563, ClientGeneral.PUSH_STAT_BASE);
        //1x unknown
        registerInstruction(435, ClientGeneral.PUSH_STAT_NOCAPBASE);
        registerInstruction(462, ClientGeneral.PUSH_STAT_EXPERIENCE);
        //1x unknown
        registerInstruction(603, ClientGeneral.PUSH_PLAYER_COORD);
        registerInstruction(492, ClientGeneral.PUSH_COORD_X);
        registerInstruction(269, ClientGeneral.PUSH_COORD_LEVEL);
        registerInstruction(427, ClientGeneral.PUSH_COORD_Y);
        //8x unknown
        registerInstruction(450, ClientGeneral.PUSH_MAP_MEMBERS);
        //2x unknown
        registerInstruction(531, ClientGeneral.PUSH_INV_OTHERPLAYER_SLOTOBJ);
        registerInstruction(400, ClientGeneral.PUSH_INV_OTHERPLAYER_SLOTCOUNT);
        registerInstruction(395, ClientGeneral.PUSH_INV_OTHERPLAYER_OBJCOUNT);
        registerInstruction(234, ClientGeneral.PUSH_RIGHTS);
        registerInstruction(507, ClientGeneral.PUSH_REBOOT_TIMER);
        registerInstruction(647, ClientGeneral.PUSH_WORLDID);
        registerInstruction(64, ClientGeneral.PUSH_RUNENERGY);
        registerInstruction(1059, ClientGeneral.PUSH_RUNWEIGHT);
        //2x unknown
        registerInstruction(856, ClientGeneral.PUSH_PLAYER_ISMEMBER);
        registerInstruction(1109, ClientGeneral.PUSH_PLAYER_COMBATLEVEL);
        registerInstruction(1133, ClientGeneral.PUSH_PLAYER_ISFEMALE);
        registerInstruction(224, ClientGeneral.PUSH_CHAT_RESTRICTED);
        registerInstruction(569, ClientGeneral.PUSH_INV_FREESLOTCOUNT);
        registerInstruction(261, ClientGeneral.PUSH_INV_WEIGHTEDSLOTCOUNT);
        registerInstruction(1072, ClientGeneral.PUSH_INV_WEIGHTEDSLOTCOUNT_STACKS);
        registerInstruction(845, ClientGeneral.PUSH_LANGUAGE);
        //4x unknown
        registerInstruction(795, ClientGeneral.PUSH_APPLET_FOCUSED);
        
        //Enum instructions
        registerInstruction(1028, ScriptEnum.PUSH_VALUE_STR);
        registerInstruction(69, ScriptEnum.PUSH_VALUE);
        registerInstruction(1086, ScriptEnum.PUSH_CONTAINS_INT);
        registerInstruction(56, ScriptEnum.PUSH_CONTAINS_STR);
        registerInstruction(401, ScriptEnum.PUSH_SIZE);
        registerInstruction(358, ScriptEnum.PUSH_INTVALKEYS_COUNT);
        registerInstruction(890, ScriptEnum.PUSH_STRVALKEYS_COUNT);
        registerInstruction(768, ScriptEnum.PUSH_INTVALKEY);
        registerInstruction(245, ScriptEnum.PUSH_STRVALKEY);
        
        //Friend (includes ignore and friendchat)
        registerInstruction(91, Friend.PUSH_FRIENDLIST_SIZE);
        registerInstruction(681, Friend.PUSH_FRIEND_NAMES);
        registerInstruction(937, Friend.PUSH_FRIEND_NODEID);
        registerInstruction(1044, Friend.PUSH_FRIEND_RANK);
        registerInstruction(23, Friend.PUSH_FRIEND_NOTE);        
        registerInstruction(566, Friend.PUSH_FRIEND_WORLDFLAGS);
        registerInstruction(304, Friend.PUSH_IS_FRIEND);
        registerInstruction(590, Friend.PUSH_FRIEND_WORLDNAME);
        registerInstruction(1003, Friend.PUSH_FRIENDCHAT_NAME);
        registerInstruction(746, Friend.PUSH_FRIENDCHAT_USERCOUNT);
        registerInstruction(1079, Friend.PUSH_FRIENDCHAT_USER_NAME);
        registerInstruction(882, Friend.PUSH_FRIENDCHAT_USER_NODEID);
        registerInstruction(420, Friend.PUSH_FRIENDCHAT_USER_RANK);
        registerInstruction(826, Friend.PUSH_FRIENDCHAT_RANKKICK);
        registerInstruction(9, Friend.PUSH_FRIENDCHAT_ACTIVEPLAYERRANK);
        registerInstruction(1132, Friend.PUSH_IGNORELIST_SIZE);
        registerInstruction(970, Friend.PUSH_IGNORE_NAMES);
        registerInstruction(695, Friend.PUSH_IGNORE_NOTE);
        registerInstruction(463, Friend.PUSH_IS_IGNORED);
        registerInstruction(985, Friend.PUSH_FRIENDCHAT_USER_IS_ACTIVEPLAYER);
        registerInstruction(445, Friend.PUSH_FRIENDCHAT_OWNER);
        registerInstruction(84, Friend.PUSH_FRIENDCHAT_USER_WORLDNAME);
        registerInstruction(1110, Friend.PUSH_FRIEND_GAME);
        registerInstruction(50, Friend.PUSH_FRIEND_SLOT);
        registerInstruction(651, Friend.PUSH_IGNORE_SLOT);
        //1x unknown (client paramater)
        registerInstruction(893, Friend.PUSH_IGNORE_ISTEMP);
        registerInstruction(124, Friend.PUSH_FRIENDCHAT_USER_NAMEUNFILTERED);
        registerInstruction(179, Friend.PUSH_IGNORE_NAME);
        registerInstruction(460, Friend.PUSH_FRIEND_RECRUITED);        
        
        //Clan (settings and channel)
        registerInstruction(1194, Clan.SETTINGS_SETGUEST);
        registerInstruction(608, Clan.SETTINGS_SETMAIN);
        registerInstruction(788, Clan.PUSH_SETTINGS_NAME);
        registerInstruction(760, Clan.PUSH_SETTINGS_ALLOWNONMEMBERS);
        registerInstruction(974, Clan.PUSH_SETTINGS_RANKTALK);
        registerInstruction(528, Clan.PUSH_SETTINGS_RANKKICK);
        registerInstruction(918, Clan.PUSH_SETTINGS_RANKLOOTSHARE);
        //1x unknown
        registerInstruction(780, Clan.PUSH_SETTINGS_MEMBERCOUNT);
        registerInstruction(1115, Clan.PUSH_SETTINGS_MEMBER_NAME);
        registerInstruction(259, Clan.PUSH_SETTINGS_MEMBER_RANK);
        //1x unknown
        registerInstruction(254, Clan.PUSH_SETTINGS_BANCOUNT);
        registerInstruction(874, Clan.PUSH_SETTINGS_BAN_NAME);
        registerInstruction(607, Clan.PUSH_SETTINGS_MEMBER_VARBIT);
        registerInstruction(927, Clan.PUSH_SETTINGS_OWNERSLOT);
        registerInstruction(41, Clan.PUSH_SETTINGS_REPLACEMENTOWNERSLOT);
        registerInstruction(1043, Clan.PUSH_SETTINGS_MEMBER_SLOT);
        registerInstruction(1127, Clan.PUSH_SETTINGS_MEMBER_SORTEDSLOT);
        registerInstruction(1142, Clan.PUSH_SETTINGS_MEMBER_JOINDAY);
        registerInstruction(1113, Clan.CHANNEL_SETGUEST);
        registerInstruction(1152, Clan.CHANNEL_SETMAIN);
        registerInstruction(210, Clan.PUSH_CHANNEL_NAME);
        registerInstruction(474, Clan.PUSH_CHANNEL_RANKKICK);
        registerInstruction(436, Clan.PUSH_CHANNEL_RANKTALK);
        registerInstruction(199, Clan.PUSH_CHANNEL_USERCOUNT);
        registerInstruction(1150, Clan.PUSH_CHANNEL_USER_NAME);
        registerInstruction(677, Clan.PUSH_CHANNEL_USER_RANK);
        registerInstruction(713, Clan.PUSH_CHANNEL_USER_NODEID);
        registerInstruction(117, Clan.PUSH_CHANNEL_USER_SLOT);
        registerInstruction(616, Clan.PUSH_CHANNEL_USER_SORTEDSLOT);
        registerInstruction(505, Clan.PUSH_VARCLAN_ENABLED);
        
        //Exchange instructions
        registerInstruction(741, Exchange.PUSH_ISSELL);
        registerInstruction(594, Exchange.PUSH_OBJID);
        registerInstruction(1081, Exchange.PUSH_PRICE);
        registerInstruction(552, Exchange.PUSH_COUNT);
        registerInstruction(894, Exchange.PUSH_COMPLETECOUNT);
        registerInstruction(143, Exchange.PUSH_COMPLETEGOLD);
        registerInstruction(345, Exchange.PUSH_ISEMPTY);
        //1x unknown
        registerInstruction(975, Exchange.PUSH_ISFINISHED);
        registerInstruction(1000, Exchange.PUSH_ISSUBMITTING);       
                
        //Arithmatic (math) instructions
        registerInstruction(36, Math.SUM);
        registerInstruction(93, Math.SUBTRACT);
        registerInstruction(459, Math.MULTIPLY);
        registerInstruction(1191, Math.DIVIDE);
        registerInstruction(399, Math.RANDOM);
        //3x unknown
        registerInstruction(705, Math.SET_BIT);
        registerInstruction(572, Math.RESET_BIT);
        registerInstruction(7, Math.TEST_BIT);
        registerInstruction(920, Math.MODULO);
        registerInstruction(5, Math.POWER);
        registerInstruction(1015, Math.ROOT);
        registerInstruction(949, Math.BITWISE_AND);
        registerInstruction(944, Math.BITWISE_OR);
        registerInstruction(884, Math.MIN);
        registerInstruction(919, Math.MAX);
        registerInstruction(877, Math.FRAC_MULTIPLY);
        //1x unknown
        registerInstruction(1107, Math.HSL_TO_RGB);
        registerInstruction(308, Math.BITWISE_NOT);

        //Text (string) instructions
        registerInstruction(203, Text.CONCAT_INT);
        registerInstruction(256, Text.CONCAT);
        //1x unknown
        registerInstruction(642, Text.COLOR_TO_CHAT_STR);
        registerInstruction(649, Text.TO_LOWER);       
        registerInstruction(820, Text.RUNEDATE_TO_STRING);
        registerInstruction(680, Text.GENDER);
        registerInstruction(1068, Text.INT_TO_STR);
        registerInstruction(317, Text.COMPARE);
        registerInstruction(936, Text.LINE_COUNT);
        registerInstruction(100, Text.RENDER_WIDTH_WX);
        registerInstruction(406, Text.RENDER_WIDTH);        
        registerInstruction(1170, Text.CHOICE);
        registerInstruction(1011, Text.ENCODE_STR);
        registerInstruction(643, Text.CONCAT_CHAR);
        registerInstruction(707, Text.IS_BREAK);
        registerInstruction(673, Text.IS_ALPHA_NUMERIC);
        registerInstruction(247, Text.IS_ALPHA);
        registerInstruction(255, Text.IS_NUMERIC);
        registerInstruction(150, Text.STRLEN);
        registerInstruction(324, Text.SUBSTR);
        registerInstruction(883, Text.STRIPCODE);
        registerInstruction(1061, Text.INDEXOF_CHAR);
        registerInstruction(1063, Text.INDEXOF_STR);
        registerInstruction(279, Text.CHAR_TOLOWER);
        registerInstruction(119, Text.CHAR_TOUPPER);
        registerInstruction(842, Text.INT_FORMATTEDSTR);
        registerInstruction(226, Text.PUSH_STR_WIDTH);
        //1x unknown
        registerInstruction(158, Text.UTC_TIMESTAMP);
        registerInstruction(380, Text.LONG_TO_BASE36);
        
        //Object config instructions
        registerInstruction(729, Config.PUSH_OBJ_NAME);
        registerInstruction(336, Config.PUSH_OBJ_OP);
        registerInstruction(1050, Config.PUSH_OBJ_IOP);
        registerInstruction(602, Config.PUSH_OBJ_COST);
        registerInstruction(778, Config.PUSH_OBJ_STACKABLE);
        //2x unknown
        registerInstruction(363, Config.PUSH_OBJ_FROMCERT);
        registerInstruction(295, Config.PUSH_OBJ_TOCERT);
        //3x unknown
        registerInstruction(142, Config.PUSH_OBJ_WEARPOS);
        registerInstruction(701, Config.PUSH_OBJ_WEARPOS2);
        registerInstruction(521, Config.PUSH_OBJ_WEARPOS3);
        registerInstruction(971, Config.PUSH_OBJ_MEMBERS);
        registerInstruction(480, Config.PUSH_OBJ_PARAM);
        registerInstruction(1200, Config.PUSH_OBJ_IOPCUR);
        registerInstruction(977, Config.PUSH_OBJ_MULTISTACKSIZE);
        registerInstruction(438, Config.OBJ_SEARCH);
        registerInstruction(540, Config.PUSH_OBJ_SEARCH_NEXTRESULT);
        registerInstruction(139, Config.PUSH_OBJ_HASIOPCOL);
        registerInstruction(1108, Config.PUSH_OBJ_IOPCOL);
        
        //NPC config instructions
        registerInstruction(665, Config.PUSH_NPC_PARAM);
        
        //Location config instructions
        registerInstruction(243, Config.PUSH_LOC_PARAM);
        
        //Struct config instructions
        registerInstruction(396, Config.PUSH_STRUCT_PARAM);
        
        //Base (render) config instructions
        registerInstruction(176, Config.PUSH_BASE_IDLEANIM);
        
        //Chat instructions
        registerInstruction(331, Chat.PUSH_PUBLICFILTERSTATUS);
        registerInstruction(889, Chat.PUSH_BYID);
        registerInstruction(376, Chat.PUSH_PRIVATEFILTERSTATUS);
        //registerInstruction(-1, Chat.PUSH_NAME);
        //registerInstruction(-1, Chat.PUSH_CLAN);
        //registerInstruction(-1, Chat.PUSH_QUICKCHATID);
        registerInstruction(3, Chat.PUSH_PLAYER_NAME);
        registerInstruction(721, Chat.PUSH_TRADEFILTERSTATUS);
        registerInstruction(277, Chat.PUSH_HISTORYSIZE);
        //registerInstruction(-1, Chat.PUSH_ARGS);
        //registerInstruction(-1, Chat.PUSH_NAMEUNFILTERED);
        registerInstruction(207, Chat.PUSH_PLAYER_NAMESIMPLE);
        //registerInstruction(-1, Chat.PUSH_ID);
        //registerInstruction(-1, Chat.PUSH_TIME);
        //registerInstruction(-1, Chat.PUSH_NAMESIMPLE);
        registerInstruction(442, Chat.PUSH_QC_CATEGORY_NAME);
        registerInstruction(177, Chat.PUSH_QC_CATEGORY_SUBCATCOUNT);
        registerInstruction(455, Chat.PUSH_QC_CATEGORY_SUBCAT);
        registerInstruction(428, Chat.PUSH_QC_CATEGORY_PHRASECOUNT);
        registerInstruction(550, Chat.PUSH_QC_CATEGORY_PHRASE);
        registerInstruction(997, Chat.PUSH_QC_PHRASE_TEMPLATE);
        registerInstruction(545, Chat.PUSH_QC_PHRASE_RESPONSECOUNT);
        registerInstruction(1134, Chat.PUSH_QC_PHRASE_RESPONSE);
        registerInstruction(909, Chat.PUSH_QC_CATEGORY_SUBCAT_KEYCODE);
        registerInstruction(1031, Chat.PUSH_QC_CATEGORY_PHRASE_KEYCODE);
        registerInstruction(169, Chat.PUSH_QC_CATEGORY_SUBCAT_FORKEYCODE);
        registerInstruction(1018, Chat.PUSH_QC_CATEGORY_PHRASE_FORKEYCODE);
        registerInstruction(472, Chat.PUSH_QC_PHRASE_ARGCOUNT);
        registerInstruction(280, Chat.PUSH_QC_PHRASE_ARGTYPE);
        registerInstruction(170, Chat.PUSH_QC_PHRASE_ARGKEY);
        registerInstruction(712, Chat.QC_SEARCH);
        registerInstruction(846, Chat.PUSH_QC_SEARCH_NEXTRESULT);
        
        //Window mode instructions
        registerInstruction(426, WindowMode.FS_SET_DIMENSIONS);
        //3x unknown
        registerInstruction(8, WindowMode.PUSH_MODE);
        
        //Login instructions
        registerInstruction(334, Login.PUSH_RESPONSE);
        registerInstruction(368, Login.PUSH_HAS_SIGNLE_SIGNON_KEY);
        
        
        //World list instructions
        registerInstruction(159, WorldList.FETCH);
        registerInstruction(96, WorldList.PUSH_FIRSTENTRY);
        registerInstruction(309, WorldList.PUSH_NEXTENTRY);
        registerInstruction(740, WorldList.SETACTIVE);
        registerInstruction(902, WorldList.PUSH_ENTRY);
        //registerInstruction(-1, WorldList.SORT);
        //registerInstruction(-1, WorldList.RESETACTIVE);
        //registerInstruction(-1, WorldList.SETFETCHPING);
        registerInstruction(58, WorldList.PUSH_ACTIVEFLAGS);
        
        registerInstruction(68, Widget.PUSH_TOP);
        
        // Input
        //registerInstruction(-1, Input.PUSH_PRESSED_MOUSE_BUTTONS);
        // Camera
        //registerInstruction(-1, Camera.SETZOOM);
        //registerInstruction(-1, Camera.GET_CAMERA_ROT_X);
        //registerInstruction(-1, Camera.GET_CAMERA_ROT_Y);
        //registerInstruction(-1, Camera.CAMERA_IS_ANINT1621_EQUALS_4);
        //registerInstruction(-1, Camera.UNKNOWN_METHOD);
        
        //Unknown
        registerInstruction(847, Unsorted.PUSH_CHAT_RELATED_BOOLEAN);
        registerInstruction(960, Unsorted.PUSH_DOB);
        
        
        // Quest instructions
        registerInstruction(394, Quest.PUSH_NAME);
        //1x unknown
        registerInstruction(1034, Quest.PUSH_CATEGORY);
        registerInstruction(314, Quest.PUSH_DIFFICULTY);
        registerInstruction(968, Quest.PUSH_MEMBERS);
        registerInstruction(844, Quest.PUSH_REWARD_POINTS);
        registerInstruction(202, Quest.PUSH_QUESTREQ_COUNT);
        registerInstruction(1153, Quest.PUSH_QUESTREQ);
        registerInstruction(1124, Quest.PUSH_MEETS_QUESTREQ);
        registerInstruction(1088, Quest.PUSH_POINTSREQ);
        registerInstruction(439, Quest.PUSH_MEETS_POINTSREQ);
        registerInstruction(697, Quest.PUSH_STATREQ_COUNT);
        registerInstruction(1069, Quest.PUSH_STATREQ);
        registerInstruction(972, Quest.PUSH_STATREQ_LEVEL);
        registerInstruction(413, Quest.PUSH_MEETS_STATREQ);
        registerInstruction(880, Quest.PUSH_VARPREQ_COUNT);
        registerInstruction(913, Quest.PUSH_VARPREQ_NAME);
        registerInstruction(244, Quest.PUSH_MEETS_VARPREQ);
        registerInstruction(683, Quest.PUSH_VARBITREQ_COUNT);
        registerInstruction(6, Quest.PUSH_VARBITREQ_NAME);
        registerInstruction(206, Quest.PUSH_MEETS_VARBITREQ);
        registerInstruction(978, Quest.PUSH_MEETS_REQS);
        registerInstruction(983, Quest.PUSH_STARTED);
        registerInstruction(1012, Quest.PUSH_COMPLETED);
        registerInstruction(581, Quest.PUSH_PARAM);
        System.out.println("Registered " + getRegisteredInstructions().size() + " instruction.");
        
    }
    
    @Override
    public void registerLarges() {
        registerLarge(382);
        registerLarge(1042);
		registerLarge(539);
		registerLarge(1040);
		registerLarge(790);
		registerLarge(1129);
		registerLarge(102);
		registerLarge(1161);
		registerLarge(669);
		registerLarge(258);
		registerLarge(498);
		registerLarge(1185);
		registerLarge(312);
		registerLarge(215);
		registerLarge(1032);
		registerLarge(613);
		registerLarge(101);
		registerLarge(1080);
		registerLarge(449);
		registerLarge(134);
		registerLarge(1188);
		registerLarge(859);
		registerLarge(1049);
		registerLarge(1176);
		registerLarge(1187);
		registerLarge(761);
		registerLarge(49);
		registerLarge(344);
		registerLarge(532);
		registerLarge(988);
		registerLarge(192);
		registerLarge(662);
		registerLarge(1195);
		registerLarge(1164);
		registerLarge(704);
    }


}
