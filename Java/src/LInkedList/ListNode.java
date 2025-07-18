package LInkedList;

public class ListNode<T> {
    public T value;
    public ListNode<T> next;

    public ListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(T value, ListNode<T> next) {
        this.value = value;
        this.next = next;
    }
}