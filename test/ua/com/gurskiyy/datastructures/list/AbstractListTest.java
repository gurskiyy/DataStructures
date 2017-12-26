package ua.com.gurskiyy.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractListTest {
    abstract List<String> getList();

    private List<String> listWithZeroElements = getList();
    private List<String> listWithFiveElements = getList();
    private List<String> listWithTwentyElements = getList();

    @Before
    public void before() {

        int value = 0;
        for (int i = 0; i < 5; i++) {
            listWithFiveElements.add("" + value++);
        }

        char c = 'A';
        for (int i = 0; i < 20; i++) {
            listWithTwentyElements.add("" + c++);
        }
    }

    @Test
    public void testRemove() {
        listWithFiveElements.remove(2);
        assertEquals("0", listWithFiveElements.get(0));
        assertEquals("1", listWithFiveElements.get(1));
        assertEquals("3", listWithFiveElements.get(2));
        assertEquals("4", listWithFiveElements.get(3));
        assertEquals(4, listWithFiveElements.size());

        listWithTwentyElements.remove("B");
        assertEquals(19, listWithTwentyElements.size());
    }

    @Test
    public void testAdd() {
        listWithZeroElements.add("A");
        assertEquals(1, listWithZeroElements.size());

        listWithFiveElements.add("25", 5);
        listWithFiveElements.add("100");
        assertEquals(7, listWithFiveElements.size());

        listWithTwentyElements.add("Stas", 5);
        listWithTwentyElements.add("A");

        assertEquals(22, listWithTwentyElements.size());

    }

    @Test
    public void testIsEmpty() {
        assertTrue(listWithZeroElements.isEmpty());
        listWithZeroElements.add("Hello");
        assertFalse(listWithZeroElements.isEmpty());

        assertFalse(listWithTwentyElements.isEmpty());
        listWithTwentyElements.clear();
        assertTrue(listWithTwentyElements.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, listWithZeroElements.size());
        assertEquals(5, listWithFiveElements.size());
        assertEquals(20, listWithTwentyElements.size());
    }


    @Test
    public void testClear() {
        listWithZeroElements.add("2");
        listWithZeroElements.add("3");
        listWithZeroElements.add("4");
        listWithZeroElements.clear();
        assertEquals(0, listWithZeroElements.size());
    }

    @Test
    public void testIndexOf() {
        listWithZeroElements.add("2");
        listWithZeroElements.add("3");
        listWithZeroElements.add("4");

        assertEquals(1, listWithZeroElements.indexOf("3"));

        assertEquals(1, listWithTwentyElements.indexOf("B"));

        assertEquals(3, listWithFiveElements.indexOf("3"));
    }

    @Test
    public void testLastIndexOf() {
        listWithZeroElements.add("A");
        listWithZeroElements.add("B");
        listWithZeroElements.add("C");
        assertEquals(0, listWithZeroElements.lastIndexOf("A"));
        assertEquals(3, listWithFiveElements.lastIndexOf("3"));
        assertEquals(3, listWithTwentyElements.lastIndexOf("D"));
    }


    @Test
    public void testSet() {
        listWithZeroElements.add("2");
        listWithZeroElements.set("11", 0);
        assertEquals("11", listWithZeroElements.get(0));

        listWithTwentyElements.add("Stas");
        listWithTwentyElements.set("Это ты сушил моих пингвинов?", 20);
        assertEquals("Это ты сушил моих пингвинов?", listWithTwentyElements.get(20));

    }

    @Test
    public void testGet() {
        listWithZeroElements.add("HAHA", 0);
        assertEquals("HAHA", listWithZeroElements.get(0));
    }

    @Test
    public void testReSize() {
        for (int i = 0; i < 20; i++) {
            listWithZeroElements.add("a");
        }
        assertEquals(20, listWithZeroElements.size());

        for (int i = 0; i < 40; i++) {
            listWithTwentyElements.add("We are testing resize");
        }
        assertEquals(60, listWithTwentyElements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexException() {
        listWithFiveElements.get(5);
    }

    @Test(expected = RuntimeException.class)
    public void testGetAndRemoveException() {
        listWithZeroElements.get(0);
    }
}
