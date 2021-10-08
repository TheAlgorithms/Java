//Program to check a number is Harshad or not for Decimal Numbers
import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n,temp;
	    //Declaring the sum variable to add the sum of the digits
	    int sum=0;
		System.out.println("Enter the Number to check it is a Harshad Number or not\n");
		//Accepting the number to check it's Harshad No.
		n=sc.nextInt();
		//Assigning the number to a temp variable to be used
		temp=n;
		
		while(temp!=0){
		    sum=sum+(temp%10);
		    temp=temp/10;
		}
		//if the given number when divide by sum leaves a remainder zero then 
		// sum of the digit (i.e var sum) divides the number
		// It is a Harshad Number.
		if((n%sum)==0){
		    System.out.println("It is a Harshad Number\n");
		}
		//It is not a Harshad number
		else{
		    System.out.println("It is not a Harshad Number\n");
		}
	}
}
