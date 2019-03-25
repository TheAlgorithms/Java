import java.util.Scanner;

/**
 * This program will print out the factorial of any non-negative
 * number that you input into it.
 * 
 * @author Marcus
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
          System.out.print("Enter a non-negative integer: ");
          
       //If user does not enter an Integer, we want program to fail gracefully, letting the user know why it terminated   
          try{
            int number = input.nextInt();
            
       //We keep prompting the user until they enter a positive number 
            while(number < 0){
              System.out.println("Your input must be non-negative. Please enter a positive number: ");
              number = input.nextInt();
            }
       //Display the result
 	   System.out.println("The factorial of " + number + " will yield: " + factorial(number));  
           
          }catch(Exception e){
            System.out.println("Error: You did not enter an integer. Program has terminated.");
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
          if(n == 0 || n == 1) return 1;
          return n * factorial(n - 1);
	}
}
