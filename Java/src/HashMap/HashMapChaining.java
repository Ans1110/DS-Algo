package HashMap;

import java.util.ArrayList;
import java.util.List;

public class HashMapChaining {
    int size;
    int capacity;
    double loadThreshold;
    int extendRatio;
    List<List<Pair>> buckets;

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThreshold = 0.75;
        extendRatio = 2;
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int hashFn(int key) {
        return key % capacity;
    }

    private double loaderFactor() {
        return (double) size / capacity;
    }

    private void extend() {
        List<List<Pair>> bucketsTmp = buckets;
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        for (List<Pair> bucket : bucketsTmp) {
            for (Pair pair : bucket) {
                put(pair.key, pair.value);
            }
        }
    }

    public String get(int key) {
        int index = hashFn(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                return pair.value;
            }
        }
        return null;
    }

    public void put(int key, String value) {
        if (loadThreshold < loaderFactor()) extend();
        int index = hashFn(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }
        bucket.add(new Pair(key, value));
        size++;
    }

    public void remove(int key) {
        int index = hashFn(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                size--;
                return;
            }
        }
    }

    public void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + " -> " + pair.value);
            }
            System.out.println(res);
        }
    }
}
