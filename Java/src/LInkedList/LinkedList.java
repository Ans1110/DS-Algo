package LInkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinkedList<T> {
    public ListNode<T> head = null;
    public ListNode<T> tail = null;

    public void prepend(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        this.head = newNode;

        if (this.tail == null) {
            this.tail = newNode;
        }
    }

    public void append(T value) {
        ListNode<T> newNode = new ListNode<>(value, null);
        if (this.head == null) {
            this.head = newNode;
        }
        if (this.tail == null) {
            this.tail = newNode;
        }

        this.tail.next = newNode;
        this.tail = newNode;
    }

    public void insert(T value, int rawIndex) {
        int index = Math.max(rawIndex, 0);

        if (index == 0) {
            this.prepend(value);
        } else {
            int count = 1;
            ListNode<T> currentNode = this.head;
            ListNode<T> newNode = new ListNode<>(value);

            while (currentNode != null) {
                if (count == index) break;
                currentNode = currentNode.next;
                count++;
            }

            if (currentNode != null) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            } else {
                if (this.tail != null) {
                    this.tail.next = newNode;
                } else {
                    this.head = newNode;
                }
                this.tail = newNode;
            }
        }
    }

    public void delete(T value) {
        if (this.head == null) return;

        while (this.head.value == value) {
            this.head = this.head.next;
        }

        ListNode<T> currentNode = this.head;
        while (currentNode.next != null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }

        this.tail = this.head;
        while (this.tail.next != null) {
            this.tail = this.tail.next;
        }
    }

    public ListNode<T> find(T target) {
        if (this.head == null) return null;

        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.value == target) return currentNode;
            currentNode = currentNode.next;
        }

        return null;
    }

    public int findIndex(T target) {
        if (this.head == null) return -1;

        int index = 0;
        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.value == target) return index;
            currentNode = currentNode.next;
            index++;
        }

        return -1;
    }

    public void deleteTail() {
        if (this.head == null) return;

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        }

        ListNode<T> currentNode = this.head;
        while (currentNode.next != null) {
            if (currentNode.next.next != null) {
                currentNode = currentNode.next;
            } else {
                currentNode.next = null;
            }
        }

        this.tail = currentNode;
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

    public void deleteAt(int index) {
        if (this.head == null) return;

        if (index == 0) {
            this.deleteHead();
        } else {
            int count = 1;
            ListNode<T> currentNode = this.head;
            while (currentNode.next != null) {
                if (count == index) break;
                currentNode = currentNode.next;
                count++;
            }

            if (currentNode.next != null) {
                currentNode.next = currentNode.next.next;
            }
        }
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

    public String toString() {
        List<String> list = new ArrayList<>();
        ListNode<T> cur = this.head;
        while (cur != null) {
            list.add(cur.value.toString());
            cur = cur.next;
        }
        return String.join("->", list);
    }

    public void reverse() {
        ListNode<T> currentNode = this.head;
        ListNode<T> prevNode = null;
        ListNode<T> nextNode;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = prevNode;

            prevNode = currentNode;
            currentNode = nextNode;
        }

        this.head = prevNode;
        this.tail = this.head;
    }
 }