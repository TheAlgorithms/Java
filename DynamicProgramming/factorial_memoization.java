// Factorial of a number using memoization
public class Main
{
    static int result[] = new int[10];
    
    public static int factorial(int num){
        if(num < 0){
            System.out.println("Number should not be negative.");
            return(0);
            }
        if(result[num] != -1)
            return result[num];
        else{
            result[num] = num * factorial(num - 1);
            // uncomment the following to see how recalculations are avoided
            // System.out.println(result)
            return(result[num]);
            }
    }
    public static void main(String[] args) {
        for(int i=0; i<=9; i++){
            result[i]=-1;
        }
        result[0] = 1;
        result[1] = 1;
        System.out.println(factorial(5));
    }
}
