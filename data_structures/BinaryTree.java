//A binary tree is a data structure in which an element has two successors(children)
//The left child is usually smaller than the parent, and the right child is usually bigger
class Node{
	public int data;
	public Node left;
	public Node right;
	public Node parent;

	public Node(int value){
		data = value;
		left = null;
		right = null;
		parent = null;
	}
}

class Tree{
	private Node root;

	public Tree(){
		root = null;
	}
	//Returns the node if it finds it, otherwise returns the parent
	public Node find(int key){
		Node current = root;
		Node last = root;
		while(current != null){
			last = current;
			if(key < current.data)
				current = current.left;
			else if(key > current.data)
				current = current.right;
			//If you find the value return it
			else
				return current;
		}
		return last;
	}

	//Inserts the given value
	public void put(int value){
		Node newNode = new Node(value);
		if(root == null)
			root = newNode;
		else{
			//This will return the soon to be parent of the value you're inserting
			Node parent = find(value);

			//This if/else assigns the new node to be either the left or right child of the parent
			if(value < parent.data){
				parent.left = newNode;
				parent.left.parent = parent;
				return;
			}
			else{
				parent.right = newNode;
				parent.right.parent = parent;
				return;
			}
		}
	}

	//Deletes the given value
	public boolean remove(int value){
		//temp is the node to be deleted
		Node temp = find(value);

		//If the value doesn't exist
		if(temp.data != value)
			return false;

		//No children
		if(temp.right == null && temp.left == null){
			if(temp == root)
				root = null;

			//This if/else assigns the new node to be either the left or right child of the parent
			else if(temp.parent.data < temp.data)
				temp.parent.right = null;
			else
				temp.parent.left = null;
			return true;
		}

		//Two children
		else if(temp.left != null && temp.right != null){
			Node succesor = findSuccesor(temp);

			//The left tree of temp is made the left tree of the successor
			succesor.left = temp.left;
			succesor.left.parent = succesor;

			//If the successor has a right child, the child's grandparent is it's new parent
			if(succesor.right != null && succesor.parent != temp){
				succesor.right.parent = succesor.parent;
				succesor.parent.left = succesor.right;
				succesor.right = temp.right;
				succesor.right.parent = succesor;
			}
			if(temp == root){
				succesor.parent = null;
				root = succesor;
				return true;
			}

			//If you're not deleting the root
			else{
				succesor.parent = temp.parent;

				//This if/else assigns the new node to be either the left or right child of the parent
				if(temp.parent.data < temp.data)
					temp.parent.right = succesor;
				else
					temp.parent.left = succesor;
				return true;
			}
		}
		//One child
		else{
			//If it has a right child
			if(temp.right != null){
				if(temp == root){
					root = temp.right; return true;}

				temp.right.parent = temp.parent;

				//Assigns temp to left or right child
				if(temp.data < temp.parent.data)
					temp.parent.left = temp.right;
				else
					temp.parent.right = temp.right;
				return true;
			}
			//If it has a left child
			else{
				if(temp == root){
					root = temp.left; return true;}

				temp.left.parent = temp.parent;

				//Assigns temp to left or right side
				if(temp.data < temp.parent.data)
					temp.parent.left = temp.left;
				else
					temp.parent.right = temp.left;
				return true;
			}
		}
	}

	//Move right once and go left down the tree as far as you can
	public Node findSuccesor(Node n){
		if(n.right == null)
			return n;
		Node current = n.right;
		Node parent = n.right;
		while(current != null){
			parent = current;
			current = current.left;
		}
		return parent;
	}

	public Node getRoot(){
			return root;
	}

	//Prints leftChild - root - rightChild
	public void inOrder(Node localRoot){
		if(localRoot != null){
			inOrder(localRoot.left);
			System.out.print(localRoot.data + " ");
			inOrder(localRoot.right);
		}
	}
	//Prints root - leftChild - rightChild
	public void preOrder(Node localRoot){
		if(localRoot != null){
			System.out.print(localRoot.data + " ");
			preOrder(localRoot.left);
			preOrder(localRoot.right);
		}
	}
	//Prints rightChild - leftChild - root
	public void postOrder(Node localRoot){
		if(localRoot != null){
			postOrder(localRoot.left);
			postOrder(localRoot.right);
			System.out.print(localRoot.data + " ");
		}
	}
}