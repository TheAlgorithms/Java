import java.io.*;
class stringreverse
{
	String reverseString(String str)
	{
		String reverse=" ";
		if(str.length()==1)
		{
			return str;
		}
		else
		{
			reverse=reverse+str.charAt(str.length()-1)+reverseString(str.substring(0,str.length()-1));
			return reverse;
		}
	}
		public static void main(String args[])
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the string");
			String srr=br.readLine();
			System.out.println("Reverse="+reverseString(srr));
		}
}
		