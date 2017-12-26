package ua.com.gurskiyy.datastructures.queue;

public interface Queue<E> {
    void add(E element);
    boolean isEmpty();
    Object pop();
    int size();
}
