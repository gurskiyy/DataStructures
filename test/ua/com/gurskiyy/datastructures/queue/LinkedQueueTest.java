package ua.com.gurskiyy.datastructures.queue;


public class LinkedQueueTest extends AbstractQueueTest{
   Queue<String> getQueue(){
       return new LinkedQueue<>();
   }
}