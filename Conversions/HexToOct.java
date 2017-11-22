/* Java Program - Convert Hexadecimal to Octal 
Author - Tanmay Joshi*/
		
import java.util.Scanner;

public class HexToOct
{ 
	//Function that takes the Hexadecimal number as input and returns the decimal form. 
    public static int hex2decimal(String s)
    {
             String str = "0123456789ABCDEF";
             s = s.toUpperCase();
             int val = 0;
             for (int i = 0; i < s.length(); i++)
             {
                 char a = s.charAt(i);
                 int n = str.indexOf(a);
                 val = 16*val + n;
             }
             return val;
    } 
	// Main function that gets the hex input from user and converts it into octal.
    public static void main(String args[])
    {
        String hexadecnum;
        int decnum, i=1, j;
        int octnum[] = new int[100];
        Scanner scan = new Scanner(System.in);
		
        System.out.print("Enter Hexadecimal Number : ");
        hexadecnum = scan.nextLine();
        
        // first convert hexadecimal to decimal
        
        decnum = hex2decimal(hexadecnum);
        
        // convert decimal to octal
        
        while(decnum != 0)
        {
            octnum[i++] = decnum%8;
            decnum = decnum/8;
        }
	//Print the octal form of the number.	
        System.out.print("Equivalent Octal Number is :\n");
        for(j=i-1; j>0; j--)
        {
            System.out.print(octnum[j]);
        }
    }
}
