
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Tree root = BuildTree();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int inputX0 = sc.nextInt();
        Point toPrint = nearestRightPoint(root, inputX0);
        System.out.println("X: " + toPrint.x + " Y: " + toPrint.y);
    }

    public static Tree BuildTree() {
        int randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        int randomY = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        Tree root = new Tree(null, null, new Point(randomX, randomY));

        for (int i = 0; i < 1000; i++) {
            randomX = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            randomY = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            root = root.insertPoint(root, new Point(randomX, randomY));
        }

        Tree.print2D(root);

        return root;
    }

    public static Point nearestRightPoint(Tree root, int x0) {
        //Check whether tree is empty
        if(root == null){
           return new Point(0,0);
        }
        else {
            if(root.data.x - x0 > 0){
                // Go left
                Point temp = nearestRightPoint(root.left, x0);
                if(temp.x == 0 && temp.y == 0){
                    return root.data;
                }
                return temp;
            } else {
                // Go right
                return nearestRightPoint(root.right, x0);
            }

        }
    }

}


public class Tree {

    public Tree left;
    public Tree right;
    public Point data;

    public Tree(Point p) {
        this.left = null;
        this.right = null;
        this.data = p;
    }

    public Tree(Tree right, Tree left, Point point) {
        this.left = left;
        this.right = right;
        this.data = point;
    }

    public Tree insertPoint(Tree current, Point value) {
        if (current == null) {
            return new Tree(value);
        }

        if (value.x < current.data.x) {
            current.left = insertPoint(current.left, value);
        } else if (value.x > current.data.x) {
            current.right = insertPoint(current.right, value);
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
        System.out.print(root.data.x + "\n");

        print2DUtil(root.left, space);
    }

    public static void print2D(Tree root)
    {
        print2DUtil(root, 0);
    }
}

public class Point {
    public int x;
    public int y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
