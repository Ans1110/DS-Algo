import DoublyLinkedList.DoubleLinkedList;
import HashMap.ArrayHashMap;
import HashMap.HashMapChaining;
import HashMap.HashMapOpenAddress;
import LInkedList.LinkedList;
import Queue.ArrayQueue;
import Queue.LinkedQueue;
import SeqList.Array;
import Stack.LinkedStack;


public class Main {
    public static void main(String[] args) {
        HashMapOpenAddress hm = new HashMapOpenAddress();
        hm.put(1, "one");
        hm.put(2, "two");
        hm.put(3, "three");
        hm.put(4, "four");
        hm.put(5, "five");
        hm.put(6, "six");
        hm.put(7, "seven");
        hm.put(8, "eight");
        hm.put(9, "nine");
        hm.put(10, "ten");
        hm.print();
        System.out.println(hm.get(1));
        hm.remove(1);
        hm.print();
    }
}