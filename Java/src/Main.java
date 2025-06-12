import DoublyLinkedList.DoubleLinkedList;
import LInkedList.LinkedList;
import SeqList.Array;


public class Main {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(5);
        System.out.println(linkedList.toString());

        linkedList.deleteTail();
        linkedList.deleteHead();
        System.out.println(linkedList.toString());

        linkedList.insert(6, 2);
        System.out.println(linkedList.toString());

        linkedList.deleteAt(2);
        System.out.println(linkedList.toString());

        System.out.println(linkedList.findIndex(3));
    }
}