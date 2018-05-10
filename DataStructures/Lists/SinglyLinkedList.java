package DataStructures.Lists;

/**
 * This class implements a SinglyLinked List. This is done
 * using SinglyLinkedList class and a LinkForLinkedList Class.
 * 
 * A linked list is implar to an array, it hold values.
 * However, links in a linked list do not have indexes. With
 * a linked list you do not need to predetermine it's size as
 * it gorws and shrinks as it is edited. This is an example of
 * a singly linked list. Elements can only be added/removed
 * at the head/front of the list.
 * 
 * @author Unknown
 *
 */
class SinglyLinkedList{
	/**Head refered to the front of the list */
	private Node head;

	/**
	 * Constructor of SinglyLinkedList
	 */
	public SinglyLinkedList(){
		head = null;
	}

	/**
	 * This method inserts an element at the head
	 * 
	 * @param x Element to be added
	 */
	public void insertHead(int x){
		Node newNode = new Node(x); //Create a new link with a value attached to it
		newNode.next = head; 		//Set the new link to point to the current head
		head = newNode; 			//Now set the new link to be the head
	}
	
	/**
	 * Finds the size of the list by looping from head until tail.
	 * @return the size of the list.
	 */
	public int getSize() {
		int size = 0;
		if (head == null) {
			return 0;
		}
		else if (head.next == null) {
			return 1;
		}
		else {
			Node realHead = head;
			size++;
			while (head.next != null) {
				size++;
				head = head.next;
			}
			head = realHead;
			return size;
		}
	}

	/**
     * Inserts a new node at a specified position
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
    **/

    public void insertAtNth(int data, int position) {
        if(position > this.getSize()) {
        	throw new IndexOutOfBoundsException();
        }
        else {
        	Node tempHead = this.head;
        	for (int i = 0; i < position; i++) {
        		head = head.next;
        	}
        	head.next = new Node(data);
        	head = tempHead;
        }
    }
    
	/**
	 * This method deletes an element at the head
	 * 
	 * @return The element deleted
	 */
	public Node deleteHead(){
		Node temp = head;
		head = head.next; //Make the second element in the list the new head, the Java garbage collector will later remove the old head
		return temp;
	}

	/**
	 * Checks if the list is empty
	 * 
	 * @return true is list is empty
	 */
	public boolean isEmpty(){
		return(head == null);
	}

	/**
	 * Prints contents of the list
	 */
	public String display(){
		String ret="";
		Node current = head;
		while(current!=null){
			ret +=current.getValue()+" ";
			current = current.next;
		}
		return ret;
	}
	
	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]){
		SinglyLinkedList myList = new SinglyLinkedList();

		System.out.println(myList.isEmpty()); //Will print true

		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);

		myList.display(); // 10(head) --> 7 --> 5

		myList.deleteHead();

		myList.display(); // 7(head) --> 5
	}
}

/**
 * This class is the nodes of the SinglyLinked List.
 * They consist of a vlue and a pointer to the node
 * after them.
 * 
 * @author Unknown
 *
 */
class Node{
	/** The value of the node */
	public int data;
	/** Point to the next node */
	public Node next; //This is what the link will point to

	/**
	 * Constructor
	 * 
	 * @param valuein Value to be put in the node
	 */
	public Node(int valuein){
		data = valuein;
	}

	/**
	 * Returns value of the node
	 */
	public int getValue(){
		return data;
	}

}
