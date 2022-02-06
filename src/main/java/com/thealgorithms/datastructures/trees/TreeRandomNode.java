package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;

// Using auxiliary array to find the random node in a given binary tree
class Node {
    int item;
    Node left, right;

    public Node(int key) {
        item = key;
        left = right = null;
    }
}

public class TreeRandomNode {

    // Using an arraylist to store the inorder traversal of the given binary tree
    static ArrayList<Integer> list = new ArrayList<>();
    // root of Tree
    Node root;

    TreeRandomNode() {
        root = null;
    }

    // Now lets find the inorder traversal of the given binary tree
    static void inOrder(Node node) {
        if (node == null)
            return;

        // traverse the left child
        inOrder(node.left);

        list.add(node.item);
        // traverse the right child
        inOrder(node.right);
    }

    public void getrandom(Node val)
    {
        inOrder(val);
        // getting the count of node of the binary tree
        int n = list.size();
        int min = 0;
        int max = n - 1;
        //Generate random int value from 0 to n-1
        int b = (int)(Math.random()*(max-min+1)+min);
        // displaying the value at the generated index
        int random = list.get(b);
        System.out.println("Random Node : " + random);

    }
}


/* Explanation of the Approach :
  (a) Form the required binary tree
  (b) Now use the inOrder() method to get the nodes in inOrder fashion and also store them in the given arraylist 'list'
  (c) Using the getRandom() method generate a random number between 0 to n-1, then get the value at the generated random number
      from the arraylist using get() method and finally display the result.
 */


/* OUTPUT :
  First output :
  Random Node : 15
  Second output :
  Random Node : 99
 */

/* Time Complexity : O(n)
  Auxiliary Space Complexity : O(1)
 */

