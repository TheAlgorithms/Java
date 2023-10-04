/**
 * Author : Gaurav Pathak
 * Github : https://github.com/GauravPathak77
 */

/** Program description - To maximize theft from a binary tree of houses without neighboring house robberies. */
package com.thealgorithms.dynamicprogramming;

import java.util.*;

public class HouseRobberIII {
    /*
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    }

    // Helper function for recursive calculation
    private int[] robSub(TreeNode root) {
        if (root == null) {
        return new int[] { 0, 0 };
        }

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);

        // If we rob the current house, we cannot rob its children
        int robCurrent = root.val + left[1] + right[1];

        // If we skip the current house, we can choose to rob or skip its children
        int skipCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { robCurrent, skipCurrent };
    }

    public int rob(TreeNode root) {
        int[] result = robSub(root);
        return Math.max(result[0], result[1]);
    }
}

/**
     * OUTPUT :
     * Input - root = [3,2,3,null,3,null,1]
     * Output: 7
     * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     * Time complexity is O(N), where N is the number of nodes in the binary tree.
     * Space complexity is O(H), where H is the height of the binary tree.
     */