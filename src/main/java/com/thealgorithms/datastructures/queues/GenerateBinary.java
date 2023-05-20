package com.thealgorithms.datastructures.queues;

import java.util.LinkedList;
import java.util.Queue;

/* Generate binary numbers from 1 to n using Queue.
   e.g.  input n=3
         output = {"1","10","11"}
         input n=5
         output = {"1","10","11","100","101"} */
public class GenerateBinary {

    public static String[] generate(int n) {
        Queue<String> q = new LinkedList<>();
        String[] result = new String[n];
        q.offer("1");
        for(int i=0;i<n;i++) {
            result[i] = q.poll();
            String n1 = result[i] + "0";
            String n2 = result[i] + "1";
            q.offer(n1);
            q.offer(n2);
        }
        return result;
    }

    public static void main(String args[]) {
        String[] result = generate(5);
        for(String s:result) {
            System.out.print(s + ", ");
        }
    }
}
