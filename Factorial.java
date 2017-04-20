import java.util.Scanner;

/**
 * This program will print out the factorial of any non-negative
 * number that you input into it.
 * 
 * @author Unknown
 *
 */
public class Factorial{

	/**
	 * The main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		//Prompt user to enter integer
		System.out.print("Enter a non-negative integer: ");
		
		//Proceed with factorial calculation only if inputted number is not negative
		if(input.hasNextInt()){
			int number = input.nextInt();
			if (number < 0){
				System.out.print("Cannot execute. Please enter a non-negative integer: ");
				number = input.nextInt();
		} else {
			//Output of factorial for any non-negative number
			System.out.println("The factorial of "+number+" will yield: "+factorial(number));
		}
		}
		input.close();
	}

	/**
	 * Recursive Factorial Method
	 * 
	 * @param n The number to factorial
	 * @return The factorial of the number
	 */
	public static long factorial(int n){

		if (n==0){
			return 1;
		} else if (n==1){
			return 1;
		} else {
			return n * factorial(n-1);
		}

	}
}