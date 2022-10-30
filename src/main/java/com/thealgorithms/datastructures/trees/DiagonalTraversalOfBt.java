package com.thealgorithms.datastructures.trees;

import javax.swing.tree.TreeNode;
import java.util.*;

public class Main {

    /*  Given a binary tree, print all nodes for each diagonal having negative slope (\).
     *  Assume that the left and right child of a node makes a 45–degree angle with the parent.
     *  Link for further explanation: https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
     *  Question Link: - https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/0
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    /**
     * Definition of Pair class
     * public static class Pair {
     *         int hd; //horizontal distance
     *         TreeNode node;
     *
     *         public Pair(int hd, TreeNode node) {
     *             this.hd = hd;
     *             this.node = node;
     *         }
     *     }
     */


    public static ArrayList<Integer> diagonal(TreeNode root){

        //Make Map DataStructure to Store elements of each diagonal in specific key
        Map<Integer,ArrayList<Integer>> map = new TreeMap<>();

        Queue<Pair> q =new LinkedList<>();

        //Adding the first Node into Queue
        q.add(new Pair(1,root));
        while (!q.isEmpty()){
            int size = q.size();

            while(size--!=0) { //help to traverse each component of particular diagonal
                Pair current = q.poll();
//                size--;
                while (current.node!= null) {  //traverse each component
                    if(map.containsKey(current.hd)){
                        map.get(current.hd).add(current.node.data);
                    }
                    else{
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(current.node.data);
                        map.put(current.hd,temp);
                    }
                    if (current.node.left != null) {
                        q.add(new Pair(current.hd + 1, current.node.left));
                    }
                    current = new Pair(current.hd, current.node.right);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer,ArrayList<Integer>> entry: map.entrySet()){
            ans.addAll(entry.getValue());
        }
        return ans;
    }
}
