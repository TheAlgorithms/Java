import java.util.Scanner;
public class Trapezoidal {
    public static double func(double x) {
        // Define the function here
        return x * x * x;
    }

    public static double trapezoidal(double a, double b, int n) {
        double h = (b - a) / n;
        double s = func(a) + func(b);

        for (int i = 1; i < n; i++) {
            s += 2 * func(a + i * h);
        }

        return (h / 2) * s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        int n = scanner.nextInt();
        System.out.println(trapezoidal(a, b, n));
        scanner.close();
    }
}
