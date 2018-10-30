
import java.util.Scanner;
/**
 *
 * @author jayesh
 */
public class AssemblyLineTechnique {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int lineSize = sc.nextInt();
        int Line1[] = new int[lineSize];
        int Line2[] = new int[lineSize];
        int data[][] = new int[2][lineSize];
        int entry[] = {sc.nextInt(), sc.nextInt()};
        int exit[] = {sc.nextInt(), sc.nextInt()};
        int cross[][] = new int[2][lineSize];
        
        for(int i = 0; i < lineSize; i++)
            data[0][i] = sc.nextInt();
        
        for(int i = 0; i < lineSize; i++)
            data[1][i] = sc.nextInt();    
        
        for(int i = 0; i < lineSize; i++)
            cross[0][i] = sc.nextInt();    
        
        for(int i = 0; i < lineSize; i++)
            cross[1][i] = sc.nextInt();
       
        
        Line1[0] = entry[0] + data[0][0];
        Line2[0] = entry[1] + data[1][0];
        
        int min;
        
        for(int i = 1; i < lineSize; ++i)
        {
            Line1[i] = Integer.min(Line1[i - 1] + data[0][i], Line2[i - 1] + cross[1][i] + data[0][i]);
            Line2[i] = Integer.min(Line2[i - 1] + data[1][i], Line1[i - 1] + cross[0][i] + data[1][i]);
        }
        System.out.println(Integer.min(Line1[lineSize - 1] + exit[0], Line2[lineSize - 1] + exit[1]));
    }   
}
