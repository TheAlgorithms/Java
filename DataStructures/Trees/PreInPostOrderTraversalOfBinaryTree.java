class Node{
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    void enterLeft(int data){
        this.left = new Node(data);
    }
    void enterRight(int data){
        this.right = new Node(data);
    }
}

class BinaryTree{
    Node root;

    BinaryTree(int data){
        this.root = new Node(data);
    }

    void preOrder(Node root){
        if(root==null)
            return;

        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder((root.right));
    }

    void inOrder(Node root){
        if(root==null)
            return;

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    void postOrder(Node root){
        if(root==null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

}

public class PreInPostOrderTraversalOfBinaryTree  {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(2);
        tree.root.enterLeft(7);
        tree.root.enterRight(5);
        tree.root.left.enterLeft(2);
        tree.root.left.enterRight(6);
        tree.root.left.right.enterLeft(5);
        tree.root.left.right.enterRight(11);
        tree.root.right.enterRight(9);
        tree.root.right.right.enterLeft(4);

        System.out.println("PreOrder");tree.preOrder(tree.root);
        System.out.println();
        System.out.println("InOrder");tree.inOrder(tree.root);
        System.out.println();
        System.out.println("PostOrder");tree.postOrder(tree.root);

    }
}

