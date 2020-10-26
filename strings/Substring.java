import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class Substring
{
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    String st = sc.next();
    int start = sc.nextInt();
    int end = sc.nextInt();
    System.out.println(st.substring(start, end));
  }
}
