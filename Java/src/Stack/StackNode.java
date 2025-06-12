package Stack;

public class StackNode<T> {
    public T value;
    public StackNode<T> next;
    public StackNode(T value) {
        this.value = value;
        this.next = null;
    }

    public StackNode(T value, StackNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
