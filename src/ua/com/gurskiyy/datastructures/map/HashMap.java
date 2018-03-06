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
            return getForNullKey();
        } else {
            int index = findIndex(key, array.length);
            Node<K, V> temp;
            for (temp = array[index]; temp != null; temp = temp.getNext()) {
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
        int index = findIndex(key, array.length);
        Node<K, V> newNode = new Node<>(key, value);
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
                    size++;
                    return null;
                }
            }
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int index;
        if (key == null) {
            return removeForNullKey();
        } else {
            index = findIndex(key, array.length);
        }
        Node<K,V> current = array[index];
        Node<K,V> prev = array[index];
        if(current.getNext() == null){
            if(key.equals(current.getKey())){
                V oldValue = current.getValue();
                array[index] = null;
                size--;
                return oldValue;
            }else{
                return null;
            }
        }else{
            while (!key.equals(current.getKey())){
                if(current.getNext() == null){
                    return null;
                }else{
                    prev = current;
                    current = current.getNext();
                }
            }
            if(current.getNext() == null){
                V oldValue = current.getValue();
                prev.setNext(null);
                size--;
                return oldValue;
            }else{
                V oldValue = current.getValue();
                prev.setNext(current.getNext());
                size--;
                return oldValue;
            }
        }
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
        Collection<V> collection = new HashSet<>();
        for (Node<K, V> anArray : array) {
            for (Node<K, V> temp = anArray; temp != null; temp = temp.getNext()) {
                collection.add(temp.getValue());
            }
        }
        return collection;
    }

    private V putForNullKey(V value) {
        Node<K, V> newNode = new Node<>(null, value);
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
                    size++;
                    return null;
                }
            }
        }
        size++;
        return null;
    }

    private V removeForNullKey() {
        Node<K,V> current = array[0];
        Node<K,V> prev = array[0];
        if(current.getNext() == null){
            if(null == current.getKey()){
                V oldValue = current.getValue();
                array[0] = null;
                size--;
                return oldValue;
            }else{
                return null;
            }
        }else{
            while (null != current.getKey()){
                if(current.getNext() == null){
                    return null;
                }else{
                    prev = current;
                    current = current.getNext();
                }
            }
            if(current.getNext() == null){
                V oldValue = current.getValue();
                prev.setNext(null);
                size--;
                return oldValue;
            }else{
                V oldValue = current.getValue();
                prev.setNext(current.getNext());
                size--;
                return oldValue;
            }
        }
    }

    private int findIndex(Object key, int capacity) {
        int keyHashCode = Math.abs(key.hashCode());
        return keyHashCode % capacity;
    }

    private V getForNullKey() {
        for (Node<K, V> temp = array[0]; temp != null; temp = temp.getNext()) {
            if (temp.getKey() == null) {
                return temp.getValue();
            }
        }
        return null;
    }

    private void resize() {
        if (size >= array.length * LOAD_FACTOR) {
            Node<K, V>[] newArray = new Node[array.length * 2];
            transfer(newArray);
            array = newArray;
        }
    }

    private void transfer(Node<K, V>[] newArray) {
        int newCapacity = newArray.length;
        for (Node<K, V> anArray : array) {
            if (anArray != null) {
                for (Node<K, V> temp = anArray; temp != null; temp = temp.getNext()) {
                    Node<K, V> newTemp = new Node<>(null, null);
                    newTemp.setValue(temp.getValue());
                    newTemp.setKey(temp.getKey());
                    if (temp.getKey() == null) {
                        if (newArray[0] == null) {
                            newArray[0] = newTemp;
                        } else {
                            Node<K, V> current = newArray[0];
                            while (current.getNext() != null) {
                                current = current.getNext();
                            }
                            current.setNext(newTemp);
                        }
                    } else {
                        int index = findIndex(temp.getKey(), newCapacity);
                        if (newArray[index] == null) {
                            newArray[index] = newTemp;
                        } else {
                            Node<K, V> current = newArray[index];
                            while (current.getNext() != null) {
                                current = current.getNext();
                            }
                            current.setNext(newTemp);
                        }
                    }
                }
            }
        }
    }
}