import java.util.Scanner;
public class abundant_number_or_not
{	
	public static void main(String[] args)
	{
		//scanner class declaration
		Scanner sc = new Scanner(System.in);
		//input from user
		System.out.print("Enter a number : ");				
		int number = sc.nextInt();
		//declare a variable to store sum of factors of the number
		int sum = 0;
		//loop for calculating sum of factors of the number
		for(int i = 1 ; i < number ; i++)
		{
			if(number % i == 0)
				sum = sum + i;
		}
		//condition for checking whether the sum is greater than number or not
		if(sum > number)
		        System.out.println("Abundant Number");
		else
		        System.out.println("Not an Abundant Number");
		//closing scanner class(not compulsory, but good practice)
		sc.close();													
	}
}