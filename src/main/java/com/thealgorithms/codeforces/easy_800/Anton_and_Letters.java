package easy_800;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Anton_and_Letters {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        s=s.substring(1);
        s=s.substring(0,s.length()-1);
        String a[]=s.split(",");
        Set<String > ss=new HashSet<>();
        for(String b:a){
            b=b.trim();
            if(!b.equals(""))ss.add(b);
        }
        System.out.println(ss.size());
    }
}
