// This is detailed implementation of Disjoint Union Set 
// We will use wieghted Disjoint union set for better performance in Dense Graphs or Like graphs where finding root might take upto O(n) time
// We can use this data structure to like detect cycles in Undirected graphs, we can check if 2 components are connected directly or indirectly.


// Implementaion : 

public class DisjointUnionSet {
    int parent[];
    int wieght[];
    int countOfComponents;
    DisjointUnionSet(int totalComponents){
        parent = new int[totalComponents];
        countOfComponents = totalComponents;
        wieght = new int[totalComponents];
        for(int cur=0;cur<totalComponents;cur++){
            parent[cur] = cur; // At start every component is a single entity
            wieght[cur] = 1; // having wieght == 1
        }
    }
    public int getCountOfComponents(){
        // Returns total number of Components in our set
        return countOfComponents;
    }
    public int getRootOf(int component){
        // Idea is to find root of the given component
        while(parent[component]!=component) {
            //So at each step we need to fetch where this particular component leads us to
            parent[component] = parent[parent[component]];
            component = parent[component];
        }
        //Returns index of the root component
        return component;
    }
    public boolean combineTwoComponents(int componentA,int componentB){
        int rootA = getRootOf(componentA);
        int rootB = getRootOf(componentB);

        if(rootA == rootB)
            return false; // both of the components are already connected

        //When we join there roots the hieght of the new connected component is not greater
        //Connect the root with the smaller wieght with the larger one
        if(wieght[rootA] < wieght[rootB]){
            parent[rootA] = parent[rootB]; // Doing so represents that rootB is now parent to rootA
            wieght[rootB] += wieght[rootA];
        }else{
            parent[rootB] = parent[rootA];
            wieght[rootA] += wieght[rootB];
        }
        countOfComponents -= 1; // irrespective of their sizes we are always having one less component after connecting
        return true; // a new Connection is made between two components
    }
}

// For more detailed Analysis visit below given Resources
// https://en.wikipedia.org/wiki/Disjoint-set_data_structure
// https://cp-algorithms.com/data_structures/disjoint_set_union.html
// https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/
