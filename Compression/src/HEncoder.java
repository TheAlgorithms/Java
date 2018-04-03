package Compression.src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HEncoder {

	public HashMap<Character, String> encoder = new HashMap<>(); // in order to encode
	public HashMap<String, Character> decoder = new HashMap<>(); // in order to decode

	private static class Node {

		Character ch;
		Integer freq;
		Node left;
		Node right;

		public static final Nodecomparator Ctor = new Nodecomparator();

		public static class Nodecomparator implements Comparator<Node> {

			@Override
			public int compare(Node o1, Node o2) {
				return o2.freq - o1.freq;
			}

		}
	}

	public HEncoder(String feeder) {
		// 1. freq map
		HashMap<Character, Integer> freqmap = new HashMap<>();
		for (int i = 0; i < feeder.length(); ++i) {
			char ch = feeder.charAt(i);
			if (freqmap.containsKey(ch)) {
				freqmap.put(ch, freqmap.get(ch) + 1);
			} else {
				freqmap.put(ch, 1);
			}
		}

		// 2. prepare the heap from keyset
		genericheap<Node> heap = new genericheap<Node>(Node.Ctor);
		ArrayList<Character> k = new ArrayList<>(freqmap.keySet());
		for (Character c : k) {
			Node n = new Node();
			n.ch = c;
			n.left = null;
			n.right = null;
			n.freq = freqmap.get(c);
			heap.add(n);
		}

		// 3.Prepare tree, remove two , merge, add it back
		Node fn = new Node();
		while (heap.size() != 1) {
			Node n1 = heap.removeHP();
			Node n2 = heap.removeHP();
			fn = new Node();

			fn.freq = n1.freq + n2.freq;
			fn.left = n1;
			fn.right = n2;

			heap.add(fn);
		}

		// 4. traverse

		traverse(heap.removeHP(), "");
	}

	private void traverse(Node node, String osf) {

		if (node.left == null && node.right == null) {
			encoder.put(node.ch, osf);
			decoder.put(osf, node.ch);
			return;
		}
		traverse(node.left, osf + "0");
		traverse(node.right, osf + "1");

	}

	// compression work done here
	public String compress(String str) {
		String rv = "";
		for (int i = 0; i < str.length(); ++i) {
			rv += encoder.get(str.charAt(i));
		}
		return rv;
	}
	

	//in order to decompress
	public String decompress(String str) {
		String s = "";
		String code = "";
		for (int i = 0; i < str.length(); ++i) {
			code += str.charAt(i);
			if (decoder.containsKey(code)) {
				s += decoder.get(code);
				code = "";
			}
		}

		return s;
	}
}
