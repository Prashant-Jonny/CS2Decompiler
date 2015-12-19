package com.wycody;

import com.jagex.game.runetek5.config.enumtype.EnumTypeList;
import com.jagex.game.runetek5.config.paramtype.ParamTypeList;
import com.jagex.game.runetek5.config.structtype.StructTypeList;
import com.thedoge.Blocks.MultiBlock;
import com.thedoge.Dev;
import com.thedoge.Update;
import com.thedoge.funky.Factory;
import com.wycody.cs2d.CS2Decompiler;
import com.wycody.cs2d.Context;
import com.wycody.cs2d.print.ConsolePrinter;
import com.wycody.cs2d.rev.RS2Revision;
import com.wycody.cs2d.rev.impl.Revision742;
import com.wycody.cs2d.rev.impl.Revision850;
import com.wycody.cs2d.rev.impl.Revision861;
import com.wycody.cs2d.script.CS2Script;
import com.wycody.cs2d.script.inst.Instruction;
import com.wycody.cs2d.script.inst.InstructionType;
import com.wycody.cs2d.script.inst.base.PushEnumValueInstruction;
import com.wycody.cs2d.script.inst.base.PushInstruction;
import com.wycody.cs2d.script.inst.base.PushParamInstruction;
import com.wycody.cs2d.script.inst.base.rs3.PushVar;
import com.wycody.cs2d.script.inst.base.rs3.StoreVar;
import com.wycody.cs2d.script.inst.impl.Push;
import com.wycody.cs2d.utils.ConfigParser;
import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;
import org.apache.commons.collections4.list.TreeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("Duplicates")
public class Fear861 {

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
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {
//		Update.updateFile(Update.Revision._861);
//		Dev.printMissingInstructions(getNewContext());
		//Dev.findInstructions(getNewContext(),973);
	//	Revision850.debug_instructions = true;
	//	Revision850.print_unknowns = true;
		//itemTest();
		test861(new String[]{"1384"});
//		test861(new String[]{"350"});


		//test861(new String[]{"11472"});
//		test861(new String[]{"1619"});
		//test861(new String[]{"973"});
//		test861(new String[]{"11846"});
	//	Fear.test850(new String[]{"1217"});
//		test861(new String[]{"7318"});
//
//		Revision850.debug_instructions = true;
//		test861(new String[]{"3238"});

		/*
		 CS2Script script = context.getDecompiler().disassemble(scriptId);
		 Error in script: 11091
		Error in script: 11951
		Error in script: 11952
		Error in script: 11953
		Error in script: 11954
		Error in script: 11955
		Error in script: 11956
		Error in script: 11957
		Loading script: 11958
		 */
		//test861(args);
//		test861(new String[]{"7304"});
//		test861(new String[]{"7306"});
//		test861(new String[]{"7308"});
//		test861(new String[]{"7313"});
//		test861(new String[]{"7318"});
//		test861(new String[]{"8876"});
	//	test861(new String[]{"7308"});
	//	test861(new String[]{"7318"});
//		test861(new String[]{"19"});
//		test861(new String[]{"1012"});
//		test861(new String[]{"2660"});
//		test861(new String[]{"6579"});
//		test861(new String[]{"8447"});
//		test861(new String[]{"8449"});

		//test861(new String[]{"280"});
		//test861(new String[]{"281"});
		//test861(new String[]{"6263"});
//		test861(new String[]{"11958"});


//		Context context = getNewContext();
//		context.getDisassembledIterator().forEachRemaining(t -> {
//			if(t == null)
//				return;
//			String name = null;
//			for(Instruction ins : t.getInstructions()) {
//				if(ins instanceof PushVar) {
//					name = ((PushVar)ins).getTypeName();
//				}else if(ins instanceof StoreVar){
//					name = ((StoreVar)ins).getTypeName();
//				}else continue;
//
//				if(name.equalsIgnoreCase("%varclient4819") || name.startsWith("%varclient482")
//						|| name.startsWith("%varclient483")
//						|| name.startsWith("%varclient484")
//						|| name.startsWith("%varclient485")
//						|| name.startsWith("%varclient486")
//						)
//					if(ins instanceof StoreVar)
//						System.out.println("Script write: " + t.getId());
//					else
//						System.out.println("Script read: " + t.getId());
//			}
//		});
	}

	private static void test861(String[] args) throws FileNotFoundException{
        if (new File("C:\\rscd\\data").exists()) {
			FileStore s = FileStore.open(new File("C:\\rscd\\data"));
			Cache c = new Cache(s);
			RS2Revision revision = new Revision861(c);
            
            ConfigParser config = new ConfigParser();
            //config.download("850");
            config.loadRevision("861", revision);
            
			Main.paramTypeList = new ParamTypeList(c);
			Main.enumTypeList = new EnumTypeList(c);
			Main.structTypeList = new StructTypeList(c);

			Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
			CS2Decompiler decompiler = new CS2Decompiler(context);
            context.withBlockEditing(false).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());

         			//int scriptId = 534;
            //1013 - wtf
			//int scriptId = 1434;////1895; //47;

//6489
			int scriptId = 6438;//291; //11497 or 11494
			if (args.length > 0) {
                try{
                    System.err.println("Loading script: " + args[0]);
    				scriptId = Integer.parseInt(args[0]);
                }catch(NumberFormatException nfe){}
			}
			CS2Script script = decompiler.decompile(scriptId);
			script.print(context);
		}
    }


	private static Context getNewContext() throws FileNotFoundException {
		FileStore s = FileStore.open(new File("C:\\rscd\\data"));
		Cache c = new Cache(s);
		ConfigParser config = new ConfigParser();
		RS2Revision revision = new Revision861(c);
		config.loadRevision("861", revision);

		Main.paramTypeList = new ParamTypeList(c);
		Main.enumTypeList = new EnumTypeList(c);
		Main.structTypeList = new StructTypeList(c);

		Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		CS2Decompiler decompiler = new CS2Decompiler(context);
		context.withBlockEditing(false).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		return context;
	}



	private static void itemTest() throws IOException {

		Revision850.debug_instructions = false;
		Revision850.print_unknowns = false;

		FileStore s = FileStore.open(new File("C:\\rscd\\data"));

		FileWriter fw = new FileWriter("f:\\RS-EVO\\push_str.log");

		int[] find = new int[]{9075,24224};


		Cache c = new Cache(s);
		ConfigParser config = new ConfigParser();
		RS2Revision revision = new Revision861(c);
		config.loadRevision("861", revision);

		Main.paramTypeList = new ParamTypeList(c);
		Main.enumTypeList = new EnumTypeList(c);
		Main.structTypeList = new StructTypeList(c);

		Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		CS2Decompiler decompiler = new CS2Decompiler(context);
		context.withBlockEditing(true).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());

		for(int scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
			try {
				CS2Script script = decompiler.disassemble(scriptId);
//				fw.write("\nscript " + scriptId + " pushes:\t");
				HashSet<String> ids = new HashSet<>();

				for (Instruction instruction : script.getInstructions()) {
					if(instruction.getType() == InstructionType.PUSH_INT){
						PushInstruction pi = (PushInstruction)instruction;
						int pushInt = (int) pi.getStackValue();
						for(int test : find)
							if(test == pushInt)
								System.out.println("script: " + scriptId);

//						if(!ids.contains(pushInt)){
//							fw.write( pushInt + "\t");
//							ids.add(pushInt);
//						}
					} else if(instruction.getType() == InstructionType.PUSH_OBJ) {
						PushInstruction pi = (PushInstruction)instruction;
						String pushStr = (String) pi.getStackValue();
						if(pushStr.toLowerCase().contains("gambdfsdfsdler"))
							System.out.println("script: " + scriptId);

						if(!ids.contains(pushStr)){
							fw.write("Script: " + scriptId + "\ttext: " + pushStr + "\n");
							ids.add(pushStr);
						}
					}
				}

			}catch(Throwable t){
				System.err.println("Error in script: " + scriptId);
			//	t.printStackTrace();
				//	t.printStackTrace();
			}


		}

		fw.flush();
		fw.close();
	}
}
