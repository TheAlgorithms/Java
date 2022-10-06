

import java.util.Scanner;
public class DateToDay {
    public static int get_last_two(int a){
        int n=a%100;
        
      
        
        return n;
    }
    public static int division(int n){
    	
    	
    	
        int r=n/4;
        return r;
    }
    public static int Month(String m) {
        int a = 0;
        if (m.equals("January"))
        {
            a=0;
            return a;
        }
        else if(m.equals("February"))
        {
            a=3;
            return a;
        }
        else if(m.equals("March"))
        {
            a=3;
            return a;
        }
        else if(m.equals("April"))
        {
            a=6;
            return a;
        }
        else if(m.equals("May"))
        {
            a=1;
            return a;
        }
        else if(m.equals("June"))
        {
            a=4;
            return a;
        }
        else if(m.equals("July"))
        {
            a=6;
            return a;
        }
        else if(m.equals("August"))
        {
            a=2;
            return a;
        }
        else if(m.equals("September"))
        {
            a=5;
            return a;
        }
        else if(m.equals("October"))
        {
            a=0;
            return a;
        }
        else if(m.equals("November"))
        {
            a=3;
            return a;
        }
        else if(m.equals("December"))
        {
            a=5;
            return a;
        }

        return a;
    }
    public static int year(int y){
        if(y>=1600 && y<=1699)
            return 6;
        else if(y>=1700 && y<=1799)
            return 4;
        else if(y>=1800 && y<=1899)
            return 2;
        else if(y>=1900 && y<=1999)
            return 0;
        else if(y>=2000 && y<=2099)
            return 6;
        else
        {
            System.out.println("Not Possible to figure out.");
        }

        return y;
    }

    public static void main(String[] args) {
        System.out.print("Enter the year:");
        Scanner sc=new Scanner(System.in);
        int y=sc.nextInt();
        System.out.print("Enter the month:(First letter should be in Capital):");
        String m=sc.next();
        System.out.print("Enter the date in digit(Only the digit,no zeros at front):");
        int c=sc.nextInt();
        int a = get_last_two(y);
        int b=division(a);
        int d=Month(m);
        int e=year(y);
        int total=a+b+c+d+e;
        int rem=total%7;
        sc.close();
        if(rem==0)
            System.out.println("It's Sunday.");
        else if(rem==1)
            System.out.println("It's Monday.");
        else if(rem==2)
            System.out.println("It's Tuesday.");
        else if(rem==3)
            System.out.println("It's Wednesday.");
        else if (rem==4)
            System.out.println("It's Thursday.");
        else if(rem==5)
            System.out.println("It's Friday.");
        else if(rem==6)
            System.out.println("It's Saturday.");
    }
}