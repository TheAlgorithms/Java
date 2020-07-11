package Others;

import java.util.Scanner;

class TowerOfHanoi {
    public static void shift(int n, String startPole, String intermediatePole, String endPole) {
        // if n becomes zero the program returns thus ending the loop.
        if (n != 0) {
            // Shift function is called in recursion for swapping the n-1 disc from the startPole to the intermediatePole
            shift(n - 1, startPole, endPole, intermediatePole);
            System.out.format("Move %d from %s to %s\n", n, startPole, endPole); // Result Printing
            // Shift function is called in recursion for swapping the n-1 disc from the intermediatePole to the endPole
            shift(n - 1, intermediatePole, startPole, endPole);
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter number of discs on Pole 1: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfDiscs = scanner.nextInt(); //input of number of discs on pole 1
        shift(numberOfDiscs, "Pole1", "Pole2", "Pole3"); //Shift function called
        scanner.close();
    }
}
