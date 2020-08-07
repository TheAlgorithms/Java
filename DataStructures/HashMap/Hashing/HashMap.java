package DataStructures.HashMap.Hashing;

/**
 * This class is an implementation of a hash table
 * 
 */
class HashMap {
    private int hsize;
    private LinkedList[] buckets;

    /**
     * Constructor initialize HashMap object and 
     * create new Hashmap based on the size 
     */
    public HashMap(int hsize) {
        buckets = new LinkedList[hsize];
        for (int i = 0; i < hsize; i++) {
            buckets[i] = new LinkedList();
            // Java requires explicit initialisaton of each object
        }
        this.hsize = hsize;
    }

    /**
     * Inserting the hash object into the bucket 
     * @param key the key of the hash
     */
    public void insertHash(int key) {
        int hash = hashing(key);
        buckets[hash].insert(key);
    }

    /**
     * Delete the item in bucket based on their key
     * @param key the key for a hash that will be delete
     */
    public void deleteHash(int key) {
        int hash = hashing(key);

        buckets[hash].delete(key);
    }

    /**
     * For displaying the hash table in buckets field
     * print to the console / terminal
     */
    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            System.out.printf("Bucket %d :", i);
            buckets[i].display();
        }
    }

    /**
     * For do the hashing using modulo from key and hsize division
     * @param key the key of the hash 
     * @return int a result after do hashing 
     */
    private int hashing(int key) {
        int hash = key % hsize;
        if (hash < 0)
            hash += hsize;
        return hash;
    }
}