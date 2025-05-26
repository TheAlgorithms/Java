package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;

/**
 * Implementation of a B-Tree, a self-balancing tree data structure that maintains sorted data
 * and allows searches, sequential access, insertions, and deletions in logarithmic time.
 *
 * B-Trees are generalizations of binary search trees in that a node can have more than two children.
 * They're widely used in databases and file systems.
 *
 * For more information: https://en.wikipedia.org/wiki/B-tree
 */

public class BTree {
    static class BTreeNode {
        int[] keys;
        int t; // Minimum degree (defines range for number of keys)
        BTreeNode[] children;
        int n; // Current number of keys
        boolean leaf;

        BTreeNode(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new BTreeNode[2 * t];
            this.n = 0;
        }

        void traverse(ArrayList<Integer> result) {
            for (int i = 0; i < n; i++) {
                if (!leaf) {
                    children[i].traverse(result);
                }
                result.add(keys[i]);
            }
            if (!leaf) {
                children[n].traverse(result);
            }
        }

        BTreeNode search(int key) {
            int i = 0;
            while (i < n && key > keys[i]) {
                i++;
            }
            if (i < n && keys[i] == key) {
                return this;
            }
            if (leaf) {
                return null;
            }
            return children[i].search(key);
        }

        void insertNonFull(int key) {
            int i = n - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > key) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = key;
                n++;
            } else {
                while (i >= 0 && keys[i] > key) {
                    i--;
                }
                if (children[i + 1].n == 2 * t - 1) {
                    splitChild(i + 1, children[i + 1]);
                    if (keys[i + 1] < key) {
                        i++;
                    }
                }
                children[i + 1].insertNonFull(key);
            }
        }

        void splitChild(int i, BTreeNode y) {
            BTreeNode z = new BTreeNode(y.t, y.leaf);
            z.n = t - 1;

            System.arraycopy(y.keys, t, z.keys, 0, t - 1);
            if (!y.leaf) {
                System.arraycopy(y.children, t, z.children, 0, t);
            }
            y.n = t - 1;

            for (int j = n; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;

            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[t - 1];
            n++;
        }

        void remove(int key) {
            int idx = findKey(key);

            if (idx < n && keys[idx] == key) {
                if (leaf) {
                    removeFromLeaf(idx);
                } else {
                    removeFromNonLeaf(idx);
                }
            } else {
                if (leaf) {
                    return; // Key not found
                }

                boolean flag = idx == n;
                if (children[idx].n < t) {
                    fill(idx);
                }

                if (flag && idx > n) {
                    children[idx - 1].remove(key);
                } else {
                    children[idx].remove(key);
                }
            }
        }

        private int findKey(int key) {
            int idx = 0;
            while (idx < n && keys[idx] < key) {
                ++idx;
            }
            return idx;
        }

        private void removeFromLeaf(int idx) {
            for (int i = idx + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }
            n--;
        }

        private void removeFromNonLeaf(int idx) {
            int key = keys[idx];
            if (children[idx].n >= t) {
                int pred = getPredecessor(idx);
                keys[idx] = pred;
                children[idx].remove(pred);
            } else if (children[idx + 1].n >= t) {
                int succ = getSuccessor(idx);
                keys[idx] = succ;
                children[idx + 1].remove(succ);
            } else {
                merge(idx);
                children[idx].remove(key);
            }
        }

        private int getPredecessor(int idx) {
            BTreeNode cur = children[idx];
            while (!cur.leaf) {
                cur = cur.children[cur.n];
            }
            return cur.keys[cur.n - 1];
        }

        private int getSuccessor(int idx) {
            BTreeNode cur = children[idx + 1];
            while (!cur.leaf) {
                cur = cur.children[0];
            }
            return cur.keys[0];
        }

        private void fill(int idx) {
            if (idx != 0 && children[idx - 1].n >= t) {
                borrowFromPrev(idx);
            } else if (idx != n && children[idx + 1].n >= t) {
                borrowFromNext(idx);
            } else {
                if (idx != n) {
                    merge(idx);
                } else {
                    merge(idx - 1);
                }
            }
        }

        private void borrowFromPrev(int idx) {
            BTreeNode child = children[idx];
            BTreeNode sibling = children[idx - 1];

            for (int i = child.n - 1; i >= 0; --i) {
                child.keys[i + 1] = child.keys[i];
            }

            if (!child.leaf) {
                for (int i = child.n; i >= 0; --i) {
                    child.children[i + 1] = child.children[i];
                }
            }

            child.keys[0] = keys[idx - 1];

            if (!child.leaf) {
                child.children[0] = sibling.children[sibling.n];
            }

            keys[idx - 1] = sibling.keys[sibling.n - 1];

            child.n += 1;
            sibling.n -= 1;
        }

        private void borrowFromNext(int idx) {
            BTreeNode child = children[idx];
            BTreeNode sibling = children[idx + 1];

            child.keys[child.n] = keys[idx];

            if (!child.leaf) {
                child.children[child.n + 1] = sibling.children[0];
            }

            keys[idx] = sibling.keys[0];

            for (int i = 1; i < sibling.n; ++i) {
                sibling.keys[i - 1] = sibling.keys[i];
            }

            if (!sibling.leaf) {
                for (int i = 1; i <= sibling.n; ++i) {
                    sibling.children[i - 1] = sibling.children[i];
                }
            }

            child.n += 1;
            sibling.n -= 1;
        }

        private void merge(int idx) {
            BTreeNode child = children[idx];
            BTreeNode sibling = children[idx + 1];

            child.keys[t - 1] = keys[idx];

            for (int i = 0; i < sibling.n; ++i) {
                child.keys[i + t] = sibling.keys[i];
            }

            if (!child.leaf) {
                for (int i = 0; i <= sibling.n; ++i) {
                    child.children[i + t] = sibling.children[i];
                }
            }

            for (int i = idx + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }

            for (int i = idx + 2; i <= n; ++i) {
                children[i - 1] = children[i];
            }

            child.n += sibling.n + 1;
            n--;
        }
    }

    private BTreeNode root;
    private final int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse(ArrayList<Integer> result) {
        if (root != null) {
            root.traverse(result);
        }
    }

    public boolean search(int key) {
        return root != null && root.search(key) != null;
    }

    public void insert(int key) {
        if (search(key)) {
            return;
        }
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                s.children[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    public void delete(int key) {
        if (root == null) {
            return;
        }
        root.remove(key);
        if (root.n == 0) {
            root = root.leaf ? null : root.children[0];
        }
    }
}
