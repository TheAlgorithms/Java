/**
 * This class implements a SinglyLinked List. This is done
 * using SinglyLinkedList class and a LinkForLinkedList Class.
 * 
 * A linked list is similar to an array, it hold values.
 * However, links in a linked list do not have indexes. With
 * a linked list you do not need to predetermine it's size as
 * it grows and shrinks as it is edited. This is an example of
 * a singly linked list. Elements can only be added/removed
 * at the head/front of the list.
 * 
 * @author Unknown
 *
 */
class SinglyLinkedList {
	public Node getHead() {
		return head;
	}

	/**
	 * Head refered to the front of the list
	 */
	private Node head;
	private int nodesCounter;

	/**
	 * Constructor of SinglyLinkedList
	 */
	public SinglyLinkedList() {
		head = null;
	}

	//returns Number of the Nodes in the list
	public int getNodesCounter() {
		return nodesCounter;
	}

	/**
	 * This method inserts an element at the head
	 *
	 * @param data Element to be added
	 */
	public Node insertHead(int data) {
		Node newNode = new Node(data); //Create a new link with a value attached to it
		//If list is empty then just simply point head to newly created Node(i.e. newNode)
		if (head == null) head = newNode;
		else {
			newNode.setNextNodeReference(head);           //Set the new link to point to the current head
			head = newNode;               //Now set the new link to be the head
		}
		nodesCounter++;
		return head;
	}


	/**
	 * Inserts a new node at a specified position
	 *
	 * @param data     data to be stored in a new node
	 * @param position position at which a new node is to be inserted
	 * @return reference of the head of the linked list
	 */
	Node insertAtPosition(int data, int position) {
		if (position == 0) {
			return insertHead(data); // return the head Node
		} else if (position > nodesCounter) {
			System.out.println("Poistion specified goes out of bound,only " + nodesCounter + " Nodes Present in the list");
		} else {
			Node current = head;
			Node newNode = new Node(data);
			while (--position > 1) {							//gets to the position before the node to be inserted
				current = current.getNextNodeReference();
			}

			if (current.getNextNodeReference() == null) { 		//if Node to insert , needs to insert at end this statement executes
				current.setNextNodeReference(newNode);
			} else { 											//otherwise node will be insert in between the list
				newNode.setNextNodeReference(current.getNextNodeReference());
				current.setNextNodeReference(newNode);
			}
			nodesCounter++;
		}
		return head;
	}

	/**
	 * This method deletes an element at the head
	 *
	 * @return The element deleted
	 */
	private Node deleteHead() {
		Node temp = head;
		head = head.getNextNodeReference(); //Make the second element in the list the new head, the Java garbage collector will later remove the old head
		nodesCounter--; // decreases number of the nodes in the list
		return temp;
	}
	/**
	 * This method deletes the Node from the List
	 * @param data takes an integer value as a parameter  ,to specify the Node to delete
	 * @return deleted Node
	 */
	public Node delete(int data) {
		if (head == null) System.out.println("Error:" + " No node is in the List already!");
		else {
			Node returnNode = null;
			if (nodesCounter == 1 || head.getValue() == data) return deleteHead();
			else {
				Node currentNode = head;
				while ( currentNode.getNextNodeReference() != null) {
					if (currentNode.getNextNodeReference().getValue() == data){
						//make returnNode points to Node to Remove
						returnNode = currentNode.getNextNodeReference();
						//Node before the node to be deleted will now point to next Node
						//		 ___	  _____	   ____
						//		|___|--->/____/-->/___/
						//

						//		 ____	_____   ____
						//		|___|  /____/->/___/
						//		  '------------^

						currentNode.setNextNodeReference(currentNode.getNextNodeReference().getNextNodeReference());
						//		 ____	_____   ____
						//		|___|  /____/  /___/
						//		  '------------^
						//returnNode(i.e. Node deleted From List), its nextNodeReference Field now points to null
						returnNode.setNextNodeReference(null);
						//Number of the Nodes decreases in the list
						nodesCounter--;
						return returnNode;
					}
					currentNode = currentNode.getNextNodeReference();
				}
			}
		}
		return null;
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
		Node current = head;
		while(current!=null){
			System.out.print(current.getValue()+" ");
			current = current.getNextNodeReference();
		}
		System.out.println();
	}
//
//	/**
//	 * Main method
//	 *
//	 * @param args Command line arguments
//	 */
//	public static void main(String args[]){
//		SinglyLinkedList myList = new SinglyLinkedList();
//
//		System.out.println(myList.isEmpty()); //Will print true
//
//		myList.insertHead(5);
//		myList.insertHead(7);
//		myList.insertHead(10);
//		myList.insertAtPosition(8 , 2);
//		myList.insertAtPosition(20 , 4);
//		myList.display(); // 10(head) --> 7 --> 5
//
//		myList.delete(10);
//		myList.delete(20);
//
//		myList.display(); // 7(head) --> 5
//	}

}

/**
 * This class is the nodes of the SinglyLinked List.
 * They consist of a value and a pointer to the node
 * after them.
 * 
 * @author Unknown
 *
 */
class Node{

	/** The value of the node */
	private int value;
	/** Point to the next node */
	private Node nextNodeReference; //This is what the link will point to

	//returns value of a Node
	public int getValue() {
		return value;
	}
	// Set or modify the data value in a Node
	public void setValue(int value) {
		this.value = value;
	}

	//return the reference of the next Node
	public Node getNextNodeReference() {
		return nextNodeReference;
	}

	//Making current Node pointing to the Node passed as Parameter
	public void setNextNodeReference(Node nextNodeReference) {
		this.nextNodeReference = nextNodeReference;
	}

	/**
	 * Constructor
	 * 
	 * @param valuein Value to be put in the node
	 */
	public Node(int valuein){
		value = valuein;
	}

	/**
	 * 	Default Constructor
	 *
	 */
	public Node(){

	}
}
