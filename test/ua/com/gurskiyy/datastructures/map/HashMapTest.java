package ua.com.gurskiyy.datastructures.map;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap<String, String> hashMap = new HashMap<>(2);

    @Test
    public void testSize() {
        hashMap.put("Stas", "Java");
        hashMap.put("Stas", "Taras");
        hashMap.put("Stas", "Kvas");

        hashMap.put(null, "Java");
        hashMap.put(null, "Key");
        assertEquals(2, hashMap.size());

    }

    @Test
    public void testIsEmptyAndClear() {
        hashMap.clear();
        assertTrue(hashMap.isEmpty());

        hashMap.put("Stas", "Java");
        hashMap.put(null, "Java");
        hashMap.clear();
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", null);

        assertTrue(hashMap.containsKey("Stas"));
        assertTrue(hashMap.containsKey(null));
        assertFalse(hashMap.containsKey("asdsd"));
    }

    @Test
    public void testContainsValue() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", null);

        assertTrue(hashMap.containsValue("Java"));
        assertTrue(hashMap.containsValue("King"));
        assertTrue(hashMap.containsValue(null));
        assertFalse(hashMap.containsValue("DDDD"));
    }

    @Test
    public void testGetAndPut() {
        hashMap.put("Stas", "Java");
        hashMap.put("Stas", "Deniska");
        hashMap.put(null, "King");
        hashMap.put(null, "Jack");
        hashMap.put("Ted", null);

        assertEquals("Jack", hashMap.get(null));
        assertEquals("Deniska", hashMap.get("Stas"));
        assertEquals(null, hashMap.get("Ted"));
        assertEquals(3, hashMap.size());
    }


    @Test
    public void testRemove() {
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put(null, "Stas");
        hashMap.put("lol", "Jack");
        hashMap.put("Ted", null);
        hashMap.put("Radjesh", "Kutropalli");

        assertEquals("Java", hashMap.remove("Stas"));
        assertEquals("Stas", hashMap.remove(null));
        assertEquals(null, hashMap.remove("Ted"));
        assertTrue(hashMap.containsValue("Jack"));
        assertFalse(hashMap.containsValue(null));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testPutAll() {
        //prepare
        hashMap.put("Stas", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("lol", "Stas");

        HashMap<String, String> newMap = new HashMap();
        newMap.put(null, "new Value");
        newMap.put("Stas", "Jack Daniels");
        newMap.put("111", "Deniska");
        //when
        hashMap.putAll(newMap);
        //then
        assertEquals(5, hashMap.size());
        assertEquals(true, hashMap.containsValue("Deniska"));
        assertEquals("new Value", hashMap.get(null));
        assertEquals("Jack Daniels", hashMap.get("Stas"));
    }


    @Test
    public void testKeySet() {
        //prepare
        hashMap.put("Mamba", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("gh", "Core");
        hashMap.put("lol", "Stas");
        //when
        Set<String> set = hashMap.keySet();
        //then
        assertTrue(set.contains("Mamba"));
        assertTrue(set.contains(null));
        assertTrue(set.contains("gh"));
        assertFalse(set.contains("bla bla"));
        assertEquals(4, set.size());
    }

    @Test
    public void testValues() {
        //prepare
        hashMap.put("Mamba", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("gh", "Core");
        hashMap.put("lol", "Stas");
        //when
        Collection<String> collection = hashMap.values();
        //then
        assertTrue(collection.contains("Java"));
        assertTrue(collection.contains("King"));
        assertTrue(collection.contains("Core"));
        assertFalse(collection.contains("Nata"));
        assertEquals(4, collection.size());
    }

    @Test
    public void testEntrySet() {
        //prepare
        hashMap.put("Mamba", "Java");
        hashMap.put(null, "King");
        hashMap.put("gh", "Nata");
        hashMap.put("gh", "Core");
        hashMap.put("lol", null);
        //when
        Set set = hashMap.entrySet();
        //then
        assertEquals(4, set.size());
    }

    @Test
    public void testResize() {
        hashMap.put("Mamba", "Java");
        hashMap.put(null, "King");
        hashMap.put(null, "java");
        hashMap.put("gh", "Nata");
        hashMap.put("gh", "Core");
        hashMap.put("lol", null);
        hashMap.put("lol", null);
        assertEquals(4, hashMap.size());
    }
}