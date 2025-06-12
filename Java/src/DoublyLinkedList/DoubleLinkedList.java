package DoublyLinkedList;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList<T> {
    public ListNode<T> head;
    public ListNode<T> tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void prepend(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (this.head != null) {
            this.head.prev = newNode;
        }
        this.head = newNode;

        if  (this.tail == null) {
            this.tail = newNode;
        }
    }

    public void append(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }

        if (this.tail != null) {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
    }

    public void insert(T value, int rawIndex) {
        int index = Math.max(rawIndex, 0);

        if (index == 0) this.prepend(value);

        int count = 1;
        ListNode<T> currentNode = this.head;

        while (currentNode != null) {
            if (count == index) break;
            currentNode = currentNode.next;
            count++;
        }

        if (currentNode != null) {
            ListNode<T> newNode = new ListNode<>(value);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            } else {
                this.tail = newNode;
            }
        } else {
            this.append(value);
        }
    }

    @SuppressWarnings("t")
    public void delete(T value) {
        if (this.head == null) return;

        ListNode<T> currentNode = this.head;
        ListNode<T> deleteNode;

        while (currentNode != null) {
            if (currentNode.value == value) {
                deleteNode = currentNode;

                if (deleteNode == this.head) {
                    this.head = deleteNode.next;

                    if (this.head != null) {
                        this.head.prev = null;
                    }

                    if (deleteNode == this.tail) {
                        this.tail = null;
                    }
                } else if (deleteNode == this.tail) {
                    this.tail = deleteNode.prev;
                    if (this.tail != null) {
                        this.tail.next = null;
                    }
                } else {
                    ListNode<T> prevNode = deleteNode.prev;
                    ListNode<T> nextNode = deleteNode.next;
                    if (prevNode != null) {
                        prevNode.next = nextNode;
                    }
                    if (nextNode != null) {
                        nextNode.prev = prevNode;
                    }
                }
            }
            currentNode = currentNode.next;
        }
    }

    public void deleteHead() {
        if (this.head == null) return;

        if (this.head.next != null) {
            this.head = this.head.next;
        } else {
            this.head = null;
            this.tail = null;
        }
    }

    public void deleteTail() {
        if (this.tail == null) return;

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
    }

    public void deleteAt(int index) {
        if (this.head == null) return;

        if (index == 0) {
            this.deleteHead();
        }

        int count = 1;
        ListNode<T> currentNode = this.head;

        while (currentNode.next != null) {
            if (count == index) break;
            currentNode = currentNode.next;
            count++;
        }

        if (currentNode.next != null) {
            ListNode<T> prevNode = currentNode.prev;
            ListNode<T> nextNode = currentNode.next;

            if (prevNode != null) {
                prevNode.next = nextNode;
            }

            nextNode.prev = prevNode;
        }
    }

    public void find(T target) {
        if (this.head == null) return;

        ListNode<T> currentNode = this.head;

        while (true) {
            if (currentNode.value == target) return;
            if (currentNode.next == null) return;
            currentNode = currentNode.next;
        }
    }

    public int findIndex(T target) {
        if (this.head == null) return -1;

        int index = 0;
        ListNode<T> currentNode = this.head;

        while (true) {
            if (currentNode.value == target) return index;
            if (currentNode.next == null) return -1;
            currentNode = currentNode.next;
            index++;
        }
    }

    public void reverse() {
        ListNode<T> prevNode = null;
        ListNode<T> nextNode;
        ListNode<T> currentNode = this.head;

        while (currentNode != null) {
            nextNode = currentNode.next;
            prevNode = currentNode.prev;
            currentNode.next = prevNode;
            currentNode.prev = nextNode;

            prevNode = currentNode;
            currentNode = nextNode;
        }
        this.head = prevNode;
        this.tail = this.head;
    }

    public List<T> toArray() {
        List<T> arr = new ArrayList<>();
        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
            arr.add(currentNode.value);
            currentNode = currentNode.next;
        }
        return arr;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        ListNode<T> cur = this.head;
        while (cur != null) {
            list.add(cur.value.toString());
            cur = cur.next;
        }
        return String.join("->", list);
    }
}
