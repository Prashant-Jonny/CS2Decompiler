package com.jagex.game.runetek5.config.vartype.constants;

import com.jagex.core.constants.SerialEnum;
import com.jagex.core.stringtools.general.StringTools;
import com.jagex.maths.Vector3;


public enum ScriptVarType implements SerialEnum {
	INT(0, 'i', BaseVarType.INTEGER, Integer.valueOf(0)),
	BOOLEAN(1, '1', BaseVarType.INTEGER, Integer.valueOf(0)),
	TYPE_2(2, '2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	QUEST(3, ':', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_4(4, ',', BaseVarType.INTEGER, Integer.valueOf(-1)),
	CURSOR(5, '@', BaseVarType.INTEGER, Integer.valueOf(-1)),
	ANIMATION(6, 'A', BaseVarType.INTEGER, Integer.valueOf(-1)),
	UNKNOWN3(7, 'C', BaseVarType.INTEGER, Integer.valueOf(-1)),
	MEDIUM(8, 'H', BaseVarType.INTEGER, Integer.valueOf(-1)),
	INTERFACE(9, 'I', BaseVarType.INTEGER, Integer.valueOf(-1)),
	IDENTIKIT(10, 'K', BaseVarType.INTEGER, Integer.valueOf(-1)),
	MUSIC(11, 'M', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_12(12, 'N', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_13(13, 'O', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_14(14, 'P', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_15(15, 'Q', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_16(16, 'R', BaseVarType.INTEGER, Integer.valueOf(-1)),
	SKILL(17, 'S', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_18(18, 'T', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_19(19, 'V', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_20(20, '^', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_21(21, '`', BaseVarType.INTEGER, Integer.valueOf(-1)),
	COORDINATE(22, 'c', BaseVarType.INTEGER, Integer.valueOf(-1)),
	GRAPHIC(23, 'd', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_24(24, 'e', BaseVarType.INTEGER, Integer.valueOf(-1)),
	FONT(25, 'f', BaseVarType.INTEGER, Integer.valueOf(-1)),
	ENUM(26, 'g', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_27(27, 'h', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_28(28, 'j', BaseVarType.INTEGER, Integer.valueOf(-1)),
	CHAT_CATEGORY(29, 'k', BaseVarType.INTEGER, Integer.valueOf(-1)),
	LOCATION(30, 'l', BaseVarType.INTEGER, Integer.valueOf(-1)),
	MODEL(31, 'm', BaseVarType.INTEGER, Integer.valueOf(-1)),
	NPC(32, 'n', BaseVarType.INTEGER, Integer.valueOf(-1)),
	OBJECT(33, 'o', BaseVarType.INTEGER, Integer.valueOf(-1)),
	PLAYER(34, 'p', BaseVarType.INTEGER, Integer.valueOf(-1)),
	DESCRIPTION(35, 'r', BaseVarType.LONG, Long.valueOf(-1L)),
	STRING(36, 's', BaseVarType.STRING, ""),
	SPOT(37, 't', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_38(38, 'u', BaseVarType.INTEGER, Integer.valueOf(-1)),
	INV(39, 'v', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_40(40, 'x', BaseVarType.INTEGER, Integer.valueOf(-1)),
	CATEGORY(41, 'y', BaseVarType.INTEGER, Integer.valueOf(-1)),
	CHAR(42, 'z', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_43(43, '|', BaseVarType.INTEGER, Integer.valueOf(-1)),
	RENDER(44, '\u20ac', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_45(45, '\u0192', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_46(46, '\u2021', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_47(47, '\u2030', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_48(48, '\u0160', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_49(49, '\u0152', BaseVarType.LONG, Long.valueOf(-1L)),
	VECTOR3(50, '\u017d', BaseVarType.VECTOR3, new Vector3()),
	TYPE_51(51, '\u0161', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_53(53, '\u00a1', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_54(54, '\u00a2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_55(55, '\u00a3', BaseVarType.INTEGER, Integer.valueOf(-1)),
	LONG(56, '\u00a7', BaseVarType.LONG, Long.valueOf(-1L)),//CLAN_THREAD
	TYPE_57(57, '\u00ab', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_58(58, '\u00ae', BaseVarType.INTEGER, Integer.valueOf(-1)),
	WORLDMAP_ELEMENT(59, '\u00b5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_60(60, '\u00b6', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_61(61, '\u00c6', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_62(62, '\u00d7', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_63(63, '\u00de', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_64(64, '\u00e1', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_65(65, '\u00e6', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_66(66, '\u00e9', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_67(67, '\u00ed', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_68(68, '\u00ee', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_69(69, '\u00f3', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_70(70, '\u00fa', BaseVarType.INTEGER, Integer.valueOf(-1)),
	USERHASH(71, '\u00fb', BaseVarType.LONG, Long.valueOf(-1L)),
	TYPE_72(72, '\u00ce', BaseVarType.INTEGER, Integer.valueOf(-1)),
	STRUCT(73, 'J', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_74(74, '\u00d0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_75(75, '\u00a4', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_76(76, '\u00a5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_77(77, '\u00e8', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_78(78, '\u00b9', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_79(79, '\u00b0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_80(80, '\u00ec', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_81(81, '\u00eb', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_83(83, '\u00fe', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_84(84, '\u00fd', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_85(85, '\u00ff', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_86(86, '\u00f5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_87(87, '\u00f4', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_88(88, '\u00f6', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_89(89, '\u00f2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_90(90, '\u00dc', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_91(91, '\u00f9', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_92(92, '\u00ef', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_93(93, '\u00af', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_94(94, '\u00ea', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_95(95, '\u00f0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_96(96, '\u00e5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_97(97, 'a', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_98(98, 'F', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_99(99, 'L', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_100(100, '\u00a9', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_101(101, '\u00dd', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TEXTURE(102, '\u00ac', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_103(103, '\u00f8', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_104(104, '\u00e4', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_105(105, '\u00e3', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_106(106, '\u00e2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_107(107, '\u00e0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_108(108, '\u00c0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_109(109, '\u00d2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_110(110, '\u00cf', BaseVarType.LONG, Long.valueOf(0L)),
	TYPE_111(111, '\u00cc', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_112(112, '\u00c9', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_113(113, '\u00ca', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_114(114, '\u00f7', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_115(115, '\u00bc', BaseVarType.LONG, Long.valueOf(-1L)),
	TYPE_116(116, '\u00bd', BaseVarType.LONG, Long.valueOf(-1L)),
	TYPE_117(117, '\u2022', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_118(118, '\u00c2', BaseVarType.LONG, Long.valueOf(-1L)),
	TYPE_119(119, '\u00c3', BaseVarType.INTEGER, Integer.valueOf(-1)),
	TYPE_120(120, '\u00c5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4707(121, '\u00cb', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4758(122, '\u00cd', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4777(123, '\u00d5', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4778(124, '\u00b2', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4779(200, 'X', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4702(201, 'W', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4781(202, 'b', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4787(203, 'B', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4837(204, '4', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4780(205, 'w', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4785(206, 'q', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4786(207, '0', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4826(208, '6', BaseVarType.INTEGER, Integer.valueOf(-1)),
	unknownScriptVarType4788(BaseVarType.INTEGER, Integer.valueOf(-1), '#'),
	unknownScriptVarType4789(BaseVarType.INTEGER, Integer.valueOf(-1), '('),
	unknownScriptVarType4813(BaseVarType.INTEGER, Integer.valueOf(-1), '%'),
	unknownScriptVarType4688(BaseVarType.INTEGER, Integer.valueOf(-1), '&'),
	unknownScriptVarType4792(BaseVarType.INTEGER, Integer.valueOf(-1), ')'),
	unknownScriptVarType4793(BaseVarType.INTEGER, Integer.valueOf(-1), '3'),
	unknownScriptVarType4668(BaseVarType.INTEGER, Integer.valueOf(-1), '5'),
	unknownScriptVarType4795(BaseVarType.INTEGER, Integer.valueOf(-1), '7'),
	unknownScriptVarType4796(BaseVarType.INTEGER, Integer.valueOf(-1), '8'),
	unknownScriptVarType4797(BaseVarType.INTEGER, Integer.valueOf(-1), '9'),
	unknownScriptVarType4831(BaseVarType.INTEGER, Integer.valueOf(-1), 'D'),
	unknownScriptVarType4685(BaseVarType.INTEGER, Integer.valueOf(-1), 'G'),
	unknownScriptVarType4800(BaseVarType.INTEGER, Integer.valueOf(-1), 'U'),
	unknownScriptVarType4801(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00c1'),
	unknownScriptVarType4802(BaseVarType.INTEGER, Integer.valueOf(-1), 'Z'),
	unknownScriptVarType4803(BaseVarType.INTEGER, Integer.valueOf(-1), '~'),
	unknownScriptVarType4804(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00b1'),
	unknownScriptVarType4805(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00bb'),
	unknownScriptVarType4656(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00bf'),
	unknownScriptVarType4807(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00c7'),
	unknownScriptVarType4808(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d8'),
	unknownScriptVarType4809(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d1'),
	unknownScriptVarType4806(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00f1'),
	unknownScriptVarType4719(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d9'),
	unknownScriptVarType4812(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00df'),
	unknownScriptVarType4675(BaseVarType.INTEGER, Integer.valueOf(-1), 'E'),
	INT_ARRAY(BaseVarType.INTEGER, Integer.valueOf(-1), 'Y'),
	unknownScriptVarType4833(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00c4'),
	unknownScriptVarType4658(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00fc'),
	unknownScriptVarType4817(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00da'),
	unknownScriptVarType4771(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00db'),
	unknownScriptVarType4695(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d3'),
	unknownScriptVarType4820(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00c8'),
	unknownScriptVarType4761(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d4'),
	unknownScriptVarType4822(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00be'),
	unknownScriptVarType4823(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00d6'),
	unknownScriptVarType4730(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00b3'),
	unknownScriptVarType4825(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00b7'),
	unknownScriptVarType4835(BaseVarType.INTEGER, Integer.valueOf(-1), '\0'),
	unknownScriptVarType4827(BaseVarType.INTEGER, Integer.valueOf(-1), '\0'),
	unknownScriptVarType4828(BaseVarType.INTEGER, Integer.valueOf(-1), '\0'),
	unknownScriptVarType4829(BaseVarType.INTEGER, Integer.valueOf(-1), '\u00ba'),
	unknownScriptVarType4830(null, Integer.valueOf(-1), '!'),
	unknownScriptVarType4832(null, Integer.valueOf(-1), '$'),
	unknownScriptVarType4694(null, Integer.valueOf(-1), '?'),
	unknownScriptVarType4749(null, Integer.valueOf(-1), '\u00e7'),
	unknownScriptVarType4661(null, Integer.valueOf(-1), '*');


	private ScriptVarType(BaseVarType class448, Object object, char c) {
		this(-1, c, class448, object);
	}
	private ScriptVarType(int id, char c, BaseVarType varBase, Object defaultVal) {
		serialID = id;
		legacyChar = c;
		baseType = varBase;
		defaultValue = defaultVal;
		createScriptCharMap(this);
	}
	
	static void createScriptCharMap(ScriptVarType scriptVar) {
		if (varByLegacyChar == null) {
			varByLegacyChar = new ScriptVarType[256];
		}
		varByLegacyChar[StringTools.getByteFromCharacter(scriptVar.legacyChar) & 0xff] = scriptVar;
	}


	private final int serialID;
	public final BaseVarType baseType;
	private Object defaultValue;
	public char legacyChar;
	public static ScriptVarType[] varByLegacyChar;

	public BaseVarType getVarBaseType() {
		return this.baseType;
	}

	public Object getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public int getSerialId() {
		return this.serialID;
	}
	
	public char getChar() {
		return this.legacyChar;
	}
	
	public static ScriptVarType getByChar(char id) {
		if(id == 'O') {
			return OBJECT;
        }
		for (ScriptVarType value : values()) {
			if (value.legacyChar == id) {
				return value;
			}
		}
		throw new Error("Invalid charactor code passed to ScriptVarType getByChar(): " + id + " (" + ((int) id) + ")");
	}

}
