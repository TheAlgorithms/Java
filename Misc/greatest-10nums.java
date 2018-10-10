import java.util.Scanner;
 
class Factorial
{
   public static void main(String args[])
   {
      int num,max=0;

      for(int i=1;i<=10;i++)
     {

      System.out.println("Enter an integer to calculate it's factorial");
      Scanner in = new Scanner(System.in);
     
      num = in.nextInt();
       //IF NUMBER IS GREATER THAN MAX THEN SWAPING VALUES
       if(num>max)
         max=num;     
 
    }

    System.out.println("GREATEST NO===="+max);

     
   }
}