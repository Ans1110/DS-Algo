use std::cell::RefCell;
use std::rc::Rc;

type Link<T> = Option<Rc<RefCell<QueueNode<T>>>>;

#[derive(Debug)]
pub struct QueueNode<T> {
    pub value: T,
    pub next: Link<T>,
}

impl<T> QueueNode<T> {
    pub fn new(value: T) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(QueueNode {
            value,
            next: None
        }))
    }
}

#[allow(dead_code)]
pub struct LinkedListQueue<T> {
    front: Link<T>,
    rear: Link<T>,
    que_size: usize,
}

impl<T: Copy> LinkedListQueue<T> {
    pub fn new() -> Self {
        Self {
            front: None,
            rear: None,
            que_size: 0,
        }
    }

    pub fn size(&self) -> usize {
        self.que_size
    }

    pub fn is_empty(&self) -> bool {
        self.que_size == 0
    }

    pub fn enqueue(&mut self, value: T) {
        let new_rear = QueueNode::new(value);
        match self.rear.take() {
            Some(old_rear) => {
                old_rear.borrow_mut().next = Some(new_rear.clone());
                self.rear = Some(new_rear);
            }
            None => {
                self.front = Some(new_rear.clone());
                self.rear = Some(new_rear);
            }
        }
        self.que_size += 1;
    }

    pub fn dequeue(&mut self) -> Option<T> {
        self.front.take().map(|old_front| {
            match old_front.borrow_mut().next.take() {
                Some(new_front) => {
                    self.front = Some(new_front);
                }
                None => {
                    self.rear.take();
                }
            }
            self.que_size -= 1;
            old_front.borrow().value
        })
    }

    pub fn peek(&self) -> Option<&Rc<RefCell<QueueNode<T>>>> {
        self.front.as_ref()
    }

    pub fn to_array(&self) -> Vec<T> {
        let mut vec = Vec::new();
        let mut current = self.front.clone();
        while let Some(current_node) = current {
            vec.push(current_node.borrow().value);
            current = current_node.borrow().next.clone();
        }
        vec
    }
}

mod tests {
    use super::LinkedListQueue;
    #[test]
    fn test_queue() {
        let mut queue = LinkedListQueue::new();
        queue.enqueue(2);
        queue.enqueue(3);
        assert_eq!(queue.dequeue(), Some(2));
        assert_eq!(queue.dequeue(), Some(3));
        assert_eq!(queue.dequeue(), None);
        queue.enqueue(1);
        queue.enqueue(2);
        assert_eq!(queue.to_array(), vec![1, 2]);
    }
}

pub struct ArrayQueue<T> {
    queue: Vec<T>,
    front: i32,
    size: i32,
    capacity: i32,
}

impl<T: Copy> ArrayQueue<T> {
    pub fn new(capacity: i32) -> Self {
        Self {
            // queue: vec![T::default(); capacity as usize],
            queue: Vec::with_capacity(capacity as usize),
            front: 0,
            size: 0,
            capacity,
        }
    }

    pub fn capacity(&self) -> i32 {
        self.capacity
    }

    pub fn size(&self) -> i32 {
        self.size
    }

    pub fn is_empty(&self) -> bool {
        self.size == 0
    }
    pub fn enqueue(&mut self, value: T) {
        if self.size == self.capacity {
            panic!("Queue is full");
        }

        let rear = (self.front + self.size) % self.capacity;
        if self.queue.len() < self.capacity as usize {
            self.queue.push(value);
        } else {
            self.queue[rear as usize] = value;
        }
        self.size += 1;
    }

    pub fn dequeue(&mut self) -> Option<T> {
        if self.size == 0 {
            panic!("Queue is empty");
        } else {
            self.front = (self.front + 1) % self.capacity;
            self.size -= 1;
            Some(self.queue[(self.front - 1) as usize])
        }
    }

    pub fn peek(&self) -> Option<T> {
        if self.size == 0 {
            panic!("Queue is empty");
        } else {
            Some(self.queue[self.front as usize])
        }
    }

    pub fn to_array(&self) -> Vec<T> {
        let capacity = self.capacity;
        let mut vec = Vec::with_capacity(capacity as usize);
        for i in 0..self.size {
            let index = (self.front + i) % capacity;
            vec.push(self.queue[index as usize]);
        }
        vec
    }
}

mod array_queue_tests {
    use super::ArrayQueue;
    #[test]
    fn test_queue() {
        let mut queue = ArrayQueue::new(5);
        
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        println!("{}", queue.dequeue().unwrap());
        println!("{}", queue.dequeue().unwrap());
        // println!("{:?}", queue.size());
        println!("{:?}", queue.to_array());
    }
}