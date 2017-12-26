package ua.com.gurskiyy.datastructures.queue;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    public void add(E element) {
        Node<E> oldLast = last;
        last = new Node<>();
        last.setObject(element);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.setNext(last);
        }
        size++;
    }


    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayQueue is empty");
        }
        E result = first.getObject();
        first = first.getNext();
        size--;
        if(isEmpty()){
            last = null;
        }
        return result;
    }


    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return size;
    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
