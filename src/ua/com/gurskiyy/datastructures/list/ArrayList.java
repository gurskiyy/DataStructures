package ua.com.gurskiyy.datastructures.list;

import java.util.StringJoiner;

public class ArrayList<E> implements List<E> {
    private E[] array;
    private int size;

    public ArrayList(int capacity) {

        array = (E[]) new Object[capacity];
    }

    public void add(E object) {
        resize();
        array[size] = object;
        size++;
    }

    @Override
    public void remove(E object) {
        if (isEmpty()) {
            throw new RuntimeException("arrayList is empty, could not remove an object");
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(array[i])) {
                    remove(i);
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        if (isEmpty()) {
            throw new RuntimeException("arrayList is empty, could not remove an object");
        } else {
            validateIndex(index);
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
        }
    }

    private void validateIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 (inclusive) and " + (size) + " (inclusive)");
        }
    }

    private void validateIndex(int index) {

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be between 0 (inclusive) and " + (size) + " (exclusive)");
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void add(E object, int index) {
        resize();
        validateIndexToAdd(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = object;
        size++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;

    }

    @Override
    public int indexOf(E object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E object) {
            for (int i = size-1; i >= 0; i--) {
                if (object.equals(array[i])) {
                    return i;
                }
            }
            return -1;
    }

     @Override
    public void set(E object, int index) {
        validateIndex(index);
        array[index] = object;
    }


    @Override
    public E get(int index) {
        if (isEmpty()) {
            throw new RuntimeException("arrayList is empty, could not get an object");
        } else {
            validateIndex(index);
            return array[index];
        }
    }

    private void resize() {
        if (size == 0) {
            array =(E[]) new Object[10];
        }
        if (array.length == size) {
            E[] newArray = (E[])new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner("," ,"[", "]");
        for(int i=0; i<size; i++){
            if(array[i] != null) {
                result.add(String.valueOf(array[i]));
            }
        }
        return result.toString();
    }
}
