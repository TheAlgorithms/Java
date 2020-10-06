 
//WRITE A JAVA PROGRAM TO CHECK IF A GIVEN NUMBER IS A SPECIAL NUMBER

//Ex : 145 [1! + 4! + 5! = 145]

//Integer n
//Special_Number()     --> Constructor to init n as 0
//Special_Number(int)  --> Constructor to init n as the given input
//void isSpecial()     --> Check and display if n is a SPECIAL NUMBER

import java.util.*;

public class SpecialNumber {
    //Global variable n
    int n;
    
    //Default constructor to init n as 0
    Special_Number(){
        n=0;
    }
    
    //Parameterized constructor to init n as the input value
    Special_Number(int x){
        n=x;
    }
    
    //function to check if the given number is SPECIAL
    void isSpecial(){
        //s to store sum of factorial of each digit in n
        int s = 0;
        //Loop to extract each digit of n
        for(int b=n;b>0;b/=10){
            //f to store the factorial
            int f = 1;
            //Loop to find the factorial of the extracted digit from n
            for(int i=1;i<=b%10;i++){
                f*=i;
            }
            //Add factorial to sum
            s+=f;
        }
        //Check if sum of factorials is equal to the original number
        if(s==n){
            System.out.println(n+" is a SPECIAL NUMBER");
        }else{
            System.out.println(n+" aint a SPECIAL NUMBER");
        }
    }
    
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to check for");
        int in = sc.nextInt();//Take input
        
        //Create object of the class
        Special_Number sn = new Special_Number(in);
        //Call isSpecial from the class object
        sn.isSpecial();
    }
}

