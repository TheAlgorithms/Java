package com.thealgorithms.tree;

public class BinarySearchTree {
    class Node {
        int key;
        Node left,right;
        Node(int key){
            this.key = key;
            left = right = null;
        }

    }

    Node root;

    public void insert(int key){
        root = insertRec(root,key);
    }

    public Node insertRec(Node root, int key){
        if(root == null){
            return new Node(key);
        }
        if(key < root.key){
            root.left = insertRec(root.left,key);
        }else if (key > root.key){
            root.right = insertRec(root.right,key);
        }
        return root;
    }

    public void inorder(){
        inorderRec(root);
    }
    
    void inorderRec(Node root){
        if(root != null){
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public void preOrder(){
        preOrderRec(root);
    }

    void preOrderRec(Node root){
        if(root !=null){
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    public void postOrder(){
        postOrderRec(root);
    }

    void postOrderRec(Node root){
        if(root!=null){
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);    
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();
        System.out.println();

        System.out.println("Preorder traversal of the given tree");
        tree.preOrder();
        System.out.println();

        System.out.println("Postorder traversal of the given tree");
        tree.postOrder();
        System.out.println();
    }
}

