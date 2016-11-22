/*
 * A linked list is similar to an array, it holds values. However, links in a linked list do not have indexes.
 * With a linked list you do not need to predetermine it's size as it grows and shrinks as it is edited.
 * This is an example of a singly linked list. Elements can only be added/removed at the head/front of the list.
*/
class LinkedList{
	private Link head; //Head refers to the front of the list

	public LinkedList(){
		head = null;
	}

	public void insertHead(int x){	//Insert an element at the head
		Link newLink = new Link(x); //Create a new link with a value attached to it
		newLink.next = head; 		//Set the new link to point to the current head
		head = newLink; 			//Now set the new link to be the head
	}

	public Link deleteHead(){ //Delete the element at the head
		Link temp = head;
		head = head.next; //Make the second element in the list the new head, the Java garbage collector will later remove the old head
		return temp;
	}

	public boolean isEmpty(){ //Returns true if list is empty
		return(head == null);
	}

	public void display(){ //Prints contents of the list
		Link current = head;
		while(current!=null){
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
}

class Link{
	public int value;
	public Link next; //This is what the link will point to

	public Link(int valuein){
		value = valuein;
	}

	public void displayLink(){
		System.out.print(value+" ");
	}
}

//Example
public class LinkedLists{
	public static void main(String args[]){
		LinkedList myList = new LinkedList();

		System.out.println(myList.isEmpty()); //Will print true

		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);

		myList.display(); // 10(head) --> 7 --> 5

		myList.deleteHead();

		myList.display(); // 7(head) --> 5
	}
}