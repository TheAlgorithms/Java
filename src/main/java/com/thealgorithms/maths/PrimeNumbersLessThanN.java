package maths;

import java.util.Arrays;
import java.util.Scanner;

public class PrimeNumbersLessThanN {

    static boolean isPrime(int n) {

        if(n==1)
            return false;
        if(n==2||n==3)
            return true;
        if(n%2==0||n%3==0)
            return false;
        for(int i=5;i*i<=n;i=i+6){
            if(n%i==0 ||n%(i+2)==0)
                return false;
        }
        return true;
    }

    static void printPrime(int num){
          for(int i=2;i<=num;i++) {
              if (isPrime(i))
                  System.out.println("--" + i);
          }
    }

    static void seiveSolution(int n){
        boolean []arr = new boolean[n+1];
        Arrays.fill(arr,true);
        for(int i=2;i<=n;i++){
            if(arr[i]){
                System.out.println(i);
                for(int j=i*i;j<=n;j=j+i){
                     arr[j]=false;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int number =sc.nextInt();
        printPrime(number);
        seiveSolution(number);
    }
}
