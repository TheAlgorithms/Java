import java.util.Scanner;
/**
 * Converts any Binary Number to a HexaDecimal Number
 *
 * @author Pritam Dash
 */
class BinToHexa
{
    /**
     * This method converts any binary number to a Hexadecimal number.
     *
     * @param st The binary number
     * @param l Takes 1 for the whole-number part and 0 for the fractional part of the Double type binary number
     * @return The Hexadecimal number
     */

    static String convert(String st, int l)
    {
        String st1="",st2="";
        int c=0;
        if(l==1)
        {
            for(int i=st.length()-1; i>=0; i--)
            {
                char ch=st.charAt(i);
                c++;
                st1=ch+st1;
                if(c%4==0 || i==0)
                {
                    st2=convert2(Integer.parseInt(st1))+st2;
                    st1="";
                }
            }
        }
        else if(l==0)
        {
            for(int i=0; i<st.length(); i++)
            {
                char ch=st.charAt(i);
                st1+=ch;
                if((i+1)%4==0)
                {
                    st2+=convert2(Integer.parseInt(st1));
                    c+=4;
                    st1="";
                }
                else if((i+1)%4!=0 && i==st.length()-1)
                {
                    c+=4;
                    st2+=convert2(Integer.parseInt(st1)*(int)Math.pow(10,(c-st.length())));
                    st1="";
                }
            }
        }
        return st2;
    }
    
    /**
     * This method converts a 4-bit binary number to a Hexadecimal number.
     *
     * @param x The 4-bit binary number
     * @return The Hexadecimal number
     */
    
    static String convert2(int x)
    {
        int s=0,a=0;
        String z;
        while(x>0)
        {
            int d=x%10;
            s+=d*(int)Math.pow(2,a);
            x/=10;
            a++;
        }
        if(s>9)
            z=Character.toString((char)(s+55));
        else
            z=Integer.toString(s);
        return z;
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */

    public static void main(String args[])
    {
        String s1="",s2="",s3="";
        Scanner sc = new Scanner(System.in);
        System.out.print("Binary number: ");
        String bin = sc.nextLine();
        int f=0;
        if(bin.charAt(0) == '-')
            f=1;
        if(bin.contains(".")==true)
        {
            s1=bin.substring(f,bin.indexOf('.'));
            s2=bin.substring(bin.indexOf('.')+1);
            s3=convert(s1,1)+"."+convert(s2,0);
        }
        else
            s3=convert(bin.substring(f),1);
        if(f==1)
            System.out.println("HexaDecimal equivalent: -" +s3);
        else
            System.out.println("HexaDecimal equivalent: " +s3);
        sc.close();
    }
}