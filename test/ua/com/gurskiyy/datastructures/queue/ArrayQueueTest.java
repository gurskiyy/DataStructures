package ua.com.gurskiyy.datastructures.queue;

public class ArrayQueueTest extends AbstractQueueTest{
    Queue<String> getQueue(){

        return new ArrayQueue<>();
    }
}