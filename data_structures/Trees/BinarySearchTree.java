/**
 * This entire class is used to build a Binary Search Tree data structure.
 * There is the Node Class and the BinarySearchTree Class, both explained below.
 * @author Shubham Singh (ss3681755)
 *
 **/


class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

	/* Generic data type T for value of Node. */
	private T value;
	/* Left child of node containing generic data type T. */
	private Node<T> left;
	/* Right child of node containing generic data type T. */
	private Node<T> right;
	/* Parent of node containing generic data type T. */
	private Node<T> parent;

	/**
	 *
	 * Class constructor when value of the node is provided.
	 *
	 **/
	public Node(T value) {
		this(value, null, null, null);
	}

	/**
	 *
	 * Class constructor when value and parent of the node are provided.
	 *
	 **/	
	public Node(T value, Node<T> parent) {
		this(value, null, null, parent);
	}

	/**
	 *
	 * Class constructor when value, left and right children of the node are provided.
	 *
	 **/
	public Node(T value, Node<T> left, Node<T> right) {
		this(value, left, right, null);
	}

	/**
	 *
	 * Class constructor when every attribute of node is provided.
	 *
	 **/
	public Node(T value, Node<T> left, Node<T> right, Node<T> parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	/**
	 *
	 * Setter method to set the value of Node
	 * @param value
	 *
	 **/
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 *
	 * Getter method to fetch the value of Node
	 * @return value of the Node
	 *
	 **/
	public T getValue() {
		return value;
	}

	/**
	 *
	 * Setter method to set the left child of Node
	 * @param Left Child
	 *
	 **/
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	/**
	 *
	 * Getter method to fetch the left child of Node
	 * @return Left Child
	 *
	 **/
	public Node<T> getLeft() {
		return left;
	}

	/**
	 *
	 * Setter method to set the right child of Node
	 * @param Right Child
	 *
	 **/
	public void setRight(Node<T> right) {
		this.right = right;
	}

	/**
	 *
	 * Getter method to fetch the right child of Node
	 * @return Right Child
	 *
	 **/
	public Node<T> getRight() {
		return right;
	}

	/**
	 *
	 * Setter method to set the parent of Node
	 * @param Parent Node
	 *
	 **/
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	/**
	 *
	 * Getter method to fetch the parent of Node
	 * @return Parent Node
	 *
	 **/
	public Node<T> getParent() {
		return parent;
	}

	/**
	 *
	 * This function compares to Node type objects on top
	 * of the comparators of Primitive type and returns positive
	 * value if current object is greater than passed one, negative
	 * if current object is lesser than passed one and 0 if both 
	 * are same.
	 * @param Node to which comparison will be done
	 * @return integer value as per rules discussed above.
	 *
	 **/
	public int compareTo(Node<T> object) {
		return getValue().compareTo(object.getValue());
	}
}

public class BinarySearchTree<T extends Comparable<T>> {
	
	/* Root node of tree containing generic data type T. */
	private Node<T> root;

	/**
	 *
	 * Constructor for Tree when nothing is provided.
	 *
	 **/
	public BinarySearchTree() {
		this(null);
	}

	/**
	 *
	 * Constructor of Tree when root of Tree is provided.
	 *
	 **/
	public BinarySearchTree(Node<T> root) {
		this.root = root;
	}

	/**
	 *
	 * Setter method to set the root of tree.
	 * @param root of tree
	 *
	 **/
	public void setRoot(Node<T> root) {
		this.root = root;
	}

	/**
	 *
	 * Getter method to fetch the root of tree.
	 * @return root of tree
	 *
	 **/
	public Node<T> getRoot() {
		return root;
	}

	/**
	 *
	 * Search for the key provided by user.
	 * @param Key
	 * @return Node containing Key if available otherwise null
	 *
	 **/
	public Node<T> find(T key) {
		
		// If tree is empty or key is null
		if(key == null || this.root == null)
			return null;
		
		// Otherwise find it in tree
		Node<T> keyNode = new Node<>(key);
		return find(this.root, keyNode);
	}

	/**
	 *
	 * Search for the key provided in tree. This function is a
	 * utility and is not acessible to user as it is private.
	 * @param Local root of tree
	 * @param Node containing key value.
	 * @return Node containing key
	 *
 	 **/
	private Node<T> find(Node<T> localRoot, Node<T> keyNode) {

		// Return null if tree is empty.
		if(localRoot == null) {
			return null;
		}
		
		int compareValue = localRoot.compareTo(keyNode);
		
		if(compareValue > 0) {
			// Key < localRoot then search in left subtree.
			return find(localRoot.getLeft(), keyNode);
		} else if(compareValue < 0) {
			// Key > localRoot then search in right subtree.
			return find(localRoot.getRight(), keyNode);
		}

		// Key == localRoot then return localRoot
		return localRoot;
	}

	/**
	 *
	 * This function returns the possbile Immediate parent of a node for
	 * a provided key. It is not necessary for key to be present in tree.
	 * @param Key
	 * @return Possible immediate parent node of key
	 *
	 **/
	public Node<T> findImmediateParent(T key) {
		if(this.root == null || key == null)
			return null;

		Node<T> keyNode = new Node<>(key);
		return findImmediateParent(this.root, keyNode);
	}

	/**
	 *
	 * Just a utility of function above and is not accessible to user.
	 * This function returns Possible Immediate parent Node of the key
	 * if key is as same as root then it returns null.
	 * @param Local root of a tree.
	 * @param Node containing key
	 * @return Possible immediate parent node
	 *
	 **/
	private Node<T> findImmediateParent(Node<T> localRoot, Node<T> keyNode) {
		// If tree is empty.
		if(localRoot == null)
			return null;
		
		int compareValue = localRoot.compareTo(keyNode);
		Node<T> ret = null;
		
		if(compareValue > 0) {
			// Key < localRoot search in left subtree.
			ret = findImmediateParent(localRoot.getLeft(), keyNode);
			// If ret is null means localRoot's left child is either key
			// or possibly will be the key is inserted.
			ret = ret == null ? localRoot : ret;
		} else if(compareValue < 0) {
			// Key > localRoot search in right subtree.
			ret = findImmediateParent(localRoot.getRight(), keyNode);
			// If ret is null means localRoot's right child is either key
			// or possibly will be the key is inserted.
			ret = ret == null ? localRoot : ret;
		}

		// Key == localRoot the return ret as null.
		return ret;
	}	

	/**
	 *
	 * This function inserts key into tree if not available.
	 * Duplicate insertion is not allowed in this tree.
	 * @param Key
	 *
	 **/
	public void put(T key) {
		if(key == null)
			return ;

		Node<T> keyNode = new Node<>(key);
		this.root = put(this.root, keyNode);
	}

	/**
	 *
	 * This function is a utility of function described above.
	 * But it is not accessible to user as it is private.
	 * @param Local root node of tree
	 * @param Key node
	 * @return Modified local root node of tree.
	 *
	 **/
	private Node<T> put(Node<T> localRoot, Node<T> keyNode) {
		// Tree is empty then make Key node as root
		if(localRoot == null) {
			localRoot = keyNode;
			return localRoot;
		}

		int compareValue = localRoot.compareTo(keyNode);
		
		if(compareValue > 0) {
			// Key < localRoot then traverse in left subtree./
			localRoot.setLeft(put(localRoot.getLeft(), keyNode));
			// In case localRoot's left child has been modified update
			// its parent as localRoot.
			localRoot.getLeft().setParent(localRoot);
		} else if(compareValue < 0) {
			// Key < localRoot then traverse in right subtree./
			localRoot.setRight(put(localRoot.getRight(), keyNode));
			// In case localRoot's right child has been modified update
			// its parent as localRoot.
			localRoot.getRight().setParent(localRoot);
		}

		// Key == localRoot return localRoot.
		return localRoot;
	}

	/**
	 *
	 * This function removes a node containing key provided by user.
	 * And returns true if successfully deleted otherwise false.
	 * @param Key
	 * @return boolean
	 *
	 **/
	public boolean remove(T key) {
		// If tree is empty or key is null or is not present in tree.
		if(this.root == null || key == null || find(key) == null) {
			return false;
		}

		Node<T> keyNode = new Node<>(key);
		this.root = remove(this.root, keyNode);
		return true;
	}

	/**
	 *
	 * This function is a utility to the function described above.
	 * But this is not acessible to user as it is private.
	 * @param Local root of tree.
	 * @param Node containing key.
	 * @return Modified local root node after deleting key.
	 *
	 **/
	private Node<T> remove(Node<T> localRoot, Node<T> keyNode) {
		
		int compareValue = localRoot.compareTo(keyNode);

		if(compareValue > 0) {
			// Key < localRoot then traverse left subtree.
			localRoot.setLeft(remove(localRoot.getLeft(), keyNode));
			// Now get Modified left child of localRoot.
			Node<T> left = localRoot.getLeft();
			// If left child is not null then update its parent as localRoot.
			if(left != null)
				left.setParent(localRoot);
		} else if(compareValue < 0) {
			// Key < localRoot then traverse right subtree.
			localRoot.setRight(remove(localRoot.getRight(), keyNode));
			// Now get Modified right child of localRoot.
			Node<T> right = localRoot.getLeft();
			// If right child is not null then update its parent as localRoot.
			if(right != null)
				right.setParent(localRoot);
		} else {
			// Key == localRoot then process deleting node.

			if(localRoot.getRight() == null) {
				// If right child of root is null then assign its left child.
				localRoot = localRoot.getLeft();
			} else if(localRoot.getLeft() == null) {
				// If left child of root is null then assign its right child.
				localRoot = localRoot.getRight();
			} else {
				// None of the children is null then find out inorder predecessor
				// of the node localRoot in its left Subtree.
				Node<T> maxValNode = maxValueNode(localRoot.getLeft());
				// Copy value of inorder predecessor to localRoot
				localRoot.setValue(maxValNode.getValue());
				// After that remove inorder predecessor from its left subtree.
				// And update the localRoot's left child.
				localRoot.setLeft(remove(localRoot.getLeft(), maxValNode));
				// Now get the updated left child of localRoot.
				maxValNode = localRoot.getLeft();
				// If left child is not null the update its parent as localRoot.
				if(maxValNode != null)
					maxValNode.setParent(localRoot);
			}
		}
		return localRoot;
	}

	/**
	 *
	 * This function returns a node containing maximum value in tree.
	 * If tree is empty then null.
	 * @param localRoot of tree.
	 * @return Node containing maximum value.
	 *
	 **/
	public Node<T> maxValueNode(Node<T> localRoot) {
		if(localRoot != null) {
			Node<T> ret = maxValueNode(localRoot.getRight());
			ret = ret == null ? localRoot : ret;
			return ret;
		}
		return localRoot;
	}

	/**
	 *
	 * This function returns a node containing minimum value in tree.
	 * If tree is empty then null.
	 * @param localRoot of tree.
	 * @return Node containing minimum value.
	 *
	 **/
	public Node<T> minValueNode(Node<T> localRoot) {
		if(localRoot != null) {
			Node<T> ret = minValueNode(localRoot.getLeft());
			ret = ret == null ? localRoot : ret;
		}
		return localRoot;
	}

	/**
	 *
	 * This function prints the inorder traversal of tree.
	 *
	 **/
	public void inOrderTraversal() {
		inOrderTraversal(this.root);
		System.out.println();
	}

	/**
	 *
	 * This is a utility to function described above.
	 * It is not accessible by user as it is private.
	 * @param Local root of tree.
	 *
	 **/
	private void inOrderTraversal(Node<T> localRoot) {
		if(localRoot == null) {
			return ;
		}

		inOrderTraversal(localRoot.getLeft());
		System.out.print(localRoot.getValue().toString() + " ");
		inOrderTraversal(localRoot.getRight());
	}

	/**
	 *
	 * This function prints the preorder traversal of tree.
	 *
	 **/
	public void preOrderTraversal() {
		preOrderTraversal(this.root);
		System.out.println();
	}

	/**
	 *
	 * This is a utility to function described above.
	 * It is not accessible by user as it is private.
	 * @param Local root of tree.
	 *
	 **/
	private void preOrderTraversal(Node<T> localRoot) {
		if(localRoot == null) {
			return ;
		}

		System.out.print(localRoot.getValue().toString() + " ");
		preOrderTraversal(localRoot.getLeft());
		preOrderTraversal(localRoot.getRight());
	}

	/**
	 *
	 * This function prints the postorder traversal of tree.
	 *
	 **/
	public void postOrderTraversal() {
		postOrderTraversal(this.root);
		System.out.println();
	}

	/**
	 *
	 * This is a utility to function described above.
	 * It is not accessible by user as it is private.
	 * @param Local root of tree.
	 *
	 **/
	private void postOrderTraversal(Node<T> localRoot) {
		if(localRoot == null)
			return ;

		postOrderTraversal(localRoot.getLeft());
		postOrderTraversal(localRoot.getRight());
		System.out.print(localRoot.getValue().toString() + " ");
	}

	/* Driver Program to test class.*/
	/*
		public static void main(String[] args) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>();
			tree.put(10);
			tree.put(4); tree.put(1); tree.put(7);
			tree.put(16); tree.put(13); tree.put(19);
			tree.inOrderTraversal();
			tree.remove(10);
			tree.inOrderTraversal();
		}
	*/
}