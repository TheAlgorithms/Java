package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;

/***
 * This class is an implementation of a hash table using linear probing.
 * @see <a href="https://en.wikipedia.org/wiki/Linear_probing">Linear Probing Hash Table</a>
 *
 * @param <Key> keys type.
 * @param <Value> values type.
 */
public class LinearProbingHashMap<Key extends Comparable<Key>, Value> extends Map<Key, Value> {
    private int hsize; // size of the hash table
    private Key[] keys;
    private Value[] values;
    private int size; // amount of elements in the hash table

    public LinearProbingHashMap() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashMap(int size) {
        this.hsize = size;
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
    }

    @Override
    public boolean put(Key key, Value value) {
        if (key == null) {
            return false;
        }

        if (size > hsize / 2) {
            resize(2 * hsize);
        }

        int keyIndex = hash(key, hsize);
        for (; keys[keyIndex] != null; keyIndex = increment(keyIndex)) {
            if (key.equals(keys[keyIndex])) {
                values[keyIndex] = value;
                return true;
            }
        }

        keys[keyIndex] = key;
        values[keyIndex] = value;
        size++;
        return true;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        for (int i = hash(key, hsize); keys[i] != null; i = increment(i)) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }

        return null;
    }

    @Override
    public boolean delete(Key key) {
        if (key == null || !contains(key)) {
            return false;
        }

        int i = hash(key, hsize);
        while (!key.equals(keys[i])) {
            i = increment(i);
        }

        keys[i] = null;
        values[i] = null;

        i = increment(i);
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRehash, valToRehash);
            i = increment(i);
        }

        size--;
        if (size > 0 && size <= hsize / 8) {
            resize(hsize / 2);
        }

        return true;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    int size() {
        return size;
    }

    @Override
    Iterable<Key> keys() {
        ArrayList<Key> listOfKeys = new ArrayList<>(size);
        for (int i = 0; i < hsize; i++) {
            if (keys[i] != null) {
                listOfKeys.add(keys[i]);
            }
        }

        listOfKeys.sort(Comparable::compareTo);
        return listOfKeys;
    }

    private int increment(int i) {
        return (i + 1) % hsize;
    }

    private void resize(int newSize) {
        LinearProbingHashMap<Key, Value> tmp = new LinearProbingHashMap<>(newSize);
        for (int i = 0; i < hsize; i++) {
            if (keys[i] != null) {
                tmp.put(keys[i], values[i]);
            }
        }

        this.keys = tmp.keys;
        this.values = tmp.values;
        this.hsize = newSize;
    }
}
