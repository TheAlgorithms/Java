package com.thealgorithms.maths;
//if the sum of its power of the positions from left to right is equal to the number.
//Example:
//1^1 + 3^2 + 5^3 = 1 + 9 + 125 = 135
 import java.util.*;

public class Disarium {
    public static void main(String args[])
 {
Scanner sc = new Scanner(System.in);
System.out.println("Enter the number");
int n=sc.nextInt();
sc.close();
int digits=0;
int m=n;
while(m>0){
    digits=digits+1;
    m=m/10;
}
int sum=0;
int temp=n;
while(temp>0){
    int rem=temp%10;
    sum=sum+(int)Math.pow(rem,digits);
    digits=digits-1;
    temp=temp/10;

 }
if(sum==n){
    System.out.println(n+" is a Disarium number");
}
else{
    System.out.println(n+" is not a Disarium number");
}
}
}

 
 