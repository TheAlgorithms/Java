package easy_800;

import java.util.Scanner;

public class Anton_and_Danik {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String s=sc.next();
        int a=0,d=0;
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch=='A')a++;
            else d++;
        }
        if(a>d)System.out.println("Anton");
        else if(d>a)System.out.println("Danik");
        else System.out.println("Friendship");
    }
}
