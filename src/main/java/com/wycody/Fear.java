package com.wycody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.thedoge.Blocks.DummyBlock;
import com.thedoge.Blocks.MultiBlock;
import com.thedoge.funky.Factory;
import com.wycody.cs2d.script.inst.Instruction;
import org.apache.commons.collections4.list.TreeList;

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
import com.wycody.cs2d.utils.ConfigParser;

import net.openrs.cache.Cache;
import net.openrs.cache.FileStore;
import net.openrs.io.WrappedByteBuffer;

public class Fear {

	// here for now
	public static ParamTypeList paramTypeList;
	public static StructTypeList structTypeList;
	public static EnumTypeList enumTypeList;

    static void test(){
        TreeList<Integer> tree = new TreeList();
        for(int i=0; i < 10; i++) {
			tree.add(i);
		}
        for(int i=0; i < 5; i++)
		 {
			System.out.println(tree.remove(3));
			//tree.
		}
    }
    
    
    
	/**
	 * The main entry point for every java application, in this application
	 * we're going to initialize the compiler, and run a bunch of tests
	 * 
	 * @param args
	 *            the arguments of the application that we're going to start.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {

        //689
        //472


		//findSHIT(894,1102,913,62,455,597);


        //test();
        // 686 605
//396
//		findSHIT(351,
//				369,
//				1031,
//				1170,
//				243,
//				83);
	//	findSHIT(572);
        Revision850.debug_instructions=true;
        Revision850.print_unknowns=true;
   //     test850(new String[]{"7958"});
       //test850(new String[]{"9915"});

/*
Unknown opcode: 648
Unknown opcode: 956
 */

        /*
        script: 10118
script: 10123           -- sets view count
script: 10154           -- connection
script: 3103
script: 3106
         */


		if(args.length > 1 && args[0].equalsIgnoreCase("dec")) {
			test850(new String[]{args[1]});
		}else if(args.length > 1 && args[0].equalsIgnoreCase("find")){
			Revision850.debug_instructions=false;
			Revision850.print_unknowns=false;
			int[] ids = new int[args.length-1];
			for(int i=0; i < ids.length; i++)
				ids[i] = -1;

			int pos = 0;
			for(int i=1; i < args.length; i++)
				try{
					ids[pos] = Integer.parseUnsignedInt(args[i]);
					pos++;
				}catch (Exception e){

				}
			findSHIT(ids);
		}else{
			;
			test850(args);
		}

		//test742(args);
	}

    private static void test742(String[] args) throws FileNotFoundException{
        if (new File("F:\\CACHES\\742_RuneNova\\").exists()) {
			FileStore s = FileStore.open(new File("F:\\CACHES\\742_RuneNova\\"));
			Cache c = new Cache(s);
			//Revision revision = new Revision742(c);
            
            Revision revision = new Revision742();
            ConfigParser config = new ConfigParser();
          //  config.download("742");
            config.loadRevision("742", revision);
            
			Cache cache = new Cache(s);
			paramTypeList = new ParamTypeList(cache);
			Context context = new Context();
			CS2Decompiler decompiler = new CS2Decompiler(context);
           
            
			context.withDebug(true).withCache(cache).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());




            /*
            [18:20:21] ?????? ??????: 2181
[18:20:38] ?????? ??????: 686



1895
             */
            
            
            //686
            //2002
			//1957
			CS2Script script = decompiler.disassemble(686);//793
            Factory factory = new Factory();
            MultiBlock block = factory.breakDown(script);
            block.print(0);

			//script.print(context);
            /*
            
            ConfigParser config = new ConfigParser();
            config.download("742");
            config.loadRevision("742", revision);
            
			paramTypeList = new ParamTypeList(c);
			enumTypeList = new EnumTypeList(c);
			structTypeList = new StructTypeList(c);

			Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
			CS2Decompiler decompiler = new CS2Decompiler(context);

			//int scriptId = 534;
            //1013 - wtf
			int scriptId = 1895; //47;
			if (args.length > 0) {
                try{
    				scriptId = Integer.parseInt(args[0]);
                }catch(NumberFormatException nfe){}
			}
			File fSrc = new File("F:\\LIVE\\IMPORT\\" + scriptId + ".rs2");
			CS2Script script = decompiler.decompile(scriptId, fSrc);// 3220);
			script.print(context);*/
		}
    }

	public static ArrayList<CS2Script> findInstruction(CS2Decompiler decompiler, int id) throws IOException {
		ArrayList<CS2Script> scripts = new ArrayList<CS2Script>();
		a:for(int scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
			CS2Script script = decompiler.disassemble(scriptId);
			b:for(Instruction instruction : script.getInstructions()) {
				if(instruction.getId() == id) {
					scripts.add(script);
					continue a;
				}
			}
		}
		return scripts;
	}

	private static void findSHIT(int ... ids) throws IOException {
		FileStore s = FileStore.open(new File("F:\\CACHES\\850\\"));
		Cache c = new Cache(s);
		ConfigParser config = new ConfigParser();
		Revision revision = new Revision850(c);
//		config.download("850");
		config.loadRevision("850", revision);

		Main.paramTypeList = new ParamTypeList(c);
		Main.enumTypeList = new EnumTypeList(c);
		Main.structTypeList = new StructTypeList(c);

		Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		CS2Decompiler decompiler = new CS2Decompiler(context);
		context.withBlockEditing(true).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());

		HashSet<Integer> wantedIds = new HashSet<>();
		for(int id: ids)
				wantedIds.add(id);
//
//		wantedIds.clear();
//		wantedIds.add(716);
//		wantedIds.add(571);
//		wantedIds.add(745);
//		wantedIds.add(96);
//		wantedIds.add(1051);
//		wantedIds.add(738);
//		wantedIds.add(147);
//		wantedIds.add(183);
//		wantedIds.add(950);
//		wantedIds.add(781);
//		wantedIds.add(135);
//		wantedIds.add(495);
//		wantedIds.add(617);
//		wantedIds.add(5);

        for(int scriptId = 0; scriptId < decompiler.getContext().getCache().getFileCount(12); scriptId++) {
            int id = scriptId;
			try {
				CS2Script script = decompiler.disassemble(id);

				for (Instruction instruction : script.getInstructions()) {
					if (wantedIds.contains(instruction.getId())) {
						System.out.println("script: " + id);
						break;
					}
				}
			}catch(Throwable t){
				System.err.println("Error in script: " + id);
			//	t.printStackTrace();
			}


		}

	}
    
    
    private static void test850(String[] args) throws FileNotFoundException{
        if (new File("F:\\LIVE\\").exists()) {
			FileStore s = FileStore.open(new File("F:\\CACHES\\850"));
			Cache c = new Cache(s);
			Revision revision = new Revision850(c);
            
            ConfigParser config = new ConfigParser();
            //config.download("850");
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



			int scriptId = 11494; //11497 or 11494
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
}
