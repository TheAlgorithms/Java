//program to find the unrepeated elements in the array using the Hashset..

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class hashStringcount {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
    
         HashSet<String> unique=new HashSet<>();
         HashSet<String>repeated=new HashSet<>();
         for(int i=0;i<n;i++)
         {
            String str=sc.next();
            if(unique.contains(str))
            {
                repeated.add(str);
            }
            else
            {
                unique.add(str);
            }
         } 
         
         unique.removeAll(repeated);
         String result[]=new String[unique.size()];
         unique.toArray(result);
         int loc=sc.nextInt();
         System.out.println(result[loc-1]);

    }
}
