package datastructures.trees;

class TreeTraversalTest {

    /**
     * @author Varun Upadhyay (https://github.com/varunu28)
     */
    public static void main(String[] args) {
        datastructures.trees.TreeTraversal tree = new datastructures.trees.TreeTraversal(5);
        tree.insert(3);
        tree.insert(7);

        // Prints 3 5 7
        tree.printInOrder();
        System.out.println();

        // Prints 5 3 7
        tree.printPreOrder();
        System.out.println();

        // Prints 3 7 5
        tree.printPostOrder();
        System.out.println();
    }
}