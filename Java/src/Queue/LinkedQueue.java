package Queue;

import java.util.ArrayList;
import java.util.List;

public class LinkedQueue<T> {
    private QueueNode<T> front;
    private QueueNode<T> rear;
    private int queueSize;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.queueSize = 0;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<>(value);
        if (front == null) {
            front = node;
        } else {
            rear.next = node;
        }
        rear = node;
        queueSize++;
    }

    public T dequeue() {
        if (front == null) throw new Error("Queue is empty");
        T value = front.val;
        front = front.next;
        queueSize--;
        return value;
    }

    public T peek() {
        if (front == null) throw new Error("Queue is empty");
        return front.val;
    }

    public List<T> toArray() {
        List<T> arr = new ArrayList<>();
        QueueNode<T> currentNode = this.front;
        while (currentNode != null) {
            arr.add(currentNode.val);
            currentNode = currentNode.next;
        }
        return arr;
    }
}
