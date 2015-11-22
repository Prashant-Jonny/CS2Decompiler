package com.wycody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jagex.game.runetek5.config.enumtype.EnumTypeList;
import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.jagex.game.runetek5.config.structtype.StructTypeList;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.Revision;
import com.wycody.cs2d.rev.impl.Revision742;
import com.wycody.cs2d.rev.impl.Revision850;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.utils.ConfigParser;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;

/**
 * In this class, We're going to run the decompiler over some scripts
 * 
 * @author Walied-Yassen
 * @date Nov 6, 2015
 */
public class Main {

	// here for now
	public static ParamTypeList paramTypeList;
	public static StructTypeList structTypeList;
	public static EnumTypeList enumTypeList;

	/**
	 * The main entry point for every java application, in this application
	 * we're going to initialize the compiler, and run a bunch of tests
	 * 
	 * @param args
	 *            the arguments of the application that we're going to start.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);

		File f = new File("C:\\Users\\Ethan\\Documents\\rscd\\850\\");

		if (new File("F:\\LIVE\\").exists()) {
			/*
			 * FileStore s = FileStore.open(new File("F:\\LIVE\\")); Cache c =
			 * new Cache(s); Revision revision = new Revision850(c);
			 * paramTypeList = new ParamTypeList(c); enumTypeList = new
			 * EnumTypeList(c); structTypeList = new StructTypeList(c);
			 * 
			 * Context context = new
			 * Context().withCache(c).withDisassembler(revision).
			 * withInstructionDecoder(revision).withPrinter(new
			 * ConsolePrinter()); CS2Decompiler decompiler = new
			 * CS2Decompiler(context);
			 * 
			 * //int scriptId = 534; //1013 - wtf int scriptId = 1013; if
			 * (args.length > 0) { try{ scriptId = Integer.parseInt(args[0]);
			 * }catch(NumberFormatException nfe){} } File fSrc = new File(
			 * "F:\\LIVE\\IMPORT\\" + scriptId + ".rs2"); CS2Script script =
			 * decompiler.decompile(scriptId, fSrc);// 3220);
			 * script.print(context);
			 */

			Fear.main(args);
		} else if (f.exists()) {
			FileStore s = FileStore.open(f);// "E:\\Misc\\Runescape Private
			Cache c = new Cache(s);
			Revision revision = new Revision850(c);
            
            ConfigParser config = new ConfigParser();
            //config.download("850");
            Revision850.print_unknowns = true;
            //Revision850.debug_instructions = true;
            config.loadRevision("850", revision);
            
			Main.paramTypeList = new ParamTypeList(c);
			Main.enumTypeList = new EnumTypeList(c);
			Main.structTypeList = new StructTypeList(c);

			Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
			CS2Decompiler decompiler = new CS2Decompiler(context);
            context.withBlockEditing(true).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
			
         			//int scriptId = 534;
            //1013 - wtf
			//int scriptId = 1434;////1895; //47;



			int scriptId = 11497; //11497 or 11494
			if (args.length > 0) {
                try{
                    System.err.println("Loading script: " + args[0]);
    				scriptId = Integer.parseInt(args[0]);
                }catch(NumberFormatException nfe){}
			}
			CS2Script script = decompiler.decompile(scriptId);
			script.print(context);

		} else {
			int scriptId = 5350;

			if (args.length >= 1) {
				try {
					scriptId = Integer.parseInt(args[0]);
				} catch (NumberFormatException nfe) {
				}
			}
			File cachePath = new File("E:\\Misc\\Runescape Private Servers\\Releases\\RuneNova\\Source\\data\\cache\\");
			if (args.length >= 2) {
				cachePath = new File(args[1].replaceFirst("^~", System.getProperty("user.home")));
			}

			// Load up the decompiler
			Revision revision = new Revision742();
			// ConfigParser config = new ConfigParser();
			// config.download("742");
			// config.loadRevision("742", revision);
			FileStore s = FileStore.open(cachePath);
			Cache cache = new Cache(s);
			paramTypeList = new ParamTypeList(cache);
			Context context = new Context();
			CS2Decompiler decompiler = new CS2Decompiler(context);

			context.withBlockEditing(true).withDebug(false).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
			
			 CS2Script script = decompiler.decompile(3680);//793
			 script.print(context);
//			context.withPrinter(new FilePrinter(new File("data/dumps/742/"), "%d.cs2"));
//			a: for (scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
//
//				try {
//					CS2Script script = decompiler.decompile(scriptId);
//					if(script == null) {
//						continue a;
//					}
//					script.print(context);
//				} catch (Error e) {
//					continue;
//				}
//			}
			// for(CS2Script script : findInstruction(decompiler, 350)) {
			// System.out.println(script.getId());
			// }
		}
	}

	public static ArrayList<CS2Script> findInstruction(CS2Decompiler decompiler, int id) throws IOException {
		ArrayList<CS2Script> scripts = new ArrayList<CS2Script>();
		a: for (int scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
			CS2Script script = decompiler.disassemble(scriptId);

			b: for (Instruction instruction : script.getInstructions()) {
				if (instruction.getId() == id) {
					scripts.add(script);
					continue a;
				}
			}
		}
		return scripts;
	}
}
