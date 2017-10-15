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
     * Inserts a new node at a specified position
     * @param head     head node of the linked list
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
     * @return  reference of the head of the linked list
     */

    Node InsertNth(Node head, int data, int position) {

        Node newNode = new Node(data);

        if (position == 0) {
            newNode.next = head;
            return newNode;
        }

        Node current = head;

        while (--position > 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        return head;
    }
		public void insert_At_Tail(int value){
      Node newnode = new Node(value);
      if(head==null){
        head = newnode;
      }
      else{
        Node temp = head;
        while(temp.next!=null){
          temp = temp.next;
        }
        temp.next = newnode;
      }
    }
		public void Delete_At_pos(int k){
      if(head==null){
        System.out.println("Empty List");
      }
      else if(k==1){
        head = head.next;
      }
      else{
        Node temp = head;
        int cnt = 1;
        while(cnt!=k-1){
          temp = temp.next;
          cnt++;
        }
        temp.next = temp.next.next;
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
	public void Search(int value){
      Node temp = head;
      int cnt = 1;
      while(temp.value!=value){
        temp = temp.next;
        cnt++;
      }
      System.out.println("Node " +value+ " is at index " + cnt);
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

		myList.insert_At_Tail(11);

		myList.display();

		myList.deleteAtPosition(2);

		myList.display();

		myList.Search(7);
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
	public int value;
	/** Point to the next node */
	public Node next; //This is what the link will point to

	/**
	 * Constructor
	 *
	 * @param valuein Value to be put in the node
	 */
	public Node(int valuein){
		value = valuein;
	}

	/**
	 * Returns value of the node
	 */
	public int getValue(){
		return value;
	}

}
