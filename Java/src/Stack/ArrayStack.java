package Stack;

import java.util.ArrayList;

public class ArrayStack<T> {
    private final ArrayList<T> stack;

    public ArrayStack() {
        this.stack = new ArrayList<>();
    }

    public int getSize() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void push(T value) {
        this.stack.add(value);
    }

    public T pop() {
        if (this.isEmpty()) throw new Error("Stack is empty");
        return this.stack.removeLast();
    }

    public T peek() {
        if (this.isEmpty()) throw new Error("Stack is empty");
        return this.stack.getLast();
    }
}
