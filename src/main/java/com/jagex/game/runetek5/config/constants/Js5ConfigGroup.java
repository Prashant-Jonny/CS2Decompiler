package com.jagex.game.runetek5.config.constants;
/**
 * Archives in idx2
 */
public enum Js5ConfigGroup {//TODO REFORMAT THIS FUCK
   FLOOR_UNDERLAY("FLOOR_UNDERLAY", 1, ".flo_un"),
   IDENTIKIT("IDENTIKIT", 3, ".idk"),
   FLOOR_OVERLAY("FLOOR_OVERLAY", 4, ".flo_ov"),
   INVTYPE("INVTYPE", 5, ".inv"),
   LOCTYPE(6, 8, 16),
   ENUMTYPE("ENUMTYPE", 8, ".enum", 8, 17),
   NPCTYPE(9, 7, 18),
   OBJTYPE(10, 8, 19),
   PARAMTYPE("PARAMTYPE", 11, ".param"),
   SEQTYPE(12, 7, 20),
   SPOTTYPE(13, 8, 21),
   AREATYPE("AREATYPE", 18, ".area"),
   STRUCTTYPE("STRUCTTYPE", 26, ".struct", 5),
   CHATPHRASETYPE("CHATPHRASETYPE", 27, ".chatphrase"),
   CHATCATTYPE("CHATCATTYPE", 28, ".chatcat"),
   SKYBOX("SKYBOX", 29, ""), 
   SUN("SUN", 30, ""),
   LIGHT_INTENSITY("LIGHT_INTENSITY", 31, ".intensity"),
   RENDER("RENDER", 32, ".render"),
   CURSORTYPE("CURSORTYPE", 33, ".cursor"),
   MAPSCENETYPE("MAPSCENETYPE", 34, ".map_scene"),
   QUEST("CONFIG_QUEST", 35, ".quest"),
   WORLDMAPTYPE("WORLDMAPTYPE", 36, ".worldmap"),
   HITMARKTYPE("HITMARKTYPE", 46, ".hitmark"),
   ITEMCODETYPE("ITEMCODETYPE", 48, ".itemcode"),
   VAR_PLAYER("VAR_PLAYER", 60, ".var_player"),//60
   VAR_NPC("VAR_NPC", 61, ".var_npc"),//66
   VAR_CLIENT("VAR_CLIENT", 62, ".var_client"),//62
   VAR_WORLD("VAR_WORLD", 63, ".var_world"),
   VAR_REGION("VAR_REGION", 64, ".var_region"),
   VAR_OBJECT("VAR_OBJECT", 65, ".var_object"),//61
   VAR_CLAN("VAR_CLAN", 66, ".var_clan"),//67
   VAR_CLAN_SETTING("VAR_CLAN_SETTING", 67, ".var_clan_setting"),//80
   VAR_GLOBAL("", 68, ""),
   VAR_BIT("VAR_BIT", 69, ".var_bit", 10, 22),
   HITBARTYPE("HITBARTYPE", 72, ".hitbar"),
   VAR_EXCHANGE("", 75, ""),
   SEQ_FLOWCONTROL("SEQ_FLOWCONTROL", 77, ".seq_fc"), 
   VAR_GROUP("VAR_GROUP", 80, ".var_group");
   
   public final int id;
   private final int groupSizeInBits;
   public final String groupName;
   public int altIndex = -1;

   public int getGroupSize() {
      return 1 << this.groupSizeInBits;
   }

   private Js5ConfigGroup(int id, int size) {
	      this("", id, "", size);
   }
   private Js5ConfigGroup(int id, int size, int alt) {
	      this("", id, "", size, alt);
   }
   
   private Js5ConfigGroup(String enumName, int var3, String var4) {
      this(enumName, var3, var4, 0);
   }

   private Js5ConfigGroup(String var1, int id, String name, int size) {
	      this.id = id;
	      this.groupName = name;
	      this.groupSizeInBits = size;
   }
   
   private Js5ConfigGroup(String var1, int id, String name, int groupSize, int altIdx) {
	      this.id = id;
	      this.groupName = name;
	      this.groupSizeInBits = groupSize;
	      this.altIndex = altIdx;
	}

   public int getClientFileId(int var1) {
      return var1 & (1 << this.groupSizeInBits) - 1;
   }

   public int getClientGroupId(int var1) {
      return var1 >>> this.groupSizeInBits;
   }

}

