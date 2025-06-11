import LInkedList.LinkedList;
import SeqList.Array;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Array array = new Array();

        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);

        System.out.println(list.toString());
        list.delete(1);
        System.out.println(list.toString());
        System.out.println(list.toArray());
    }
}