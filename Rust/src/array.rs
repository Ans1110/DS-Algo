use rand;

pub fn random_access(nums: &[i32]) -> i32 {
    let random_index = rand::random_range(0..(nums.len() - 1));
    let random_num = nums[random_index];
    random_num
}

pub fn insert(arr: &mut [i32], index: usize, value: i32) {
    for i in (index + 1..arr.len()).rev() {
        arr[i] = arr[i - 1];
    }
    arr[index] = value;
}

pub fn remove(arr: &mut [i32], index: usize) {
    for i in index..arr.len() - 1 {
        arr[i] = arr[i + 1];
    }
}

pub fn find(arr: &[i32], target: i32) -> Option<usize> {
    for i in 0..arr.len() {
        
        if arr[i] == target {
            return Some(i);
        }
    }
    None
}

pub fn extend(arr: &[i32], enlarge: usize) -> Vec<i32> {
    let mut res: Vec<i32> = vec![0; arr.len() + enlarge];
    for i in 0..arr.len() {
        res[i] = arr[i];
    }
    
    res
}