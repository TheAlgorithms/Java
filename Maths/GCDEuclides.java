package Java.Maths;

public class GCDEuclides {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(gcdEuclides(54, 24)); // Result: 6
        System.out.println(gcdEuclides(-10, 8)); // Result: 2
        System.out.println(gcdEuclides(1024,88)); // Result: 8
    }

     /**
     *
     * @param m the first number
     * @param n the second number
     * @return n
     */

    public static int gcdEuclides(int m, int n) {
        while (m != 0) {
            int t = m;
            m = n % m;
            n = t;
        }
        return Math.abs(n);
    }

}
