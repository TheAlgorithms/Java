package com.thealgorithms.datastructures.trees;

import java.util.Scanner;

/**
 * @author jack870131
 */
public class RedBlackBST {

    private final int red = 0;
    private final int black = 1;

    private class Node {

        int key = -1;
        int color = black;
        Node left = nil;
        Node right = nil;
        Node p = nil;

        Node(int key) {
            this.key = key;
        }
    }

    private final Node nil = new Node(-1);
    private Node root = nil;

    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(((node.color == red) ? " R " : " B ") + "Key: " + node.key + " Parent: " + node.p.key + "\n");
        printTree(node.right);
    }

    public void printTreepre(Node node) {
        if (node == nil) {
            return;
        }
        System.out.print(((node.color == red) ? " R " : " B ") + "Key: " + node.key + " Parent: " + node.p.key + "\n");
        printTreepre(node.left);
        printTreepre(node.right);
    }

    private Node findNode(Node findNode, Node node) {
        if (root == nil) {
            return null;
        }
        if (findNode.key < node.key) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = black;
            node.p = nil;
        } else {
            node.color = red;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(Node node) {
        while (node.p.color == red) {
            Node y = nil;
            if (node.p == node.p.p.left) {
                y = node.p.p.right;

                if (y != nil && y.color == red) {
                    node.p.color = black;
                    y.color = black;
                    node.p.p.color = red;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.right) {
                    node = node.p;
                    rotateLeft(node);
                }
                node.p.color = black;
                node.p.p.color = red;
                rotateRight(node.p.p);
            } else {
                y = node.p.p.left;
                if (y != nil && y.color == red) {
                    node.p.color = black;
                    y.color = black;
                    node.p.p.color = red;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.left) {
                    node = node.p;
                    rotateRight(node);
                }
                node.p.color = black;
                node.p.p.color = red;
                rotateLeft(node.p.p);
            }
        }
        root.color = black;
    }

    void rotateLeft(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
                node.p.left = node.right;
            } else {
                node.p.right = node.right;
            }
            node.right.p = node.p;
            node.p = node.right;
            if (node.right.left != nil) {
                node.right.left.p = node;
            }
            node.right = node.right.left;
            node.p.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.p = root;
            root.p = right;
            right.left = root;
            right.p = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
                node.p.left = node.left;
            } else {
                node.p.right = node.left;
            }

            node.left.p = node.p;
            node.p = node.left;
            if (node.left.right != nil) {
                node.left.right.p = node;
            }
            node.left = node.left.right;
            node.p.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.p = root;
            root.p = left;
            left.right = root;
            left.p = nil;
            root = left;
        }
    }

    void transplant(Node target, Node with) {
        if (target.p == nil) {
            root = with;
        } else if (target == target.p.left) {
            target.p.left = with;
        } else {
            target.p.right = with;
        }
        with.p = target.p;
    }

    Node treeMinimum(Node subTreeRoot) {
        while (subTreeRoot.left != nil) {
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    boolean delete(Node z) {
        Node result = findNode(z, root);
        if (result == null) {
            return false;
        }
        Node x;
        Node y = z;
        int yorigcolor = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = treeMinimum(z.right);
            yorigcolor = y.color;
            x = y.right;
            if (y.p == z) {
                x.p = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (yorigcolor == black) {
            deleteFixup(x);
        }
        return true;
    }

    void deleteFixup(Node x) {
        while (x != root && x.color == black) {
            if (x == x.p.left) {
                Node w = x.p.right;
                if (w.color == red) {
                    w.color = black;
                    x.p.color = red;
                    rotateLeft(x.p);
                    w = x.p.right;
                }
                if (w.left.color == black && w.right.color == black) {
                    w.color = red;
                    x = x.p;
                    continue;
                } else if (w.right.color == black) {
                    w.left.color = black;
                    w.color = red;
                    rotateRight(w);
                    w = x.p.right;
                }
                if (w.right.color == red) {
                    w.color = x.p.color;
                    x.p.color = black;
                    w.right.color = black;
                    rotateLeft(x.p);
                    x = root;
                }
            } else {
                Node w = x.p.left;
                if (w.color == red) {
                    w.color = black;
                    x.p.color = red;
                    rotateRight(x.p);
                    w = x.p.left;
                }
                if (w.right.color == black && w.left.color == black) {
                    w.color = red;
                    x = x.p;
                    continue;
                } else if (w.left.color == black) {
                    w.right.color = black;
                    w.color = red;
                    rotateLeft(w);
                    w = x.p.left;
                }
                if (w.left.color == red) {
                    w.color = x.p.color;
                    x.p.color = black;
                    w.left.color = black;
                    rotateRight(x.p);
                    x = root;
                }
            }
        }
        x.color = black;
    }

    public void insertDemo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Add items");

        int item;
        Node node;

        item = scan.nextInt();
        while (item != -999) {
            node = new Node(item);
            insert(node);
            item = scan.nextInt();
        }
        printTree(root);
        System.out.println("Pre order");
        printTreepre(root);
        scan.close();
    }

    public void deleteDemo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Delete items");
        int item;
        Node node;
        item = scan.nextInt();
        node = new Node(item);
        System.out.print("Deleting item " + item);
        if (delete(node)) {
            System.out.print(": deleted!");
        } else {
            System.out.print(": does not exist!");
        }

        System.out.println();
        printTree(root);
        System.out.println("Pre order");
        printTreepre(root);
        scan.close();
    }
}
