import java.util.Scanner;  
public class MagicNumber
{  
public static void main(String args[])  
{  
int n, remainder = 1, number, sum = 0;  
//creating a constructor of the Scanner class  
Scanner sc = new Scanner(System.in);  
System.out.print("Enter a number you want to check: ");  
//reading an integer form the user  
n = sc.nextInt();  
//assigning the entered number in the variable num  
number = n;  
//outer while loop  
while (number > 9)               //while(number > 0 || sum > 9)  
{  
//inner while loop      
while (number > 0)  
{  
//determines the remainder      
remainder = number % 10;   
sum = sum + remainder;  
//divides the number by 10 and removes the last digit of the number  
number = number / 10;     
}  
number = sum;  
sum = 0;  
}  
if (number == 1)  
{  
System.out.println("The given number is a magic number.");  
}  
else  
{  
System.out.println("The given number is not a magic number.");  
}  
}  
}  
