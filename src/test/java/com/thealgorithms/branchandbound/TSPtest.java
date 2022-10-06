package com.thealgorithms.branchandbound;
import java.util.*;

public class TSPtest {
    static int V = 4;
static int travllingSalesmanProblem(int graph[][],
									int s)
{
    ArrayList<Integer> vertex = new ArrayList<Integer>();

    for (int i = 0; i < V; i++)
	    if (i != s)
	        vertex.add(i);

    int min_path = Integer.MAX_VALUE;
    do
    {
	    int current_pathweight = 0;
	    int k = s;
	
    	for (int i = 0; i < vertex.size(); i++)
	    {
	        current_pathweight += graph[k][vertex.get(i)];
	        k = vertex.get(i);
	    }
	    current_pathweight += graph[k][s];
	    min_path = Math.min(min_path, current_pathweight);

    } while (findNextPermutation(vertex));

    return min_path;
}
public static ArrayList<Integer> swap(ArrayList<Integer> data,int left, int right){
    int temp = data.get(left);
    data.set(left, data.get(right));
    data.set(right, temp);
    return data;
}
public static ArrayList<Integer> reverse(ArrayList<Integer> data, int left, int right)
{
    while (left < right)
    {
	    int temp = data.get(left);
	    data.set(left++, data.get(right));
	    data.set(right--, temp);
    }
    return data;
}
public static boolean findNextPermutation(ArrayList<Integer> data)
{
    if (data.size() <= 1)
	    return false;

    int last = data.size() - 2;
    while (last >= 0)   
    {
	    if (data.get(last) < data.get(last + 1))
	    {
	        break;
	    }
	    last--;
    }
    if (last < 0)
	    return false;

    int nextGreater = data.size() - 1;
    for (int i = data.size() - 1; i > last; i--) {
	    if (data.get(i) > data.get(last))
	    {
	        nextGreater = i;
	        break;
	    }
    }
    data = swap(data,nextGreater, last);
    data = reverse(data, last + 1, data.size() - 1);
    return true;
}
public static void main(String args[]){
    int graph[][] = {{0, 20, 15, 24},
	    			{10, 0, 19, 2},
		    		{5, 31, 0, 30},
		    		{19, 25, 20, 0}};
    int s = 0;
    System.out.println(travllingSalesmanProblem(graph, s));
    }
}
