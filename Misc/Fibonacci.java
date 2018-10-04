import java.util.Scanner;

public class Fibonacci {
    public static int[] fibonacci(int n) {
        if (n <= 0)
            return new int[]{};
        else if (n == 1)
            return new int[]{1};
        else if (n == 2)
            return new int[]{1, 1};
        else if (n > 46)
            throw new java.lang.Error("Fibonacci number is too large.");

        int [] sequence = new int[n];
        sequence[0] = sequence[1] = 1;
        for (int i = 2; i < n; i++)
            sequence[i] += sequence[i - 1] + sequence[i - 2];

        return sequence;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the quantity of fibonacci numbers");
        int n = in.nextInt();
        int[] sequence = fibonacci(n);
        for (int i = 0; i < n; i++)
            System.out.print(sequence[i] + " ");
    }
}
