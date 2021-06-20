import java.io.*;
class buff_
{
	public static void main(String arg[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str,str1;
		System.out.println("Enter first name");
		str=br.readLine();
		System.out.println("Enter last name");
		str1=br.readLine();
		System.out.println("-concat-");
		System.out.println(str.concat(str1));
		System.out.println("-uppercase-");
		System.out.println(str.toUpperCase());
		System.out.println("-lowercase-");
		System.out.println(str1.toLowerCase());
		System.out.println("-length-");
		System.out.println(str.length());
		System.out.println("-contain-");	
		System.out.println(str.contains("a"));
		System.out.println("bytecode");
		System.out.println(str.getBytes());
		System.out.println("-replace-");
		System.out.println(str.replace('S', 'x'));
		


		
	}
}
