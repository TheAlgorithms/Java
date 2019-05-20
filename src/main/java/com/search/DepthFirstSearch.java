package src.main.java.com.search;

/**
 * Searching is faster in sorted structures. Binary search is O(log n).
 * However, the cost of sorting is O(n · log n).
 * What should we do when adding or removing elements? Sort again? No.
 * Instead, we should create efficient data structures to maintain sorted sequences, and search in them
 * Key example: binary sorted tree; allowing O(log N) insert, remove and lookup.
   
   In comes, depth-first search
 * Worst-case performance	O(n)
 * Best-case performance	O(1)
 * Average performance	    O(n)
 * 
 */

public class DepthFirstSearch {
	
    /**
     * Depth-first search method
     *
     * @param tree- Binary tree to be searched
     * @param value- Key being searched for
     * @return Location of the key
     */

	public static <T extends Comparable<T>> T find(T key, BinaryTree<T> tree) {
        return tree.get(key);
    }
}

/**
 * The BinaryTree class defines the structure of a binary tree
 * Also contains a static nested class called TreeNode
 * @param <T>
 */
class BinaryTree<T extends Comparable<T>> {
	
	private TreeNode<T> root;
	
	/**
	 * This class defines what a node in a binary tree looks like
	 * @param <P> 
	 */
	private static class TreeNode<P extends Comparable<P>>  {
		
		P key, value;
		TreeNode<P> left, right;
		
		private TreeNode(P key, P value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * @param node
		 * adds the specified node
		 */
		private void add(TreeNode<P> node) {
			if (node.key.compareTo(this.key) < 0) {
				if (this.left == null) {
					this.left = node;
				}
				else {
					this.left.add(node);
				}
			}
			
			else {
				if (this.right == null) {
					this.right = node;
				}
				else {
					this.right.add(node);
				}
			}
		}
		
		/**
		 * @param key
		 * @return the tree node corresponding to the key
		 */
		private TreeNode<P> find(P key) {
			if (key.compareTo(this.key) == 0) return this;

			else if (key.compareTo(this.key) < 0) {
				if (this.left == null) return null;
				else return this.left.find(key);
			}
			
			else {
				if (this.right == null) return null;
				else return this.right.find(key);
			}
		}
		
	}
	
	public BinaryTree() {
		this.root = null;
	}
	
	public void add(T key, T value) {
		TreeNode<T> node = new TreeNode<T>(key, value);
		if (this.root == null) {
			this.root = node;
		}
		else {
			this.root.add(node);
		}
	}
	
	public T get(T key) {
		if (this.root == null) return null;
		
		TreeNode<T> node = this.root.find(key);
		if (node == null) return null;
		return node.value;
	}
}
