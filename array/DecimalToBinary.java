package array;

import array.security.ProjectAlgorithmsScanner;

class DecimalToBinary {
    public static void main(String args[]) {
        int n, k, s = 0, c = 0, d;
        n = ProjectAlgorithmsScanner.getInteger();
        k = n;
        while (k != 0) {
            d = k % 2;
            s = s + d * (int) Math.pow(10, c++);
            k /= 2;
        }//converting decimal to binary
        System.out.println("Decimal number:" + n);
        System.out.println("Binary equivalent:" + s);
    }
}
