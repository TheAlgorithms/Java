/**
 * Created by Kyler Smith, 07/12/2017 
 */

public class LinearProbeHashTable<Key, Value> {
	
	// (for testing purposes) better to use a prime number for hashing - 7 | 997
	final static int MAX_ELEMENTS = 7;
	
	// max and numberElements bother used
	private int max, numberElements = 0;
	
	// Array to store the values, will add cast upon return
	private Object[] elements;
	
	
	/**
	 * Constructors
	 */
	public LinearProbeHashTable() {
		this(MAX_ELEMENTS); // number of elements to hold
	}

	public LinearProbeHashTable(int size) {
		this.max = size;
		elements = new Object[size];
	}
	
    /**
     *  Put key-value pair into the table 
     *  
     *  @param key : value to search by
     *  @param value : value to put in
     */
    void put(Key key, Value value) {
    	
    	int hash = hash(key), i = 0;
    	
    	if(elements[hash] != null) {
    		while(elements[hash + i] != null) 
    			i++;
    	}
		elements[hash + i] = new Element(key, value);
		numberElements++;
    }    	
    
    
    /*
     * Get value from the given key.
     * 
     * @param key : Value of the key to retrieve.
     * @return Value
     */
    Value get(Key key) {
    	
    	int hash = hash(key), i = 0;
    	    	
    	while(elements[hash + i] != null) {    		
			Element e = (Element) elements[hash + i];
    		
    		if(e.getKey() == key)
    			return e.getValue();
    		else
    			i++;
    	}
    	
    	return null;
    }
    
    // TODO: Implement delete function.
    void delete(Key key) {
    	
    	numberElements--;
    }
    
    /*
     * Returns true if the key is present, false if not.
     * 
     * @param key : The key to check for.
     * @return boolean 
     */
    boolean contains(Key key) {
    	
    	int hash = hash(key), i = 0;
    	
    	while(elements[hash + i] != null) {    		
			Element e = (Element) elements[hash + i];
    		
    		if(e.getKey() == key)
    			return true;
    		else
    			i++;
    	}
    	
    	return false;
    }
    
    //is the table empty?
    boolean isEmpty() {
    	return numberElements == 0;
    }
    
    //number of key-value pairs
    int size() {
    	return numberElements;
    }
    
	private int hash(Key key) {
		return ((key.hashCode() & 0x7fffffff)) % max;
	}
	
	/**
	 * Private helper class
	 */
	private class Element {
		
		private Key key;
		
		private Value value;
		
		public Element(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
		
		// getters and setters for the private class.
		public Value getValue() {
			return this.value;
		}
		public Key getKey() {
			return this.key;
		}
	}
	
	/*
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		
		LinearProbeHashTable<Integer, String> lpht = new LinearProbeHashTable<>();
		
		System.out.println(lpht.isEmpty());		// true
		System.out.println(lpht.size());		// 0
		
		lpht.put(3, "F");
		lpht.put(1, "O");
		lpht.put(20, "O");
		
		System.out.println(lpht.isEmpty());		// false
		System.out.println(lpht.size());		// 3
		
		lpht.put(7, "B");
		lpht.put(14, "A");
		lpht.put(2, "R");

		System.out.println(lpht.isEmpty());		// false
		System.out.println(lpht.size());		// 6		
		
		System.out.println(lpht.get(14));		// A
		System.out.println(lpht.get(2));		// R
		System.out.println(lpht.get(1));		// O
		System.out.println(lpht.get(7));		// B
		System.out.println(lpht.get(3));		// F
		System.out.println(lpht.get(20));		// O
		
		System.out.println(lpht.contains(1));	// true
		System.out.println(lpht.contains(3));	// true
		System.out.println(lpht.contains(5));	// false
	}	
}
