// Java program to check if binay tree is full or not 


class Node 
{ 
	int data; 
	Node left, right; 

	Node(int item) 
	{ 
		data = item; 
		left = right = null; 
	} 
} 

class BinaryTree 
{ 
	Node root; 
	
	/* this function checks if a binary tree is full or not */
	boolean isFullTree(Node node) 
	{ 
		 
		if(node == null) 
		return true; 
		
		
		if(node.left == null && node.right == null ) 
			return true; 
		
		
		if((node.left!=null) && (node.right!=null)) 
			return (isFullTree(node.left) && isFullTree(node.right)); 
		
		
		return false; 
	} 

	
	// Driver program 
	public static void main(String args[]) 
	{ 
		BinaryTree tree = new BinaryTree(); 
		tree.root = new Node(10); 
		tree.root.left = new Node(20); 
		tree.root.right = new Node(30); 
		tree.root.left.right = new Node(40); 
		tree.root.left.left = new Node(50); 
		tree.root.right.left = new Node(60); 
		tree.root.left.left.left = new Node(80); 
		tree.root.right.right = new Node(70); 
		tree.root.left.left.right = new Node(90); 
		tree.root.left.right.left = new Node(80); 
		tree.root.left.right.right = new Node(90); 
		tree.root.right.left.left = new Node(80); 
		tree.root.right.left.right = new Node(90); 
		tree.root.right.right.left = new Node(80); 
		tree.root.right.right.right = new Node(90); 
		
		if(tree.isFullTree(tree.root)) 
			System.out.print("The binary tree is full"); 
		else
			System.out.print("The binary tree is not full"); 
	} 
} 


