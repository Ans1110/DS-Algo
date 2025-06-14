package HashMap;

import java.util.ArrayList;
import java.util.List;

public class ArrayHashMap {
    private List<Pair> buckets;

    public ArrayHashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(null);
        }
    }

    private int hashFn(int key) {
        return key % buckets.size();
    }

    public String get(int key) {
        int index = hashFn(key);
        Pair pair = buckets.get(index);
        if (pair == null) return null;
        return pair.value;
    }

    public void put(int key, String value) {
        Pair pair = new Pair(key, value);
        int index = hashFn(key);
        buckets.set(index, pair);
    }

    public void remove(int key) {
        int index = hashFn(key);
        buckets.set(index, null);
    }

    public List<Pair> pairSet() {
        List<Pair> pairSet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                pairSet.add(pair);
            }
        }
        return pairSet;
    }

    public List<Integer> keySet() {
        List<Integer> keySet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                keySet.add(pair.key);
            }
        }
        return keySet;
    }

    public List<String> valueSet() {
        List<String> valueSet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                valueSet.add(pair.value);
            }
        }
        return valueSet;
    }

    public void print() {
        for (Pair pair : buckets) {
            if (pair != null) {
                System.out.println(pair.key + " -> " + pair.value);
            }
        }
    }
}
