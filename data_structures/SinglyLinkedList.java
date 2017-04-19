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
	private LinkForLinkedList head;

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
		LinkForLinkedList newLink = new LinkForLinkedList(x); //Create a new link with a value attached to it
		newLink.next = head; 		//Set the new link to point to the current head
		head = newLink; 			//Now set the new link to be the head
	}

	/**
	 * This method deletes an element at the head
	 * 
	 * @return The element deleted
	 */
	public LinkForLinkedList deleteHead(){
		LinkForLinkedList temp = head;
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
	public void display(){
		LinkForLinkedList current = head;
		while(current!=null){
			current.displayLink();
			current = current.next;
		}
		System.out.println();
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
class LinkForLinkedList{
	/** The value of the node */
	public int value;
	/** Point to the next node */
	public LinkForLinkedList next; //This is what the link will point to

	/**
	 * Constructor
	 * 
	 * @param valuein Value to be put in the node
	 */
	public LinkForLinkedList(int valuein){
		value = valuein;
	}

	/**
	 * Prints out the value of the node
	 */
	public void displayLink(){
		System.out.print(value+" ");
	}

}