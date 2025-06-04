
from typing import TypeVar

T = TypeVar("T")

type DoublyListNode[T] = {
    val: T,
    prev: DoublyListNode[T] | None,
    next: DoublyListNode[T] | None,
}

class DoublyLinkedList[T]:
    def __init__(self):
        self.head: DoublyListNode[T] | None = None
        self.tail: DoublyListNode[T] | None = None
    
    def prepend(self, value: T) -> None:
        new_node: DoublyListNode[T] = { val: value, prev: None, next: None }
        if self.head:
            self.head.prev = new_node
        self.head = new_node

        if not self.tail:
            self.tail = new_node
        return
    
    def append(self, value: T) -> None:
        new_node: DoublyListNode[T] = { val: value, prev: None, next: None }
        if not self.head:
            self.head = new_node
            self.tail = new_node
            return
        
        if self.tail:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node
        return
    
    def insert(self, value: T, rawIndex: int) -> None:
        index = 0 if rawIndex < 0 else rawIndex
        if index == 0:
            self.prepend(value)
        else:
            count = 1
            currentNode = self.head
            newNode: DoublyListNode[T] = { val: value, prev: None, next: None }
            while currentNode:
                if count == index: break
                currentNode = currentNode.next
                count += 1
            
            if currentNode:
                newNode.next = currentNode.next
                currentNode.next = newNode
                if newNode.next:
                    newNode.next.prev = newNode
                else:
                    self.tail = newNode
            else:
                self.append(value)
            return
    
    def delete(self, value: T) -> None:
        if not self.head: return

        deleteNode = None
        currentNode = self.head

        while currentNode:
            if currentNode.val == value:
                deleteNode = currentNode

                if deleteNode == self.head:
                    self.head = deleteNode.next

                    if self.head:
                        self.head.prev = None
                    
                    if deleteNode == self.tail:
                        self.tail = None
                    
                elif deleteNode == self.tail:
                    self.tail = deleteNode.prev
                    
                    if self.tail:
                        self.tail.next = None
                
                else:
                    prevNode = deleteNode.prev
                    nextNode = deleteNode.next

                    if prevNode:
                        prevNode.next = nextNode
                    if nextNode:
                        nextNode.prev = prevNode
            
            if currentNode.next:
                currentNode = currentNode.next
        return
    
    def delete_head(self) -> None:
        if not self.head: return

        if self.head.next:
            self.head = self.head.next
            self.head.prev = None
        else:
            self.head = None
            self.tail = None
        return
    

    def delete_tail(self) -> None:
        if not self.tail: return

        if self.tail.prev:
            self.tail = self.tail.prev
            self.tail.next = None
        else:
            self.head = None
            self.tail = None
        return
    
    def delete_at(self, index: int) -> None:
        if not self.head: return

        if index == 0:
            self.delete_head()
            return

        count = 1
        currentNode = self.head
        while currentNode.next:
            if count == index: break
            currentNode = currentNode.next
            count += 1
        
        if currentNode.next:
            prevNode = currentNode.prev
            nextNode = currentNode.next

            if prevNode:
                prevNode.next = nextNode
            if nextNode:
                nextNode.prev = prevNode
        
        return
    
    def find(self, target: T) -> DoublyListNode[T] | None:
        if not self.head: return

        currentNode = self.head
        while currentNode:
            if currentNode.val == target: return currentNode
            if not currentNode.next: break
            currentNode = currentNode.next
        return None
                    
    def find_index(self, target: T) -> int:
        if not self.head: return

        currentNode = self.head
        index = 0
        while currentNode:
            if currentNode.val == target: return index
            if not currentNode.next: break
            currentNode = currentNode.next
            index += 1
        return -1

    def reverse(self) -> None:
        if not self.head: return

        currentNode = self.head
        prevNode = None
        nextNode = None

        while currentNode:
            nextNode = currentNode.next
            currentNode.next = prevNode
            currentNode.prev = nextNode

            prevNode = currentNode
            currentNode = nextNode

        self.head = prevNode
        self.tail = self.head
        return
    
    def to_array(self) -> List[T]:
        nodes: List[T] = []
        currentNode = self.head
        while currentNode:
            nodes.append(currentNode.val)
            currentNode = currentNode.next
        return nodes
    
    def to_string(self, callback: Callable[[T], str] | None = None) -> str:
        if not self.head: return

        currentNode = self.head
        nodes: List[str] = []
        while currentNode:
            if callback:
                nodes.append(callback(currentNode.val))
            else:
                nodes.append(str(currentNode.val))
            currentNode = currentNode.next
        return "->".join(nodes)
    