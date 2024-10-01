package MonoAlphabetic_Cipher;
import java.util.Scanner;

public class MonoAlphabetic {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Hello User! \nEnter your name:");
        String name = read.nextLine();
        read.nextLine();

        System.out.println("Welcome "+name+"!\nDo you want to encrypt data or decrypt the data?\nFor encryption enter: 1\nFor decryption enter: 2");
        int x = read.nextInt();
        read.nextLine();

        String key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";

        switch (x) {
            case 1:
                System.out.println("\nPlease enter the data that is to be encrypted, we will be using MonoAlphabetic Cipher to encrypt the data.");
                String data = read.nextLine().toUpperCase();;
                
                String encryptedData = encrypt(data,key);
                System.out.println("Encrypted data: " + encryptedData);
                break;
            
            case 2:
                System.out.println("\nPlease enter the data that is to be decrypted, we will be using MonoAlphabetic Cipher to decrypt the data.");
                data = read.nextLine().toUpperCase();;
                
                String decryptedData = decrypt(data,key);
                System.out.println("Decrypted data: " + decryptedData);
                break;
        
            default:
                System.out.println("The input was invalid. Kindly restart.");
                break;
        }

        
    }

    public static String encrypt(String data, String key){
        int idx;
        char c;
        StringBuffer sb = new StringBuffer(data);

        for(int i=0; i<sb.length(); i++){
            idx = sb.charAt(i)-65;
            c = key.charAt(idx);
            sb.setCharAt(i, c);
        }
        return new String(sb);
    }

    public static String decrypt(String data, String key){
        int idx;
        char c;
        StringBuffer sb = new StringBuffer(data);

        for(int i=0; i<sb.length(); i++){
            c = sb.charAt(i);
            idx = getIndex(c,key);
            c = (char) (idx + 65);
            sb.setCharAt(i, c);
        }
        return new String(sb);
    }

    public static int getIndex(char c, String key){
        int idx = -1;
        for(int i=0; i<key.length();i++){
            if(key.charAt(i) == c){
                idx = i;
            }
        }
        return idx;
    }

}
