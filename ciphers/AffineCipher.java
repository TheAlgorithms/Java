import java.io.*;
public class Affine
{
	static int a ,b;
	static boolean ToEncrypt;
	public char Affine(char ch )
	{
		if(ToEncrypt)
			return ((char)('A'+(a*(ch-65) + b)% 26));
		else
	        return ((char)('A'+((26-a)*(ch-65-b)+26*(26-a))% 26));		
	}
	public String ChangeText(String Text)
	{
		String New = "";
		
		for(int i = 0;i< Text.length();i++)
		{
			char ch = Text.charAt(i);
			New = New + Affine(ch);
		}	

        System.out.println("Encrypted Text : " + New + "\n");
        return New;

	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Text ");
		String Text = br.readLine().toUpperCase();
		Affine object = new Affine();

		
		ToEncrypt = true;
        
        System.out.println("Enter the multiplicative value");
        a = Integer.parseInt(br.readLine());
        System.out.println("Enter the additive value");
        b = Integer.parseInt(br.readLine());
        
        ToEncrypt = true;
        String Encrypted = object.ChangeText(Text);

        ToEncrypt = false;
        String Decrypted = object.ChangeText(Encrypted);
    }
}