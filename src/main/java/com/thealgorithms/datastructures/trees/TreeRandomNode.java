package com.thealgorithms.datastructures.trees;

/* Author : Suraj Kumar
  Github : https://github.com/skmodi649
 */

/* PROBLEM DESCRIPTION :
  There is a Binary Search Tree given, and we are supposed to find a random node in the given binary tree.
 */

/* ALGORITHM :
  Step 1: START
  Step 2: First create a binary tree using the steps mentioned in the first approach
  Step 3: Now use a method inOrder() that takes a node as input parameter to traverse through the
          binary tree in inorder fashion as also store the values in a ArrayList simultaneously.
  Step 4: Now define a method getRandom() that takes a node as input parameter, in this first call
          the inOrder() method to store the values in the arraylist, then find the size of the binary tree and now just generate a random number between 0 to n-1.
  Step 5: After generating the number display the value of the ArrayList at the generated index
  Step 6: STOP
 */

import java.util.ArrayList;

// Using auxiliary array to find the random node in a given binary tree

public class TreeRandomNode {

    private class Node {

        int item;
        Node left, right;

        public Node(int key) {
            item = key;
            left = right = null;
        }
    }

    // Using an arraylist to store the inorder traversal of the given binary tree
    static ArrayList<Integer> list = new ArrayList<>();
    // root of Tree
    Node root;

    TreeRandomNode() {
        root = null;
    }

    // Now lets find the inorder traversal of the given binary tree
    static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        // traverse the left child
        inOrder(node.left);

        list.add(node.item);
        // traverse the right child
        inOrder(node.right);
    }

    public void getRandom(Node val) {
        inOrder(val);
        // getting the count of node of the binary tree
        int n = list.size();
        int min = 0;
        int max = n - 1;
        //Generate random int value from 0 to n-1
        int b = (int) (Math.random() * (max - min + 1) + min);
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
