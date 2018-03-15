package ua.com.gurskiyy.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private int size;
    private Node<K, V>[] array;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public HashMap() {
        array = new Node[INITIAL_CAPACITY];
    }

    public HashMap(int capacity) {
        array = new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            for (Node<K, V> current = array[0]; current != null; current = current.getNext()) {
                if (null == current.getKey()) {
                    return true;
                }
            }
        } else {
            int index = findIndex(key);
            for (Node<K, V> current = array[index]; current != null; current = current.getNext()) {
                if (key.equals(current.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            for (Node<K, V> anArray : array) {
                if (anArray != null) {
                    for (Node<K, V> current = anArray; current != null; current = current.getNext()) {
                        if (null == current.getValue()) {
                            return true;
                        }
                    }
                }
            }
        } else {
            for (Node<K, V> anArray : array) {
                if (anArray != null) {
                    for (Node<K, V> current = anArray; current != null; current = current.getNext()) {
                        if (value.equals(current.getValue())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            for (Node<K, V> temp = array[0]; temp != null; temp = temp.getNext()) {
                if (temp.getKey() == null) {
                    return temp.getValue();
                }
            }
        } else {
            int index = findIndex(key);
            for (Node<K, V> temp = array[index]; temp != null; temp = temp.getNext()) {
                if (key.equals(temp.getKey())) {
                    return temp.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        resize();
        if (key == null) {
            return putForNullKey(value);
        }
        int index = findIndex(key);
        Node<K, V> newNode = new Node<>();
        newNode.setKey(key);
        newNode.setValue(value);
        if (array[index] == null) {
            array[index] = newNode;
        } else {
            for (Node<K, V> temp = array[index]; temp != null; temp = temp.getNext()) {
                if (key.equals(temp.getKey())) {
                    V oldValue = temp.getValue();
                    temp.setValue(value);
                    return oldValue;
                } else if (temp.getNext() == null) {
                    temp.setNext(newNode);
                    break;
                }
            }
        }
        size++;
        return null;
    }

    private V putForNullKey(V value) {
        Node<K, V> newNode = new Node<>();
        newNode.setValue(value);
        if (array[0] == null) {
            array[0] = newNode;
        } else {
            for (Node<K, V> temp = array[0]; temp != null; temp = temp.getNext()) {
                if (null == temp.getKey()) {
                    V oldValue = temp.getValue();
                    temp.setValue(value);
                    return oldValue;
                } else if (temp.getNext() == null) {
                    temp.setNext(newNode);
                    break;
                }
            }
        }
        size++;
        return null;
    }

    public V remove(Object key) {
        int index;
        if (key == null) {
            return removeForNullKey();
        } else {
            index = findIndex(key);
        }
        Node<K, V> current = array[index];
        Node<K, V> prev = null;
        V result;
        while (current != null) {
            if (key.equals(current.getKey())) {
                result = current.getValue();
                if (prev == null) {
                    array[index] = current.getNext();
                } else {
                    if (current.getNext() != null) {
                        prev.setNext(current.getNext());
                    } else {
                        prev.setNext(null);
                    }
                }
                current = null;
                size--;
                return result;
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    private V removeForNullKey() {
        Node<K, V> current = array[0];
        Node<K, V> prev = null;
        V result;
        while (current != null) {
            if (null == current.getKey()) {
                result = current.getValue();
                if (prev == null) {
                    array[0] = current.getNext();
                } else {
                    if (current.getNext() != null) {
                        prev.setNext(current.getNext());
                    } else {
                        prev.setNext(null);
                    }
                }
                current = null;
                size--;
                return result;
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Node<K, V> anArray : array) {
            for (Node<K, V> temp = anArray; temp != null; temp = temp.getNext()) {
                set.add(temp.getKey());
            }
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (Node<K, V> anArray : array) {
            for (Node<K, V> temp = anArray; temp != null; temp = temp.getNext()) {
                set.add(temp);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        for (Node<K, V> anArray : array) {
            for (Node<K, V> temp = anArray; temp != null; temp = temp.getNext()) {
                collection.add(temp.getValue());
            }
        }
        return collection;
    }


    private int findIndex(Object key) {
        return findIndex(key, array.length);
    }

    private int findIndex(Object key, int capacity) {
        if (key == null) {
            return 0;
        } else {
            int keyHashCode = Math.abs(key.hashCode());
            return keyHashCode % capacity;
        }
    }

    private void resize() {
        if (size >= array.length * LOAD_FACTOR) {
            Node<K, V>[] newArray = new Node[array.length * 2];
            transfer(newArray);
            array = newArray;
        }
    }

    private void transfer(Node<K, V>[] newArray) {
        for (Node<K, V> bucket : array) {
            if (bucket != null) {
                for (Node<K, V> temp = bucket; temp != null; temp = temp.getNext()) {
                    K key = temp.getKey();
                    V value = temp.getValue();
                    Node<K, V> newNode = new Node<>();
                    newNode.setValue(value);
                    newNode.setKey(key);
                    int index = findIndex(key, newArray.length);
                    if (newArray[index] == null) {
                        newArray[index] = newNode;
                    } else {
                        Node<K, V> current = newArray[index];
                        while (current.getNext() != null) {
                            current = current.getNext();
                        }
                        current.setNext(newNode);
                    }
                }
            }
        }
    }
}