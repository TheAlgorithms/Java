/*
 * A linked list is similar to an array, it holds values. However, links in a linked list do not have indexes.
 * With a linked list you do not need to predetermine it's size as it grows and shrinks as it is edited.
 * This is an example of a double ended, doubly linked list.
 * Each link references the next link and the previous one.
*/
class LinkedList{
	private Link head; //Head refers to the front of the list
	private Link tail; //Tail refers to the back of the list

	public LinkedList(){
		head = null;
		tail = null;
	}

	public void insertHead(int x){ //Insert an element at the head
		Link newLink = new Link(x); //Create a new link with a value attached to it
		if(isEmpty()) //Set the first element added to be the tail
			tail = newLink;
		else
			head.previous = newLink; // newLink <-- currenthead(head)
		newLink.next = head; // newLink <--> currenthead(head)
		head = newLink; // newLink(head) <--> oldhead
	}

	public void insertTail(int x){
		Link newLink = new Link(x);
		newLink.next = null; // currentTail(tail)     newlink -->
		tail.next = newLink; // currentTail(tail) --> newLink -->
		newLink.previous = tail; // currentTail(tail) <--> newLink -->
		tail = newLink; // oldTail <--> newLink(tail) -->
	}

	public Link deleteHead(){ //Delete the element at the head
		Link temp = head;
		head = head.next; // oldHead <--> 2ndElement(head)
		head.previous = null; // oldHead --> 2ndElement(head) nothing pointing at old head so will be removed
		if(head == null)
			tail = null;
		return temp;
	}

	public Link deleteTail(){
		Link temp = tail;
		tail = tail.previous; // 2ndLast(tail) <--> oldTail --> null
 		tail.next = null; // 2ndLast(tail) --> null
		return temp;
	}

	public Link delete(int x){
		Link current = head;

		while(current.value != x) //Find the position to delete
			current = current.next;

		if(current == head)
			deleteHead();

		else if(current == tail)
			deleteTail();

		else{ //Before: 1 <--> 2(current) <--> 3
			current.previous.next = current.next;  // 1 --> 3
			current.next.previous = current.previous; // 1 <--> 3
		}
		return current;
	}

	public void insertOrdered(int x){
		Link newLink = new Link(x);
		Link current = head;
		while(current != null && x > current.value) //Find the position to insert
			current = current.next;

		if(current == head)
			insertHead(x);

		else if(current == null)
			insertTail(x);

		else{ //Before: 1 <--> 2(current) <--> 3
			newLink.previous = current.previous; // 1 <-- newLink
			current.previous.next = newLink; // 1 <--> newLink
			newLink.next = current; // 1 <--> newLink --> 2(current) <--> 3
			current.previous = newLink; // 1 <--> newLink <--> 2(current) <--> 3
		}
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
	public Link next; //This points to the link in front of the new link
	public Link previous; //This points to the link behind the new link

	public Link(int value){
		this.value = value;
	}

	public void displayLink(){
		System.out.print(value+" ");
	}
}

//Example
public class DoublyLinkedList{
	public static void main(String args[]){
		LinkedList myList = new LinkedList();

		myList.insertHead(13);
		myList.insertHead(7);
		myList.insertHead(10);
		myList.display(); // <-- 10(head) <--> 7 <--> 13(tail) -->

		myList.insertTail(11);
		myList.display(); // <-- 10(head) <--> 7 <--> 13 <--> 11(tail) -->

		myList.deleteTail();
		myList.display(); // <-- 10(head) <--> 7 <--> 13(tail) -->

		myList.delete(7);
		myList.display(); // <-- 10(head) <--> 13(tail) -->

		myList.insertOrdered(23);
		myList.insertOrdered(67);
		myList.insertOrdered(3);
		myList.display(); // <-- 3(head) <--> 10 <--> 13 <--> 23 <--> 67(tail) -->
	}
}