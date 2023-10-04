package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* The following class implements a vertical order traversal
in a tree from top to bottom and left to right, so for a tree :
           1
          / \
         2   3
        / \   \
       4   5   6
        \     / \
         7   8   10
          \
           9
 the sequence will be :
 4 2 7 1 5 9 3 8 6 10
 */
public class VerticalOrderTraversal {

    /*Function that receives a root Node and prints the tree
        in Vertical Order.*/
    public static ArrayList<Integer> verticalTraversal(BinaryTree.Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        /*Queue to store the Nodes.*/
        Queue<BinaryTree.Node> queue = new LinkedList<>();

        /*Queue to store the index of particular vertical
                 column of a tree , with root at 0, Nodes on left
                 with negative index and Nodes on right with positive
                 index. */
        Queue<Integer> index = new LinkedList<>();

        /*Map of Integer and ArrayList to store all the
                 elements in a particular index in a single arrayList
                 that will have a key equal to the index itself. */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        /* min and max stores leftmost and right most index to
                 later print the tree in vertical fashion.*/
        int max = 0, min = 0;
        queue.offer(root);
        index.offer(0);

        while (!queue.isEmpty()) {
            if (queue.peek().left != null) {
                /*Adding the left Node if it is not null
                                and its index by subtracting 1 from it's
                                parent's index*/
                queue.offer(queue.peek().left);
                index.offer(index.peek() - 1);
            }
            if (queue.peek().right != null) {
                /*Adding the right Node if it is not null
                                and its index by adding 1 from it's
                                parent's index*/
                queue.offer(queue.peek().right);
                index.offer(index.peek() + 1);
            }
            /*If the map does not contains the index a new
                         ArrayList is created with the index as key.*/
            if (!map.containsKey(index.peek())) {
                ArrayList<Integer> a = new ArrayList<>();
                map.put(index.peek(), a);
            }
            /*For a index, corresponding Node data is added
                         to the respective ArrayList present at that
                         index. */
            map.get(index.peek()).add(queue.peek().data);
            max = Math.max(max, index.peek());
            min = Math.min(min, index.peek());
            /*The Node and its index are removed
                         from their respective queues.*/
            index.poll();
            queue.poll();
        }
        /*Finally map data is printed here which has keys
                from min to max. Each ArrayList represents a
                vertical column that is added in ans ArrayList.*/
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            ans.addAll(map.get(i));
        }
        return ans;
    }
}
