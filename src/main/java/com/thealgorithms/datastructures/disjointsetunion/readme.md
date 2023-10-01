**Disjoint Set**
---
A Disjoint Set (also known as a Union-Find or Merge-Find) data structure is used to manage and represent a collection of disjoint (non-overlapping) sets.
The primary objective of a disjoint set data structure is to provide efficient operations for grouping and partitioning elements into sets while ensuring that no element belongs to more than one set.

    Element: Individual Item
    Set: A collection of item with unique elements
    Disjoint Set: Set that do not have elements in common 

## Operations

-   **Initialize**: Create a disjoint set data structure with a set for each element, initially containing only that element. This operation is typically performed during data structure initialization.

### Union (Merge)

-   **Union(S, A, B)**: Merge two disjoint sets `A` and `B` into a single set `S`. After the union operation, all elements previously in `A` and `B` belong to the same set, and `A` and `B` no longer exist as separate sets.

### Find (or Union-Find)

-   **Find(S, X)**: Determine which set an element `X` belongs to. This operation is used to check if two elements belong to the same set or not.

## Implementation

### Disjoint Set using Arrays

In this simple implementation, each element is represented by an index in an array. The parent of each element is stored in the array, which forms a tree-like structure. Path compression and union by rank are not applied in this basic implementation.

### Disjoint Set using Trees (Union-Find with Union by Rank and Path Compression)

This is the most commonly used and optimized implementation. It employs a combination of two techniques:

-   **Union by Rank**: Attach the smaller tree to the root of the larger tree to keep the tree height small, ensuring efficient find operations.
-   **Path Compression**: During find operations, flatten the tree by making all elements along the path to the root directly point to the root, reducing the depth of the tree.

## Applications

Disjoint set data structures find applications in various algorithms and problems, including:

-   Kruskal's algorithm for minimum spanning trees
-   Connected components in a graph
-   Image processing (segmentation)
-   Network connectivity analysis
-   Union-Find-based data structures like disjoint set forests and disjoint-set-based hash tables

## Complexity Analysis

The time complexity of disjoint set operations varies depending on the implementation:

-   **Array-based Implementation**:
    
    -   `Initialize`: O(N)
    -   `Union`: O(N)
    -   `Find`: O(1) (amortized)
-   **Tree-based Implementation (with Union by Rank and Path Compression)**:
    
    -   `Initialize`: O(N)
    -   `Union`: O(log N)
    -   `Find`: O(log N) (amortized)
