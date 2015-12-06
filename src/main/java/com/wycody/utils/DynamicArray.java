package com.wycody.utils;

import java.lang.reflect.Array;
import java.util.Iterator;

import org.apache.commons.collections4.list.TreeList;

/**
 * This is a dynamic array, change the mean of fixed array by recreating the
 * array after each resize
 * 
 * When removing an element from the array, it will resort the indexes When
 * adding an element to the array, it will resort the indexes The empty indexes
 * will be removed after each action.
 * 
 * @author Walied-Yassen
 * @date Nov 7, 2015
 * @param <T>
 *            the type of the array
 */
public class DynamicArray<T> implements Iterable<T> {

	/**
	 * The type of the array, Just in-case we're going to use it
	 */
	private Class<T> type;

	/**
	 * The data of the array
	 */
	private T[] data;

	/**
	 * Construct a new {@link DynamicArray} with specified type
	 * 
	 * @param type
	 *            the type of the DynamicArray
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(Class<T> type) {
		this.type = type;
		data = (T[]) Array.newInstance(type, 0);
	}

	/**
	 * Gets an element at index
	 * 
	 * @param index
	 *            the index that the element is located at
	 * @return the element
	 */
	public T get(int index) {
		if (index >= data.length) {
			// So we can avoid ArrayOutOfBoundsException
			return null;
		}
		return data[index];
	}

	/**
	 * Set the {@code value} at {@code index} Note: This will expand the array
	 * if needed
	 * 
	 * @param index
	 *            the index to set at
	 * @param value
	 *            the value to set
	 * 
	 */
	public void add(int index, T value) {
		// you can only increase it by one, this saved memory because when you
		// want to create additional elements they're going to be empty and
		// removed automatically
		if (index > data.length) {
			throw new ArrayIndexOutOfBoundsException("You cannot add value by more than 1 index pad to DynamicArray");
		}
		if (index == data.length) {
			// Recreate the array with the new size
			int newSize = data.length + 1;
			T[] newData = (T[]) Array.newInstance(type, newSize);
			// Copy the old array into new array
			System.arraycopy(data, 0, newData, 0, data.length);
			this.data = newData;
		}

		data[index] = value;
	}

	/**
	 * Add value at the end of the array
	 * 
	 * @param value
	 *            the value to add
	 */
	public void add(T value) {
		add(data.length, value);
	}

	/**
	 * Remove the value at {@code index}, and then resort the array
	 * 
	 * @param index
	 *            the index of the value
	 */
	public void remove(int index, boolean refresh) {
		// There's no way we can remove an element that does not exists
		if (index >= data.length) {
			return;
		}
		// Remove the value at the index
		data[index] = null;
		if (refresh) {
			refresh();
		}
	}

	public void remove(int index) {
		remove(index, true);
	}

	public void refresh() {
		// Recreate and resort the array
		// T[] newData = (T[]) Array.newInstance(type, data.length - 1);
		TreeList<T> list = new TreeList<T>();
		for (T t : data) {
			if (t != null) {
				list.add(t);
			}
		}
		data = (T[]) list.toArray();
		// int off = 0;
		// for (int i = 0; i < data.length; i++) {
		// if (data[i] != null) {
		// newData[off++] = data[i];
		// }
		// }
		// data = newData;

	}

	/**
	 * Sets a new value at index
	 * 
	 * @param index
	 *            the index to set at
	 * @param value
	 *            the value to set
	 */
	public void set(int index, T value) {
		assert value != null : "You cannot set a null value to a dynamic array";
		data[index] = value;
	}

	/**
	 * Get the size of the array
	 * 
	 * @return the size
	 */
	public int size() {
		return data.length;
	}

	/**
	 * @return the data
	 */
	public T[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T[] data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < data.length && data[index] != null;
			}

			@Override
			public T next() {
				return data[index++];
			}

		};
	}

	/**
	 * Gets the first element of the array
	 * 
	 * @return the element
	 */
	public T first() {
		return data.length < 1 ? null : data[0];
	}

	/**
	 * Gets the last element of the array
	 * 
	 * @return the element
	 */
	public T last() {
		return data.length < 1 ? null : data[data.length - 1];
	}

	/**
	 * WARNING: The index always change after each action so don't use it much
	 * Returns the index of an element
	 * 
	 * @param element
	 *            the element to check for
	 * @return the index or -1 if not found
	 */
	public int indexOf(T element) {
		for (int index = 0; index < data.length; index++) {
			if (element.equals(data[index])) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Add all elements from other array to this array
	 * 
	 * @param data
	 *            the elements to add
	 */
	public void addAll(DynamicArray<T> data) {
		for (T element : data) {
			add(element);
		}
	}

	/**
	 * Add elements before the real data
	 * 
	 * @param datathe
	 *            data to add
	 */
	public void addAllFirst(DynamicArray<T> data) {
		T[] clone = (T[]) Array.newInstance(type, size() + data.size());
		int i = 0;
		for (T element : data) {
			clone[i++] = element;
		}
		for (T element : this.data) {
			clone[i++] = element;
		}
		this.data = clone;
	}

	public void clear() {
		data = (T[]) Array.newInstance(type, 0);
	}

	public boolean contains(int key) {
		return get(key) != null;
	}

	public boolean contains(T value) {
		return indexOf(value) != -1;
	}

	public void addAfter(T after, T value) {
		int afterIndex = indexOf(after);
		if (afterIndex == -1) {
			throw new IllegalStateException("The 'after' node does not exists!");
		}
		TreeList<T> list = new TreeList<T>();
		for (int elementIndex = 0; elementIndex < size(); elementIndex++) {
			T element = data[elementIndex];
			list.add(element);
			if (element == after) {
				list.add(value);
			}
		}
		data = (T[]) list.toArray();
	}

	public void addFirst(T value) {
		T[] clone = (T[]) Array.newInstance(type, size() + 1);
		int i = 0;
		clone[i++] = value;
		for (T element : this.data) {
			clone[i++] = element;
		}
		this.data = clone;
	}

}
