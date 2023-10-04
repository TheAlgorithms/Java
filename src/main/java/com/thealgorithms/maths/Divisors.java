import java.util.Collections; 
import java.util.Vector; 
package com.thealgorithms.maths;
public class divisors
{
public Vector div(int n)
{
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
return v;
}}




