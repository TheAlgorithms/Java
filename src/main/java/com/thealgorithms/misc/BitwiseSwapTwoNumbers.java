import java.util.Scanner;
public class SwapTwoNumbers 
{  
  static void swapNumbers(int x, int y)  
  {  
    System.out.println("Before swapping");      
    System.out.println("x= " + x + ", y= " + y);  
    x = x ^ y;  
    y = x ^ y;  
    x = x ^ y;  
    System.out.println("After swapping");  
    System.out.println("x= " + x + ", y= " + y);  
  }  
  public static void main(String[] args)   
  {  
    Scanner first = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter first number: ");
    int x = first.nextInt();  // Read user input
    Scanner second = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter second number : ");
    int y = second.nextInt();  // Read user input
    swapNumbers(x,y);  
  }  
}  
