package com.thealgorithms.strings;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AlphabeticalTest {

    @Test
    public void isAlphabetical() {
        Scanner sc=new Scanner(System.in);
        char c='y';
        String s;
        while(c!='n')
        {
            System.out.print("Enter String :");
            s=sc.nextLine();
            assertTrue(Alphabetical.isAlphabetical(s));
            System.out.print("Want to continue('n' for exit)");
            c=sc.next();
        }
    }
}
