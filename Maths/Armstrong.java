import java.util.*;
class armstrong
{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n,a,r,s=0;
n=sc.nextInt();
a=n;
while(a!=0)
{
r=a%10;
s=s+r*r*r;
a=a/10;
}
if(s==n)
System.out.println("Armstrong number");
else
System.out.println("Not Armstrong number");
}
}
