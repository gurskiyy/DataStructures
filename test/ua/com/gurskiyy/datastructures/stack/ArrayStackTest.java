package ua.com.gurskiyy.datastructures.stack;

public class ArrayStackTest extends AbstractStackTest{
    Stack<String> getStack(){
        return new ArrayStack<>();
    }
}
