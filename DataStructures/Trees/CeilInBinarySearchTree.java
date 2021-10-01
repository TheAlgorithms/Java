package DataStructures.Trees;

/**
 * Problem Statement
 * Ceil value for any number x in a collection is a number y which is either equal to x or the least greater number than x.
 *
 * Problem: Given a binary search tree containing positive integer values.
 * Find ceil value for a given key in O(lg(n)) time. In case if it is not present return -1.
 *
 * Ex.1. [30,20,40,10,25,35,50] represents level order traversal of a binary search tree. Find ceil for 10.
 * Answer: 20
 *
 * Ex.2. [30,20,40,10,25,35,50] represents level order traversal of a binary search tree. Find ceil for 22
 * Answer: 25
 *
 * Ex.2. [30,20,40,10,25,35,50] represents level order traversal of a binary search tree. Find ceil for 52
 * Answer: -1
 */

/**
 *
 * Solution 1:
 * Brute Force Solution:
 * Do an inorder traversal and save result into an array. Iterate over the array to get an element equal to or greater
 * than current key.
 * Time Complexity: O(n)
 * Space Complexity: O(n) for auxillary array to save inorder representation of tree.
 * <p>
 * <p>
 * Solution 2:
 * Brute Force Solution:
 * Do an inorder traversal and save result into an array.Since array is sorted do a binary search over the array to get an
 * element equal to or greater than current key.
 * Time Complexity: O(n) for traversal of tree and O(lg(n)) for binary search in array. Total = O(n)
 * Space Complexity: O(n) for auxillary array to save inorder representation of tree.
 * <p>
 * <p>
 * Solution 3: Optimal
 * We can do a DFS search on given tree in following fashion.
 * i) if root is null then return null because then ceil doesn't exist
 * ii) If key is lesser than root value than ceil will be in right subtree so call recursively on right subtree
 * iii) if key is greater than current root, then either
 * a) the root is ceil
 * b) ceil is in left subtree: call for left subtree. If left subtree returns a non null value then that will be ceil
 * otherwise the root is ceil
 */
public class CeilInBinarySearchTree {
    class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer x) {
            val = x;
        }
    }


    public static TreeNode getCeil(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            return root;
        }

        if (root.val < key) {
            return getCeil(root.right, key);
        }

        TreeNode result = getCeil(root.left, key);
        return result == null ? root : result;
    }
}

