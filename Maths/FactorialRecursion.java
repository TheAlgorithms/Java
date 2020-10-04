package Maths;

public class FactorialRecursion {
    
    //recursion method for Factorial
	public static int factorial(int n) {
		if(n<1) 
				return 1;
		else
				return n*factorial(n-1);
	}

   /*Driver Code*/
	public static void main(String[] args) {
		//user input
		Scanner sc = new Scanner(System.in);
		//calling factorial
		int input = factorial(sc.nextInt());
		
		//output
		System.out.println("Factorial : " + input);
    }
}
