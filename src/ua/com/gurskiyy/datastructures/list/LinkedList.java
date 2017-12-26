package ua.com.gurskiyy.datastructures.list;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public boolean isEmpty() {
        return first == null;
    }

    public void add(E element) {
        add(element, size);
    }

    public void add(E element, int index) {
        validateIndexToAdd(index);
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            first = last = newNode;
        } else if (index == 0) {
            first.setPrevious(newNode);
            newNode.setNext(first);
            first = newNode;
        } else if (index == size) {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        } else {
            Node<E> after = getNode(index);
            Node<E> before = after.getPrevious();
            before.setNext(newNode);
            after.setPrevious(newNode);
            newNode.setNext(after);
            newNode.setPrevious(before);
        }
        size++;
    }

    public void remove(E element) {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty, could not remove an element");
        }
        if (element.equals(first.getElement())) {
            if (first.getNext() == null) {
                first = last = null;
            } else {
                first = first.getNext();
                first.getPrevious().setNext(null);
                first.setPrevious(null);
            }
        } else if (element.equals(last.getElement())) {
            last = last.getPrevious();
            last.getNext().setPrevious(null);
            last.setNext(null);
        } else {
            Node<E> current = first.getNext();
            for (int i = 0; i < size - 1; i++) {
                if (element.equals(current.getElement())) {
                    Node<E> beforeDelete = current.getPrevious();
                    Node<E> afterDelete = current.getNext();
                    beforeDelete.setNext(afterDelete);
                    afterDelete.setPrevious(beforeDelete);
                }
                current = current.getNext();
            }
        }
        size--;
    }

    public void remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty, could not remove an element");
        }
        validateIndex(index);
        if (index == 0) {
            if (first.getNext() == null) {
                first = last = null;
            } else {
                first = first.getNext();
                first.getPrevious().setNext(null);
                first.setPrevious(null);
            }
        } else if (index == size - 1) {
            last = last.getPrevious();
            last.getNext().setPrevious(null);
            last.setNext(null);
        } else {
            Node<E> beforeDelete = getNode(index);
            Node<E> afterDelete = beforeDelete.getNext();
            beforeDelete.setNext(afterDelete);
            afterDelete.setPrevious(beforeDelete);
        }
        size--;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty, could not get an element");
        }
        validateIndex(index);
        if (index == 0) {
            return first.getElement();
        } else if (index == size - 1) {
            return last.getElement();
        } else {
            Node<E> result = getNode(index);
            return result.getElement();
        }
    }

    public void set(E element, int index) {
        validateIndex(index);
        if (index == 0) {
            first.setElement(element);
        } else if (index == size - 1) {
            last.setElement(element);
        } else {
            Node<E> result = getNode(index);
            result.setElement(element);
        }
    }

    public int indexOf(E element) {
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            if (element.equals(current.getElement())) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }


    public int lastIndexOf(E element) {
        Node<E> current = last;
        for (int i = size - 1; i >= 0; i--) {
            if (element.equals(current.getElement())) {
                return i;
            }
            current = current.getPrevious();
        }
        return -1;
    }

    public void clear() {
        Node<E> current = first;
        while (current.getNext() != null) {
            current.setElement(null);
            current.setPrevious(null);
            current.setNext(null);
        }
        last = first = null;
        size = 0;
    }

    private Node<E> getNode(int index) {
        if (index < size / 2) {
            Node<E> current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            Node<E> current = last;
            for (int i = size - index - 1; i > 0; i--) {
                current = current.getPrevious();
            }
            return current;
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
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> current = first;
        result.append(current.getElement());
        while (current.getNext() != null) {
            current = current.getNext();
            result.append(", ").append(current.getElement());
        }
        return result.toString();
    }
}
