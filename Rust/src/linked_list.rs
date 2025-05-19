use std::cell::RefCell;
use std::rc::Rc;

#[derive(Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Rc<RefCell<ListNode>>>,
}

#[allow(non_snake_case)]
pub fn insert(n0: &Rc<RefCell<ListNode>>, P: Rc<RefCell<ListNode>>) {
    let n1 = n0.borrow_mut().next.take();
    P.borrow_mut().next = n1;
    n0.borrow_mut().next = Some(P);
}

#[allow(non_snake_case)]
pub fn remove(n0: &Rc<RefCell<ListNode>>) {
    let P = n0.borrow_mut().next.take();
    if let Some(node) = P {
        let n1: Option<Rc<RefCell<ListNode>>> = node.borrow_mut().next.take();
        n0.borrow_mut().next = n1;
    }
}

pub fn access(head: Rc<RefCell<ListNode>>, index: i32) -> Option<Rc<RefCell<ListNode>>> {
    fn dfs(head: Option<&Rc<RefCell<ListNode>>>, index: i32) -> Option<Rc<RefCell<ListNode>>> {
        if index <= 0 {
            return head.cloned();
        }

        if let Some(node) = head {
            dfs(node.borrow().next.as_ref(), index - 1)
        } else {
            None
        }
    }

    dfs(Some(head).as_ref(), index)
}

pub fn find(head: Rc<RefCell<ListNode>>, target: i32) -> i32 {
    fn find_inner(head: Option<&Rc<RefCell<ListNode>>>, target: i32, index: i32) -> i32 {
        if let Some(node) = head {
            if node.borrow().val == target {
                return index;
            }
            find_inner(node.borrow().next.as_ref(), target, index + 1)
        } else {
            -1
        }
    }

    find_inner(Some(head).as_ref(), target, 0)
}
