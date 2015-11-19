package com.jagex.game.runetek5.config.constants;

public enum Js5Archive {
	ANIMATIONS("ANIMATIONS", 0, "client.anims.js5"),
	BASES("FRAME_BASES", 1, "client.bases.js5"),
	CONFIG("CONFIG", 2, "client.config.js5"),
	INTERFACES("INTERFACES", 3, "client.interfaces.js5"),
	MAPS("MAPS", 5, "client.mapsv2.js5"),
	MODELS("MODELS", 7, "client.models.js5"),
	SPRITES("SPRITES", 8, "client.sprites.js5"),
	TEXTURES_PNG_OLD("TEXTURES", 9),
	HUFFMAN("HUFFMAN_ENCODING", 10, "client.binary.js5"),
	CLIENTSCRIPTS("CLIENTSCRIPT2", 12, "client.scripts.js5"),
	FONTMETRICS("FONTMETRICS", 13, "client.fontmetrics.js5"),
	VORBIS("VORBIS", 14, "client.vorbis"),
	CONFIG_LOC("LOCTYPE", 16, "client.loc.config.js5"),
	CONFIG_ENUM("ENUMTYPE", 17, "client.enum.config.js5"),
	CONFIG_NPC("NPCTYPE", 18, "client.npc.config.js5"),
	CONFIG_OBJ("OBJTYPE", 19, "client.obj.config.js5"),
	CONFIG_SEQ("SEQTYPE", 20, "client.seq.config.js5"),
	CONFIG_SPOT("SPOTTYPE", 21, "client.spot.config.js5"),
	CONFIG_STRUCT("STRUCTTYPE", 22, "client.struct.config.js5"),
	WORLDMAP("WORLDMAP", 23, "client.worldmap.js5"),
	QUICKCHAT("QC", 24, "client.quickchat.js5"),
	QUICKCHAT_GLOBAL("QC_GLOBAL", 25, "client.global.quickchat.js5"),
	CONFIG_MATERIAL("TEXTURETYPE", 26, "client.materials.js5"),
	CONFIG_PARTICLE("PARTICLETYPE", 27, "client.particles.js5"),
	DEFAULTS("DEFAULTS", 28, "client.defaults.js5"),
	BILLBOARDS("BILLBOARDS", 29, "client.billboards.js5"),
	DLLS("DLLS", 30, "client.dlls.js5"),
	SHADERS("SHADERS", 31, "client.shaders.js5"),//hlsl AND glsl
	LOADING_SPRITES_RAW("LOADING_PNG", 32, "client.loadingspritesraw.js5"),
	LOADING_SCREENS("LOADING_SCREENS", 33, "client.loadingscreens.js5"),
	LOADING_SPRITES("LOADING_JAG", 34, "client.loadingsprites.js5"),
	CUTSCENES("CUTSCENES", 35, "client.cutscenes.js5"),
	TEXTURES_DIFFUSE_PNG_OLD("TEXTURES_DIFFUSE_PNG_OLD", 37),
	AUDIO_STREAMS("AUDIO_STREAM", 40, "client.audiostreams"),
	WORLDMAP_LABELS("WORLDMAP_LABELS", 41, "client.worldmaplabels.js5"),
	WORLDMAP_UNKNOWN("WORLDMAP_DATA", 42),
	TEXTURES_PNG("TEXTURES_PNG", 43, "client.textures.hdr.png.js5"),
	TEXTURES_DIFFUSE_PNG("TEXTURES_DIFFUSE_PNG", 44, "client.textures.diffuse.png.js5"),
	TEXTURES_DXT("TEXTURES_DXT", 45, "client.textures.hdr.dxt.js5"),
	TEXTURES_DIFFUSE_DXT("TEXTURES_DIFFUSE_DXT", 46, "client.textures.diffuse.dxt.js5"),
	MODELSRT7("MODELS_RT7", 47, "client.modelsrt7.js5"),
	ANIMSRT7("ANIMS_RT7", 48, "client.animsrt7");



	//JS5_CONFIG_AUDIOGROUPS
	//JS5_CONFIG_AUDIOBUSS

	public final int id;
	private static int requiredArrayLength = -1;
	private final String defaultName;
	public static final Js5Archive[] VALUES = values();

	private Js5Archive(String var1, int var3, String var4) {
		this.id = var3;
		this.defaultName = var4;
	}

	private Js5Archive(String var1, int var3) {
		this.id = var3;
		this.defaultName = null;
	}

	public int getArchiveId() {
		return this.id;
	}

	public String getDefaultName() {
		return this.defaultName;
	}

	public static int getRequiredArrayLength() {
		if(requiredArrayLength == -1) {
			Js5Archive[] var0 = values();
			int var1 = var0.length;

			for(int var2 = 0; var2 < var1; ++var2) {
				Js5Archive var3 = var0[var2];
				if(var3.id > requiredArrayLength) {
					requiredArrayLength = var3.id;
				}
			}

			++requiredArrayLength;
		}

		return requiredArrayLength;
	}

}
