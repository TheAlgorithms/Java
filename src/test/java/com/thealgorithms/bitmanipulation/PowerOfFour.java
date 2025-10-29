package bitmanipulation;

public class PowerOfFour {
    public static boolean isPowerOfFour(int n) {
        // A power of 4 has only one bit set and that bit is at an even position
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        int num = 64; // change to test other numbers
        if (isPowerOfFour(num))
            System.out.println(num + " is a power of 4.");
        else
            System.out.println(num + " is NOT a power of 4.");
    }
}
