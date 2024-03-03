package org.example;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

public class CustomList<E> {

    private final static int INITIAL_CAPACITY = 1000;

    private int currentCapacity;

    private int totalCapacity;

    private Instant lastModified;

    private E[] data;

    public CustomList() {
        this.currentCapacity = 0;
        this.totalCapacity = INITIAL_CAPACITY;
        this.lastModified = Instant.now();
        this.data = (E[]) new Object[INITIAL_CAPACITY];
    }

    public E get(int index) {

        return data[index];

    }

    public void add(Object object) {
        if (currentCapacity == totalCapacity) {
            rebuildData();
        }
        data[currentCapacity] = (E) object;
        currentCapacity++;
        updateModified();
    }

    public void addAll(E[] elements) {
        Arrays.stream(elements).forEach(this::add);
    }

    public void remove(int index) {
        data[index] = null;
        updateModified();
    }

    public void removeAll() {
        setData(new Object[INITIAL_CAPACITY]);
        setCurrentCapacity(0);
        setTotalCapacity(INITIAL_CAPACITY);
        updateModified();
    }

    public int getLength() {
        return currentCapacity;
    }

    public boolean isEmpty() {
        return currentCapacity == 0;
    }



    private void updateModified() {
        lastModified = Instant.now();
    }

    private void rebuildData() {
        E[] oldData = Arrays.copyOf(data, totalCapacity);
        totalCapacity *= 2;
        setData(new Object[totalCapacity]);
        setCurrentCapacity(0);

        Arrays.stream(oldData).filter(Objects::nonNull).forEach(this::add);
        updateModified();
    }

    private void setData(Object[] data) {
        this.data = (E[]) data;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    private void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    private void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

}
