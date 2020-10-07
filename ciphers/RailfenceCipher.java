/**
* This example program shows how Rail Fence Cipher can be done in
* Java.In the rail fence cipher, the plain text is written downwards 
* and diagonally on successive "rails" of an imaginary fence, then moving up 
* when the bottom rail is reached. When the top rail is reached, the message 
* is written downwards again until the whole plaintext is written out. 
* Code by Anvikshajain
*/

class RailFenceBasic{
    int depth;
    
/* Generates a cipher text of th given plain text using no of rails or depth*/

    public static void Encryption(String plainText,int depth)
    {
        int r=depth,len=plainText.length();
        int c=len/depth;
        char mat[][]=new char[r][c];
        int k=0;
 
        String cipherText="";
 
        for(int i=0;i< c;i++)
        {
            for(int j=0;j< r;j++)
            {
                if(k!=len)
                mat[j][i]=plainText.charAt(k++);
                else
                mat[j][i]='X';
            }
        }
        for(int i=0;i< r;i++)
        {
            for(int j=0;j< c;j++)
            {
                cipherText+=mat[i][j];
            }
        }
        System.out.println("Encrypted text is : "+cipherText);
        Decryption(cipherText,depth);
    }
 
 /* Decrypts the encrypted text to the plaintext
 * using the ciphertext and its original no of rail or depth
 */
 
    public static void Decryption(String cipherText,int depth)
    {
        int r=depth,len=cipherText.length();
        int c=len/depth;
        char mat[][]=new char[r][c];
        int k=0;
 
        String plainText="";
 
 
        for(int i=0;i< r;i++)
        {
            for(int j=0;j< c;j++)
           {
                mat[i][j]=cipherText.charAt(k++);
            }
        }
        for(int i=0;i< c;i++)
        {
            for(int j=0;j< r;j++)
            {
                plainText+=mat[j][i];
            }
        }
        System.out.println("Decrypted text is : "+plainText);
    }
}
/*
* Custom inputs for testing
*/
class RailFence{
    public static void main(String args[])throws Exception
    {
        RailFenceBasic rf=new RailFenceBasic();
        int depth=3;

        String plainText,cipherText,decryptedText;
        plainText="railfencecipher";
               
        rf.Encryption(plainText,depth);
    }
}
