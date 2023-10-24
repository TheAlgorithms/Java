// An iterative Java program to check if two 
// linked lists are identical or not
class LinkedList
{
	// Head of list
	Node head; 

	// Linked list Node
	class Node
	{
		int data;
		Node next;
		Node(int d) 
		{ 
			data = d; 
			next = null; 
		}
	}

	/* Returns true if linked lists a and b 
	are identical, otherwise false */
	boolean areIdentical(LinkedList listb)
	{
		Node a = this.head, b = listb.head;
		while (a != null && b != null)
		{
			if (a.data != b.data)
				return false;

			/* If we reach here, then a and b 
			are not null and their data is 
			same, so move to next nodes in 
			both lists */
			a = a.next;
			b = b.next;
		}

		// If linked lists are identical, then 
		// 'a' and 'b' must be null at this point.
		return (a == null && b == null);
	}

	// UTILITY FUNCTIONS TO TEST fun1() and fun2() 
	/* Given a reference (pointer to pointer) to 
		the head of a list and an int, push a new 
		node on the front of the list. */
	void push(int new_data)
	{
		/* 1 & 2: Allocate the Node &
				Put in the data*/
		Node new_node = new Node(new_data);

		// 3. Make next of new Node as head 
		new_node.next = head;

		// 4. Move the head to point to new Node 
		head = new_node;
	}

	// Driver code
	public static void main(String args[])
	{
		LinkedList llist1 = new LinkedList();
		LinkedList llist2 = new LinkedList();

		/* The constructed linked lists are :
		llist1: 3->2->1
		llist2: 3->2->1 */
		llist1.push(1);
		llist1.push(2);
		llist1.push(3);

		llist2.push(1);
		llist2.push(2);
		llist2.push(3);

		if (llist1.areIdentical(llist2) == true)
			System.out.println("Identical ");
		else
			System.out.println("Not identical ");

	}
} 
// This code is contributed by Rajat Mishra 
