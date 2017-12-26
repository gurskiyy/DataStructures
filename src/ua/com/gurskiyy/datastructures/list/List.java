package ua.com.gurskiyy.datastructures.list;

public interface List<E> {
    void add(E element);

    void remove(E element);

    boolean isEmpty();

    void remove(int index);

    int size();

    E get(int index);

    void add(E element, int index);

    void clear();

    int indexOf(E element);

    int lastIndexOf(E element);

    void set(E element, int index);

}
