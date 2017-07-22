import java.util.Scanner;

class disariumcheck
{
public static void main(String args[])throws Exception
{
try
{
Scanner sc=new Scanner(System.in);
int n,d=0,sum=0,c=0,a;
n=sc.nextInt();
a=n;  
while(n>0)
{
d=n%10; //Extracting last digit one by one
c++;  //Incresing the count
n=n/10;
}
n=a;  
while(a>0)
{
d=a%10;
sum=sum+(int)Math.pow(d,c); 
c--; 
a=a/10;
}
if(sum==n) 
System.out.print("It is a Disarium number");
else
 System.out.print("It is not a Disarium number");
 }
 catch(Exception e)
 {
 	return;
 }
 }
 }