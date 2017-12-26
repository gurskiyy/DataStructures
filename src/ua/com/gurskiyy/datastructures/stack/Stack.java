package ua.com.gurskiyy.datastructures.stack;

public interface Stack<E> {
    void push(E element);

    Object pop();

    boolean isEmpty();

    int size();
}
