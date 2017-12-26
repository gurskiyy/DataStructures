package ua.com.gurskiyy.datastructures.stack;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private int size;
    private Node<E> first;


    @Override
    public void push(E element) {
        Node<E> oldFirst = first;
        first = new Node<>();
        first.setObject(element);
        first.setNext(oldFirst);
        size++;
    }


    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedStack is empty");
        }
        E result = first.getObject();
        first = first.getNext();
        size--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "size=" + size +
                ", first=" + first +
                '}';
    }
}
