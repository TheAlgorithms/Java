package com.thealgorithms.graph;
/**
 * for example
Input:
[["abc","abc@mail.com","abx@mail.com"],
["abc","abc@mail.com","aby@mail.com"],
["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]

0, 1 Share Name

0, 1 Share email



Output:
[["abc","abc@mail.com","abx@mail.com", "aby@gmail.com"],
["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]

Two accounts belong to the same person if they share at least one common email.
Even if two accounts have the same name, they might belong to different people, so merging should only be based on shared emails. Each person can have multiple accounts, and all merged accounts should have the same name.


 * 
 * 
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class MergeAccounts {

    private static List<List<String>> originalAccounts = new ArrayList<>();
    private static List<Integer> Unneeded = new ArrayList<Integer>();
//     {
//     {"abc", "abc@mail.com", "abx@mail.com"},
//     {"abc", "abc@mail.com", "aby@mail.com"},
//     {"Mary", "mary@mail.com"},
//     {"John", "johnnybravo@mail.com"}
// };
    public static void main(String[] args) {
        originalAccounts.add(new ArrayList<>(Arrays.asList("abc", "abc@mail.com", "abx@mail.com")));
        originalAccounts.add(new ArrayList<>(Arrays.asList("abc", "abc@mail.com", "aby@mail.com")));
        originalAccounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com", "many@mail.com")));
        originalAccounts.add(new ArrayList<>(Arrays.asList("Mohammed", "meedo@mail.com")));
        originalAccounts.add(new ArrayList<>(Arrays.asList("Mary", "johnnybravo@mail.com","mary@mail.com")));
        originalAccounts.add(new ArrayList<>(Arrays.asList("Mohammed", "meedo@mail.com", "Mooda@mail.com")));


        System.out.println(originalAccounts);
        // originalAccounts = mergeAccounts();
        mergeAccounts();
        System.out.println(originalAccounts);
    }
    public static void mergeAccounts(){
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
    // return originalAccounts;
// 
    }

    public static void removeUnneeded() {
        Collections.sort(Unneeded);
        System.out.println(Unneeded);
        for (int a = Unneeded.size()-1; a > -1 ; a--) {
            int i = Unneeded.get(a);
            System.out.println(i);
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