package ua.com.gurskiyy.datastructures.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private E[] array;
    private int size;

    public ArrayStack(int capacity) {

        array = (E[])new Object[capacity];
    }

    public ArrayStack(){

        array = (E[])new Object[10];
    }

    public int size(){

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public void push(E element) {
        if (array.length == size) {
            reSize();
        }
        array[size] = element;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayStack is empty, could not pop an element.");
        } else {
            E result = array[size-1];
            size--;
            return result;
        }
    }

    private void reSize(){
        Object [] newArray = new Object[array.length*2];
        System.arraycopy(array, 0, newArray, 0, array.length - 1);
        array = (E[])newArray;
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "size=" + size +
                ", capacity=" + array.length +
                ", array=" + Arrays.toString(array) +
                ", is Empty? = " + isEmpty() +
                '}';
    }
}


