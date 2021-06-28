import java.util.Random;
import java.util.Scanner;

//contributed by @nitinsultania

public class AffineCipher {

    static String encrypt(String pt,int k1,int k2)
    {
        pt=pt.toUpperCase();
        pt=pt.replaceAll(" ","");
        StringBuffer ct=new StringBuffer(pt);

        for (int i=0;i<pt.length();i++)
        {
            int temp=ct.charAt(i)-65;
            ct.replace(i,i+1, String.valueOf((char) ((temp * k1 + k2) % 26 + 65)));
        }
        return ct.toString();
    }

    static String decrypt(String ct,int k1,int k2)
    {
        StringBuffer pt=new StringBuffer(ct);
        int inv=-1;
        for (int i=0;i<26;i++)
        {
            int flag=(k1*i)%26;
            if(flag==1)
            {
                inv=i;
                break;
            }
        }
        for (int i=0;i<ct.length();i++)
        {
            int temp=ct.charAt(i)-65;
            pt.replace(i,i+1, String.valueOf((char) ((((temp - k2) % 26 + 26) % 26 * inv) % 26 + 65)));
        }
        return pt.toString();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Plain Text");
        String pt=sc.nextLine();
        int k1=17;
        int k2=20;
        String ct=encrypt(pt,k1,k2);
        System.out.println("Encrypted Text: "+ct);
        String dt=decrypt(ct,k1,k2);
        System.out.println("Decrypted Text: "+dt);
    }
}
