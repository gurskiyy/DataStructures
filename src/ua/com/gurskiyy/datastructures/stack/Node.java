package ua.com.gurskiyy.datastructures.stack;

public class Node<E> {
    private E object;
    private Node<E> next;

    public E getObject() {
        return object;
    }

    public void setObject(E object) {

        this.object = object;
    }

    public Node<E> getNext() {

        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "object=" + object +
                ", next=" + next +
                '}';
    }
}
