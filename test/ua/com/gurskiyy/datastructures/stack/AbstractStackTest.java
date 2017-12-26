package ua.com.gurskiyy.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class  AbstractStackTest {

    abstract Stack<String> getStack();
    private Stack<String> stack = getStack();

    @Before
    public void before() {
        stack.push("Lion");
        stack.push("Shadow demon");
    }

    @Test
    public void testPushAndPop() {
        assertEquals("Shadow demon", stack.pop());
        assertEquals("Lion", stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

    }

    @Test
    public void testIsEmpty() {
        stack.push("Lion");
        stack.push("Shadow demon");
        for (int i = 0; i < 4; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty());
        stack.push("SORRY NOT SORRY");
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testSize() {
        stack.push("Lion");
        stack.push("Shadow demon");
        for (int i = 0; i < 4; i++) {
            stack.pop();
        }
        assertEquals(0, stack.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopExceptionLinkedStack() {
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopExceptionArrayStack() {
        for (int i = 0; i < 3; i++) {
            stack.pop();
        }
    }
}
