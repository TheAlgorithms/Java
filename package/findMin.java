public class findMin {
    package findMin {
        import java.util.*;
        import java.lang.*;
        import java.io.*;
        class findMin {
            public static void main(String args[]) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                int a[] = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextInt();
                }
                int min = a[0];
                for (int i = 1; i < n; i++) {
                    if (a[i] < min) {
                        min = a[i];
                    }
                }
                System.out.println(min);
            }
        }
    }
}
