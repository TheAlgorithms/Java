package practice;

public class Problem21 {
    public static void main(String args[]) {
        var d = new int[10001];
        long sum = 0;
        for (var i = 2; i <= 10000; i++) {
            d[i] = divisorSum(i);
        }
        for (var a = 2; a <= 10000; a++) {
            if (d[a] <= 10000 && d[a] > a) {
                int b = d[d[a]];
                if (b == a) {

                    sum = sum + a + d[a];

                }
            }

        }
        System.out.println(sum);


    }
    private static int divisorSum(int n) {
        var sum = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i != (n / i))
                    sum += (n / i);
            }
        }
        return sum;
    }
}