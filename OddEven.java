import java.util.Scanner;
 
class OddOrEven
{
   public static void main(String args[])
   {
      int a;
      System.out.println("Enter an integer to check if it is odd or even");
      Scanner in = new Scanner(System.in);
      a = in.nextInt();
     
      if (a % 2 == 0)
         System.out.println("The number is even.");
      else
         System.out.println("The number is odd.");
   }
}
