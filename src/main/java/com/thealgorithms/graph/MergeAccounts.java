package com.thealgorithms.graph;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class MergeAccounts {
/**
 * Merges accounts that share at least one common email address.
 * 
 * <p>This method takes a list of accounts, where each account is represented as a list of strings:
 * the first element is the account holder's name, followed by one or more email addresses.
 * It merges accounts that share both the same name and at least one email address into a single list,
 * removing duplicates and redundant entries.</p>
 *
 * @param inputList a list of accounts, each represented as [name, email1, email2, ...]
 */

    private static List<Integer> Unneeded = new ArrayList<Integer>();

    private static List<List<String>> originalAccounts;
    public static List<List<String>> mergeAccounts(List<List<String>> inputAccounts){
        originalAccounts = inputAccounts;
        for (int account = 0; account < originalAccounts.size(); account++) {
            for (int otherAccount = account+1; otherAccount < originalAccounts.size(); otherAccount++) {
                if (account != otherAccount){
                    if(sameNameCheck(account,otherAccount)&&sameEmailCheck(account,otherAccount)){
                        overlapItems(account, otherAccount);
                    }


                }
           }
 
        }
    removeUnneeded();
    return originalAccounts;
// 
    }

    public static void removeUnneeded() {
        Collections.sort(Unneeded);
        // System.out.println(Unneeded);
        for (int a = Unneeded.size()-1; a > -1 ; a--) {
            int i = Unneeded.get(a);
            // System.out.println(i);
            originalAccounts.remove(i);
        }
    }
    public static boolean sameNameCheck(int acc1, int acc2) {
        return(originalAccounts.get(acc1).get(0).equals(originalAccounts.get(acc2).get(0)));
    }

    public static boolean sameEmailCheck(int acc1, int acc2) {
        // originalAccounts[acc1] originalAccounts[acc2]
        // array of 3
        int acc1Size = originalAccounts.get(acc1).size();
        int acc2Size = originalAccounts.get(acc2).size();
        for (int email = 1; email < acc1Size; email++) {
            for (int otherEmail = 1; otherEmail < acc2Size; otherEmail++) {
                // System.out.println("Compared "+ originalAccounts[acc1][email]+ " and "+ originalAccounts[acc2][otherEmail]);
                if (originalAccounts.get(acc1).get(email).equals(originalAccounts.get(acc2).get(otherEmail)))
                    return true;
            }
        }
        return false;

    }
    public static void overlapItems(int acc1, int acc2) {
        int acc1Size = originalAccounts.get(acc1).size();
        int acc2Size = originalAccounts.get(acc2).size();
        if (acc1Size < acc2Size){
            int temp = acc1;
            acc1 = acc2;
            acc2= temp;
            temp = acc1Size;
            acc1Size = acc2Size;
            acc2Size = temp;
        }
        for (int email = 1; email < acc1Size; email++) {
            for (int otherEmail = 1; otherEmail < acc2Size; otherEmail++) {
                if (!originalAccounts.get(acc1).contains(originalAccounts.get(acc2).get(otherEmail)))
                    originalAccounts.get(acc1).add(originalAccounts.get(acc2).get(otherEmail));
                    
            }
        }
                Unneeded.add(acc2);


    }

}