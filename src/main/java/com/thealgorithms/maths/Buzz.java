/** 
A number is said to be buzz if it is divisible by 7 or its last digit is divisible by 7.
*/
public class Buzz
{
public static void main(int n) // Let the number be 27
{
if(n%7==0 || n%10==7) // (27%7==0) is false but (27%10==7) is true
System.out.println(n+"is Buzz.");
else
System.out.println(n+"is not Buzz.");
}
}

/**
Guide:- https://www.javatpoint.com/buzz-number-java
*/
