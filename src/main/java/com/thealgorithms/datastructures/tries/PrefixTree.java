package com.thealgorithms.datastructures.tries;


/*
A Trie, also called digital tree or prefix tree,is a type of k-ary search tree,
a tree data structure used for locating specific keys from within a set.

Using Trie, search complexities can be brought to optimal limit (key length).
It is a special tree that can compactly store strings.

These keys are most often strings, with links between nodes defined not by the entire key,
but by individual characters. In order to access a key (to recover its value, change it, or remove it),
the trie is traversed depth-first, following the links between nodes, which represent each character in the key.

A typical problem solved by using a trie is to Find Longest Common Prefix from a stream of strings.

Input: strs = ["flower","flow","flight"]
Output: "fl"

O(N) time, O(N) space
*/
public class PrefixTree {


    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf; //last node of the word
        int numChildren; //number of children of a node
        TrieNode() {
            children = new TrieNode[26];
        }
    }


    void addToTrie(TrieNode curr, String str) {
        for (char ch : str.toCharArray()) {
            int index = ch - 'a';
            if (curr.children[index] == null) {
                TrieNode tn = new TrieNode();
                curr.children[index] = tn;
                curr.numChildren++;
            }
            curr = curr.children[index];
        }
        curr.isLeaf = true;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        TrieNode root = new TrieNode(); //first node is dummy node
        for (String str : strs) {
            addToTrie(root, str);
        }
        //Traverse the trie and stop when either:
        // 1. size of children array is > 1 (means bifurcation) OR
        // 2. a leaf is reached
        TrieNode curr = root;
        int idx = 0;
        String str = strs[0]; //any word would do
        char[] chars = str.toCharArray();
        while (curr.numChildren == 1 && !curr.isLeaf) {
            curr = curr.children[chars[idx] - 'a'];
            idx++;
        }
        return str.substring(0, idx);
    }
}

