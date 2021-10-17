package Sorts;

class TreeSort {
    //Class which has the left and right child of current node and value
    class Node {
        int key;
        Node left, right;

        public Node(int x)
        {
            key = x;
            left = right = null;
        }
    }    

    // Root of BST
    Node root;

    //Constructor
    TreeSort() {
        root = null;
    }

    //This calls insertRec()
    void insert(int k) {
        root = insertRec(root, k);
    }

    //A Recursive function to insert new key in BST
    Node insertRec(Node root, int key) 
    {
        //If tree empty, then return new Node
        if(root == null) {
            root = new Node(key);
            return root;
        }

        // Recursively iterate down the tree
        if(key < root.key) {
            root.left = insertRec(root.left, key);
        }
        else if(key > root.key) {
            root.right = insertRec(root.right, key);
        }

        //Return the root
        return root;
    }

    //Function to do inorder traversal
    void inorderRec(Node root) 
    {
        if(root != null) 
        {
            inorderRec(root.left);
            System.out.print(root.key+" ");
            inorderRec(root.right);
        }
    }

    // Tree insertion
    void treeins(int arr[])
    {
        for(int i=0;i<arr.length;++i) {
            insert(arr[i]);
        }
    }

    public static void main(String[] args) {
        TreeSort t = new TreeSort();

        int arr[] = {0,9,1,3,12,34,10,20,-9,-10,-200};
        t.treeins(arr);
        t.inorderRec(t.root);
    }
}
