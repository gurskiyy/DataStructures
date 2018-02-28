package ua.com.gurskiyy.datastructures.hashMap;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap<String, String> hashMap = new HashMap<>();

    @Test
    public void testSize() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "Java");
        assertEquals(2, hashMap.size());

    }

    @Test
    public void testIsEmptyAndClear() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "Java");
        hashMap.clear();
        assertEquals(true, hashMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        assertEquals(true, hashMap.containsKey("Stas"));
        assertEquals(true, hashMap.containsKey(null));
        assertEquals(false, hashMap.containsKey("asdsd"));
    }

    @Test
    public void testContainsValue() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        hashMap.put("sdasd", null);
        assertEquals(true, hashMap.containsValue("Java"));
        assertEquals(true, hashMap.containsValue("King"));
        assertEquals(true, hashMap.containsValue(null));
    }

    @Test
    public void testGetAndPut() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("Ted", null);
        assertEquals("Java", hashMap.get("Stas"));
        assertEquals("Nata", hashMap.get("gh"));
        assertEquals(null, hashMap.get("Ted"));
    }


    @Test
    public void testRemove() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put(null, "Stas");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        hashMap.put("Ted", null);
        assertEquals("Java", hashMap.remove("Stas"));
        assertEquals("Stas", hashMap.remove(null));
        assertEquals("Nata", hashMap.remove("gh"));

    }

    @Test
    public void testPutAll() {
        HashMap<String, String> newMap = new HashMap<String, String>();
        newMap.put("Dog", "new Value");
        newMap.put("111", "Deniska");
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        hashMap.putAll(newMap);
        assertEquals(6, hashMap.size());
        assertEquals(true, hashMap.containsValue("Deniska"));
        assertEquals("new Value", hashMap.get("Dog"));
    }


    @Test
    public void testKeySet() {
        Set<String> set;
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        set = hashMap.keySet();
        assertEquals(true, set.contains("Stas"));
        assertEquals(true, set.contains(null));
        assertEquals(4, set.size());

    }

    @Test
    public void testValues() {
        Collection<String> collection;
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        collection = hashMap.values();
        assertEquals(true, collection.contains("Java"));
        assertEquals(true, collection.contains("King"));
        assertEquals(4, collection.size());
    }

    @Test
    public void testNodeSet() {
        Set<String> set;
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");
        set = hashMap.keySet();
        assertEquals(true, set.contains("Stas"));
        assertEquals(true, set.contains(null));
        assertEquals(true, set.contains("gh"));
        assertEquals(4, set.size());
    }
}