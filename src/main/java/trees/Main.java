package trees;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== BINARY SEARCH TREE DEMO =====\n");
        BinarySearchTree bst = new BinarySearchTree();
        
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            bst.insert(val);
        }
        
        System.out.println("Inserted: " + java.util.Arrays.toString(values));
        bst.inorder();
        bst.preorder();
        bst.postorder();
        System.out.println("Min: " + bst.findMin() + ", Max: " + bst.findMax());
        
        System.out.println("\n===== AVL TREE DEMO =====\n");
        AVLTree avl = new AVLTree();
        
        for (int val : values) {
            avl.insert(val);
        }
        
        System.out.println("Inserted: " + java.util.Arrays.toString(values));
        avl.inorder();
        System.out.println("Tree Height: " + avl.getHeight());
        avl.preorder();
        avl.postorder();
        System.out.println("Min: " + avl.findMin() + ", Max: " + avl.findMax());
    }
}