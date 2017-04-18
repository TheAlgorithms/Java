import java.util.Scanner;

/**
 * You enter a string into this program, and it will return how
 * many words were in that particular string
 * 
 * @author Unknown
 *
 */
class CountTheWords
{
	/**
	 * The main method
	 * 
	 * @param args Command line arguments
	 */
    public static void main(String args[])
    {
        System.out.println("Enter the string");
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
         int count = 1;
         for (int i = 0; i < s.length()-1; i++)
        {
            if((s.charAt(i) == ' ') && (s.charAt(i+1) != ' '))
            {
                 count++;
            }
        }
        System.out.println("Number of words in the string = "+count);
    }
}