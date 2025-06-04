mod array;
mod linked_list;
mod stack;
mod queue;

fn main() {
    let mut linked_list = linked_list::LinkedList::new();
    linked_list.prepend(1);
    linked_list.prepend(2);
    linked_list.prepend(3);
    // println!("{:?}", linked_list);
    linked_list.append(4);
    // println!("{:?}", linked_list);
    linked_list.delete(4);
    // println!("{:?}", linked_list);
    linked_list.reverse();
    println!("{:?}", linked_list.to_string(None::<fn(&i32) -> String>));

    let arr: Box<[i32]> = Box::new([1, 2, 3]);
    println!("{:?}", array::random_access(&arr));
}
