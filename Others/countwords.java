import java.util.Scanner;

/**
 * You enter a string into this program, and it will return how
 * many words were in that particular string
 * 
 * @author Marcus
 *
 */
  class CountTheWords{

	public static void main(String[] args){
	  Scanner input = new Scanner(System.in);
          System.out.println("Enter your text: ");
          String str = input.nextLine();
          
          System.out.println("Your text has " + wordCount(str) + " word(s)"); 
	  input.close();
        } 

        public static int wordCount(String s){
          if(s.isEmpty() || s == null) return -1;
          return s.trim().split("[\\s]+").length;
        }
        
  }
