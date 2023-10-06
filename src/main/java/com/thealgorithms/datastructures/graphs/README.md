## Graphs
Graph is a useful data structure for representing most of the real-world problems involving a set of users/candidates/nodes and their relations. A graph consists of two parameters:

```
V = a set of vertices
E = a set of edges
```

Each edge in `E` connects any two vertices from `V`. Based on the type of edge, graphs can be of two types:

1. **Directed**: The edges are directed in nature, which means that when there is an edge from node `A` to `B`, it does not imply that there is an edge from `B` to `A`. An example of a directed edge graph is the **follow** feature of social media. If you follow a celebrity, it doesn't imply that they follow you.

2. **Undirected**: The edges don't have any direction. So if `A` and `B` are connected, we can assume that there is an edge from both `A` to `B` and `B` to `A`. For example, in a social media graph, if two persons are friends, it implies that both are friends with each other.

### Components of a Graph

**Vertices:** Vertices are the fundamental units of the graph. Sometimes, vertices are also known as vertex or nodes. Every node/vertex can be labeled or unlabelled.

**Edges:** Edges are used to connect two nodes of the graph. They can be an ordered pair of nodes in a directed graph. Edges can connect any two nodes in any possible way. There are no rules. Sometimes, edges are also known as arcs. Every edge can be labeled/unlabeled.

Graphs are used to solve many real-life problems. Graphs are used to represent networks. The networks may include paths in a city, telephone network, or circuit network. Graphs are also used in social networks like LinkedIn, Facebook. For example, on Facebook, each person is represented with a vertex (or node). Each node is a structure and contains information like person id, name, gender, locale, etc.

### Graph Representation

Graph can be represented in the following ways:

**Set Representation:** Set representation of a graph involves two sets: Set of vertices V = {V1, V2, V3, V4} and set of edges E = {{V1, V2}, {V2, V3}, {V3, V4}, {V4, V1}}. This representation is efficient for memory but does not allow parallel edges.

**Sequential Representation:** This representation of a graph can be represented by means of matrices: Adjacency Matrix, Incidence matrix, and Path matrix.

**Adjacency Matrix:** This matrix includes information about the adjacent nodes. Here, aij = 1 if there is an edge from Vi to Vj; otherwise, it's 0. It is a matrix of order V×V.

**Incidence Matrix:** This matrix includes information about the incidence of edges on the nodes. Here, aij = 1 if the jth edge Ej is incident on the ith vertex Vi; otherwise, it's 0. It is a matrix of order V×E.

**Path Matrix:** This matrix includes information about the simple path between two vertices. Here, Pij = 1 if there is a path from Vi to Vj; otherwise, it's 0. It is also called the reachability matrix of graph G.

**Linked Representation:** This representation gives information about the nodes to which a specific node is connected, i.e., adjacency lists. This representation gives the adjacency lists of the vertices with the help of arrays and linked lists. In the adjacency lists, the vertices connected to the specific vertex are arranged in the form of lists that are connected to that vertex.

### Real-Time Applications of Graph

Graphs are used to represent the flow of control in computers.
Graphs are used in social networking sites where users act as nodes, and connections between them act as edges.
In an operating system, graphs are used as resource allocation graphs.
Graphs are used in Google Maps to find the shortest route.
Graphs are also used in the airline system for effective route optimization.
In state transition diagrams, the graph is used to represent states and their transitions.
In transportation, graphs are used to find the shortest path.
In circuits, graphs can be used to represent circuit points as nodes and wires as edges.
Graphs are used in solving puzzles with only one solution, such as mazes.
Graphs are used in computer networks for Peer to Peer (P2P) applications.
Graphs, basically in the form of DAG (Directed Acyclic Graph), are used as an alternative to blockchain for cryptocurrency. For example, cryptocurrencies like IOTA and Nano are mainly based on DAG.

### Advantages of Graph

Using graphs, we can easily find the shortest path, neighbors of the nodes, and many more.
Graphs are used to implement algorithms like DFS and BFS.
They are used to find minimum spanning trees, which have many practical applications.
Graphs help in organizing data.
Because of their non-linear structure, graphs help in understanding complex problems and their visualization.

### Disadvantages of Graph

Graphs use lots of pointers, which can be complex to handle.
They can have large memory complexity.
If the graph is represented with an adjacency matrix, then it does not allow parallel edges, and multiplication of the graph is also difficult.

### Representation

1. **Adjacency Lists**: Each node is represented as an entry, and all the edges are represented as a list emerging from the corresponding node. So, if vertex 1 has edges to 2, 3, and 6, the list corresponding to 1 will have 2, 3, and 6 as entries. Consider the following graph:

```
0: 1-->2-->3
1: 0-->2
2: 0-->1
3: 0-->4
4: 3
```

It means there are edges from 0 to 1, 2, and 3; from 1 to 0 and 2, and so on.

2. **Adjacency Matrix**: The graph is represented as a matrix of size |V| x |V|, and an entry 1 in cell (i, j) implies that there is an edge from i to j. 0 represents no edge. The matrix for the above graph:

```
   0 1 2 3 4

0  0 1 1 1 0
1  1 0 1 0 0
2  1 1 0 0 0
3  1 0 0 0 1
4  0 0 0 1 0
```
