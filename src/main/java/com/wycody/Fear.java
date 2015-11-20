package com.wycody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	public static void main(String[] args) throws FileNotFoundException {

        //test();
        // 686 605
//396
//		findSHIT(564);//

        Revision850.debug_instructions=true;
        Revision850.print_unknowns=true;
        test850(new String[]{"10118"});



        /*
        script: 10118
script: 10123           -- sets view count
script: 10154           -- connection
script: 3103
script: 3106
         */

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

	private static void findSHIT(int ... ids) throws FileNotFoundException {
		FileStore s = FileStore.open(new File("F:\\LIVE\\"));
		Cache c = new Cache(s);
		ConfigParser config = new ConfigParser();
		Revision revision = new Revision850(c);
		config.download("850");
		config.loadRevision("850", revision);

		Main.paramTypeList = new ParamTypeList(c);
		Main.enumTypeList = new EnumTypeList(c);
		Main.structTypeList = new StructTypeList(c);

		Context context = new Context().withCache(c).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());
		CS2Decompiler decompiler = new CS2Decompiler(context);
		context.withBlockEditing(true).withDebug(false).withCache(c).withDecompiler(decompiler).withDisassembler(revision).withInstructionDecoder(revision).withPrinter(new ConsolePrinter());

		CS2Decompiler.scriptLoader = scriptId -> {
			try{
				File fSrc = new File("F:\\LIVE\\IMPORT\\" + scriptId + ".rs2");
				FileInputStream in = new FileInputStream(fSrc);
				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				return WrappedByteBuffer.wrap(buffer);
			}catch(Exception e){
				throw new Error(e);
			}
		};

		HashSet<Integer> wantedIds = new HashSet<>();
		for(int id: ids)
				wantedIds.add(id);
		/*
		wantedIds.add(716);
		wantedIds.add(571);
		wantedIds.add(745);
		wantedIds.add(96);
		wantedIds.add(1051);
		wantedIds.add(738);
		wantedIds.add(147);
		wantedIds.add(183);
		wantedIds.add(950);
		wantedIds.add(781);
		wantedIds.add(135);
		wantedIds.add(495);*/
		wantedIds.add(617);
		wantedIds.add(5);

		File base = new File("F:\\LIVE\\IMPORT\\");
		for(File scriptFile : base.listFiles()) {
			String fileName = scriptFile.getName();
			if(!fileName.contains(".rs2"))
				continue;
			int id = Integer.parseInt(fileName.replace(".rs2",""));
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
				//t.printStackTrace();
			}


		}

	}
    
    
    private static void test850(String[] args) throws FileNotFoundException{
        if (new File("F:\\LIVE\\").exists()) {
			FileStore s = FileStore.open(new File("F:\\LIVE\\"));
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
			
            CS2Decompiler.scriptLoader = scriptId -> {
                try{
                    File fSrc = new File("F:\\LIVE\\IMPORT\\" + scriptId + ".rs2");
                    FileInputStream in = new FileInputStream(fSrc);
                    byte[] buffer = new byte[in.available()];
                    in.read(buffer);
                    return WrappedByteBuffer.wrap(buffer);
                }catch(Exception e){
                    throw new Error(e);
                }
            };

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
			File fSrc = new File("F:\\LIVE\\IMPORT\\" + scriptId + ".rs2");
			CS2Script script = decompiler.decompile(scriptId, fSrc);// 3220);
			script.print(context);
		}
    }
}
