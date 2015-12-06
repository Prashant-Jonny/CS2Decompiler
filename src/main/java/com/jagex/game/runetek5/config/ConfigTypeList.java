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
    
    /**
	 * The {@link Cache} containing the game data
	 */
	protected Cache cache;
	
	/**
	 * The total number of files within the config group
	 */
	protected int fileCount = 0;
	
	/**
	 * The highest index of an item within the config group
	 */
	protected int maxIndex = -1;
	protected ReferenceTable referenceTable;
	Js5ConfigGroup js5Group;
	Js5Archive archiveID;

	public ConfigType[] definitions;
	protected Archive archive;
	public Class<? extends ConfigType> configType;

	//grouped indices
	int index = 0;
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
	
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, Js5ConfigGroup js5Group) {
		try {
			this.configType = clazz;
            this.js5Group = js5Group;
            this.maxIndex = getMaxIndex(cache, Js5Archive.CONFIG, js5Group);
			Container tableContainer = Container.decode(cache.getStore().read(255, Js5Archive.CONFIG.id));
			referenceTable = ReferenceTable.decode(tableContainer.getReadOnlyData());
			ReferenceTable.Entry entry = referenceTable.getEntry(js5Group.id);
			count = entry.size();
			archive = Archive.decode(cache.read(Js5Archive.CONFIG.id, js5Group.id).getReadOnlyData(), entry.size());
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, VarDomainType id) {
		try {
			varType = id;
			this.configType = clazz;
            this.js5Group = id.getJs5GroupID();
            this.maxIndex = getMaxIndex(cache, Js5Archive.CONFIG, js5Group);
            
			Container tableContainer = Container.decode(cache.getStore().read(255, Js5Archive.CONFIG.id));
			referenceTable = ReferenceTable.decode(tableContainer.getReadOnlyData());
			ReferenceTable.Entry entry = referenceTable.getEntry(js5Group.id);
			count = entry.size();
			archive = Archive.decode(cache.read(Js5Archive.CONFIG.id, js5Group.id).getReadOnlyData(), entry.size());
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
	public ConfigTypeList(Cache cache, Class<? extends ConfigType> clazz, int index, Js5ConfigGroup js5Group) {
        this.js5Group = js5Group;
		this.index = index;
		this.cache = cache;
		this.configType = clazz;
		try {
			Container tableContainer = Container.decode(cache.getStore().read(255, index));
			referenceTable = ReferenceTable.decode(tableContainer.getReadOnlyData());
			count = referenceTable.capacity() * js5Group.getGroupSize();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	static int getMaxIndex(Cache cache, Js5Archive archive, Js5ConfigGroup group) throws IOException {
		if (group.getGroupSize() > 1) {
			int groupCount = cache.getFileCount(archive.getArchiveId()) - 1;
			return (groupCount * group.getGroupSize() + cache.getContainerCount(archive.getArchiveId(), groupCount));
		}
		return cache.getContainerCount(archive.getArchiveId(), group.id);
	}

	public ConfigType list(int id) {
		if(id < 0) {
			throw new IllegalArgumentException("Invalid ID: "+id);
        }
        if (js5Group.getGroupSize() > 1) {
            int group = js5Group.getClientGroupId(id);
			int file = js5Group.getClientFileId(id);
			if(definitions == null) {
				int files = referenceTable.capacity();
				definitions = new ConfigType[files * js5Group.getGroupSize()];
			}
			if(definitions[id] != null)
				return definitions[id];
			ReferenceTable.Entry entry = referenceTable.getEntry(group);
			if (entry == null)
				return null;
			Archive archive;
			try {
				archive = Archive.decode(cache.read(index, group).getReadOnlyData(), entry.size());
			} catch (IOException e1) {
				e1.printStackTrace();
                return null;
			}
			ReferenceTable.ChildEntry childEntry = entry.getEntry(file);
			if (childEntry == null)
				return null;

			ConfigType definition;
			try {
				definition = (ConfigType) configType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
                return null;
			}
			definition.decode(WrappedByteBuffer.wrap(archive.getEntry(file)));
			definition.postDecode();
			definitions[id] = definition;
			return definition;
        } else {//no groups
            id = getRealID(id);
			if(definitions == null) {
				definitions = new ConfigType[maxIndex];
            }
			if(definitions[id] != null) {
                return definitions[id];
            }				
			WrappedByteBuffer bf = WrappedByteBuffer.wrap(archive.getEntry(id));
			ConfigType definition;
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
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
                return null;
			}
			if(bf != null) {
				definition.decode(bf);
				definition.postDecode();
			}
			definitions[id] = definition;
			return definition;
		}
	}
	
	protected int getRealID (int id) {
		if (js5Group.getGroupSize() > 1) {
			int groupID = js5Group.getClientGroupId(id);
			int fileID = js5Group.getClientFileId(id);
			ReferenceTable.ChildEntry childEntry = referenceTable.getEntry(groupID, fileID);
			if (childEntry != null) {
				return childEntry.index();
			}
		} else {
			ReferenceTable.Entry entry = referenceTable.getEntry(js5Group.id);
			if (entry == null) {
				return -1;
			}
			ReferenceTable.ChildEntry childEntry = entry.getEntry(id);
			if (childEntry != null) {
				return childEntry.index();
			}
		}
		return -1;
	}
	
	public int count() {
		return count;
	}
}
