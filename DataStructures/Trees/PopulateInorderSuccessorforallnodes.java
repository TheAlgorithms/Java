// Java program to populate inorder traversal of all nodes

// A binary tree node
class Node
{
	int data;
	Node left, right, next;

	Node(int item)
	{
		data = item;
		left = right = next = null;
	}
}

class BinaryTree
{
	Node root;
	static Node next = null;

	/* Set next of p and all descendants of p by traversing them in
	reverse Inorder */
	void populateNext(Node node)
	{
		// The first visited node will be the rightmost node
		// next of the rightmost node will be NULL
		if (node != null)
		{
			// First set the next pointer in right subtree
			populateNext(node.right);

			// Set the next as previously visited node in reverse Inorder
			node.next = next;

			// Change the prev for subsequent node
			next = node;

			// Finally, set the next pointer in left subtree
			populateNext(node.left);
		}
	}

	/* Driver program to test above functions*/
	public static void main(String args[])
	{
		/* Constructed binary tree is
			10
		/ \
		8	 12
		/
		3 */
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(8);
		tree.root.right = new Node(12);
		tree.root.left.left = new Node(3);

		// Populates nextRight pointer in all nodes
		tree.populateNext(tree.root);

		// Let us see the populated values
		Node ptr = tree.root.left.left;
		while (ptr != null)
		{
			// -1 is printed if there is no successor
			int print = ptr.next != null ? ptr.next.data : -1;
			System.out.println("Next of " + ptr.data + " is: " + print);
			ptr = ptr.next;
		}
	}
}

// This code has been contributed by Anup Kumar Maurya
