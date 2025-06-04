from operator import is_
from typing import TypeVar

T = TypeVar("T")

type StackNode[T] = {
    val: T,
    next: StackNode[T] | None,
}

class LinkedListStack[T]:
    def __init__(self):
        self.stack_peek: StackNode[T] | None = None
        self.stk_size: int = 0
    
    def size(self) -> int:
        return self.stk_size
    
    def is_empty(self) -> bool:
        return self.stk_size == 0

    def push(self, value: T) -> None:
        node: StackNode[T] = { val: value, next: None }
        if self.stack_peek:
            node.next = self.stack_peek
        self.stack_pee += 1
    
    def pop(self) -> T:
        if self.is_empty():
            raise Exception("Stack is empty")
        
        node = self.stack_peek
        self.stack_peek = self.stack_peek.next
        self.stk_size -= 1
        return node.val
    
    def peek(self) -> T:
        if self.is_empty():
            raise Exception("Stack is empty")
        
        return self.stack_peek.val
    
    def to_array(self) -> list[T]:
        if self.is_empty():
            return []
        
        currentNode = self.stack_peek
        nodes: list[T] = []
        while currentNode:
            nodes.append(currentNode.val)
            currentNode = currentNode.next
        return nodes

class ArrayStack[T]:
    def __init__(self):
        self.stack: list[T] = []
    
    def size(self) -> int:
        return len(self.stack)
    
    def is_empty(self) -> bool:
        return len(self.stack) == 0
    
    def push(self, value: T) -> None:
        self.stack.append(value)
    
    def pop(self) -> T:
        if self.is_empty():
            raise Exception("Stack is empty")
        
        return self.stack.pop()
    
    def peek(self) -> T:
        if self.is_empty():
            raise Exception("Stack is empty")
        
        return self.stack[-1]
    
    def to_array(self) -> list[T]:
        return self.stack.copy()
