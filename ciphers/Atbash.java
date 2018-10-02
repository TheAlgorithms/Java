package ciphers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Atbash                // Performing the Atbash cipher
{
    public static String encrypt_decrypt(String inp)    //encyption and decryption follow the same process.
    {
        int i;
        inp=inp.toUpperCase();
        String output="";
        for(i=0;i<inp.length();i++)
        if(inp.charAt(i)<=90 && inp.charAt(i)>=65)      //Performing the switching for alphabets only
        output+=((char)(155-(int)inp.charAt(i))+"");
        else output+=inp.charAt(i);                        // Other characters are directly added
        return output;
    }
    public static void main(String[] args)throws IOException { 
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the sentence to be encrypted: ");
        String input=br.readLine();
        System.out.println("Converted: "+encrypt_decrypt(input));
    }
}