// Java Program to print Bottom View of Binary Tree

// Input:
//           20
//        /      \
//      8          22
//    /    \     /     \
//   5      3    4     25
//        /   \
//      10     14

// Output: 5 10 4 14 25

package com.thealgorithms.datastructures.trees;

import java.util.*;
import java.util.Map.Entry;

// Tree node class
class TNode {
    int data; //data of the TNode
    int hd; //horizontal distance of the node
    TNode left, right; //left and right references

    // Constructor of tree node
    public TNode(int key) {
        data = key;
        hd = Integer.MAX_VALUE;
        left = right = null;
    }
}

//Tree class
class Tree {
    TNode root; //root node of tree

    // Default constructor
    public Tree() {
    }

    // Parameterized tree constructor
    public Tree(TNode node) {
        root = node;
    }

    // Method that prints the bottom view.
    public void bottomView() {
        if (root == null)
            return;

        // Initialize a variable 'hd' with 0 for the root element.
        int hd = 0;

        // TreeMap which stores key value pair sorted on key value
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue to store tree nodes in level order traversal
        Queue<TNode> queue = new LinkedList<TNode>();

        // Assign initialized horizontal distance value to root
        // node and add it to the queue.
        root.hd = hd;
        queue.add(root);

        // Loop until the queue is empty (standard level order loop)
        while (!queue.isEmpty()) {
            TNode temp = queue.remove();

            // Extract the horizontal distance value from the
            // dequeued tree node.
            hd = temp.hd;

            // Put the dequeued tree node to TreeMap having key
            // as horizontal distance. Every time we find a node
            // having same horizontal distance we need to replace
            // the data in the map.
            map.put(hd, temp.data);

            // If the dequeued node has a left child add it to the
            // queue with a horizontal distance hd-1.
            if (temp.left != null) {
                temp.left.hd = hd - 1;
                queue.add(temp.left);
            }
            // If the dequeued node has a right child add it to the
            // queue with a horizontal distance hd+1.
            if (temp.right != null) {
                temp.right.hd = hd + 1;
                queue.add(temp.right);
            }
        }

        // Extract the entries of map into a set to traverse
        // an iterator over that.
        Set<Entry<Integer, Integer>> set = map.entrySet();

        // Make an iterator
        Iterator<Entry<Integer, Integer>> iterator = set.iterator();

        // Traverse the map elements using the iterator.
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> me = iterator.next();
            System.out.print(me.getValue() + " ");
        }
    }
}

// Main driver class
public class BottomViewOfBinaryTree {
    public static void main(String[] args) {

        TNode root = new TNode(20);
        root.left = new TNode(8);
        root.right = new TNode(22);
        root.left.left = new TNode(5);
        root.left.right = new TNode(3);
        root.right.left = new TNode(4);
        root.right.right = new TNode(25);
        root.left.right.left = new TNode(10);
        root.left.right.right = new TNode(14);

        Tree tree = new Tree(root);
        System.out.println("Bottom view of the given binary tree:");
        tree.bottomView();

    }
}

//    Time Complexity: O(N * logN)
//    Auxiliary Space: O(N)
