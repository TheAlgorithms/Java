package DataStructures.HashMap.Hashing;

import java.util.Arrays;
import java.util.function.ToIntFunction;

/**
 * This class is an implementation of a hash table using linear probing It uses a dynamic array to
 * lengthen the size of the hash table when load factor 
 * dynamic hash function using lambda  > .7
 */
public class HashMapDynamicHashFunction {
  private int hsize; // size of the hash table
  private Integer[] buckets; // array representing the table
  private Integer AVAILABLE;
  private int size; // amount of elements in the hash table
  private ToIntFunction<Integer> hashFunc;

  /**
   * Constructor initializes buckets array, hsize, and creates dummy object for AVAILABLE
   *
   * @param hsize the desired size of the hash map
   */
  public HashMapDynamicHashFunction (int hsize, ToIntFunction<T> hashFunc) {
    this.buckets = new Integer[hsize];
    this.hsize = hsize;
    this.hashFunc = hashFunc;
    this.AVAILABLE = new Integer(Integer.MIN_VALUE);
    this.size = 0;
  }

  /**
   * inserts the key into the hash map by wrapping it as an Integer object
   *
   * @param key the desired key to be inserted in the hash map
   */
  public void insertHash(int key) {
    Integer wrappedInt = new Integer(key);
    int hash = hashFunc.applyAsInt(key);

    if (isFull()) {
      System.out.println("Hash table is full");
      return;
    }

    for (int i = 0; i < hsize; i++) {
      if (buckets[hash] == null || buckets[hash] == AVAILABLE) {
        buckets[hash] = wrappedInt;
        size++;
        return;
      }

      if (hash + 1 < hsize) {
        hash++;
      } else {
        hash = 0;
      }
    }
  }

  /**
   * deletes a key from the hash map and adds an available placeholder
   *
   * @param key the desired key to be deleted
   */
  public void deleteHash(int key) {
    Integer wrappedInt = new Integer(key);
    int hash = hashFunc.applyAsInt(key);

    if (isEmpty()) {
      System.out.println("Table is empty");
      return;
    }

    for (int i = 0; i < hsize; i++) {
      if (buckets[hash] != null && buckets[hash].equals(wrappedInt)) {
        buckets[hash] = AVAILABLE;
        size--;
        return;
      }

      if (hash + 1 < hsize) {
        hash++;
      } else {
        hash = 0;
      }
    }
    System.out.println("Key " + key + " not found");
  }

  /** Displays the hash table line by line */
  public void displayHashtable() {
    for (int i = 0; i < hsize; i++) {
      if (buckets[i] == null || buckets[i] == AVAILABLE) {
        System.out.println("Bucket " + i + ": Empty");
      } else {
        System.out.println("Bucket " + i + ": " + buckets[i].toString());
      }
    }
  }

  /**
   * Finds the index of location based on an inputed key
   *
   * @param key the desired key to be found
   * @return int the index where the key is located
   */
  public int findHash(int key) {
    Integer wrappedInt = new Integer(key);
    int hash = hashFunc.applyAsInt(key);

    if (isEmpty()) {
      System.out.println("Table is empty");
      return -1;
    }

    for (int i = 0; i < hsize; i++) {
      try {
        if (buckets[hash].equals(wrappedInt)) {
          buckets[hash] = AVAILABLE;
          return hash;
        }
      } catch (Exception E) {
      }

      if (hash + 1 < hsize) {
        hash++;
      } else {
        hash = 0;
      }
    }
    System.out.println("Key " + key + " not found");
    return -1;
  }

  private void lengthenTable() {
    buckets = Arrays.copyOf(buckets, hsize * 2);
    hsize *= 2;
    System.out.println("Table size is now: " + hsize);
  }

  /**
   * Checks the load factor of the hash table if greater than .7, automatically lengthens table to
   * prevent further collisions
   */
  public void checkLoadFactor() {
    double factor = (double) size / hsize;
    if (factor > .7) {
      System.out.println("Load factor is " + factor + ",  lengthening table");
      lengthenTable();
    } else {
      System.out.println("Load factor is " + factor);
    }
  }

  /**
   * isFull returns true if the hash map is full and false if not full
   *
   * @return boolean is Empty
   */
  public boolean isFull() {
    boolean response = true;
    for (int i = 0; i < hsize; i++) {
      if (buckets[i] == null || buckets[i] == AVAILABLE) {
        response = false;
        break;
      }
    }
    return response;
  }

  /**
   * isEmpty returns true if the hash map is empty and false if not empty
   *
   * @return boolean is Empty
   */
  public boolean isEmpty() {
    boolean response = true;
    for (int i = 0; i < hsize; i++) {
      if (buckets[i] != null) {
        response = false;
        break;
      }
    }
    return response;
  }
}
