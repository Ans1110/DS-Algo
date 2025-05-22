use std::cell::RefCell;
use std::io::Cursor;
use std::rc::Rc;

type Link<T> = Option<Rc<RefCell<ListNode<T>>>>;

#[derive(Debug)]
pub struct ListNode<T> {
    pub val: T,
    pub next: Link<T>,
}

impl<T> ListNode<T> {
    fn new(val: T) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(ListNode { val, next: None }))
    }
}

#[derive(Debug)]
pub struct LinkedList<T> {
    head: Link<T>,
    tail: Link<T>,
}

impl<T: PartialEq> LinkedList<T> {
    pub fn new() -> Self {
        Self {
            head: None,
            tail: None,
        }
    }

    pub fn prepend(&mut self, value: T) {
        let new_node = ListNode::new(value);
        new_node.borrow_mut().next = self.head.clone();
        self.head = Some(new_node.clone());

        if self.tail.is_none() {
            self.tail = Some(new_node);
        }
    }

    pub fn append(&mut self, value: T) {
        let new_node = ListNode::new(value);

        match self.tail.take() {
            Some(old_tail) => {
                old_tail.borrow_mut().next = Some(new_node.clone());
            }
            None => {
                self.head = Some(new_node.clone());
            }
        }
        self.tail = Some(new_node);
    }

    pub fn insert(&mut self, value: T, raw_index: usize) {
        if raw_index == 0 {
            self.prepend(value);
            return;
        }

        let mut index = 1;
        let mut current = self.head.clone();

        while let Some(node) = current.clone() {
            if index == raw_index {
                let new_node = ListNode::new(value);
                new_node.borrow_mut().next = node.borrow_mut().next.take();
                node.borrow_mut().next = Some(new_node.clone());

                if new_node.borrow().next.is_none() {
                    self.tail = Some(new_node);
                }
                return;
            }
            current = node.borrow().next.clone();
            index += 1;
        }
        self.append(value);
    }

    pub fn delete(&mut self, value: T) {
        while let Some(head_node) = self.head.clone() {
            if head_node.borrow().val == value {
                self.head = head_node.borrow_mut().next.take();
            } else {
                break;
            }
        }

        let mut current = self.head.clone();

        while let Some(current_node) = current.clone() {
            let mut next_opt = current_node.borrow().next.clone();
            while let Some(next_node) = next_opt.clone() {
                if next_node.borrow().val == value {
                    current_node.borrow_mut().next = next_node.borrow_mut().next.take();
                } else {
                    break;
                }
                next_opt = current_node.borrow().next.clone();
            }
            current = current_node.borrow().next.clone();
        }
        let mut tail = self.head.clone();
        while let Some(tail_node) = tail.clone() {
            if tail_node.borrow().next.is_none() {
                self.tail = Some(tail_node);
                return;
            }
            tail = tail_node.borrow().next.clone();
        }
        self.tail = None;
    }

    pub fn delete_head(&mut self) {
        if let Some(head_node) = self.head.take() {
            self.head = head_node.borrow_mut().next.take();

            if self.head.is_none() {
                self.tail = None;
            }
        }
    }

    pub fn delete_tail(&mut self) {
        match self.head.clone() {
            None => return,
            Some(head_node) => {
                if head_node.borrow().next.is_none() {
                    self.head = None;
                    self.tail = None;
                    return;
                }

                let mut current = self.head.clone();
                while let Some(ref node) = current {
                    let next = node.borrow().next.clone();
                    if let Some(ref next_node) = next {
                        if next_node.borrow().next.is_none() {
                            node.borrow_mut().next = None;
                            self.tail = Some(node.clone());
                            break;
                        }
                    }
                    current = next;
                }
            }
        }
    }

    pub fn find(&self, target: T) -> Link<T>
    where
        T: Clone,
    {
        let mut current = self.head.clone();
        while let Some(current_node) = current.clone() {
            if current_node.borrow().val == target {
                return Some(current_node);
            }
            current = current_node.borrow().next.clone();
        }
        None
    }

    pub fn find_index(&self, target: T) -> Option<usize>
    where
        T: Clone,
    {
        let mut current = self.head.clone();
        let mut index: usize = 0;
        while let Some(current_node) = current.clone() {
            if current_node.borrow().val == target {
                return Some(index);
            }
            current = current_node.borrow().next.clone();
            index += 1;
        }
        None
    }

    pub fn reverse(&mut self) {
        let mut prev = None;
        let mut current = self.head.clone();

        self.tail = self.head.clone();

        while let Some(current_node) = current.clone() {
            let next = current_node.borrow_mut().next.take();
            current_node.borrow_mut().next = prev.clone();
            prev = Some(current_node.clone());
            current = next;
        }
        self.head = prev;
    }

    pub fn to_vec(&self) -> Vec<T>
    where
        T: Clone,
    {
        let mut vec = Vec::new();
        let mut current = self.head.clone();

        while let Some(current_node) = current {
            vec.push(current_node.borrow().val.clone());
            current = current_node.borrow().next.clone();
        }
        vec
    }

    pub fn to_string<F>(&self, callback: Option<F>) -> String
    where
        T: std::fmt::Debug + Clone,
        F: Fn(&T) -> String,
    {
        self.to_vec()
            .iter()
            .map(|v| {
                if let Some(ref cb) = callback {
                    cb(v)
                } else {
                    format!("{:?}", v)
                }
            })
            .collect::<Vec<_>>()
            .join(", ")
    }
}
