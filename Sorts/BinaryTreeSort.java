package Sorts;

import java.util.Arrays;

public class BinaryTreeSort {

    public class Node {
        public Object anElement;
        public Node less;
        public Node greater;

        public Node(Object theElement) {
            this(theElement, null, null); //an empty node at the end will be by itself with no children, therefore the other 2 parameters are always null
            //obviously the node can still be a child of other elements
        }

        public Node(Object currentElement, Node lessSide, Node greaterSide) {
            anElement = currentElement;
            this.less = lessSide;
            this.greater = greaterSide;
        }
    }

    public Node root;

    public BinaryTreeSort(Object x) {
        root = new Node(x);
    }//end TreeSort constructor

    public Node insert(Node node, Integer x) {
        if (node == null) {
            return new Node(x);
        }

        if (x < (Integer) node.anElement) {
            node.less = insert(node.less, x);
        } else {
            node.greater = insert(node.greater, x);
        }
        return node;
    }

    public Node decimalInsert(Node node, Double x) {
        if (node == null) {
            return new Node(x);
        }

        if (x < (Double) node.anElement) {
            node.less = decimalInsert(node.less, x);
        } else {
            node.greater = decimalInsert(node.greater, x);
        }
        return node;
    }

    public void treeSort(Node node) {
        if (node != null) {
            treeSort(node.less);
            System.out.print(node.anElement + ", ");
            treeSort(node.greater);
        }
    }


    public static void main(String args[]) {
        int[] intArray = {12, 40, 9, 3, 19, 74, 7, 31, 23, 54, 26, 81, 12};
        BinaryTreeSort ts = new BinaryTreeSort(new Integer(intArray[0]));

        //sorts every index of the list one at a time
        for (int i = 1; i < intArray.length; i++) {
            //builds on the tree from a root node
            ts.insert(ts.root, new Integer(intArray[i]));
        }

        System.out.print("Integer Array Sorted in Increasing Order: ");
        ts.treeSort(ts.root);
        System.out.println(); //To sort a test array of integers

        Double[] decimalArray = {8.2, 1.5, 3.14159265, 9.3, 5.1, 4.8, 2.6};
        BinaryTreeSort dts = new BinaryTreeSort(new Double(decimalArray[0]).doubleValue());

        //sorts every index of the list one at a time
        for (int i = 1; i < decimalArray.length; i++) {
            //builds on the tree from a root node
            dts.decimalInsert(dts.root, new Double(decimalArray[i]).doubleValue());
        }

        System.out.print("Decimal Array, Sorted in Increasing Order: ");
        dts.treeSort(dts.root);
        System.out.println();

        String[] stringArray = {"c", "a", "e", "b", "d", "dd", "da", "zz", "AA", "aa", "aB", "Hb", "Z"};
        int last = stringArray.length;
        //Uses an imported arrays method to automatically alphabetize
        Arrays.sort(stringArray);
        System.out.print("String Array Sorted in Alphabetical Order: ");
        ts.insert(ts.root, last);
        for (String aStringArray : stringArray) {
            System.out.print(aStringArray + "\t");
            //To sort a test array of strings hard coded in the main method
            //Please Note that Capital letters always come before lower case
            //I tried to make the test array show its behavior clearly
        }
    }
}