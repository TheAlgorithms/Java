import java.lang.String;
import java.util.*;
import java.lang.Math;
class Karatsuba
{
public static void main(String args[])
{
Scanner scan=new Scanner(System.in);
System.out.print("Enter two Integers ");

String a="",b="";
a=scan.nextLine();
int j=0,n=0,f=0;
while(j<a.length())
{
if (a.charAt(j)==' ')
{
f=1;
break;
}
j++;
}
j=0;
if (f==1)
{
String[] s=a.split(" ",2);
a=s[0];
while(s[1].charAt(j)==' ')

{
j++; n++;
}
b=s[1].substring(n,s[1].length());
}
else
b=scan.nextLine();
Karatsuba obj=new Karatsuba();

long startTime=System.nanoTime();
String m=obj.Multi(a,b);
long endTime=System.nanoTime();
j=0; n=0;
while(j<m.length()&&m.charAt(j)=='0')
{
n++; j++;
}
if (n==m.length())
System.out.print("Product= 0");
else
System.out.println("Product= "+m.substring(n,m.length()));
System.out.println("Time "+(endTime-startTime));
}
String Multi(String str1,String str2)
{
int j=0;
String t="0";
int len1=str1.length();
int len2=str2.length();
if (len1>len2)
{
for(j=0;j<(len1-len2);j++)
str2=t+str2;
}
else
{

for(j=0;j<(len2-len1);j++)
str1=t+str1;
}
int len=str1.length()-1;
if (len==0)
{
int a=str1.charAt(0)-48;
int b=str2.charAt(0)-48;
return String.valueOf(a*b);
}
String al=str1.substring(0,(len/2)+1);
String ar=str1.substring((len/2)+1,len+1);
String bl=str2.substring(0,(len/2)+1);
String br=str2.substring((len/2)+1,len+1);
String p1=Multi(al,bl);
String p2=Multi(add(al,ar),add(bl,br));
String p3=Multi(ar,br);
t=add(p1,p3);
p2=sub(p2,t);
t="0";
if (len%2==0)
{
for(j=0;j<len;j++)
p1=p1+t;
for(j=0;j<len/2;j++)
p2=p2+t;
}

else
{
for(j=0;j<=len;j++)
p1=p1+t;
for(j=0;j<(len+1)/2;j++)
p2=p2+t;
}
String s1=add(p1,p2);
return add(p3,s1);
}
String add(String p1,String p2)
{
int len1=p1.length(); int len2=p2.length();
String t="0";
int j=0;
if (len1>len2)
{
for(j=0;j<(len1-len2);j++)
p2=t+p2;
}
else
{
for(j=0;j<(len2-len1);j++)
p1=t+p1;
}
String temp="";
String s="";

int carry=0,num=0;
for(j=p1.length()-1;j>=0;j--)
{
num=p1.charAt(j)+p2.charAt(j)+carry-96;
s=String.valueOf(num%10);
temp=s+temp;
carry=num/10;
}
if (carry!=0)
temp=String.valueOf(carry)+temp;
return temp;
}
String sub(String p1,String p2)
{
String t="0";
int j=0;
int len1=p1.length(); int len2=p2.length();
if (len1>len2)
{
for(j=0;j<(len1-len2);j++)
p2=t+p2;
}
else
{
for(j=0;j<(len2-len1);j++)
p1=t+p1;
}

int b=0,num=0;
String s="";
for(j=p1.length()-1;j>=0;j--)
{
num=p1.charAt(j)-b-p2.charAt(j);
if (num<0)
{
num=10+p1.charAt(j)-b-p2.charAt(j);
b=1;
}
else
b=0;
s=num+s;
}
return s;
}
}
