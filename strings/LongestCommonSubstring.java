
import java.util.Scanner;
import java.util.StringTokenizer;
class LongestSubstring
{
    
    public static String find(String b[])
    {
        String longest=b[0];
        int len=0;
        for(int i=1;i<b.length;i++)
        {
            for(int j=b[i].length();j>0;j--)
            {
                for(int k=0;k<=b[i].length()-j;k++)
                {
                    if(longest.contains(b[i].substring(k,k+j)) && (b[i].substring(k,k+j).length()>len))
                    {
                        longest=b[i].substring(k,k+j);
                        len=longest.length();
                    }
                }
                
            }
                
            
        }
        return longest;
                
    }
    public static void main(String [] args)
    {
        String a[]={"Sadness","Sadful","Sadist"};
        String b[]={"ABABABA","ABAABA"};
        System.out.println(find(a));
        System.out.println(find(b));
    }
}