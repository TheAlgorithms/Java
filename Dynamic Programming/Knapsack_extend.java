package 알고리즘_8주차;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int size = sc.nextInt();
		int limit_weight = sc.nextInt();
		OPT opt = new OPT(size, limit_weight);
		opt.setBag(setPreCondition(size));
		opt.run();
		System.out.println("End");
		sc.close();
	}

	public static List<Node> setPreCondition(int size) {
		int value = 0;
		int item_weight = 0;
		List<Node> bag = new ArrayList<>();
		bag.add(new Node(0, 0));
		for (int i = 0; i < size; i++) {
			value = sc.nextInt();
			item_weight = sc.nextInt();
			bag.add(new Node(value, item_weight));
		}
		return bag;
	}
}

class OPT {
	int size;
	int limit_weight;
	int[][] table;
	List<Node> bag;

	public OPT(int size, int limit_weight) {
		this.size = size;
		this.limit_weight = limit_weight;
		this.table = new int[size + 1][limit_weight + 1];
		this.bag = new ArrayList<>();
	}

	public void setBag(List<Node> bag) {
		this.bag = bag;
	}

	public void run() {
		for (int i = 0; i < bag.size(); i++) {
			for (int j = 0; j < limit_weight + 1; j++) {
				table[i][j] = opt(i, j);
			}
		}
		print_table();
		print_max_item();
	}

	public void print_max_item() {
		int i = size;
		int j = limit_weight;
		int max = table[i][j];
		String item = "";
		while (table[i][j] != 0) {
			if (table[i][j] == table[i - 1][j]) {
				i--;
			} else {
				j = searchJ(i, table[i][j] - bag.get(i).value);
				item += i + " ";
				i--;

			}
		}
		System.out.println("max : " + max);
		System.out.println("item : " + item);
	}

	public int searchJ(int i, int value) {
		for (int j = limit_weight; j > -1; j--) {
			if (table[i][j] == value)
				return j;
		}
		return 0;
	}

	public void print_table() {
		for (int i = 0; i < size + 1; i++) {
			for (int j = 0; j < limit_weight + 1; j++) {
				System.out.print("\t" + table[i][j]);
			}
			System.out.println("");
		}
	}

	public int opt(int i, int w) {
		if (i == 0) {
			return 0;
		}
		if (bag.get(i).weight > w) {
			return opt(i - 1, w);
		} else {
			int pre = opt(i - 1, w);
			int post = bag.get(i).value + opt(i - 1, w - bag.get(i).weight);
			return Math.max(pre, post);
		}
	}
}

class Node {
	int value;
	int weight;

	public Node(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
}
