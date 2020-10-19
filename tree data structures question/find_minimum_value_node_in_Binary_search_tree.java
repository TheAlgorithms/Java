// Java program to find minimum value node in Binary Search Tree 

 
class Node { 

	int data; 
	Node left, right; 

	Node(int d) { 
		data = d; 
		left = right = null; 
	} 
} 

class BinaryTree { 

	static Node head; 
	
	
	Node insert(Node node, int data) { 
		
		/* 1. If the tree is empty, return a new,	 
		single node */
		if (node == null) { 
			return (new Node(data)); 
		} else { 
			
			/* 2. Otherwise, recur down the tree */
			if (data <= node.data) { 
				node.left = insert(node.left, data); 
			} else { 
				node.right = insert(node.right, data); 
			} 

			/* return the (unchanged) node pointer */
			return node; 
		} 
	} 

	/* Given a non-empty binary search tree, 
	return the minimum data value found in that 
	tree. Note that the entire tree does not need 
	to be searched. */
	int minvalue(Node node) { 
		Node current = node; 

		/* loop down to find the leftmost leaf */
		while (current.left != null) { 
			current = current.left; 
		} 
		return (current.data); 
	} 
	
	
	public static void main(String[] args) { 
		BinaryTree tree = new BinaryTree(); 
		Node root = null; 
		root = tree.insert(root, 4); 
		tree.insert(root, 2); 
		tree.insert(root, 1); 
		tree.insert(root, 3); 
		tree.insert(root, 6); 
		tree.insert(root, 5); 

		System.out.println("Minimum value of BST is " + tree.minvalue(root)); 
	} 
} 

