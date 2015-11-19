package com.wycody.utils;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.list.TreeList;

/**
 * Faster, simpler implementation of DynamicArray
 */
public class DynamicArray_1<T> extends DynamicArray<T>{
	private TreeList<T> backend;
    private boolean readOnly=false;
    
	public DynamicArray_1(Class<T> type) {
        super(type);
        this.backend = new TreeList<>();
	}

	public T get(int index) {
        if(index >= this.backend.size())
            return null;
        return this.backend.get(index);
	}

    @Override
	public void add(int index, T value) {
        this.verifyCanWrite();
		
		if (index == this.backend.size()) {
            this.backend.add(value);
		} else {
			throw new Error("You cannot add value by more than 1 index to DynamicArray");
		}
	}

	public void add(T value) {
        this.verifyCanWrite();
		add(this.backend.size(), value);
	}

	public void remove(int index) {
        this.verifyCanWrite();
        this.backend.remove(index);
	}
    
	public void set(int index, T value) {
        this.verifyCanWrite();
        this.backend.set(index, value);
	}

    @Override
	public int size() {
		return this.backend.size();
	}
    
	@Override
	public Iterator<T> iterator() {
        return this.backend.iterator();
	}

    @Override
	public T first() {
        return this.backend.isEmpty() ? null : this.backend.get(0);
	}

    @Override
	public T last() {
        int size = this.backend.size();
        return size < 1 ? null : this.backend.get(size-1);
    }
    
    @Override
	public int indexOf(T element) {
        return this.backend.indexOf(element);
	}

    @Override
	public void addAll(DynamicArray<T> data) {
        verifyCanWrite();
        addToTree(this.backend,data);
	}

    @Override
	public void addAllFirst(DynamicArray<T> data) {
        verifyCanWrite();
        this.backend = mergeCopy(data,this);
	}
    
    private TreeList<T> mergeCopy(Object left, Object right) {
        TreeList<T> newTree = new TreeList<>();
        addToTree(newTree,left);
        addToTree(newTree,right);
        return newTree;
    }
    
    private void addToTree(TreeList<T> destination, Object source){
        if(source instanceof DynamicArray_1)
            destination.addAll(((DynamicArray_1)source).backend);
        else if(source instanceof DynamicArray)
            for(T el : ((DynamicArray<T>)source))
                destination.add(el);
        else if(source instanceof Collection)
            destination.addAll((Collection)source);
        else
            throw new IllegalArgumentException();
    }    
    
    private void verifyCanWrite(){
        if(readOnly)
            throw new IllegalStateException();
    }
    
    public void setReadOnly(boolean value){
        this.readOnly = value;
    }
}
