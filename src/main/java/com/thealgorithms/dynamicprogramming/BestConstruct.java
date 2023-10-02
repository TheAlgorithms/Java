package com.thealgorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Test case
// bestConstruct(8,{2,3,5})
//possiblities   1-{2,2,2,2}  and 2->{3,5}
 //answer will be (3,5)
public class BestConstruct {
    static List<Integer> bestConstruct(int target,int[] nums){
        List<Integer> []table=new List[target+1];
        table[0]=new ArrayList<>();
        System.out.println(Arrays.toString(table));
        for (int i = 0; i < target; i++) {
            if(table[i]!=null){
                for (int num:nums) {
                    if(i+num<=target){
                      List<Integer>  combination=new ArrayList<>(table[i]);
                        combination.add(num);
                        if(table[i+num]==null||table[i+num].size()>combination.size()){
                            table[i+num]=combination;
                        }

                    }
                }
            }
        }
        return table[target];
    }
}
