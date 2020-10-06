import java.util.*;
public class Power{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a sentence : ");
    String inp = sc.nextLine();
    boolean isOk = false;
    int words=0;

    for(int i=0;i<inp.length();i++){
      char c = inp.charAt(i);
      if(c>='A' && c<='Z' || c==' ' || c=='.' || c=='?' || c=='!'){
        isOk=true;
        if(c==' '){words+=1;}
      }else{
        System.out.println("INVALID OUTPUT");
        isOk=false;
        break;
      }
    }
    if(isOk){// Getting each word of string into an array
      String[] wrd_a = new String[words+1];
      String wrd = "";
      int words_n = words;
      for(int i=0;i<inp.length();i++){
        char c = inp.charAt(i);
        if(c=='.' || c=='?' || c=='!'){
          wrd_a[words_n]=wrd;
          break;
        }
        if(c==' '){
          wrd_a[words_n]=wrd;
          words_n-=1;
          wrd="";
        }
        else{wrd+=inp.charAt(i);}
      }
      int[] pow_a = new int[words+1];
      for(int i=wrd_a.length-1;i>=0;i--){
        String w = wrd_a[i];
        int pow = 0;
        for(int j=0;j<w.length();j++){
          int c = w.charAt(j);
          pow+=c;
        }
        pow_a[i]=pow;
      }
      for(int i=wrd_a.length-1;i>=0;i--){
        System.out.println(wrd_a[i]+"="+pow_a[i]);
      }
      String incr = "";
      int L=pow_a[0];
      int n = pow_a.length;
      for(int i=0; i < n; i++){
        for(int j=1; j < (n-i); j++){
          if(pow_a[j-1] < pow_a[j]){
            int temp = pow_a[j-1];
            pow_a[j-1] = pow_a[j];
            pow_a[j] = temp;

            String temps = wrd_a[j-1];
            wrd_a[j-1] = wrd_a[j];
            wrd_a[j] = temps;
          }
        }
      }
      for(int i=wrd_a.length-1;i>=0;i--){
        System.out.print(wrd_a[i]+" ");
      }System.out.println();
    }
  }
}
