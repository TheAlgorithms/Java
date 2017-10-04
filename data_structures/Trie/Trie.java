package lecture20;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trie {
	private class Node {
		Character data;
		HashMap<Character, Node> children;
		boolean isTerminal;

		public Node(Character data, boolean isTerminal) {
			this.data = data;
			this.children = new HashMap<>();
			this.isTerminal = isTerminal;
		}
	}

	private Node root;
	private int numWords;

	public Trie() {
		this.root = new Node('\0', false);
		this.numWords = 0;
	}

	public int getNumWords() {
		return this.numWords;
	}

	public void display() {
		this.display(this.root, "");
	}

	private void display(Node node, String osf) {
		if (node.isTerminal) {
			String toprint= osf.substring(1) + node.data;
		//	osf = osf.substring(1);
			System.out.println(toprint);
		}

		Set<Map.Entry<Character, Node>> entries = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : entries) {
			this.display(entry.getValue(), osf + node.data);
		}
	}

	public void add(String word) {
		this.add(this.root, word);
	}

	private void add(Node node, String word) {
		if (word.length() == 0) {
			if (!node.isTerminal) {
				node.isTerminal = true;
				this.numWords++;
			}
			return;
		}
		if (node.children.get(word.charAt(0)) == null) {
			Node n = new Node(word.charAt(0), false);
			node.children.put(word.charAt(0), n);
		}
		this.add(node.children.get(word.charAt(0)), word.substring(1));
	}
	
	public boolean search(String word){
		return this.search(this.root,word);
	}
	
	private boolean search(Node node,String word){
		if (word.length() == 0) {
			if (node.isTerminal) {
				return true;
			}else{
				return false;
			}
		}
		if (node.children.get(word.charAt(0)) == null) {
			return false;
		}
		return this.search(node.children.get(word.charAt(0)), word.substring(1));
	}
	
	public void displayAsTree(){
		this.displayAsTree(this.root);
	}
	
	private void displayAsTree(Node node){
		System.out.print(node.data+"=>");
		Set<Map.Entry<Character, Node>> entries = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : entries) {
			System.out.print((entry.getKey()+", "));
		}
		System.out.println("END");
		for (Map.Entry<Character, Node> entry : entries) {
			this.displayAsTree(entry.getValue());
		}
	}
	
	public void remove(String word){
		this.remove(this.root,word);
	}
	
	private void remove(Node node,String word){
		if (word.length() == 0) {
			if (node.isTerminal) {
				node.isTerminal=false;
				this.numWords--;
			}
			return;
		}
		if (node.children.get(word.charAt(0)) == null) {
			return;
		}
		this.remove(node.children.get(word.charAt(0)), word.substring(1));
		if(!node.children.get(word.charAt(0)).isTerminal&&node.children.size()==0){
			node.children.remove(word.charAt(0));
		}
	}
}
