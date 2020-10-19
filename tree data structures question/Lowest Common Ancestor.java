// Data structure to store a Binary Tree node
class Node
{
	int data;
	Node left = null, right = null;

	Node(int data) {
		this.data = data;
	}
}

class Main
{
	
	static class NodeWrapper {
		public Node node;

		NodeWrapper(Node node) {
			this.node = node;
		}
	}

	
	public static boolean isNodePresent(Node root, Node node)
	{
		// base case
		if (root == null) {
			return false;
		}

		
		if (root == node) {
			return true;
		}

		
		return isNodePresent(root.left, node) || isNodePresent(root.right, node);
	}

	
	public static boolean findLCA(Node root, NodeWrapper lca, Node x, Node y)
	{
		
		if (root == null) {
			return false;
		}

		
		if (root == x || root == y)
		{
			
			lca.node = root;
			return true;
		}

		// recursively check if x or y exists in the left subtree
		boolean left = findLCA(root.left, lca, x, y);

		
		boolean right = findLCA(root.right, lca, x, y);

		
		if (left && right) {
			lca.node = root;
		}

		// return true if x or y is found in either left or right subtree
		return left || right;
	}

	
	public static void findLCA(Node root, Node x, Node y)
	{
		
		Node lca = null;

		
		NodeWrapper LCA = new NodeWrapper(lca);

		
		if (isNodePresent(root, y) && isNodePresent(root, x)) {
			findLCA(root, LCA, x, y);
			lca = LCA.node;
		}

		
		if (lca != null) {
			System.out.println("LCA is " + lca.data);
		} else {
			System.out.print("LCA do not exist\n");
		}
	}

	public static void main(String[] args)
	{
	    /* Construct below tree
	          1
	        /   \
	       /     \
	      2          3
	       \     / \
	        4   5   6
	           / \
	          7   8
	    */

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);
		root.right.right = new Node(6);
		root.right.left.left = new Node(7);
		root.right.right.right = new Node(8);

		findLCA(root, root.right.left.left, root.right.right);
		findLCA(root, root.right.left.left, new Node(10));
		findLCA(root, root.right.left.left, root.right.left.left);
		findLCA(root, root.right.left.left, root.right.left);
		findLCA(root, root.left, root.right.left);
	}
}