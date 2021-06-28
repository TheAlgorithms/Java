/**
 * Here I imnplement a hashing function
 * the Open_Addressing object uses an array for hashing
 * using quadratic hashing, we make it possible to add an element and retrieve its value from the array in constant time
 */
public class OpenAddressing {

    public int tableSize; // number of SLOTS AVAILABLE
    public int randomNumber; // the default random number
    int index;
    int hasher;
    public int[] Table;

    protected OpenAddressing(int index, int seed) {
        this.index = index;
        this.hasher = (int) (index - 1) / 2 + 1;
        this.tableSize = power2(hasher);
        this.randomNumber = generateRandom((int) power2(index - 1), (int) power2(index), seed);
        this.Table = new int[tableSize];

        /**
         * empty slots are initalized as -1
         * assuming that only positive keys are allowed
         */
        for (int i = 0; i < tableSize; i++) {
            Table[i] = -1;
        }

    }
    /**
     * Implements the hash function that uses quadratic haching top create slots
     * can also be used to check if an element is already there
     */
    public int probe(int key, int i) {
        int probeHash = 0;
        int chainHash = chain(key); //computes h(k)
        int powerR = power2(hasher); //

        probeHash = (chainHash + i) % powerR;
        
        return probeHash;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {

        int numCollisions = 0;
        int slotValue = 0;
        int probeHashed = probe(key, slotValue);
        while(!isSlotEmpty(probeHashed) && slotValue < tableSize){
            numCollisions++;
            slotValue++;
            probeHashed = probe(key, slotValue);
        }
        //only insert if the key is not a duplicate
        if(isDuplicate(Table, key) == false){
            Table[probeHashed] = key;

        }
        
        return numCollisions ;
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        int numCollisions = 0;
        int index = 0;
        int probeHashed = probe(key, index);

        while(Table[probeHashed] != key && index < tableSize){
            numCollisions++;
            index++;
            probeHashed = probe(key, index);
        }
        if(Table[probeHashed] == key){
            Table[probeHashed] = -1; //remove the key
        }
        
        return numCollisions;
    }
    //Helper method to check if the key is inside already
    public boolean isDuplicate(int [] table, int key){
        boolean found = false;

        for (int i = 0; i < table.length; i++){
            if( table[i] == key){
                found = true;
            }
        }
        return found;
    }

     /**
     * Calculate 2^index
     */
    public static int power2(int index) {
        return (int) Math.pow(2, index);
    }
}
