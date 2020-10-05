import java.io.*;

import java.net.*;

 

class tclient

{

public static void main(String a[])

{

BufferedReader br=null,br1=null;

PrintWriter pw=null;

Socket c;

String str;

try

{

c=new Socket("localhost",3008);

br=new BufferedReader(new InputStreamReader(System.in));

br1=new BufferedReader(new InputStreamReader(c.getInputStream()));

pw=new PrintWriter(c.getOutputStream(),true);

while(true)

{

str=br.readLine();

pw.println(str);

String s=br1.readLine();

System.out.println(s);

if(s.equals("bye"))

break;

}

}

catch(Exception e){}

}

}
