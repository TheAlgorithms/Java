// https://en.wikipedia.org/wiki/Tower_of_Hanoi
/**
 * TowerOfHanoi.java
 *
 * This program demonstrates the recursive solution for the Tower of Hanoi problem.
 * It prints the sequence of moves required to move n disks from source rod to target rod.
 */
public class TowerOfHanoi {

    /**
     * Solves the Tower of Hanoi problem recursively.
     *
     * @param n    Number of disks
     * @param from Source rod
     * @param to   Target rod
     * @param aux  Auxiliary rod
     */
    public static void solveTowerOfHanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }

        // Move n-1 disks from source to auxiliary
        solveTowerOfHanoi(n - 1, from, aux, to);

        // Move the nth disk from source to target
        System.out.println("Move disk " + n + " from " + from + " to " + to);

        // Move the n-1 disks from auxiliary to target
        solveTowerOfHanoi(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        int numberOfDisks = 3; // You can change this to test with more disks
        System.out.println("Tower of Hanoi solution for " + numberOfDisks + " disks:");
        solveTowerOfHanoi(numberOfDisks, 'A', 'C', 'B');
    }
}
