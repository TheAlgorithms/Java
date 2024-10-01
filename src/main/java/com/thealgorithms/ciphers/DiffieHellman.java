import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {
    private DiffieHellman() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Hello User! \nEnter your name:");
        String name = read.nextLine();
        read.nextLine();
        System.out.println("Welcome " + name + "!");

        BigInteger n;
        BigInteger g;
        BigInteger x;
        BigInteger y;
        BigInteger k1;
        BigInteger k2;
        BigInteger a;
        BigInteger b;

        System.out.println("Enter two prime numbers: ");
        n = new BigInteger(read.next());
        g = new BigInteger(read.next());

        System.out.println("Person A : Enter your secret number");
        x = new BigInteger(read.next());
        a = g.modPow(x, n);

        System.out.println("Person B : Enter your secret number");
        y = new BigInteger(read.next());
        b = g.modPow(y, n);

        k1 = b.modPow(x, n);
        k2 = a.modPow(y, n);

        System.out.println("A's secret key: " + k1);
        System.out.println("B's secret key: " + k2);
    }
}
