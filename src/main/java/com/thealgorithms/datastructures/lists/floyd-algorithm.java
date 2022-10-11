import java.util.*;

class GFG{

static class Node {
	int data;
	Node next;

	Node(int data)
	{
		this.data = data;
		next = null;
	}
};

// initialize a new head for the linked list
static Node head = null;
static class Linkedlist {
	// insert new value at the start
	void insert(int value)
	{
		Node newNode = new Node(value);
		if (head == null)
			head = newNode;
		else {
			newNode.next = head;
			head = newNode;
		}
	}

	// detect if there is a loop
	// in the linked list
	boolean detectLoop()
	{
		Node slowPointer = head,
			fastPointer = head;

		while (slowPointer != null
			&& fastPointer != null
			&& fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			if (slowPointer == fastPointer)
				return true;
		}

	return false;
	}
}

public static void main(String[] args)
{
	Linkedlist l1 = new Linkedlist();
	// inserting new values
	l1.insert(10);
	l1.insert(20);
	l1.insert(30);
	l1.insert(40);
	l1.insert(50);

	// adding a loop for the sake
	// of this example
	Node temp = head;
	while (temp.next != null)
		temp = temp.next;

	temp.next = head;

	// loop added;

	if (l1.detectLoop())
		System.out.print("Loop exists in the"
			+ " Linked List" +"\n");
	else
		System.out.print("Loop does not exists "
			+ "in the Linked List" +"\n");

}
}
