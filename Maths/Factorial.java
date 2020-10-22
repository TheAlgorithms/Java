import java.util.*;
class factorial
{
public int fact(long n)
{
long f=1;
for(long i=1;i<=n;i++)
f=f*i;
return f;
}
public static void main(String args[])
{
factorial obj=new factorial();
int a[]={1,4,7,3,10};
for(j=0;j<5;j++)
{
if(a[j]<0)
System.out.println("Number is negative");
else if(a[j]==0)
System.out.println("1");
else
{
long a=obj.fact(a[j]);
System.out.println(a);
}
}
}
}
