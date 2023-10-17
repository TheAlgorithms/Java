package easy_800;

import java.util.Scanner;

public class Add_Odd_or_Subtract_Even {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t-->0){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int m=0;
            if(a<b){
                int d=b-a;
                m=1;
                if(d%2==0)m++;
            }
            else if(a>b){
                m=1;
                int d=a-b;
                if(d%2==1)m++;
            }
            System.out.println(m);
        }
    }
}
