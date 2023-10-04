import java.util.Scanner;
import java.util.Collections; 
import java.util.Vector; 
class divisors
{
public static void main(String args[])
{
int n;
Scanner sc=new Scanner(System.in);
n=sc.nextInt();
Vector v=new Vector();
for(int i=1;i*i<=n;i++)//O(root N)
{
if(n%i==0)
{
if(n/i!=i)
{
v.add(i);
int x=n/i;
v.add(x);
} else {
  v.add(i);
}
}
}
Collections.sort(v);
System.out.println(v);
}}


