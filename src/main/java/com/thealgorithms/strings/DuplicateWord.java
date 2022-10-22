import java.util.Scanner;
public class DuplicateWord {    
  static void duplicateWords(String string)
  {
    int count;   
     //Converts the string into lowercase    
        string = string.toLowerCase();    
            
        //Split the string into words using built-in function    
        String words[] = string.split(" ");    
            
        System.out.println("Duplicate words in a given string : ");     
        for(int i = 0; i < words.length; i++) {    
            count = 1;    
            for(int j = i+1; j < words.length; j++) {    
                if(words[i].equals(words[j])) {    
                    count++;    
                    //Set words[j] to 0 to avoid printing visited word    
                    words[j] = "0";    
                }    
            }    
                
            //Displays the duplicate word if count is greater than 1    
            if(count > 1 && words[i] != "0")    
                System.out.println(words[i]);    
  }
  }
    public static void main(String[] args) {    
          
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter username");

    String string = myObj.nextLine();  // Read user input
         duplicateWords(string);          
        }   
        
}    
