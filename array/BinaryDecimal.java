package array;

import array.security.ProjectAlgorithmsScanner;

class BinaryDecimal {
    public static void main(String args[]) {
        int n, k, d, s = 0, c = 0;
        n = ProjectAlgorithmsScanner.getInteger();
        k = n;
        while (k != 0) {
            d = k % 10;
            s += d * (int) Math.pow(2, c++);
            k /= 10;
        }
        System.out.println("Binary number:" + n);
        System.out.println("Decimal equivalent:" + s);
    }

}
