package com.thealgorithms.datastructures.trees;

/**
 * Time complexity is O(n)
 *  Space Complexity is O(n) considering stack space
 */
public class BstToDLL {

    Node head=null;
    Node tail=null;

    //converting tree to dll using inorder traversal
    void convert(Node root){
        if(root==null)
            return;
        convert(root.left);
        if(head==null){
            head = root;
            tail = root;
        }else{
            tail.right=root;
            root.left=tail;
            tail=root;
        }
        convert(root.right);
    }
    //Function to convert binary tree to doubly linked list and return it.
    Node bToDLL(Node root)
    {
        convert(root);
        return head;
    }
    public static void main(String[] args){
        // creating a tree
        Node root = new Node(10);
        root.left = new Node(12);
        root.right = new Node(15);
        root.left.left = new Node(25);
        root.left.right = new Node(30);
        root.right.left = new Node(36);
        BstToDLL bstToDLL = new BstToDLL();
        bstToDLL.bToDLL(root);
        Node head1 = bstToDLL.head;
        //printing doubly linked list 25<->12<->30<->10<->36<->15
        while( head1 != null){
            System.out.println(head1.data + " ");
            head1=head1.right;
        }
    }
}
