package DoublyLinkedList;

public class ListNode<T> {
    public T value;
    public ListNode<T> next;
    public ListNode<T> prev;

    public ListNode(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public ListNode(T value, ListNode<T> next, ListNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
