package com.thealgorithms.datastructures.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * TrieNode class which holds the characters and references to its child nodes
 *
 * <a href="https://en.wikipedia.org/wiki/Trie">Wikipedia</a>
 *
 * @author <a href="https://github.com/sailok">Sailok Chinta</a>
 */
public class TrieNode {
    private Map<Character, TrieNode> children;
    private char value;
    private boolean end;

    public TrieNode(char value) {
        this.value = value;
        this.children = new HashMap<>();
        this.end = false;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    /*
    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }
    */

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
