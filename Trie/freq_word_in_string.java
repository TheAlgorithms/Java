package Trie;

import java.util.HashMap;
import java.util.Map;

public class freq_word_in_string {

class TrieNode {
	Map<Character, TrieNode> children;
	boolean endOfWord;
	int count;

	public TrieNode() {
	children = new HashMap<>();
	endOfWord = false;
	count = 0;
	}
}

private TrieNode root = new TrieNode();
private int maxCount = Integer.MIN_VALUE;
private String mostFrequentString;

public void insert(String word) {
	TrieNode current = root;
	for(int i=0; i<word.length(); i++) {
	Character ch = word.charAt(i);
	if(current.children.size() == 0 || (!current.children.containsKey(ch))) {
		current.children.put(ch, new TrieNode());
	}
	TrieNode child = current.children.get(ch);
	current = child;
	}
	current.endOfWord = true;
	current.count++;
	if (maxCount < current.count) {
	maxCount = current.count;
	mostFrequentString = word;
	}
}

public static void main(String[] args) {
	String [] words = { "geeks", "for", "geeks", "a",
						"portal", "to", "learn", "can", "be",
						"computer", "science", "zoom", "yup",
						"fire", "in", "be", "data", "geeks" };
	freq_word_in_string test = new freq_word_in_string();
	for (String word : words) {
	test.insert(word);
	}
// print max count and
	System.out.println(test.maxCount);
	System.out.println(test.mostFrequentString);
}
}