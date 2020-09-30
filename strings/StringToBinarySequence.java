package strings;
import java.util.Scanner;
public class StringToBinary {
	
	public static void main(String[] args) {
        System.out.println("STRING TO BINARY SEQUENCE");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Text : ");
        String text = sc.nextLine();
        sc.close();

        int length_of_string = text.length();
        for (int i = 0; i < length_of_string; i++) {
            // CHAR TO ASCII
            int asciival = text.charAt(i);

            // ASCII TO BINARY
            String bin = "";
            
            while(asciival>0) {
            	if(asciival%2==1) {
            		bin = bin + '1';
            	}
            	else {
            		bin = bin + '0';
            	}
            	asciival = asciival/2;
            }
        System.out.print("0"+reverse(bin) + " ");    
        }
    }
	public static String reverse(String str) {
		String res = "";
		for(int i = str.length()-1 ; i >= 0 ; i--) {
			res = res + str.charAt(i);
		}
		return res;
	}
}
