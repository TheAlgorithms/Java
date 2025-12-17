public class TowerOfHanoi {

    // Recursive function to solve Tower of Hanoi
    // Time complexity : O(2^n)
    // Space complexity:O(n) (Recursion Stack)
    // n               : number of disks
    // src             : source rod
    // helper          : auxiliary rod
    // dest            : destination rod
    public static void towerOfHanoi(int n, char src, char helper, char dest) {

        // Base case: only one disk
        if (n == 1) {
            System.out.println("Move disk " + n + " from " + src + " to " + dest);
            return;
        }

        // Step 1: Move n-1 disks from source to helper
        towerOfHanoi(n - 1, src, dest, helper);

        // Step 2: Move nth disk from source to destination
        System.out.println("Move disk " + n + " from " + src + " to " + dest);

        // Step 3: Move n-1 disks from helper to destination
        towerOfHanoi(n - 1, helper, src, dest);
    }

    public static void main(String[] args) {
        int n = 3;  // Number of disks
        towerOfHanoi(n, 'A', 'B', 'C');
    }
}
