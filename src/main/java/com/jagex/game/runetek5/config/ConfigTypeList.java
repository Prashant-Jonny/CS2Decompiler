package com.jagex.game.runetek5.config;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jagex.game.runetek5.config.constants.Js5Archive;
import com.jagex.game.runetek5.config.constants.Js5ConfigGroup;
import com.jagex.game.runetek5.config.vartype.VarType;
import com.jagex.game.runetek5.config.vartype.bit.VarBitType;
import com.jagex.game.runetek5.config.vartype.constants.VarDomainType;

import net.openrs.cache.Archive;
import net.openrs.cache.Cache;
import net.openrs.cache.Container;
import net.openrs.cache.ReferenceTable;
import net.openrs.io.WrappedByteBuffer;

public class ConfigTypeList {

	public ConfigType[] definitions;
	protected Archive archive;
	public Class<? extends ConfigType> configType;

	//grouped indices
	int groupSize = 0;
	protected ReferenceTable table;
	int index = 0;
	protected Cache cache;
	protected int count = 0;
	private VarDomainType varType;
	
	/**
	 * Used when custom archive info is needed. list MUST be overridden
	 * 
	 * @param clazz
	 * @param cache
	 */
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz) {
		this.configType = clazz;
		this.cache = cache;
		count = 1;
	}
	
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, int id) {
		try {
			this.configType = clazz;
			Container tableContainer = Container.decode(cache.getStore().read(255, Js5Archive.CONFIG.id));
			ReferenceTable table = ReferenceTable.decode(tableContainer.getReadOnlyData());
			ReferenceTable.Entry entry = table.getEntry(id);
			count = entry.size();
			archive = Archive.decode(cache.read(Js5Archive.CONFIG.id, id).getReadOnlyData(), entry.size());
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, VarDomainType id) {
		try {
			varType = id;
			this.configType = clazz;
			Container tableContainer = Container.decode(cache.getStore().read(255, Js5Archive.CONFIG.id));
			ReferenceTable table = ReferenceTable.decode(tableContainer.getReadOnlyData());
			ReferenceTable.Entry entry = table.getEntry(id.getJs5GroupID().id);
			count = entry.size();
			archive = Archive.decode(cache.read(Js5Archive.CONFIG.id, id.getJs5GroupID().id).getReadOnlyData(), entry.size());
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, int index, int id) {
		try {
			this.configType = clazz;
			Container tableContainer = Container.decode(cache.getStore().read(255, index));
			ReferenceTable table = ReferenceTable.decode(tableContainer.getReadOnlyData());
			ReferenceTable.Entry entry = table.getEntry(id);
			count = entry.size();
			archive = Archive.decode(cache.read(index, id).getReadOnlyData(), entry.size());
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, int index, Js5ConfigGroup cfg) {
		groupSize = cfg.getGroupSize();
		this.index = index;
		this.cache = cache;
		this.configType = clazz;
		try {
			Container tableContainer = Container.decode(cache.getStore().read(255, index));
			table = ReferenceTable.decode(tableContainer.getReadOnlyData());
			count = table.capacity() * groupSize;
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void list() {
		if(table == null) {//no groups
			int archiveLength = archive.size();
			if(definitions == null)
				definitions = new ConfigType[archiveLength];
			for(int id = 0; id < archiveLength; id++) {
				WrappedByteBuffer bf = WrappedByteBuffer.wrap(archive.getEntry(id));
				ConfigType definition = null;
				try {
					definition = (ConfigType) configType.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(bf != null) {
					definition.decode(bf);
					definition.postDecode();
				}
				definitions[id] = definition;
			}
		} else {
			int files = table.capacity();
			if(definitions == null)
				definitions = new ConfigType[files * groupSize];

			for (int file = 0; file < files; file++) {
				ReferenceTable.Entry entry = table.getEntry(file);
				if (entry == null)
					continue;
				Archive archive = null;
				try {
					archive = Archive.decode(cache.read(index, file).getReadOnlyData(), entry.size());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int nonSparseMember = 0;
				for (int member = 0; member < entry.capacity(); member++) {
					ReferenceTable.ChildEntry childEntry = entry.getEntry(member);
					if (childEntry == null)
						continue;

					int id = file * groupSize + member;
					ConfigType definition = null;
					try {
						definition = (ConfigType) configType.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					definition.decode(WrappedByteBuffer.wrap(archive.getEntry(nonSparseMember++)));
					definition.postDecode();
					definitions[id] = definition;

				}
			}
		}
	}

	public ConfigType list(int id) {
		if(id == -1)
			return null;
		if(table == null) {//no groups
			int archiveLength = archive.size();
			if(definitions == null)
				definitions = new ConfigType[archiveLength];
			if(definitions[id] != null)
				return definitions[id];
			WrappedByteBuffer bf = WrappedByteBuffer.wrap(archive.getEntry(id));
			ConfigType definition = null;
			try {
				
				if(varType != null) {
					definition = new VarType(id);
					((VarType) definition).setDomainType(varType);
				} else {
					definition = (ConfigType) configType.newInstance();
					if(definition instanceof VarBitType) {
						((VarBitType)definition).id = id;
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(bf != null) {
				definition.decode(bf);
				definition.postDecode();
			}
			definitions[id] = definition;
			return definition;
		} else {
			int group = id >>> (int)(Math.log(groupSize) / Math.log(2));//log2(groupsize)=group size in bits
			int file = id & (groupSize - 1);
			if(definitions == null) {
				int files = table.capacity();
				definitions = new ConfigType[files * groupSize];
			}
			if(definitions[id] != null)
				return definitions[id];
			ReferenceTable.Entry entry = table.getEntry(group);
			if (entry == null)
				return null;
			Archive archive = null;
			try {
				archive = Archive.decode(cache.read(index, group).getReadOnlyData(), entry.size());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ReferenceTable.ChildEntry childEntry = entry.getEntry(file);
			if (childEntry == null)
				return null;

			ConfigType definition = null;
			try {
				definition = (ConfigType) configType.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			definition.decode(WrappedByteBuffer.wrap(archive.getEntry(file)));
			definition.postDecode();
			definitions[id] = definition;
			return definition;

		}
	}
	
	public int count() {
		return count;
	}
}
