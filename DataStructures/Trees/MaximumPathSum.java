package DataStructures.Trees;

/**
 * Maximum Path Sum in a Binary Tree.
 * 
 * Given the root of a Binary Tree, return the maximum path sum of any path. 
 * Path Sum is the sum of sequence of nodes where each pair of nodes has
 * an edge connecting them. Path can be from any node to any node (does not need 
 * to pass through the root). -1000 <= Node.val <= 1000 
 * 
 * Input : Root Node Object
 * Output : Integer value
 *
 * @author Deepti Shahi (https://github.com/DeeptiShahi)
 */
public class MaximumPathSum {

    //Declarataion of Node class
    class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    /* The result that will be returned as output, since the output is
    supposed to maximum, initializing this global var with min */
    int res = Integer.MIN_VALUE;

    /* Main function that will return the max. path sum.
       Input : root Node
       Output : int sum  */
    public int maxPathSum(Node root) {
        if (root == null)
            return 0;
        maxPathSumUtil(root);
        return res;
    }

    /* Helper method to resursively calculate maximum sum at every node
       and store the maximum in global var at every recursion.
       Input : root Node */
    private int maxPathSumUtil(Node root) {
        //Base condition
        if (root == null)
            return 0;
        
        // Calculate max path sum of left subtree.
        int left = maxPathSumUtil(root.left);

        // Calculate max path sum of right subtree.
        int right = maxPathSumUtil(root.right);

        /* Maximum possible Sum at the current node will be maximum between maximum of either subtrees plus the root value
           or the root value itself (This helps in calculation if there are nodes with negative values) */
        int temp1 = Math.max(Math.max(left, right) + root.val, root.val);
        
        /* Check if including the current root in the sum of left and right 
           subtrees will result in a path with max sum so far.  */
        int temp2 = Math.max(left + right + root.val, temp1);
        res = Math.max(res, temp2);
        
        // Return the maximum sum calculated till the current node.
        return temp1;
    }
}
