import java.util.*;
class Rectangleorsquare {
    static void rectngleorsqure(int a,int b,int c,int d){
        if(a==b && a==c && b==c && c==d && d==b && a==d){
            System.out.println("SQUARE");
            return;
        }
        if(a==b && c==d || a==d && b==c || a==c && b==d ){
            System.out.println("RECTANGLE");
        }
        else System.out.println("NEITHER RECTANGLE OR SQUARE");
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int y=sc.nextInt();
        int w=sc.nextInt();
        int z=sc.nextInt();
        rectngleorsqure(x,y,w,z);

    }    
}
