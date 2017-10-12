import java.util.Arrays;

public class DisjointSet {
  int[] p; // array of parents
  int[] rank; // rank of each set
  int[] size; // size of each set
  int numSets; // number of sets

  public DisjointSet(int nSets) {
    this.makeSet(nSets);
  }

  /**
  * initialize n sets and wipes the old sets (if there are any)
  * @param n number of sets to initialize
  */
  public void makeSet(int n) {
    p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = i;
    }
    rank = new int[n];

    size = new int[n];
    for (int i = 0; i < n; i++) {
      size[i] = 1;
    }
    numSets = n;
  }

  /**
  * Find the parent (identifier) of the set x belongs to
  * @param x an element of a set
  */
  public int find(int x) {
    if (x < 0 || x >= numSets) {
      throw new IndexOutOfBoundsException();
    }
    int i = x;
    while (i != p[i]) i = p[i];
    p[x] = i;
    return i;
  }

  /**
  * Find if element i belongs to the same set as element j
  * @param i an element of a set
  * @param j an element of a set
  */
  public boolean isSameSet(int i, int j) {
    return find(i) == find(j);
  }

  /**
  * Merges two sets
  * @param i an element of a set
  * @param j an element of a set
  */
  public void union(int i, int j) {
    int iParent = find(i);
    int jParent = find(j);
    if (iParent != jParent) {
      if (rank[iParent] <= rank[jParent]) {
        p[jParent] = iParent;
        size[iParent] += size[jParent];
        if (rank[iParent] == rank[jParent]) {
          rank[iParent] += 1;
        }
      } else {
        p[iParent] = jParent;
        size[jParent] += size[iParent];
        if (rank[iParent] == rank[jParent]) {
          rank[jParent] += 1;
        }
      }
      numSets--;
    }
  }

  public int numDisjointSets() {
    return numSets;
  }

  public int sizeOfSet(int i) {
    return size[find(i)];
  }

  public String toString() {
    return Arrays.toString(p);
  }

  public static void main(String[] args) {
    DisjointSet ds = new DisjointSet(10);
    ds.union(2, 6);
    ds.union(1, 2);
    System.out.println(ds);
    System.out.println("disjoint sets count: " + ds.numDisjointSets());
    System.out.println("disjoint set 6 size: " + ds.sizeOfSet(6));
  }
}
