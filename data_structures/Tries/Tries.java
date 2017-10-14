import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tries {

	private class Node {
		Character data;
		HashMap<Character, Node> children;
		boolean isTerminal;

		public Node(Character data, boolean isTerminal) {
			this.data = data;
			this.children = new HashMap<>();
			this.isTerminal = false;
		}

	}

	private int numWords;
	private Node root;

	public Tries() {
		this.numWords = 0;
		this.root = new Node('\0', false);
	}

	public int numWords() {
		return this.numWords;
	}

	public boolean isEmpty() {
		return this.numWords() == 0;
	}

	public void add(String str) {
		this.add(str, root);
	}

	private void add(String str, Node parent) {
		if (str.length() == 0) {
			if (parent.isTerminal) {
			} else {
				parent.isTerminal = true;
				this.numWords++;

			}
			return;
		}
		String ros = str.substring(1);
		char cc = str.charAt(0);
		Node child = parent.children.get(cc);
		if (child == null) {
			child = new Node(cc, false);
			parent.children.put(cc, child);
			this.add(ros, child);
		} else {
			this.add(ros, parent.children.get(cc));

		}
	}

	public void display() {
		this.display(this.root, "");
	}

	private void display(Node node, String osf) {
		if (node.isTerminal) {
			String toPrint = osf.substring(1) + node.data;
			System.out.println(toPrint);
		}
		Set<Map.Entry<Character, Node>> entries = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : entries)
			this.display(entry.getValue(), osf + node.data);

	}

	public boolean search(String str) {
		return this.search(str, root);
	}

	private boolean search(String str, Node node) {
		if (str.length() == 0) {
			if (node.isTerminal)
				return true;
			else
				return false;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);
		Node child = node.children.get(cc);
		if (child == null)
			return false;
		return this.search(ros, child);

	}

}
