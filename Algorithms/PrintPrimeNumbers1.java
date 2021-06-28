import java.util.Scanner;
public class PrintPrimeNumbers1 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		int i, number, count; 
		int n=sc.nextInt();
		System.out.println(" Prime Numbers from 1 to 100 are : ");	
		for(number = 1; number <= n; number++)
		{
			count = 0;
		    for (i = 2; i <= number/2; i++)
		    {
		    	if(number % i == 0)
		    	{
		    		count++;
		    		break;
		    	}
		    }
		    if(count == 0 && number != 1 )
		    {
		    	System.out.print(number + " ");
		    }  
		}
	}
}