package trees;

/**
 * AVL Tree Implementation (Self-Balancing Binary Search Tree)
 * Maintains balance factor to ensure O(log n) operations
 * Supports: insert, delete, search, rotations (LL, RR, LR, RL)
 */
public class AVLTree {
    private TreeNode root;
    
    public AVLTree() {
        this.root = null;
    }
    
    // ============= HEIGHT MANAGEMENT =============
    private int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }
    
    private int getBalance(TreeNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    
    private void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }
    
    // ============= ROTATION OPERATIONS =============
    // Right Rotation (LL case)
    private TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    // Left Rotation (RR case)
    private TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    // ============= INSERT OPERATION =============
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    private TreeNode insertRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        } else {
            return node; // Duplicate ignored
        }
        
        updateHeight(node);
        return balance(node);
    }
    
    // ============= BALANCE OPERATION =============
    private TreeNode balance(TreeNode node) {
        int balanceFactor = getBalance(node);
        
        // Left Heavy Cases
        if (balanceFactor > 1) {
            if (getBalance(node.left) < 0) {
                // LR case: Left-Right
                node.left = rotateLeft(node.left);
            }
            // LL case: Left-Left
            return rotateRight(node);
        }
        
        // Right Heavy Cases
        if (balanceFactor < -1) {
            if (getBalance(node.right) > 0) {
                // RL case: Right-Left
                node.right = rotateRight(node.right);
            }
            // RR case: Right-Right
            return rotateLeft(node);
        }
        
        return node;
    }
    
    // ============= DELETE OPERATION =============
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private TreeNode deleteRecursive(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        
        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node to delete found
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
            TreeNode minRight = findMinNode(node.right);
            node.value = minRight.value;
            node.right = deleteRecursive(node.right, minRight.value);
        }
        
        updateHeight(node);
        return balance(node);
    }
    
    // ============= SEARCH OPERATION =============
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    private boolean searchRecursive(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        
        if (value == node.value) {
            return true;
        } else if (value < node.value) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }
    
    // ============= UTILITY METHODS =============
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinNode(root).value;
    }
    
    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxNode(root).value;
    }
    
    private TreeNode findMaxNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    // ============= TREE TRAVERSALS =============
    public void inorder() {
        System.out.print("Inorder: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }
    
    public void preorder() {
        System.out.print("Preorder: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    private void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
    public void postorder() {
        System.out.print("Postorder: ");
        postorderRecursive(root);
        System.out.println();
    }
    
    private void postorderRecursive(TreeNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
    
    // ============= HELPER METHODS =============
    public int getHeight() {
        return getHeight(root);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void clear() {
        root = null;
    }
}