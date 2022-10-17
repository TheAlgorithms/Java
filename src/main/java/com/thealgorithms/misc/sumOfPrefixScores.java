package com.thealgorithms.misc;
import java.util.ArrayList;
import java.util.List;

//Additional Node class to count and updates by each character in each word
class Node {
    Node links[]=new Node[26];
    int count = 0;
    boolean containsKey(char ch) {
        return links[ch-'a'] != null;
    }
    Node get(char ch) {
        return links[ch-'a'];
    }
    void put(char ch, Node node) {
        links[ch-'a'] = node;
    }
    void updateCount() {
        count++;
    }
    int getCount() {
        return count;
    }
}
//Build the trie to insert and search
class Trie {
    Node root;
    Trie() {
        root = new Node();
    }
    void insert(String word) {
        Node node = root;
        for(char ch: word.toCharArray()) {
            if(!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
            node.updateCount();
        }
    }
    int search(String word) {
        int res = 0;
        Node node = root;
        for(char ch: word.toCharArray()) {
            node = node.get(ch);
            res += node.getCount();
        }
        return res;
    }
};

class Solution {
//Now preety simple just insert it into the trie and invoke search to get whole strings which starts with all the prefixes of given word in words
    int[] sumOfPrefixScores(String[] words) {
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        List<Integer> ans = new ArrayList<Integer>();
        for(String word : words) {
            int count = trie.search(word);
            ans.add(count);
        }
        return ans.stream().mapToInt(i->i).toArray();
    }
};
