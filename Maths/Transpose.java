import java.io.*;
class Transpose
{
public static void main(String [] args)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter the size of Matrix");
int rows=Integer.parseInt(br.readLine());
int col=Integer.parseInt(br.readLine());
int om[][]=new int[rows][col];
//inputting the matrix
for(int i=0;i<rows;i++)
{
System.out.println("Enter the "+(i+1) +" row elemnets");
for(int j=0;j<col;j++)
{
om[i][j]=Integer.parseInt(br.readLine());
}
System.out.println("\f");
}
System.out.println("\f");
//Displaying the original matrix
System.out.println("Original matrix is:");
for(int i=0;i<rows;i++)
{

for(int j=0;j<col;j++)
{
System.out.print(om[i][j]+"   ");
}
System.out.println();
}
int tm[][]=new int[rows][col];
//Transposing the matrix
for(int i=0;i<rows;i++)
{
for(int j=0;j<col;j++)
{
tm[j][i]=om[i][j];
}
}
//Printing the transposed matrix
System.out.println("Transposed Matrix is:");
for(int i=0;i<rows;i++)
{

for(int j=0;j<col;j++)
{
System.out.print(tm[i][j]+"   ");
}
System.out.println();
}
}
}
