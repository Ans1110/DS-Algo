
from typing import TypeVar

T = TypeVar("T")

type ListNode[T] = {
    val: T,
    next: ListNode[T] | None,
}

class LinkedList[T]:
    def __init__(self):
        self.head: ListNode[T] | None = None
        self.tail: ListNode[T] | None = None

    def prepend(self, value: T) -> None:
        newNode: ListNode[T] = { val: value, next: None}
        self.head = newNode
        if not self.tail:
            self.tail = newNode

    def append(self, value: T) -> None:
        newNode: ListNode[T] = { val: value, next: None }
        if not self.head:
            self.head = newNode
            self.tail = newNode
        else:
            self.tail.next = newNode
            self.tail = newNode
    
    def insert(self, value: T, rawIndex: int) -> None:
        index = 0 if rawIndex < 0 else rawIndex
        if index == 0:
            self.prepend(value)
        else:
            count = 1
            currentNode = self.head
            newNode: ListNode[T] = { val: value, next: None }
            while currentNode:
                if count == index: break
                currentNode = currentNode.next
                count += 1
            if currentNode:
                newNode.next = currentNode.next
                currentNode.next = newNode
            else:
                if self.tail:
                    self.tail.next = newNode
                    self.tail = newNode
                else:
                    self.head = newNode
                    self.tail = newNode
    
    def delete(self, value: T) -> None:
        if not self.head: return
        while self.head and self.head.val == value:
            self.head = self.head.next

        currentNode = self.head
        while currentNode and currentNode.next:
            if currentNode.next.val == value:
                currentNode.next = currentNode.next.next
            else:
                currentNode = currentNode.next

        self.tail = self.head
        while self.tail and self.tail.next:
            self.tail = self.tail.next

    def find(self, target: T) -> ListNode[T] | None:
        if not self.head: return

        currentNode: ListNode[T] | None = self.head
        while currentNode:
            if currentNode.val == target: return currentNode
            currentNode = currentNode.next
        return None
    
    def find_index(self, target: T) -> int:
        if not self.head: return

        currentNode: ListNode[T] | None = self.head
        index = 0
        while currentNode:
            if currentNode.val == target: return index
            currentNode = currentNode.next
            index += 1
        return -1
    
    def delete_tail(self) -> None:
        if self.tail == self.head:
            self.head = None
            self.tail = None
        
        currentNode = self.head
        while currentNode.next:
            if not currentNode.next.next:
                currentNode.next = None
            else:
                currentNode = currentNode.next

        self.tail = currentNode

    def delete_head(self) -> None:
        if not self.head: return

        if self.head.next:
            self.head = self.head.next
        else:
            self.head = None
            self.tail = None

    def delete_at(self, index: int) -> None:
        if index == 0:
            self.delete_head()
        else:
            currentNode = self.head
            count = 1
            while currentNode.next:
                if count == index: break
                currentNode = currentNode.next
                count += 1
            
            if currentNode.next:
                currentNode.next = currentNode.next.next
    
    def to_array(self) -> list[T]:
        nodes: list[T] = []

        currentNode = self.head
        while currentNode:
            nodes.append(currentNode.val)
            currentNode = currentNode.next
        return nodes

    def to_string(self) -> str:
        nodes = self.to_array()
        nodes = [str(value) for value in nodes]
        return nodes

    def reverse(self) -> None:
        currentNode = self.head
        prevNode = None
        nextNode = None
        
        while currentNode:
            nextNode = currentNode.next
            currentNode.next = prevNode

            prevNode = currentNode
            currentNode = nextNode
        
        self.tail = self.head
        self.head = prevNode
