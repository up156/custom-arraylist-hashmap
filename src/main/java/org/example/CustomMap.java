package org.example;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomMap<K, V> {

    private static final int START_BUCKETS_COUNT = 100;
    private static final double BUCKETS_CAPACITY = 0.7;

    private Instant lastModified;

    private List<CustomMapObject<K, V>>[] buckets;

    private int currentBucketsCount;
    private int bucketsCount;

    public CustomMap() {
        this.buckets = new List[START_BUCKETS_COUNT];
        this.bucketsCount = START_BUCKETS_COUNT;
        this.currentBucketsCount = 0;
        this.lastModified = Instant.now();
    }

    public void put(K k, V v) {

        if (!enoughCapacity()) {
            rebuildBucketsArray();
        }
        CustomMapObject<K, V> object = new CustomMapObject<>(k, v);
        int bucketNumber = object.getCustomHashCode() % bucketsCount;
        List<CustomMapObject<K, V>> list = buckets[bucketNumber];
        if (list == null) {
            list = new ArrayList<>();
            currentBucketsCount++;
        }

        list.add(object);
        buckets[bucketNumber] = list;
        setLastModified(Instant.now());

    }

    public V getValue(K k) {

        int bucketNumber = calculateHashCode(k.toString().toCharArray()) % bucketsCount;
        List<CustomMapObject<K, V>> list = buckets[bucketNumber];
        if (list.size() == 0) {
            return null;
        }

        return list
                .stream()
                .filter(e -> e.getKey().equals(k))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .getValue();
    }

    public boolean removeValue(K k) {
        int bucketNumber = calculateHashCode(k.toString().toCharArray()) % bucketsCount;
        List<CustomMapObject<K, V>> list = buckets[bucketNumber];
        if (list.size() == 0) {
            return false;
        }

        setLastModified(Instant.now());
        return list.removeIf(e -> e.getKey().equals(k));

    }


    private boolean enoughCapacity() {
        return currentBucketsCount < (bucketsCount * BUCKETS_CAPACITY);
    }

    private void rebuildBucketsArray() {

        List<CustomMapObject<K, V>>[] oldBuckets = Arrays.copyOf(buckets, buckets.length);
        buckets = new List[bucketsCount * 2];
        bucketsCount *= 2;
        currentBucketsCount = 0;

        for (List<CustomMapObject<K, V>> list : oldBuckets) {
            if (list == null) {
                continue;
            }
            for (CustomMapObject<K, V> object : list) {
                put(object.getKey(), object.getValue());
            }
        }
    }

    private static int calculateHashCode(char[] array) {
        int customHashCode = 0;

        for (char c : array) {
            customHashCode += c;
        }

        return customHashCode;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

}
