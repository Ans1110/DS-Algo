use std::cell::RefCell;
use std::rc::Rc;

use linked_list::ListNode;
use linked_list::find;

mod array;
mod linked_list;

fn main() {
    let n0: Rc<RefCell<ListNode>> = Rc::new(RefCell::new(ListNode { val: 1, next: None }));
    let n1: Rc<RefCell<ListNode>> = Rc::new(RefCell::new(ListNode { val: 2, next: None }));
    let n2: Rc<RefCell<ListNode>> = Rc::new(RefCell::new(ListNode { val: 3, next: None }));
    let n3: Rc<RefCell<ListNode>> = Rc::new(RefCell::new(ListNode { val: 4, next: None }));
    let n4: Rc<RefCell<ListNode>> = Rc::new(RefCell::new(ListNode { val: 5, next: None }));

    n0.borrow_mut().next = Some(n1.clone());
    n1.borrow_mut().next = Some(n2.clone());
    n2.borrow_mut().next = Some(n3.clone());
    n3.borrow_mut().next = Some(n4.clone());

    println!("{}", find(n0, 3));
}
