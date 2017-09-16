package datastructures.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HashMap<K, V> {
    public class HmNodes {
        K key;
        V value;

        @Override
        public String toString() {
            return "HmNodes{" +
                "key=" + key +
                ", value=" + value +
                '}';
        }
    }

    private int size = 0;
    private LinkedList<HmNodes> buckets[];

    public HashMap() {
        //initially create bucket of any size
        buckets = new LinkedList[4];
        for (int i = 0; i < 4; i++)
            buckets[i] = new LinkedList<>();
    }

    public void put(K key, V value) {
        //find the index,the new key will be inserted in linklist at that index
        int bi = bucketIndex(key);
        //check if key already exists or not
        int fountAt = find(bi, key);
        if (fountAt == -1) {
            //if doesn't exist create new node and insert
            HmNodes temp = new HmNodes();
            temp.key = key;
            temp.value = value;
            buckets[bi].addLast(temp);
            this.size++;
        } else {
            //if already exist modify the value
            buckets[bi].get(fountAt).value = value;
        }

        double lambda = (this.size * 1.0) / this.buckets.length;
        if (lambda > 2.0) {
            //rehashing function which will increase the size of bucket as soon as lambda exceeds 2.0
            rehash();
        }
    }


    public V get(K key) {
        int bi = bucketIndex(key);
        int fountAt = find(bi, key);
        if (fountAt == -1) {
            return null;
        } else {
            return buckets[bi].get(fountAt).value;
        }
    }

    public V remove(K key) {
        int bi = bucketIndex(key);
        int fountAt = find(bi, key);
        if (fountAt == -1) {
            return null;
        } else {
            this.size--;
            return buckets[bi].remove(fountAt).value;
        }
    }

    public boolean containsKey(K key) {
        int bi = bucketIndex(key);
        int fountAt = find(bi, key);
        return fountAt != -1;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public List<K> keySet() {
        ArrayList<K> arr = new ArrayList<>();
        Arrays.stream(buckets)
            .forEach(bucket -> bucket.forEach(aBucket -> arr.add(aBucket.key)));
        return arr;
    }

    public List<V> valueSet() {
        ArrayList<V> arr = new ArrayList<>();
        Arrays.stream(buckets)
            .forEach(bucket -> bucket.forEach(aBucket -> arr.add(aBucket.value)));
        return arr;
    }

    public void display() {
        Arrays.stream(buckets).forEach(bucket -> {
            System.out.print("Bucket: " + bucket + " ");
            bucket.forEach(System.out::print);
            System.out.println();
        });
    }

    public int find(int bi, K key) {
        for (int i = 0; i < buckets[bi].size(); i++) {
            if (key.equals(buckets[bi].get(i).key))
                return i;
        }
        return -1;
    }

    public int bucketIndex(K key) {
        int bi = key.hashCode();
        return Math.abs(bi % buckets.length);
    }

    private void rehash() {
        LinkedList<HmNodes> ob[] = buckets;
        buckets = new LinkedList[ob.length * 2];
        for (int i = 0; i < ob.length * 2; i++)
            buckets[i] = new LinkedList<>();

        size = 0;
        Arrays.stream(ob)
            .forEach(listOb -> listOb.forEach(hmOb -> put(hmOb.key, hmOb.value)));
    }
}
