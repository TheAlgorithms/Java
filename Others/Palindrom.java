package Others;

import java.util.Scanner;

public class Palindrom {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your text: ");
    String str = input.nextLine();
    input.close();
    boolean final_res = palindrom(str);
    if(final_res){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }


  private boolean palindrom(String str) {
    boolean result = true;
    int back = str.length - 1;
    for( int i = 0 ; i < str.length/2 ; i++){
      if(str.charAt(i) != str.charAt(back)){
        result = false;
        break;
      }
    }
    return result
  }
}
