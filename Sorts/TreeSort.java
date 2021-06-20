package Sorts;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Sorts.SortUtils.*;

/**
 * @author ValeKI (https://github.com/ValeKI)
 * @see SortAlgorithm
 *
 * The tree sort uses a BST (Binary search tree)
 *
 * The binary search tree is a binary tree with the following property.
 *
 * Every node in the left subtree of a node x are less than or equal to x
 * and every node in the right subtree are greater than or equal to x.
 *
 * In my case the node, with the same value, counts how many appers in the sequence.
 */
public class TreeSort implements SortAlgorithm {
    /**
     * a node of the BST
     * @param <T> the T must be comparable in order to allow the order of the tree
     */
    private class Node<T extends Comparable<T>>{
        T value;
        int numOfOccurrences = 1;
        Node<T> right = null;
        Node<T> left = null;

        Node(T value){
            this.value = value;
        }

        void insert(T t){
            if(less(t, value)){
                if(left == null){
                    left = new Node<T>(t);
                }
                else {
                    left.insert(t);
                }
            }else if(greater(t, value)){
                if(right == null){
                    right = new Node<T>(t);
                }
                else {
                    right.insert(t);
                }
            } else {
                numOfOccurrences++;
            }
        }
    }

    /**
     * this method reads via DFS search the order tree
     * @param root is the node which will be print first the left child, after print
     * @param array will be the ordered sequence
     */
    private <T extends Comparable<T>> void inorder(Node<T> root, List<T> array){
        if(root != null){
            inorder(root.left, array);
            for(int j=0; j<root.numOfOccurrences; j++) {
                array.add(root.value);
            }
            inorder(root.right, array);
        }
    }

    /**
     *
     * @param unsorted - an array should be sorted
     * Before all the value of the sequence will be added in the BTS (root).
     * After the tree will be read as an array and return
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if(unsorted.length == 0){
            return unsorted;
        }

        Node<T> root = new Node<>(unsorted[0]);
        for(int i=1; i<unsorted.length; i++){
            root.insert(unsorted[i]);
        }
        List<T> list = new ArrayList<>();
        inorder(root,new ArrayList<T>());
        return list.toArray(unsorted);
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        for(int i=0; i<20; i++){
            integers.add(random.nextInt(200));
        }

        print(integers);

        print(new TreeSort().sort(integers));
    }
}
