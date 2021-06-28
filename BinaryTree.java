package MarchLongChallenge;

import java.util.Scanner;

public class BinaryTree {
	
	private class Node{
		int data;
		Node leftNode;
		Node rightNode;
		
		Node(int data,Node left,Node right){
			this.data=data;
			this.leftNode=left;
			this.rightNode=right;
		}
	}
	
	private Node rootNode;
	private int datasize;
	
	public BinaryTree() {
		Scanner s =new Scanner(System.in);
		this.rootNode= takeInput(s,null,false);
	}

	private Node takeInput(Scanner s, Node parent, boolean isleftorright) {
		if(parent==null) {
			System.out.println("Enter the value for parent data");
		}else {
			if(isleftorright) {
				System.out.println("Enter data for left child of "+ parent.data);
			}else {
				System.out.println("Enter data for right child of "+ parent.data);
			}
			
			
		}
		int data =s.nextInt();
		Node node=new Node(data,null,null);
		this.datasize++;
		
		boolean choice =false;
		System.out.println("Does "+ node.data + " have a left Node");
		choice=s.nextBoolean();
		if(choice) {
			node.leftNode=takeInput(s, node, true);
		}
		
		choice =false;
		System.out.println("Does "+ node.data +" have a right Node");
		choice=s.nextBoolean();
		if(choice) {
			node.rightNode= takeInput(s, node, false);
		}
		
		return node;
	}
	
	public void display() {
		if(this.rootNode ==null) {
			System.out.println("Root is Empty");
		}else {
			this.display(this.rootNode);
		}
	}
	
	private void display(Node node) {
		if(node.leftNode!=null) {
			System.out.print(node.leftNode.data+"->");
		}else {
			System.out.print("##->");
		}
		
		System.out.print(node.data);
		
		if(node.rightNode!=null) {
			System.out.print("<-"+node.rightNode.data);
		}else {
			System.out.print("<-##");
		}
		
		System.out.println();
		
		if(node.leftNode!=null) {
			this.display(node.leftNode);
		}
		if(node.rightNode!=null) {
			this.display(node.rightNode);
		}
	}
	
	public int height() {
		return this.height(this.rootNode);
	}
	
	private int height(Node node) {
		if(node==null) {
			return -1;
		}
		int lheight = this.height(node.leftNode);
		int rheight = this.height(node.rightNode);
		return Math.max(lheight,rheight) + 1;
	}
	
	public int  min() {
		return this.min(this.rootNode);
	}
	
	private int min(Node node) {
		if(node == null) {
		 return Integer.MAX_VALUE;	
		}
		int lmin = this.min(node.leftNode);
		int rmin = this.min(node.rightNode);
		return Math.min(node.data, Math.min(lmin, rmin));
	}
	
	public int max() {
		return this.max(this.rootNode);
	}
	
	private int max(Node node) {
		if(node == null) {
			return Integer.MIN_VALUE;
		}
		int lmax = this.max(node.leftNode);
		int rmax = this.max(node.rightNode);
		return Math.max(node.data, Math.max(lmax, rmax));
	}
	
	public void preorder() {
		this.preorder(this.rootNode);
	}
	
	private void preorder(Node node) {
		if(node ==null) {
			return ;
		}
		System.out.print(node.data+ " ");
		preorder(node.leftNode);
		preorder(node.rightNode);
	}
	
	public void inorder() {
		this.inorder(this.rootNode);
	}
	
	private void inorder(Node node) {
		if(node ==null) {
			return ;
		}
		preorder(node.leftNode);
		System.out.print(node.data+ " ");
		preorder(node.rightNode);
	}
	
	public void postorder() {
		this.postorder(this.rootNode);
	}
	
	private void postorder(Node node) {
		if(node ==null) {
			return ;
		}
		preorder(node.leftNode);
		preorder(node.rightNode);
		System.out.print(node.data+ " ");
	}
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		// 2 true 7 true 2 false false true 6 true 5 false false true 11 false false true 5 false true 9 true 4 false false false 
		tree.display();
		System.out.println("Height of tree is: "+ tree.height());
		System.out.println("Max element of tree is: "+ tree.max());
		System.out.println("Min element of tree is: "+ tree.min());
		System.out.println("Preorder Traversal :--");
		tree.preorder();
		System.out.println();
		System.out.println("Inorder Traversal :--");
		tree.inorder();
		System.out.println();
		System.out.println("Postorder Traversal :--");
		tree.postorder();
	}
}


