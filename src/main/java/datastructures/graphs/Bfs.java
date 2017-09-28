package datastructures.graphs;


import datastructures.stacks.StackArray;

import java.util.*;

/**
 * Implementation of a Breadth First Search
 *
 * @author Unknown
 */
public class Bfs {

    /**
     * The BFS implemented in code to use.
     *
     * @param a        Structure to perform the search on a graph, adjacency matrix etc.
     * @param vertices The vertices to use
     * @param source   The Source
     */
    public static void bfsImplement(byte[][] a, int vertices, int source) {
        //passing adjacency matrix and no of vertices
        //flag container containing status of each vertices
        byte[] b = new byte[vertices];
        //status initialization
        Arrays.fill(b, (byte) -1);
        /*
               code   status
               -1  =  ready
               0  =  waiting
			   1  =  processed
	    */

        //operational stack
        StackArray st = new StackArray(vertices);
        //assigning source
        st.push(source);
        while (!st.isEmpty()) {
            //assigning waiting status
            b[st.peek()] = (byte) 0;
            System.out.println(st.peek());
            int pop = st.peek();
            //assigning processed status
            b[pop] = (byte) 1;
            //removing head of the queue
            st.pop();
            for (int i = 0; i < vertices; i++) {
                if (a[pop][i] != 0 && b[i] != (byte) 0 && b[i] != (byte) 1) {
                    st.push(i);
                    //assigning waiting status
                    b[i] = (byte) 0;
                }
            }
        }
    }
}
