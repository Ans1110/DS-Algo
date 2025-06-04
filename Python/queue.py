from typing import TypeVar
from webbrowser import get

T = TypeVar("T")

type QueueNode[T] = {
    val: T,
    next: QueueNode[T] | None,
}


class LinkedListQueue[T]:
    def __init__(self) -> None:
        self.front: QueueNode[T] | None = None
        self.rear: QueueNode[T] | None = None
        self.queueSize: int = 0

    def enqueue(self, value: T) -> None:
        node: QueueNode[T] = { val: value, next: None }
        if not self.front:
            self.front = node
            self.rear = node
        else:
            self.rear.next = node
            self.rear = node
        self.queueSize += 1
    
    def dequeue(self) -> T:
        if not self.front:
            raise Exception("Queue is empty")
        
        node = self.front
        self.front = self.front.next
        self.queueSize -= 1
        return node.val
    
    def peek(self) -> T:
        if not self.front:
            raise Exception("Queue is empty")
        return self.front.val
    
    def toArray(self) -> list[T]:
        arr = []
        currentNode = self.front
        while currentNode:
            arr.append(currentNode.val)
            currentNode = currentNode.next
        return arr

class ArrayQueue[T]:
    def __init__(self, capacity: int) -> None:
        self.queue: list[T] = [None] * capacity
        self.front: int = 0
        self.queueSize: int = 0

    @property
    def size(self) -> int:
        return self.queueSize
    
    @property
    def capacity(self) -> int:
        return len(self.queue)
    
    def isEmpty(self) -> bool:
        return self.size == 0
    
    def enqueue(self, value: T) -> None:
        if self.size == self.capacity:
            raise Exception("Queue is full")
        rear = (self.front + self.queueSize) % self.capacity
        self.queue[rear] = value
        self.queueSize += 1
    
    def dequeue(self) -> T:
        if self.size == 0:
            raise Exception("Queue is empty")
        front = self.queue[self.front]
        self.front = (self.front + 1) % self.capacity
        self.queueSize -= 1
        return front
    
    def peek(self) -> T:
        if self.size == 0:
            raise Exception("Queue is empty")
        return self.queue[self.front]
    
    def toArray(self) -> list[T]:
        arr = []
        for i in range(self.queueSize):
            arr.append(self.queue[(self.front + i) % self.capacity])
        
        return arr

if __name__ == "__main__":
    queue = ArrayQueue[int](10)
    for i in range(10):
        queue.enqueue(i)
    print(queue.toArray())
    print(queue.dequeue())
    print(queue.toArray())
    