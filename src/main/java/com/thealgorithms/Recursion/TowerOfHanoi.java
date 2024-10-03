public class TowerOfHanoi {
    
    // Method to solve Tower of Hanoi
    public static void solveTowerOfHanoi(int n, char source, char auxiliary, char target) {
        // Base case: If there is only one disk, move it from source to target
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + target);
            return;
        }
        
        // Move n-1 disks from source to auxiliary, using target as a temporary peg
        solveTowerOfHanoi(n - 1, source, target, auxiliary);
        
        // Move the nth disk from source to target
        System.out.println("Move disk " + n + " from " + source + " to " + target);
        
        // Move the n-1 disks from auxiliary to target, using source as a temporary peg
        solveTowerOfHanoi(n - 1, auxiliary, source, target);
    }

    public static void main(String[] args) {
        int numberOfDisks = 3; // You can change the number of disks here
        solveTowerOfHanoi(numberOfDisks, 'A', 'B', 'C'); // A, B and C are names of rods
    }
}
