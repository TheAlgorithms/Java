import java.util.Scanner;
public class arrays{
  public static void main(String[] args){
    System.out.println("Enter your numbers to be added in list:-");
    Scanner read=new Scanner (System.in);
    int []lst=new int[10];
    for(int i=0;i<lst.length;i++){
      lst[i]=read.nextInt();
    }
    int j=0;
    while(j<lst.length){
      System.out.print(lst[i]+"\t");
      j++;
    }
  }
}
