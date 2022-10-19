import java.io.*;
 
public class swapTwoNumbers {
 
    public static void main(String[] args)
    {
        int x = 13;
        int y = 54;
      
      
        System.out.println("Before swapping:"
                           + " x = " + x + ", y = " + y);
      
        x = x + y;
        y = x - y;
        x = x - y;
      
        System.out.println("After swapping:"
                           + " x = " + x + ", y = " + y);
    }
}
