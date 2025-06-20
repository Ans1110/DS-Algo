import DoublyLinkedList.DoubleLinkedList;
import HashMap.ArrayHashMap;
import HashMap.HashMapChaining;
import HashMap.HashMapOpenAddress;
import LInkedList.LinkedList;
import Queue.ArrayQueue;
import Queue.LinkedQueue;
import SeqList.Array;
import Stack.LinkedStack;

import java.util.PriorityQueue;
import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<>();
        q.offer(4);
        q.offer(3);
        q.offer(2);
        q.offer(1);
        System.out.println(q);
    }
}