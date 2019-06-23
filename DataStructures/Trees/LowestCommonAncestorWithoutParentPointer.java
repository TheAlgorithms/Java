package com.org.algo.binaryTree;

public class LowestCommonAncestorWithoutParentPointer {

	public class Node{
		Node left, right, parent;
		int data;
		
		public Node(int key)
		{
			data = key;
			left = right = parent = null;
		}
	}
	
	Node root;
	public void insert(int num){
		root = insertRec(root, num);
	}
	
	public Node insertRec(Node root, int num)
	{
		if(root == null){
			root = new Node(num);
			return root;
		}
		if(num < root.data){
			root.left = insertRec(root.left, num);
			root.left.parent = root;
			}
		else{
			root.right = insertRec(root.right, num);
			root.right.parent = root;
			}
		return root;
	}
	Node succ = null;
	public Node inorderSuccessor(Node root, int k)
	{
		if(root == null)
			return null;
		while(root != null){
			if(k < root.data){
				succ = root;
				root = root.left;
				System.out.println("left");
			}
			else if (k > root.data){
				root = root.right;
			System.out.println("right");
			}
			else
				break;
		}
		return succ;
	}
	
	//Recursive(no parent pointer, this is the best solution) - Complexity : O(n)
	public Node findLCA(Node root, int n1, int n2){
		if(root == null)
			return null;
		else if(root.data > n1 && root.data > n2)
			return findLCA(root.left, n1, n2);
		else if(root.data < n1 && root.data < n2)
			return findLCA(root.right, n1, n2);
		return root;
	}
	
	//Iterative, but not working correctly : T.Complexity - O(h. logh) & S.Complexity: O(h)
	/*public Node findLCA(Node one, Node two)
	{
		//Node one = new Node(n1);
		//Node two = new Node(n2);
		Map<Node, Boolean> ancestors = new HashMap<Node, Boolean>();
		
		while(one != null)
		{
			ancestors.put(two, true);
			one = one.parent;
		}
		
		while(two != null){
			if(ancestors.containsKey(two) != ancestors.isEmpty())
				return two;
			else
				two = two.parent;
		}
		return null;
	}*/
	
	public static void main(String[] args) {
		LowestCommonAncestorWithoutParentPointer lca = new LowestCommonAncestorWithoutParentPointer();
		lca.insert(10);
		lca.insert(1);
		lca.insert(6);
		lca.insert(9);
		lca.insert(4);
		lca.insert(0);
		lca.insert(12);
		System.out.println(lca.inorderSuccessor(lca.root, 12).data);
		//System.out.println(lca.findLCA(lca.root, 0, 12).data);
	}
}
