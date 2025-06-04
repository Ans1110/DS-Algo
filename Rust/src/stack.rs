use std::cell::RefCell;
use std::rc::Rc;

type Link<T> = Option<Rc<RefCell<ListNode<T>>>>;

#[derive(Debug)]
pub struct ListNode<T> {
    pub val: T,
    pub next: Link<T>,
}

impl<T> ListNode<T> {
    pub fn new(value: T) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(ListNode { val: value, next: None }))
    }
}

#[allow(dead_code)]
pub struct LinkedListStack<T> {
    pub stack_peek: Link<T>,
    pub stack_size: usize,
}

impl<T: Copy> LinkedListStack<T> {
    pub fn new() -> Self {
        Self {
            stack_peek: None,
            stack_size: 0,
        }
    }
    
    pub fn size(&self) -> usize {
        self.stack_size
    }
    
    pub fn is_empty(&self) -> bool {
        self.stack_size == 0
    }
    
    pub fn push(&mut self, value: T) {
        let node = ListNode::new(value);
        node.borrow_mut().next = self.stack_peek.take();
        self.stack_peek = Some(node);
        self.stack_size += 1;
    }
    
    pub fn pop(&mut self) -> Option<T> {
        self.stack_peek.take().map(|old_node| {
            self.stack_peek = old_node.borrow_mut().next.take();
            self.stack_size -= 1;
            
            old_node.borrow().val
        })
    }
    
    pub fn peek(&self) -> Option<&Rc<RefCell<ListNode<T>>>> {
        self.stack_peek.as_ref()
    }
    
    pub fn to_array(&self) -> Vec<T> {
        if let Some(node) = self.stack_peek.clone() {
            let mut arr: Vec<T> = Vec::new();
            let mut current = Some(node.clone());
            while let Some(node) = current {
                arr.push(node.borrow().val);
                current = node.borrow().next.clone();
            }
            arr
        } else {
            Vec::new()
        }
    }
}

mod tests {
    use super::LinkedListStack;
    
    #[test]
    fn test_stack() {
        let mut stack = LinkedListStack::new();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assert_eq!(stack.pop(), Some(3));
        assert_eq!(stack.pop(), Some(2));
        assert_eq!(stack.pop(), Some(1));
        assert_eq!(stack.pop(), None);
        stack.push(4);
        stack.push(5);
        let arr = stack.to_array();
        assert_eq!(arr, vec![5, 4]);
    }
}

pub struct ArrayStack<T> {
    pub stack: Vec<T>
}

impl<T> ArrayStack<T> {
    pub fn new() -> Self {
        Self {
            stack: Vec::new()
        }
    }
    
    pub fn size(&self) -> usize {
        self.stack.len()
    }
    
    pub fn is_empty(&self) -> bool {
        self.stack.is_empty()
    }
    
    pub fn push(&mut self, value: T) {
        self.stack.push(value);
    }
    
    pub fn pop(&mut self) -> Option<T> {
        self.stack.pop()
    }
    
    pub fn peek(&self) -> Option<&T> {
        if self.stack.is_empty() {
            println!("Stack is empty");
        }
        self.stack.last()
    }
    
    pub fn to_array(&self) -> &Vec<T> {
        &self.stack
    }
}


mod array_stack_tests {
    use super::ArrayStack;
    
    #[test]
    fn test_stack() {
        let mut stack = ArrayStack::new();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assert_eq!(stack.pop(), Some(3));
        assert_eq!(stack.pop(), Some(2));
        assert_eq!(stack.pop(), Some(1));
        assert_eq!(stack.pop(), None);
        stack.push(4);
        stack.push(5);
        assert_eq!(stack.peek(), Some(&5));
    }
}