package Heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    public  List<Integer> maxHeap;
    public MaxHeap(List<Integer> nums) {
        maxHeap = new ArrayList<>(nums);
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
    private int parent(int i) { return (i - 1) / 2; }
    private int size() { return maxHeap.size(); }
    private boolean isEmpty() { return size() == 0; }

    private void swap(int i, int j) {
        int tmp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, tmp);
    }

    public int peek() {
        return maxHeap.getFirst();
    }

    public void push(int value) {
        maxHeap.add(value);
        siftUp(size() - 1);
    }

    private void siftUp(int i) {
        while (true) {
            int parent = parent(i);
            if (parent < 0 || maxHeap.get(i) <= maxHeap.get(parent)) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    public int pop() {
        if (isEmpty()) throw new Error("Heap is empty");

        swap(0, size() - 1);
        int value = maxHeap.remove(size() - 1);
        siftDown(0);
        return value;
    }

    private void siftDown(int i) {
        while (true) {
            int left = left(i), right = right(i), max = i;
            if (left < size() && maxHeap.get(left) > maxHeap.get(max)) max = left;
            if (right < size() && maxHeap.get(right) > maxHeap.get(max)) max = right;
            if (max == i) break;
            swap(i, max);
            i = max;
        }
    }
}