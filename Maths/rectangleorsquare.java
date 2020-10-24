import java.util.*;
class rectangleorsquare {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();
        int d=sc.nextInt();
        if(a==b && a==c && b==c && c==d && d==b && a==d){
            //if(a=b=c=d){
            System.out.println("SQUARE");
            return;
        }
        if(a==b && c==d || a==d && b==c || a==c && b==d ){
            System.out.println("RECTANGLE");
        }
        else System.out.println("NEITHER RECTANGLE OR SQUARE.");
    }    
}
