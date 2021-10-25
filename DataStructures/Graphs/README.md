## Graph

Graph is a useful data structure for representing most of the real world problems involving a set of users/candidates/nodes and their relations. A Graph consists of two parameters :

```
V = a set of vertices
E = a set of edges
```

Each edge in `E` connects any two vertices from `V`. Based on the type of edge, graphs can be of two types:

1. **Directed**: The edges are directed in nature which means that when there is an edge from node `A` to `B`, it does not imply that there is an edge from `B` to `A`.
An example of directed edge graph the **follow** feature of social media. If you follow a celebrity, it doesn't imply that s/he follows you.

2. **Undirected**: The edges don't have any direction. So if `A` and `B` are connected, we can assume that there is edge from both `A` to `B` and `B` to `A`.
Example: Social media graph, where if two persons are friend, it implies that both are friend with each other.


### Representation

1.  **Adjacency Lists**: Each node is represented as an entry and all the edges are represented as a list emerging from the corresponding node. So if vertex `1` has eadges to 2,3, and 6, the list corresponding to 1 will have 2,3 and 6 as entries. Consider the following graph.

```
0: 1-->2-->3
1: 0-->2
2: 0-->1
3: 0-->4
4: 3
```
It means there are edges from 0 to 1, 2 and 3; from 1 to 0 and 2 and so on.
2. **Adjacency Matrix**: The graph is represented as a matrix of size `|V| x |V|` and an entry 1 in cell `(i,j)` implies that there is an edge from i to j. 0 represents no edge.
The mtrix for the above graph:

```
   0 1 2 3 4

0  0 1 1 1 0
1  1 0 1 0 0
2  1 1 0 0 0
3  1 0 0 0 1
4  0 0 0 1 0
```