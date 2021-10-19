package Strings;
import java.util.Scanner;
public class convertUpperToLower
{
   
  public static void main()
  {
      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      for(int i=0; i<=s.length(); i++)
      {
          String res = "";
          char x = s.charAt(i);
          if(x>=65 && x<=90) res+=x+32;
          else if(x>=99 && x<=122) res+=x-32;
      }
      System.out.println("Converted String :- " + res);
  }
}
