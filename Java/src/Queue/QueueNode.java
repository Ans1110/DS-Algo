package Queue;

public class QueueNode<T> {
    T val;
    QueueNode<T> next;

    public QueueNode(T val) {
        this.val = val;
        this.next = null;
    }

    public QueueNode(T val, QueueNode<T> next) {
        this.val = val;
        this.next = next;
    }
}
