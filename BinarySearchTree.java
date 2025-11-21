import java.util.Scanner;

public class BinarySearchTree {

    static class Node {
        int key;
        Node left, right;
        Node(int k) {
            key = k;
        }
    }

    Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else {
            if (key < node.key) {
                node.left = insertRec(node.left, key);
            } else {
                node.right = insertRec(node.right, key);
            }
            return node;
        }
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) {
            return node;
        } else {
            if (key < node.key) {
                node.left = deleteRec(node.left, key);
            } else if (key > node.key) {
                node.right = deleteRec(node.right, key);
            } else {
                if (node.left == null) {
                    return node.right;
                } else {
                    if (node.right == null) {
                        return node.left;
                    } else {
                        node.key = minValue(node.right);
                        node.right = deleteRec(node.right, node.key);
                    }
                }
            }
            return node;
        }
    }

    private int minValue(Node node) {
        int min = node.key;
        while (node.left != null) {
            min = node.left.key;
            node = node.left;
        }
        return min;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while (true) {
            System.out.println("1.Insert  2.Delete  3.Inorder  4.Preorder  5.Postorder  6.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter value: ");
                int v = sc.nextInt();
                bst.insert(v);
            } else {
                if (ch == 2) {
                    System.out.print("Enter value to delete: ");
                    int v = sc.nextInt();
                    bst.delete(v);
                } else {
                    if (ch == 3) {
                        bst.inorder();
                    } else {
                        if (ch == 4) {
                            bst.preorder();
                        } else {
                            if (ch == 5) {
                                bst.postorder();
                            } else {
                                if (ch == 6) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
