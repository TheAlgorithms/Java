import java.util.Scanner;
public class Simpson {
    public static double func(double x) {
        // Define the function here
        return x * x * x;
    }

    public static double simpsons(double a, double b, int n) {
        double h = (b - a) / n;
        double s = func(a) + func(b);

        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                s += 2 * func(a + i * h);
            } else {
                s += 4 * func(a + i * h);
            }
        }

        return (h / 3) * s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        int n = scanner.nextInt();
        System.out.println(simpsons(a, b, n));
        scanner.close();
    }
}
