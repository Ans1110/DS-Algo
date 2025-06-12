package Queue;

import java.util.ArrayList;
import java.util.List;

public class ArrayQueue<T> {
    private final List<T> queue;
    private int front;
    private int queueSize;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.queue = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            queue.add(null);
        }
        this.front = 0;
        this.queueSize = 0;
        this.capacity = capacity;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public void enqueue(T value) {
        if (queueSize == capacity) throw new Error("Queue is full");
        int rear = (front + queueSize) % capacity;
        queue.set(rear, value);
        queueSize++;
    }

    public T dequeue() {
        if (queueSize == 0) throw new Error("Queue is empty");
        T value = queue.get(front);
        front = (front + 1) % capacity;
        queueSize--;
        return value;
    }

    public T peek() {
        if (queueSize == 0) throw new Error("Queue is empty");
        return queue.get(front);
    }
}
