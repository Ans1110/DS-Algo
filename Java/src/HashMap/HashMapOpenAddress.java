package HashMap;

public class HashMapOpenAddress {
    private int size;
    private int capacity = 4;
    private final double loadThreshold = 0.75;
    private final int extendRatio = 2;
    private Pair[] buckets;
    private final Pair TOMBSTONE = new Pair(-1, "-1");

    public HashMapOpenAddress() {
        size = 0;
        buckets = new Pair[capacity];
    }

    private int hashFn(int key) {
        return key % capacity;
    }

    private double loaderFactor() {
        return (double) size / capacity;
    }

    private int findBucket(int key) {
        int index = hashFn(key);
        int firstTombstone = -1;

        while (buckets[index] != null) {
            if (buckets[index].key == key) {
                if (firstTombstone != -1) {
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone;
                }
                return index;
            }

            if (firstTombstone == -1 && buckets[index] == TOMBSTONE) {
                firstTombstone = index;
            }
            index = (index + 1) % capacity;
        }
        return firstTombstone == -1 ? index : firstTombstone;
    }

    private void extend() {
        Pair[] bucketsTmp = buckets;
        capacity *= extendRatio;
        buckets = new Pair[capacity];
        for (Pair pair : bucketsTmp) {
            if (pair != null && pair != TOMBSTONE) {
                put(pair.key, pair.value);
            }
        }
    }

    public String get(int key) {
        int index = findBucket(key);
        if (buckets[index] != null && buckets[index] != TOMBSTONE) return buckets[index].value;
        return null;
    }

    public void put(int key, String value) {
        if (loadThreshold < loaderFactor()) extend();
        int index = findBucket(key);
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index].value = value;
            return;
        }
        buckets[index] = new Pair(key, value);
        size++;
    }

    public void remove(int key) {
        int index = findBucket(key);
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index] = TOMBSTONE;
            size--;
        }
    }

    public void print() {
        for (Pair pair : buckets) {
            if (pair == null) {
                System.out.println("null");
            } else if (pair == TOMBSTONE) {
                System.out.println("tombstone");
            } else {
                System.out.println(pair.key + " -> " + pair.value);
            }
        }
    }
}
