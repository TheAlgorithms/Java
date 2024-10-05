public class LowestSetBit {

    // Method to isolate the lowest set bit
    public int isolateLowestSetBit(int n) {
        if (n == 0) return 0; // Special case for 0
        return n & -n;
    }

    // Method to clear the lowest set bit
    public int clearLowestSetBit(int n) {
        return n & (n - 1);
    }
}
