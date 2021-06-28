import java.io.*;

import java.net.*;

 

class tserver

{

public static void main(String a[])

{

BufferedReader br2=null,br3=null;

PrintWriter pw=null;

Socket cc;

ServerSocket ss;

String s;

try

{

ss=new ServerSocket(3008);

cc=ss.accept();

br2=new BufferedReader(new InputStreamReader(System.in));

br3=new BufferedReader(new InputStreamReader(cc.getInputStream()));

pw=new PrintWriter(cc.getOutputStream(),true);

while(true)

{

String str2=br3.readLine();

System.out.println(str2);

if(str2.equals("bye"))

break;

s=br2.readLine();

pw.println(s);

 

}

}

catch(Exception e){}

}

}

 
