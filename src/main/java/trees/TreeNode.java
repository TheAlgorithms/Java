package trees;

/**
 * Generic node class for tree implementations
 */
public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
    public int height; // Used for AVL Tree balancing
    
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}