package Others;

//Armstrong number is a number whose sum of the cube of the digits results in the original number.
//For example, take num = 153, the individual didgits are:1, 5 and 3 so the their cubes are: 1, 125, 27.
//The sum of those cubes will be : 1+125+27 = 153, so the number 153 is an armstrong number. 

import java.util.Scanner;//Scanner class: Util package

public class Armstrong{

    static Boolean checkArmstrong(int n){   //static function is a function whose name can be addressed anywhere without any object.
        int m=0, d, s=0;
        m=n;
        while(n!=0){
            d=n%10;
            s=s+(d*d*d);        // Checking block with while(entry-controlled loop)
            n=n/10;
        }
        if(s==m)
            return true;
        else                        //returning either True or False
            return false;

    } 
    public static void main(String args[]){         //Main Definition

        
        Scanner sc=new Scanner(System.in);          //Scanner declaration
        int t=sc.nextInt();
        for(int i=0;i<t;i++){                       //The number of times a loop will run according to the no. of Test Cases.
            int n=sc.nextInt();

            boolean c=checkArmstrong(n);            //Caller definition
            if(c)
                System.out.println(n+" Is an Armstrong Number!");
            else
                System.out.println(n+" Is not an Armstrong Number!");
        }   //end of for loop

    }   //end of main
}       //end of class