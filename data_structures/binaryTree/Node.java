package data_structures.binaryTree;

//A binary tree is a data structure in which an element has two successors(children)
//The left child is usually smaller than the parent, and the right child is usually bigger
public class Node {
    public int data;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int value) {
        data = value;
        left = null;
        right = null;
        parent = null;
    }
}
