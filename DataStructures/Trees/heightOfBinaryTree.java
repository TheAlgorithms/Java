import java.util.*;
import java.io.*;

// defining the node used to form the tree
class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Main {

    // recursive function to find the height of tree
	public static int height(Node root) {
      	
          if(root==null) return 0; // when the node goes below the leaf node
          if(root.left==null && root.right==null) return 0;  // if its's a leaf node ,we return 0
          int left = height(root.left); // recursively call the left subtree
          int right = height(root.right); // recursively call the right subtree
         // return the maximum of the height of left subtree 
         //and right subtree and add 1 to it to consider the current node as well
          return Math.max(left,right)+1;  
    }

    // function to form the tree
	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }	
}

/*
  Conversion of Tree to input - 

            3
           / \                           7 (No. of nodes)
          2   5                  =>      3 5 2 1 4 6 7
         /   / \
        1   4   6
                 \
                  7

Test Cases:-                                  

TEST CASE 1 - 
7
3 5 2 1 4 6 7

OUTPUT - 
3


TEST CASE 2 - 
1
15

OUTPUT -
0


TEST CASE 3 -
5
3 1 7 5 4

OUTPUT-
3

*/
