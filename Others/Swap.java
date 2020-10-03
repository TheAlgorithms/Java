import java.util.*;

public class Swap{
	public static void main(String args[]) 
	{
		//Swap two numbers without using a third variable

		Scanner scanner = new Scanner(System.in);
		int number1 = scanner.nextInt();
		int number2 = scanner.nextInt();
		
		System.out.println("The numbers before swapping are a:"+number1+" b:"+number2);

		// We use the resultant of number1+number2 to swap those numbers
		
		number1 = number1+number2;
		number2 = number1 - number2;
		number1 = number1 - number2;
		
		System.out.println("The numbers after swapping are a:"+number1+" b:"+number2);
	}
}