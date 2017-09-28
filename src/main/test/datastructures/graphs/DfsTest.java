package datastructures.graphs;

import java.util.Scanner;

import static datastructures.graphs.Dfs.dfsImplement;

public class DfsTest {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int vertices = in.nextInt(), source = in.nextInt();
        byte[][] a = new byte[vertices][vertices];
        //initially all elements of a are initialized with value zero

        for (int i = 0; i < vertices; i++) {
            int size = in.nextInt();
            for (int j = 0; j < size; j++) {
                //taking adjacency entries by assigning 1
                a[i][in.nextInt()] = 1;
            }
        }
        dfsImplement(a, vertices, source);
        in.close();
    }
}