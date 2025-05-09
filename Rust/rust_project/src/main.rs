mod array;

fn main() {
    let mut arr: [i32; 5] = [1, 2, 3, 4, 5];
    // let slice: &[i32] = &[0; 5];
    let mut nums: Vec<i32> = vec![1, 2, 3, 4, 5];

    //test case
    //random access
    let random_num = array::random_access(&nums);
    println!("Random number: {}", random_num);

    //insert
    array::insert(&mut arr, 1, 3);
    println!("Array after insert: {:?}", arr);

    //delete
    array::remove(&mut arr, 3);
    println!("Array after delete: {:?}", arr);
    
    //find
    let index = array::find(&arr, 3);
    println!("Index of 3: {:?}", index);
    
    //extend
    let extended_arr = array::extend(&arr, 2);
    println!("Extended array: {:?}", extended_arr);
}
