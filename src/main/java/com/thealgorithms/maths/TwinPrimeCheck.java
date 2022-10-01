package com.thealgorithms.maths;

import java.util.Scanner;
/**
 *
 * @author Litania Chauke
 */
public class TwinPrimeCheck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the 2 numbers: ");
        final int num1=scan.nextInt();
        scan.nextLine();
        final int num2=scan.nextInt();
        Checker check = new Checker(num1,num2);
        if(check.check()){
            System.out.println("Numbers "+num1+" and "+num2+" are twin prime");
        }else{
            System.out.println("Numbers "+num1+" and "+num2+" are not twin prime");
        }
    }

}
class Checker{
    private int num1,
            num2,
            count1=0,
            count2=0,
            diff;

    public Checker(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    public boolean check(){

        for(int i=2;i<=num1/2;i++){
            if(num1%i==0){
                count1++;
            }
        }
        for(int i=2;i<=num2/2;i++){
            if(num2%i==0){
                count2++;
            }
        }
        diff=Math.abs(num1-num2);

        return count1==0 && count2==0 && diff==2;
    }

}

