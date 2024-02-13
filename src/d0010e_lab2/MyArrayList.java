package d0010e_lab2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("serial")
public class MyArrayList<E> implements Serializable, Cloneable, RandomAccess {
	private int size;
	private int capacity;
	private Object[] elementData;
// ---------------------------------------------------------------
public static void main(String[] args) {
	
}
// ---------------------------------------------------------------
public MyArrayList(int initialCapacity) {
	if (initialCapacity < 0) {
		throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
	}
	this.capacity = initialCapacity;
	this.elementData = new Object[initialCapacity];
}
public MyArrayList() {
	this(10);
}
// -- 1 --
public int size() {
	return this.size;

}
public boolean isEmpty() {
	if (size == 0) {
		return true;
	};
	return false;
}

public void clear() {
	if (size > 0) {
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	} else {
		System.out.print("Array is already empty");
	}
	
}
// -- 2 --
public void ensureCapacity(int minCapacity) {
	if (minCapacity > capacity) {
		Object[] newArray = new Object[minCapacity];
		System.arraycopy(elementData, 0, newArray, 0, size);
		elementData = newArray;
		capacity = minCapacity;
	}
}

public void trimToSize() {
	if (size < capacity) {
		Object[] newArray = new Object[capacity];
		System.arraycopy(elementData, 0, newArray, 0, size);
		elementData = newArray;
	}
}
// -- 3 --
public void add(int index, E element) {
	if (index < 0 || index > elementData.length) {
		throw new IndexOutOfBoundsException("Index:" + index + "Size: " + size);
	}
	
	if (size == elementData.length) {
		ensureCapacity(size + 1);
	}
	
	for (int i = size; i > index; i--) {
		elementData[i] = elementData[i-1];
	}
	
	elementData[index] = element;
	size++;
}
public boolean add(E e) {
	add(size , e);
return false; 
}
// -- 4 --
@SuppressWarnings("unchecked")
public E get(int index) {
	if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
	return (E) elementData[index];
}
public E set(int index, E element) {
	if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
	elementData[index] = element;
	return (E) element;
}
// -- 5 --
public E remove(int index) {
	if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
	elementData[index] = null;
	return (E) this.elementData[index];
}
protected void removeRange(int fromIndex, int toIndex) {
	if (fromIndex < 0 || toIndex >= size) {
        throw new IndexOutOfBoundsException("Index: " + toIndex + ", Size: " + size);
    }
	for (int i = fromIndex; i <= toIndex; i++) {
		elementData[i] = null;
	}
}
// -- 6 --
public int indexOf(Object o) {
	for (int i = 0; i < size; i++) {
		if (elementData[i] != null && elementData[i].equals(o)) {
			return i;
		} 
	}
	return -1;

}
public boolean remove(Object o) {
	for (int i = 0; i < size; i++) {
		if (elementData[i] != null && elementData[i].equals(o)) {
            elementData[i] = null;
            return true;
		} 
	}
	return false;
}
public boolean contains(Object o) {
	for (int i = 0; i < size; i++) {
		if (elementData[i] == o) {
			return true;
		} 
	}
	return false;
}

// -- 7 --

public Object clone() {
	try {
		MyArrayList<E> clone = (MyArrayList<E>) super.clone();
		clone.elementData = Arrays.copyOf(elementData, capacity);
		System.out.print(Arrays.toString(clone.elementData));
		return clone;
	}
	catch (CloneNotSupportedException e){
		throw new InternalError(e);
	}
	
}
public Object[] toArray() {
    return (E[]) Arrays.copyOf(elementData, size, elementData.getClass());
}
}
