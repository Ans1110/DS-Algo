
#[derive(Debug, Clone, PartialEq)]
pub struct Pair {
    pub key: i32,
    pub value: String
}

pub struct ArrayHashMap {
    pub buckets: Vec<Option<Pair>>
}

impl ArrayHashMap {
    pub fn new() -> ArrayHashMap {
        Self {
            buckets: vec![None; 100]
        }
    }

    pub fn hash_fn(&self, key: i32) -> usize {
        key as usize % self.buckets.len()
    }

    pub fn get(&self, key: i32) -> Option<&String> {
        let index = self.hash_fn(key);
        self.buckets[index].as_ref().map(|pair| &pair.value)
    }

    pub fn put(&mut self, key: i32, value: &str) {
        let index = self.hash_fn(key);
        self.buckets[index] = Some(Pair {
            key,
            value: value.to_string(),
        });
    }

    pub fn remove(&mut self, key: i32) {
        let index = self.hash_fn(key);
        self.buckets[index] = None;
    }

    pub fn entry_set(&self) -> Vec<&Pair> {
        self.buckets
            .iter()
            .filter_map(|pair| pair.as_ref())
            .collect()
    }

    pub fn key_set(&self) -> Vec<&i32> {
        self.buckets
            .iter()
            .filter_map(|pair| pair.as_ref())
            .map(|pair| &pair.key)
            .collect()
    }

    pub fn value_set(&self) -> Vec<&String> {
        self.buckets
            .iter()
            .filter_map(|pair| pair.as_ref())
            .map(|pair| &pair.value)
            .collect()
    }

    pub fn print(&self) {
        for pair in self.entry_set() {
            println!("{} -> {}", pair.key, pair.value);
        }
    }
}

mod tests {
    use super::ArrayHashMap;

    #[test]
    fn test_array_hash_map() {
        let mut map = ArrayHashMap::new();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        assert_eq!(map.get(1), Some(&"a".to_string()));
        assert_eq!(map.get(2), Some(&"b".to_string()));
        assert_eq!(map.get(3), Some(&"c".to_string()));
        assert_eq!(map.get(4), None);
        map.remove(2);
        assert_eq!(map.get(2), None);
    }
}


pub struct HashMapChaining {
    pub size: usize,
    pub capacity: usize,
    pub load_threes: f32,
    pub extend_ratio: usize,
    buckets: Vec<Vec<Option<Pair>>>
}

impl HashMapChaining {
    fn new() -> Self {
        Self {
            size: 0,
            capacity: 4,
            load_threes: 2.0 / 3.0,
            extend_ratio: 2,
            buckets: vec![Vec::new(); 4]
        }
    }

    pub fn hash_fn(&self, key: i32) -> usize {
        key as usize % self.capacity
    }

    pub fn load_factor(&self) -> f32 {
        self.size as f32 / self.capacity as f32
    }

    pub fn remove(&mut self, key: i32) -> Option<String> {
        let index = self.hash_fn(key);

        for (i, pair) in self.buckets[index].iter_mut().enumerate() {
            if pair.as_ref().unwrap().key == key {
                let pair = self.buckets[index].remove(i);
                self.size -= 1;
                return Some(pair.unwrap().value);
            }
        }

        None
    }

    pub fn extend(&mut self) {
        let buckets_tmp = std::mem::take(&mut self.buckets);

        self.capacity *= self.extend_ratio;
        self.buckets = vec![Vec::new(); self.capacity as usize];
        self.size = 0;

        for bucket in buckets_tmp {
            for pair in bucket {
                self.put(pair.as_ref().unwrap().key, pair.unwrap().value);
            }
        }
    }

    pub fn put(&mut self, key: i32, value: String) {
        if self.load_factor() > self.load_threes {
            self.extend();
        }

        let index = self.hash_fn(key);

        for pair in self.buckets[index].iter_mut() {
            if pair.as_ref().unwrap().key == key {
                pair.as_mut().unwrap().value = value;
                return;
            }
        }

        let pair = Pair {
            key,
            value
        };
        self.buckets[index].push(Option::from(pair));
        self.size += 1;
    }

    pub fn get(&self, key: i32) -> Option<&str> {
        let index = self.hash_fn(key);

        for pair in self.buckets[index].iter() {
            if pair.as_ref().unwrap().key == key {
                return Some(pair.as_ref().unwrap().value.as_str());
            }
        }

        None
    }

    pub fn print(&self) {
        for bucket in &self.buckets {
            let mut res: Vec<String> = Vec::new();
            for pair in bucket {
                res.push(format!("{} -> {}", pair.as_ref().unwrap().key, pair.as_ref().unwrap().value));
            }
            println!("{:?}", res);
        }
    }
}

mod hashmap_chaining_tests {
    use super::HashMapChaining;

    #[test]
    fn test_hashmap_chaining() {
        let mut map = HashMapChaining::new();
        map.put(1, "a".to_string());
        map.put(2, "b".to_string());
        map.put(3, "c".to_string());
        assert_eq!(map.get(1), Some("a"));
        assert_eq!(map.get(2), Some("b"));
        assert_eq!(map.get(3), Some("c"));
        assert_eq!(map.get(4), None);
        assert_eq!(map.remove(2), Some("b".to_string()));
        assert_eq!(map.get(2), None);
    }
}

struct HashMapOpenAddressing {
    pub size: usize,
    pub capacity: usize,
    pub load_threes: f64,
    pub extend_ratio: usize,
    pub buckets: Vec<Option<Pair>>,
    pub TOMBSTONE: Option<Pair>
}

impl HashMapOpenAddressing {
    pub fn new() -> Self {
        Self {
            size: 0,
            capacity: 4,
            load_threes: 2.0 / 3.0,
            extend_ratio: 2,
            buckets: vec![None; 4],
            TOMBSTONE: Some(Pair {
                key: -1,
                value: "-1".to_string()
            })
        }

    }

    pub fn hash_fn(&self, key: i32) -> usize {
        (key & self.capacity as i32) as usize
    }

    pub fn load_factor(&self) -> f64 {
        self.size as f64 / self.capacity as f64
    }

    pub fn find_bucket(&mut self, key: i32) -> usize {
        let mut index = self.hash_fn(key);
        let mut first_tombstone = -1;

        while self.buckets[index].is_some() {
            if self.buckets[index].as_ref().unwrap().key == key {
                if first_tombstone != -1 {
                    self.buckets[first_tombstone as usize] = self.buckets[index].take();
                    self.buckets[index] = self.TOMBSTONE.clone();
                    return first_tombstone as usize;
                }
            }
            return index;
        }

        if first_tombstone == -1 && self.buckets[index] == self.TOMBSTONE {
            first_tombstone = index as i32;
        }
        index = (index + 1) % self.capacity;

        if first_tombstone == -1 {
            index
        } else {
            first_tombstone as usize
        }
    }
    
    pub fn get(&mut self, key: i32) -> Option<&str> {
        let index = self.find_bucket(key);
        
        if self.buckets[index].is_some() && self.buckets[index] != self.TOMBSTONE {
            return self.buckets[index].as_ref().map(|pair| &pair.value as &str);
        }
        
        None
    }
    
    pub fn put(&mut self, key: i32, value: String) {
        if self.load_factor() > self.load_threes {
            self.extend();
        }
        
        let index = self.find_bucket(key);
        if self.buckets[index].is_some() && self.buckets[index] != self.TOMBSTONE {
            self.buckets[index].as_mut().unwrap().value = value;
            return;
        }
        
        self.buckets[index] = Some(Pair {
            key,
            value
        });
        self.size += 1;
    }
    
    pub fn remove(&mut self, key: i32) {
        let index = self.find_bucket(key);
        
        if self.buckets[index].is_some() && self.buckets[index] != self.TOMBSTONE {
            self.buckets[index] = self.TOMBSTONE.clone();
            self.size -= 1;
        }
    }
    
    pub fn extend(&mut self) {
        let buckets_tmp = self.buckets.clone();
        self.capacity *= self.extend_ratio;
        self.buckets = vec![None; self.capacity];
        self.size = 0;
        
        for pair in buckets_tmp {
            if pair.is_none() || pair == self.TOMBSTONE {
                continue;
            }
            let pair = pair.unwrap();
            
            self.put(pair.key, pair.value);
        }
    }
    pub fn print(&self) {
        for pair in &self.buckets {
            if pair.is_none() {
                println!("None");
            } else if pair == &self.TOMBSTONE {
                println!("TOMBSTONE");
            } else {
                let pair = pair.as_ref().unwrap();
                println!("{} -> {}", pair.key, pair.value);
            }
        } 
    }
}


