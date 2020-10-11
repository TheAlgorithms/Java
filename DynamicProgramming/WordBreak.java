package DynamicProgramming;
import java.util.*;
public class WordBreak {
	public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
            {
                int n;
                n = sc.nextInt();
                ArrayList<String> arr = new ArrayList<String>();
                for(int i = 0;i<n;i++)
                    {
                        String p = sc.next();
                        arr.add(p);
                    }
                String line = sc.next();
                Solution obj = new Solution();  
                System.out.println(obj.wordBreak(line,arr));  
            }
    }
}
class Solution
{
     public static boolean wordBreak1(String s, ArrayList<String> wordDict) {
        HashSet<String> set=new HashSet<>();
        for(String word:wordDict)
            set.add(word);
        if(s.length()==0)
            return true;
        for(int i=0;i<=s.length();i++){
        if(set.contains(s.substring(0,i))&&wordBreak1(s.substring(i,s.length()),wordDict))
                return true;
        }
        return false;
    }
    public static int wordBreak(String A, ArrayList<String> B )
    {
       if(wordBreak1(A,B))
       return 1;
       return 0;
    }
}
