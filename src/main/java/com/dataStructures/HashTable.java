package src.main.java.com.dataStructures;

/**
 * Hash table without repetitions
 * @author Rick
 * @param <E> Data type
 * @param <T> Key type
 */
public class HashTable <T,E> {
    
    private E[] dataTable;

    public HashTable(int tableSize) {
        data_table = (E[])(new Object[tableSize]);
    }

    public HashTable() {
        this(50);
    }

    private int hash(String key){
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += (int)c;
        }
        return sum % this.data_table.length;
    }
    
    public void add(T key, E data){
        this.data_table[ hash(key.toString()) ] = data;
    }
    
    public void delete(T key){
        this.data_table[ hash(key.toString()) ] = null;
    }
    
    public boolean contains(T key){
        return this.data_table[ hash(key.toString()) ] != null;
    }
    
    public E get( T key ){
        return this.data_table[ hash(key.toString()) ];
    }
    
}
