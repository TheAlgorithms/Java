public class ClearBit {
    public static int clearBit(int num, int clear) {
        int mask = ~(1 << clear);
        return num & mask;
    }
}
