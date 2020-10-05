package com.company;

import java.io.*;
import java.util.*;


class Graph
{
    private int vertices;
    LinkedList<Integer> adjacencyList[];
    private List<Integer> visitedNodes = new ArrayList<Integer>();
    boolean visited[];

    Graph(int v)
    {
        //create adjacencyList object
        adjacencyList = new LinkedList[v]; //length of the nodes;
        vertices = v; //to reuse v
        for(int i=0;i<v;i++)
        {
            adjacencyList[i] = new LinkedList<Integer>(); //create a node for each vertex
        }
        visited = new boolean[vertices];
    }
    void addEdge(int source, int target)
    {
        adjacencyList[source].add(target);//find the source node and add a link
        //System.out.println(adjacencyList[source]); // shows all by=ut first node
    }

    void dfs(int source)
    {
        visited[source] = true;
        visitedNodes.add(source);

        System.out.print(visitedNodes);

        Iterator<Integer> node = adjacencyList[source].listIterator();

        while(node.hasNext())
        {
            source = node.next();
            if(!visited[source])
                dfs(source);
        }

        //visit unrelated nodes

        for(int i = 0;i<vertices;i++)
        {
            if(!visited[i])
                dfs(i);
        }
    }

}
