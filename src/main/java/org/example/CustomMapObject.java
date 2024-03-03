package org.example;

public class CustomMapObject<K, V> {

    private K key;

    private V value;

    private int customHashCode;

    public CustomMapObject() {
    }

    public CustomMapObject(K key, V value) {
        this.key = key;
        this.value = value;
        setHashCodeForNewObject();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    private void setHashCodeForNewObject() {

        char[] array = this.getKey().toString().toCharArray();

        this.customHashCode = calculateHashCode(array);
    }

    public int getCustomHashCode() {
        return customHashCode;
    }

    private static int calculateHashCode(char[] array) {
        int customHashCode = 0;

        for (char c : array) {
            customHashCode += c;
        }

        return customHashCode;
    }

}
