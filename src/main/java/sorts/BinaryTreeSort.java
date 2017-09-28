package sorts;

import misc.Node;

public class BinaryTreeSort {

    public Node root;

    public BinaryTreeSort(Object x) {
        root = new Node(x);
    }

    public Node insert(Node node, Integer value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < (Integer) node.anElement) {
            node.less = insert(node.less, value);
        } else {
            node.greater = insert(node.greater, value);
        }
        return node;
    }

    public Node decimalInsert(Node node, Double value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < (Double) node.anElement) {
            node.less = decimalInsert(node.less, value);
        } else {
            node.greater = decimalInsert(node.greater, value);
        }
        return node;
    }

    public void treeSort(Node node) {
        if (node != null) {
            treeSort(node.less);
            System.out.print(((Object) node.anElement) + ", ");
            treeSort(node.greater);
        }
    }
}

