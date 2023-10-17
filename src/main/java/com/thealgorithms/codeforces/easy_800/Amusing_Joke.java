package easy_800;

import java.util.Arrays;
import java.util.Scanner;

public class Amusing_Joke {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s1=sc.next()+sc.next();
        String s2=sc.next();
        char a[]=s1.toCharArray();
        Arrays.sort(a);
        char a2[]=s2.toCharArray();
        Arrays.sort(a2);
        if(Arrays.equals(a,a2)) System.out.println("YES");
        else System.out.println("NO");
    }
}
