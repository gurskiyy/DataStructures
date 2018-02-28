package ua.com.gurskiyy.datastructures.hashMap;

import java.util.*;

public class HashMap<K, V> {
    private int size;
    private Node[] array;

    public HashMap() {
        array = new Node[16];
    }

    public HashMap(int capacity) {
        array = new Node[capacity];
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        int index;
        if (key == null) {
            index = 0;
        } else {
            index = findIndex(key, array.length);
        }
        for (Node current = array[index]; current != null; current = current.getNext()) {
            if (key == current.getKey()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < array.length; i++) {
            for (Node current = array[i]; current != null; current = current.getNext()) {
                if (value == current.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public V get(Object key) {
        if (key == null) {
            return getForNullKey();
        } else {
            int index = findIndex(key, array.length);
            Node<K, V> temp = array[index];
            if (temp.getKey() == key) {
                return temp.getValue();
            } else {
                while (temp.getNext() != null) {
                    if (key.equals(temp.getKey())) {
                        return temp.getValue();
                    } else {
                        temp = temp.getNext();
                    }
                }
                if (key.equals(temp.getKey())) {
                    return temp.getValue();
                }
            }
        }
        throw new NoSuchElementException();
    }

    private V getForNullKey() {
        for (Node<K, V> temp = array[0]; temp != null; temp.getNext()) {
            if (temp.getKey() == null) {
                return temp.getValue();
            }
        }
        throw new NoSuchElementException();
    }

    public V put(K key, V value) {
        resize();
        if (key == null) {
            putForNullKey(value);
        } else {
            int index = findIndex(key, array.length);
            Node<K, V> newNode = new Node<>(key, value);
            if (array[index] == null) {
                array[index] = newNode;
            } else {
                Node temp = array[index];
                if (key.equals(temp.getKey())) {
                    temp.setValue(value);
                } else {
                    while (temp.getNext() != null) {
                        if (key.equals(temp.getKey())) {
                            temp.setValue(value);
                            return value;
                        } else {
                            temp = temp.getNext();
                        }
                    }
                    temp.setNext(newNode);
                }
            }
        }
        size++;
        return value;
    }

    public V remove(Object key) {
        int index;
        if (key == null) {
            index = 0;
        } else {
            index = findIndex(key, array.length);
        }
        return removeByIndex(index, key);
    }

    public void putAll(HashMap<? extends K, ? extends V> m) {
        for (Node<? extends K, ? extends V> e : m.nodeSet())
            put(e.getKey(), e.getValue());
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            for (Node<K, V> temp = array[i]; temp != null; temp = temp.getNext()) {
                set.add(temp.getKey());
            }
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> collection = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            for (Node<K, V> temp = array[i]; temp != null; temp = temp.getNext()) {
                collection.add(temp.getValue());
            }
        }
        return collection;
    }

    public Set<Node<K, V>> nodeSet() {
        Set<Node<K, V>> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            for (Node<K, V> temp = array[i]; temp != null; temp = temp.getNext()) {
                set.add(temp);
            }
        }
        return set;
    }

    private void putForNullKey(V value) {
        Node<K, V> newNode = new Node<>(null, value);
        if (array[0] == null) {
            array[0] = newNode;
        } else {
            Node temp = array[0];
            if (array[0].getKey() == null) {
                temp.setValue(value);
            } else {
                while (temp.getNext() != null) {
                    if (temp.getKey() == null) {
                        temp.setValue(value);
                    } else {
                        temp.getNext();
                    }
                }
            }
        }
    }

    private int findIndex(Object key, int capacity) {
        int keyHashCode = key.hashCode();
        return keyHashCode % capacity;
    }

    private void resize() {
        if (size >= array.length * 0.75) {
            Node[] newArray = new Node[array.length * 2];
            transfer(newArray);
            array = newArray;
        }
    }

    private void transfer(Node<K, V>[] newArray) {
        Node[] src = array;
        int newCapacity = newArray.length;
        for (int i = 0; i < src.length; i++) {
            Node current = src[i];
            if (current != null) {
                src[i] = null;
                do {
                    Node next = current.getNext();
                    int index = findIndex(current.getValue(), newCapacity);
                    current.setNext(newArray[index]);
                    newArray[index] = current;
                    current = next;
                } while (current != null);
            }
        }
    }

    private V removeByIndex(int index, Object key) {
        V oldValue;
        Node<K, V> current = array[index];
        Node<K, V> previous;
        if (current.getNext() == null) {
            if (current.getKey() == key) {
                oldValue = current.getValue();
                array[index] = null;
                size--;
                return oldValue;
            } else {
                throw new NoSuchElementException();
            }
        } else if (current.getKey() == key) {
            return current.getValue();
        } else {
            previous = current;
            for (Node<K, V> temp = current; temp != null; temp = temp.getNext()) {
                if (temp.getKey() == key) {
                    previous.setNext(current.getNext());
                    oldValue = current.getValue();
                    size--;
                    return oldValue;
                }
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}