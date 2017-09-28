package datastructures.graphs;

import datastructures.stacks.StackArray;

import java.util.Arrays;

/**
 * Implementation of a Depth First Search
 *
 * @author Unknown
 */

public class Dfs {

    /**
     * Implementation in code of a DFS
     *
     * @param a        structure to be DFS'ed
     * @param vertices The vertices
     * @param source   The source
     */
    public static void dfsImplement(byte[][] a, int vertices, int source) {
        //passing adjacency matrix and no of vertices
        //flag container containing status of each vertices
        byte[] b = new byte[vertices];
        Arrays.fill(b, (byte) -1);
        //status initialization
        /*
        code   status
        -1  =  ready
		0  =  waiting
		1  =  processed
		*/

        StackArray st = new StackArray(vertices);         //operational stack
        st.push(source);                          //assigning source
        while (!st.isEmpty()) {
            b[st.peek()] = (byte) 0;                 //assigning waiting status
            System.out.println(st.peek());
            int pop = st.pop();
            b[pop] = (byte) 1;                       //assigning processed status
            for (int i = 0; i < vertices; i++) {
                if (a[pop][i] != 0 && b[i] != (byte) 0 && b[i] != (byte) 1) {
                    st.push(i);
                    b[i] = (byte) 0;                  //assigning waiting status
                }
            }
        }

    }
}
