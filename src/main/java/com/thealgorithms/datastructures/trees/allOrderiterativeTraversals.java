package com.thealgorithms.datastructures.trees;

import java.util.*;

class tree_Node {
    int data;
    tree_Node left;
    tree_Node right;

    tree_Node() {
    }

    tree_Node(int data) {
        this.data = data;
    }

    tree_Node(int data, tree_Node left, tree_Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
//T.C-O(N)
//S.C-O(N)
public class allOrderiterativeTraversals {
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        tree_Node root = createTree();
        // inOrder(root);
        // System.out.println();

        System.out.println(preinpostTraversal(root));
    }

    static tree_Node createTree() {
        tree_Node root = null;
        System.out.println("Enter data: ");
        int data = sc.nextInt();
        if (data == -1)
            return null;
        root = new tree_Node(data);
        System.out.println("Enter left for " + data);
        root.left = createTree();
        System.out.println("Enter right for " + data);
        root.right = createTree();

        return root;

    }

    public static List<Integer> preinpostTraversal(tree_Node root) {
        Stack<pair> s = new Stack<pair>();
        s.push(new pair(root, 1));
        List<Integer> preorder = new ArrayList<Integer>();
        List<Integer> inorder = new ArrayList<Integer>();
        List<Integer> postorder = new ArrayList<Integer>();
        if (root == null)
            return inorder;
        while (!s.isEmpty()) {
            pair p = s.pop();
            if (p.val == 1) {
                preorder.add(p.node.data);
                p.val++;
                s.push(p);
                if (p.node.left != null) {
                    s.push(new pair(p.node.left, 1));
                }
            } else if (p.val == 2) {
                inorder.add(p.node.data);
                p.val++;
                s.push(p);
                if (p.node.right != null) {
                    s.push(new pair(p.node.right, 1));
                }
            } else {
                postorder.add(p.node.data);
            }
        }

        // return postorder;
        // return preorder;
        return inorder;

    }

}

class pair {
    tree_Node node;
    int val;

    public pair(tree_Node node, int val) {
        this.node = node;
        this.val = val;
    }
}
// all Tree Traversals in one function.....
