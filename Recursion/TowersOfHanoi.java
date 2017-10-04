/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

import java.util.Stack;

public class TowersOfHanoi {

    /**
     * This method implements the Towers of hanoi algorithm using recursion
     *
     * @param n The number of disks in the initial tower
     * @param from A stack representing the initial tower
     * @param to A stack representing the final tower where disks needs to be moved
     * @param temp A stack representing a temprary tower
     **/

    public static void TOH(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> temp) {

        if (n == 1) {
            to.push(from.pop());
        }
        else {
            TOH(n-1, from, temp, to);
            TOH(1, from, to, temp);
            TOH(n-1, temp, to, from);
        }

    }

    // Driver Program
    public static void main(String[] args) {

        int n = 5;

        Stack<Integer> fromTower = new Stack<>();
        Stack<Integer> tempTower = new Stack<>();
        Stack<Integer> toTower = new Stack<>();

        // Order of disks in initial tower => 5 4 3 2 1
        for (int i=n;i>0;i--) {
            fromTower.push(i);
        }

        TOH(n, fromTower, toTower, tempTower);

        // Order of disks in final tower => 5 4 3 2 1
        while (!toTower.isEmpty()) {
            System.out.println(toTower.pop());
        }
    }
}
