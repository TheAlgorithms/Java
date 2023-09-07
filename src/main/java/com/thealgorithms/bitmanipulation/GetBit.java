public class GetBit {
    public static int getBit(int num, int get) {
        return (num>>get) & 1;
    }
}
