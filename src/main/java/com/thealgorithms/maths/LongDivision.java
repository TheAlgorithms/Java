//        Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
//
//        The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, 
//        and -2.7335 would be truncated to -2.
//        My method used Long Division, here is the source "https://en.wikipedia.org/wiki/Long_division"

package com.thealgorithms.maths;

public class LongDivision {

    private static boolean[] branchesTaken = new boolean[17];

    // Method to get the coverage information
    public static boolean[] getBranchCoverage() {
        return branchesTaken;
    }

    public static void resetCoverage() {
        for (int i = 0; i < 17; i++) {
            branchesTaken[i] = false;
        }
    }

public static int divide(int dividend, int divisor) {

        // Creating a boolean array to keep track of which branches are taken (holds coverage info)
        int numberOfBranches = 17;
        //boolean[] branchesTaken = new boolean[numberOfBranches];
        for (int i = 0; i < numberOfBranches; i++) {
            branchesTaken[i] = false;
        }

        long new_dividend_1 = dividend;
        long new_divisor_1 = divisor;

        // Branch ID: 1
        if (dividend < 0) {      
            branchesTaken[0] = true;                     
            new_dividend_1 = new_dividend_1 * -1;
        }
        // Branch ID: 2
        if (divisor < 0) {     
            branchesTaken[1] = true;                             
            new_divisor_1 = new_divisor_1 * -1;
        }
        // Branch ID: 3
        if (dividend == 0 || new_dividend_1 < new_divisor_1) {  
            branchesTaken[2] = true;
            printCoverageInformation(branchesTaken);
            return 0;                                           
        }

        StringBuilder answer = new StringBuilder();

        String dividend_string = "" + new_dividend_1;
        int last_index = 0;

        String remainder = "";

        // Branch ID: 4
        for (int i = 0; i < dividend_string.length(); i++) {    
            branchesTaken[3] = true;                            
            String part_v1 = remainder + "" + dividend_string.substring(last_index, i + 1);
            long part_1 = Long.parseLong(part_v1);
            // Branch ID: 5
            if (part_1 > new_divisor_1) {  
                branchesTaken[4] = true;                                                 
                int quotient = 0;
                // Branch ID: 6
                while (part_1 >= new_divisor_1) {      
                    branchesTaken[5] = true;                                     
                    part_1 = part_1 - new_divisor_1;
                    quotient++;
                }
                answer.append(quotient);
            }
            // Branch ID: 7 
            else if (part_1 == new_divisor_1) {    
                branchesTaken[6] = true;                                       
                int quotient = 0;
                // Branch ID: 8
                while (part_1 >= new_divisor_1) {      
                    branchesTaken[7] = true;                                    
                    part_1 = part_1 - new_divisor_1;
                    quotient++;
                }
                answer.append(quotient);
            } 
            // Branch ID: 9
            else if (part_1 == 0) {   
                branchesTaken[8] = true;                                                 
                answer.append(0);
            } 
            // Branch ID: 10
            else if (part_1 < new_divisor_1) {   
                branchesTaken[9] = true;                                         
                answer.append(0);
            }
            // Branch ID: 11
            if (!(part_1 == 0)) {       
                branchesTaken[10] = true;                                                    
                remainder = String.valueOf(part_1);
            }
            // Branch ID: 12
            else{         
                branchesTaken[11] = true;                                                             
                remainder = "";
            }

            last_index++;
        }
        // Branch ID: 13
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {    
            branchesTaken[12] = true;           
            // Branch ID: 14
            try {   
                branchesTaken[13] = true;         
                printCoverageInformation(branchesTaken);                                                               
                return Integer.parseInt(answer.toString()) * (-1);                          
            } 
            // Branch ID: 15
            catch (NumberFormatException e) {   
                branchesTaken[14] = true;   
                printCoverageInformation(branchesTaken);                                       
                return -2147483648;                                                         
            }
        }
        // Branch ID: 16
        try {        
            branchesTaken[15] = true;      
            printCoverageInformation(branchesTaken);                                                                 
            return Integer.parseInt(answer.toString());                                   
        } 
        // Branch ID: 17
        catch (NumberFormatException e) {   
            branchesTaken[16] = true;     
            printCoverageInformation(branchesTaken);                                        
            return 2147483647;                                                             
        }
    }

    public static void printCoverageInformation(boolean[] array){
        float numberOfTrue = 0;
        float numberOfFalse = 0;
        float percentage = 0;
        for (boolean b : array) {
            if (b){
                numberOfTrue++;
            }
            else if (!b){
                numberOfFalse++;
            }
            //System.out.println(b);
        }
        percentage = (numberOfTrue/numberOfFalse) * 100;
        //System.out.println("Number of true: " + numberOfTrue);
        //System.out.println("Number of false: " + numberOfFalse);
        System.out.println("PERCENTAGE COVERAGE: " + percentage);
    }
}