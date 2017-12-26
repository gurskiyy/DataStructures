package ua.com.gurskiyy.datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] array;
    private int size;

    public ArrayQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }
    public ArrayQueue(){

    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public void add(E element) {
        resize();
        array[size] = element;
        size++;
    }

    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayQueue is empty, could not element");
        }
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayQueue is empty, could not element");
        }
        Object element = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        return element;
    }

    private void resize() {
        if (size == 0) {
            array = (E[]) new Object[10];
        }
        if (size == array.length) {
            E[] newArray = (E[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size - 1);
            array = newArray;
        }

    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }
}
