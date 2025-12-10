## Tree
### Description

Tree is a data structure where the data is organized in a hierarchial structure. There should be one root node (which does not have any parent) and all subsequent nodes are represented as children of the root node and its children. If a node has at least one child, it is called `internal` node and nodes with no children are called `leaf` nodes.

### Basic Structure

```
class Tree<E>{
    E value;
    Tree left;
    Tree right;
}
```

This basic structure is for a binary tree where each internal tree has at least one and at most two children. `left` and `right` represent the two children and `value` is the placeholder for data.


### Properties
1. Tree data structure gives the facility to organize data in a hierarchial structure
2. Tree nodes can be inserted in a sorted order which can be used for searching and inserting data in O(logN) time where N is the number of nodes.

### Types of Trees
1. **Binary Search Tree:** A binary tree where the elements are inserted in asorted order. Here the searching can be done in O(logN) time in (depending on the structure)
2. **AVL Tree and Red-Black Tree:** Binary search trees where the height is balanced. Here, searching is guaranteed to be in O(logN) time.
3. **Traversal algorithms:** <br>
a.  **BFS:** Breadth-first-search where all the children at each level are traversed at once. <br>
b. **DFS:** Depth-first-search where the first discovered child is traversed first.
4. **MultiWay Search Tree:** Tree in sorted order, but more than two children in each internal node.
5. **Trie:** A character based multiway search tree where words can be retrieved based on their prefix. Useful for implementing prefix based search algorithm.