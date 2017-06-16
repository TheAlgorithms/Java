import java.util.Scanner;
  class FloydTriangle
  {
  public static void main(String[] args)
  {
Scanner sc = new Scanner(System.in);
System.out.println("Enter the number of rows which you want in your Floyd Triangle: ");
int r = sc.nextInt();
int n=0;
for(int i=0; i&lt;r; i++)
{
for(int j=0; j&lt;=i; j++)
{
System.out.print(++n+" ");
}
System.out.println();
}
}
}