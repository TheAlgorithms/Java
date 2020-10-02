
public class Factorial {

    /* Driver Code */
    public static void main(String[] args) {
        assert factorial(0) == 1;
        assert factorial(1) == 1;
        assert factorial(5) == 120;
        assert factorial(10) == 3628800;
    }

    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static int factorial(int n){
        int[] arr = new int[1001];
        arr[0] = 1;
        if(n >= 0){
            if(n == 0){
                return 1;
            }
            for(int i = 1 ; i <= n ; i ++){
                arr[i] = i * factorial(i-1);
            }
        }
        return arr[n];
    }
}
