import DoublyLinkedList.DoubleLinkedList;
import LInkedList.LinkedList;
import Queue.ArrayQueue;
import Queue.LinkedQueue;
import SeqList.Array;
import Stack.LinkedStack;


public class Main {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(3);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
    }
}