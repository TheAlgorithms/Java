import java.util.Scanner;

public class LetterShifterEncryption {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.print("Input a encryption shift value (number) : ");
        int x = Integer.parseInt(scan.nextLine());

        System.out.println("Select the Option \n1. Encrypter (Enter 1)\n2. Decrypter(Enter 2)\n3. To Close(Enter 0)");
        String selector = scan.nextLine();

        switch (selector){
            case "1":
                System.out.println("Enter the message you want to Encrypt");
                String ss = scan.nextLine();
                System.out.print("Encrypted message : ");
                for(int i=0;i<ss.length(); i++) {
                    if(ss.charAt(i) != ' '){

                        char c = ss.charAt(i);

                        if('A'<= c && 'Z'>=c){
                            int t = ((int)c - 65+x)%26;
                            System.out.print((char)(65+t));
                        }else{
                            int t = ((int)c - 97+x)%26;
                            System.out.print((char)(97+t));
                        }

                    }else System.out.print(" ");
                }
                break;
            case "2":
                System.out.println("Enter the message you want to Decrypt");
                String ss2 = scan.nextLine();
                System.out.print("Decrypted message : ");
                for(int i=0;i<ss2.length(); i++) {
                    if(ss2.charAt(i) != ' '){

                        char c = ss2.charAt(i);

                        if('A'<= c && 'Z'>=c){
                            int t = ((int)c -65 -x)%26;
                            int t2 = t >=0 ? t : 26+t;
                            System.out.print((char)(65+t2));
                        }else{
                            int t = ((int)c -97 -x)%26;
                            int t2 = t >=0 ? t : 26+t;
                            System.out.print((char)(97+t2));
                        }

                    }else System.out.print(" ");
                }
                break;
             default:
                 System.out.println("Program closed");
        }


    }
}
