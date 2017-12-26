package ua.com.gurskiyy.datastructures.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractQueueTest {

    abstract Queue<String> getQueue();

    private Queue<String> queue = getQueue();

    @Test
    public void testAdd() {
        queue.add("Stas");
        queue.add("Java one love");
        queue.add("Hello world");
        assertEquals(3, queue.size());
    }

    @Test
    public void testPop() {
        queue.add("Stas");
        queue.add("Java one love");
        queue.add("Hello world");
        assertEquals("Stas", queue.pop());
        assertEquals("Java one love", queue.pop());
        assertEquals("Hello world", queue.pop());
        assertEquals(0, queue.size());
    }

    @Test
    public void testSize() {
        queue.add("Lion");
        queue.add("Shadow demon");
        for (int i = 0; i < 2; i++) {
            queue.pop();
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.add("Ted");
        assertFalse(queue.isEmpty());
    }

}