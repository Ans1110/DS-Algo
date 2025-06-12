package Stack;

import java.util.ArrayList;
import java.util.List;

public class LinkedStack<T> {
    private StackNode<T> stackPeek;
    private int stkSize;

    public int getSize() {
        return this.stkSize;
    }

    public boolean isEmpty() {
        return this.stkSize == 0;
    }

    public void push(T value) {
        StackNode<T> node = new StackNode<T>(value);
        if (this.stackPeek != null) {
            node.next = this.stackPeek;
        }
        this.stackPeek = node;
        this.stkSize++;
    }

    public T pop() {
        if (this.stackPeek == null) throw new Error("Stack is empty");
        T value = this.stackPeek.value;
        this.stackPeek = this.stackPeek.next;
        this.stkSize--;
        return value;
    }

    public T peek() {
        if (this.stackPeek == null) throw new Error("Stack is empty");
        return this.stackPeek.value;
    }

    public List<T> toArray() {
        List<T> arr = new ArrayList<>();
        StackNode<T> currentNode = this.stackPeek;
        while (currentNode != null) {
            arr.add(currentNode.value);
            currentNode = currentNode.next;
        }
        return arr;
    }
}
