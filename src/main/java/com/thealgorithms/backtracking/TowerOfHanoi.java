import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*The problem consists of three rods and a number of disks of different sizes, which can be slid onto any rod. The puzzle starts with the disks in a neat stack in ascending order of size on one rod, the source rod, with the largest disk at the bottom and the smallest at the top. The goal is to move the entire stack to another rod, the target rod, subject to the following rules:

Only one disk can be moved at a time.
A disk can only be placed on top of a larger disk or an empty rod. */

public class TowerOfHanoi {
    //the three rods are src-source,helper,destination and n is number of disk
    public static void towerOfHanoi(int n,String src,String helper,String dest){
        if(n==1){//this is base condition
            System.out.println("transfer disk  "+n+"  from  "+src+"  to  "+dest);
            return ;
        }
//Move the top n-1 disks from the source rod to an auxiliary rod,
// using the target rod as the temporary rod
        towerOfHanoi(n-1, src, dest, helper);
        //Move the largest disk from the source rod to the target rod.
        System.out.println("transfer disk  "+n+"  from  "+src+"  to  "+dest);
        //Move the n-1 disks from the auxiliary rod to the target rod, 
        //using the source rod as the temporary rod.
        towerOfHanoi(n-1, helper, src, dest);
        
    }
     @Test
    public void testTowerOfHanoi() {
        // Redirect System.out 
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Test case for n = 1
        TowerOfHanoi.towerOfHanoi(1, "S", "H", "D");
        String expectedOutput1 = "transfer disk 1 from S to D\n";

        // Test case for n = 2
        outContent.reset();
        TowerOfHanoi.towerOfHanoi(2, "S", "H", "D");
        String expectedOutput2 = "transfer disk 1 from S to H\n" +
                                 "transfer disk 2 from S to D\n" +
                                 "transfer disk 1 from H to D\n";

        // Compare captured output to expected output . These assertions are crucial for ensuring that the method produces the expected output.
        assertEquals(expectedOutput1, outContent.toString());
        assertEquals(expectedOutput2, outContent.toString());

        // you can add many more test cases
    }


    

        
    
}
