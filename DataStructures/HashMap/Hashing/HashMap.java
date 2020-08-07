package DataStructures.HashMap.Hashing;

/**
 * This class is an implementation of a hash table
 * 
 */
public class HashMap {
    private int hsize;
    private LinkedList[] buckets;

    /**
     * Constructor initialize HashMap object and 
     * create new Hashmap based on the size 
		 * @param hsize for declaring the size of hashmap
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
            System.out.println(buckets[i].display());
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
    
    public static class LinkedList {
    	private Node first;
			
			/**
			 * Constructor initializes new linkedlist
			 */
    	public LinkedList() {
    		first = null;
    	}
			
			/**
			 * insert some new node to the linkedlist
			 * @param key the key of a node
			 */
    	public void insert(int key){
    		if(isEmpty()) {
    			first = new Node(key);
    			return;
    		}
    		
    		Node temp = findEnd(first);
    		temp.setNext(new Node(key));
    	}
			
			/**
			 * Find the last node of linkedlist
			 * @param n a node which a member in linkedlist
			 * @return the next node, or last node if the next node is null 
			 */
    	private Node findEnd(Node n) {
    		if(n.getNext() == null) {
    			return n;
    		} else {
    			return findEnd(n.getNext());
    		}
    	}
			
			/**
			 * Find particular node based on the key
			 * @param key key of node that will be find
			 * @return Node based on the key 
			 */
    	public Node findKey(int key) {
    		if(!isEmpty()) {
    			return findKey(first, key);
    		} else {
    			System.out.println("List is empty");
    			return null;
    		}
    		
    	}
			
			/**
			 * Find node based on the key and for certain node 
			 * @param n a node which a member in linkedlist
			 * @param key key of a node which will be found
			 * @return Node based on the key 
			 */
    	private Node findKey(Node n, int key) {
    		if(n.getKey() == key) {
    			return n;
    		} else if(n.getNext() == null) {
    			System.out.println("Key not found");
    			return null;
    		} else {
    			return findKey(n.getNext(),key);
    		}
    	}
			
			/**
			 * Delete some node based on the key
			 * if first node's key is didn't equal then 
			 * will call delete method with first node as a argument
			 * @param key key of a node which will be delete
			 */
    	public void delete(int key) {
    		if(!isEmpty()) {
    			if(first.getKey() == key) {
    				first = null;
    			} else {
    				delete(first,key);
    			}
    		} else {
    			System.out.println("List is empty");
    		}
    	}
			
			/**
			 * Delete some node based on the key and particular node
			 * @param n a first node or next node before the particular node found
			 * @param key key of a node which will be delete
			 */
    	private void delete(Node n, int key) {
    		if(n.getNext().getKey() == key) {
    			if(n.getNext().getNext() == null) {
    				n.setNext(null);
    			} else {
    				n.setNext(n.getNext().getNext());
    			}
    		}
    	}
			
			/**
			 * Call the display method  
			 * @return String some member of the linked list
			 */
    	public String display() {
    		return display(first);
    	}
			
			
			/**
			 * Print all of item in linked list
			 * @param n the front node before the rest item of linked list
			 * @return String some member of the linked list 
			 */
    	private String display(Node n) {
    		if(n == null) {
    			return "null";
    		} else {
    			return n.getKey() + "->" + display(n.getNext());
    		}
    	}
			
			/**
			 * Check if the linked list empty or not 
			 * @return boolean condition is the first item of linkedlist empty or not 
			 */
    	public boolean isEmpty() {
    		return first == null;
    	}
    }
    
    public static class Node {
    	private Node next;
    	private int key;
			
			/**
			 * Constructor to create a new node and assign the initial key
			 * @param key the key of a node 
			 */
    	public Node(int key) {
    		next = null;
    		this.key = key;
    	}
			
			/**
			 * Get the next node after the current node
			 * @return Node next node
			 */
    	public Node getNext() {
    		return next;
    	}
			
			/**
			 * Get key of current node
			 * @return int key of a node 
			 */
    	public int getKey() {
    		return key;
    	}
			
			/**
			 * Set next node of current node, make it chaining with current node 
			 * @param next the next node after the current node
			 */
    	public void setNext(Node next) {
    		this.next = next;
    	}
    }
}
