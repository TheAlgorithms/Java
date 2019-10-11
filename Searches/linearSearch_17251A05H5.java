import java.util.Scanner;
class linearSearch{
  public static void main(String args[]){
    
    //Scanning the elements of the array
    Scanner sc=new Scanner(System.in);
    System.out.println("enter no. of elements");
    int n=sc.nextInt();
    int[] a=new int[n];
    for(int i=0;i<n;i++) a[i]=sc.nextInt();
    
    System.out.println("enter the element to be searched");
    int key=sc.nextInt();
    
    //searching
    int c=-1;
    for(int i=0;i<a.length;i++){
        if(a[i]==key){c=i;System.out.println(c);break;}
    }
    if(c==-1) System.out.println("element Not present");
    else System.out.println("element present at:"+c);
  }
}
