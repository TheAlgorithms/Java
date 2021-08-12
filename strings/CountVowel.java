package strings


import java.util.Scanner;
public class CountVowel
{
    /** Driver Code */
	public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String in which you want to count the number of vowels");
        String str = sc.nextLine();
        System.out.println("Number of vowels: "+countVowels(str));
	}
    /** Driver Code End*/

    
	public static int countVowels(String str)
    {
        int len = str.length();
        int cntVowels=0;
        if(len==0)
        {
            return cntVowels;
        }

		String strLowerCase = str.toLowerCase();

        for(int i=0;i<len;i++)
        {
            char c = strLowerCase.charAt(i);
            if(c =='a' || c =='e' || c =='i' || c =='o' || c =='u')
            {
                cntVowels++;
            }
        }
        return cntVowels;
    }
}
