package DataStructures.Trees;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Main {

    public static void main(String[] args) {
        Tree root = BuildTree();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int inputX0 = sc.nextInt();
        int toPrint = nearestRightKey(root, inputX0);
        System.out.println("Key: " + toPrint);
    }

    public static Tree BuildTree() {
        int randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        Tree root = new Tree(null, null, randomX);

        for (int i = 0; i < 1000; i++) {
            randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            root = root.insertKey(root, randomX);
        }

        Tree.print2D(root);

        return root;
    }

    public static int nearestRightKey(Tree root, int x0) {
        //Check whether tree is empty
        if(root == null){
           return 0;
        }
        else {
            if(root.data - x0 > 0){
                // Go left
                int temp = nearestRightKey(root.left, x0);
                if(temp == 0){
                    return root.data;
                }
                return temp;
            } else {
                // Go right
                return nearestRightKey(root.right, x0);
            }

        }
    }

}


class Tree {

    public Tree left;
    public Tree right;
    public int data;

    public Tree(int x) {
        this.left = null;
        this.right = null;
        this.data = x;
    }

    public Tree(Tree right, Tree left, int x) {
        this.left = left;
        this.right = right;
        this.data = x;
    }

    public Tree insertKey(Tree current, int value) {
        if (current == null) {
            return new Tree(value);
        }

        if (value < current.data) {
            current.left = insertKey(current.left, value);
        } else if (value > current.data) {
            current.right = insertKey(current.right, value);
        }

        return current;

    }

    static final int COUNT = 10;

    static void print2DUtil(Tree root, int space)
    {
        if (root == null)
            return;

        space += COUNT;

        print2DUtil(root.right, space);

        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");

        print2DUtil(root.left, space);
    }

    public static void print2D(Tree root)
    {
        print2DUtil(root, 0);
    }
}
