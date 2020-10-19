// Java program to check if binary tree is complete or not 


class Node 
{ 
	int data; 
	Node left, right; 

	Node(int item) { 
		data = item; 
		left = right = null; 
	} 
} 

class BinaryTree 
{ 
	Node root; 

	
	int countNodes(Node root) 
	{ 
		if (root == null) 
			return (0); 
		return (1 + countNodes(root.left) + countNodes(root.right)); 
	} 

	
	boolean isComplete(Node root, int index, int number_nodes) 
	{ 
		
		if (root == null)		 
		return true; 

		
		if (index >= number_nodes) 
		return false; 

		
		return (isComplete(root.left, 2 * index + 1, number_nodes) 
			&& isComplete(root.right, 2 * index + 2, number_nodes)); 

	} 

	
	public static void main(String args[]) 
	{ 
		BinaryTree tree = new BinaryTree(); 
		
		// Le us create tree in the last diagram above 
		Node NewRoot = null; 
		tree.root = new Node(1); 
		tree.root.left = new Node(2); 
		tree.root.right = new Node(3); 
		tree.root.left.right = new Node(5); 
		tree.root.left.left = new Node(4); 
		tree.root.right.right = new Node(6); 
		
		int node_count = tree.countNodes(tree.root); 
		int index = 0; 
		
		if (tree.isComplete(tree.root, index, node_count)) 
			System.out.print("The binary tree is complete"); 
		else
			System.out.print("The binary tree is not complete"); 
	} 
} 


