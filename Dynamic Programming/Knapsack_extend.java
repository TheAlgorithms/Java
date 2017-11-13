import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {
	static Scanner sc = new Scanner(System.in);

	/*
	 * 1. Enter the number of items and weight of knapsack.
	 * 2. Enter the item's value and weight in the order of the number of items entered
	 * 3. Returns the index of the item that makes up the knapsack table and its maximum value.
	 */
	public static void main(String[] args) {
		int size = sc.nextInt();
		int limit_weight = sc.nextInt();
		OPT opt = new OPT(size, limit_weight);
		opt.setBag(setPreCondition(size));
		opt.run();
		System.out.println("End");
		sc.close();
	}

	// A method to create an input item and place it in a knapsack.
	public static List<Item> setPreCondition(int size) {
		int value = 0;
		int item_weight = 0;
		List<Item> bag = new ArrayList<>();
		bag.add(new Item(0, 0));
		for (int i = 0; i < size; i++) {
			value = sc.nextInt();
			item_weight = sc.nextInt();
			bag.add(new Item(value, item_weight));
		}
		return bag;
	}
}

// The OPT class that performs the knapsack algorithm. 
class OPT {
	int size; // The number of items to receive.
	int limit_weight; // Maximum usable weight of knapsack.
	int[][] table; // Table to store the result of knapsack algorithm.
	List<Item> knapsack; // Knapsack to save the input items.

	// Constructor that initializes filed value.
	public OPT(int size, int limit_weight) {
		this.size = size;
		this.limit_weight = limit_weight;
		this.table = new int[size + 1][limit_weight + 1];
		this.knapsack = new ArrayList<>();
	}

	// A setter method that sets the knapsack value.
	public void setBag(List<Item> knapsack) {
		this.knapsack = knapsack;
	}

	/* Using the opt(i, j) method, when the number of items is i and limit_weight
	 * is j, the result of the knapsack algorithm is stored in the table.
	 */
	public void run() {
		for (int i = 0; i < knapsack.size(); i++) {
			for (int j = 0; j < limit_weight + 1; j++) {
				table[i][j] = opt(i, j);
			}
		}
		print_table();
		print_max_item();
	}

	/*
	 * Tihs method prints the maximum value of the knapsack algorithm and item.
	 * At this time, an algorithm is added to examine the table to find
	 * the maximum item.
	 */
	public void print_max_item() {
		int i = size;
		int j = limit_weight;
		int max = table[i][j];
		String item = "";
		while (table[i][j] != 0) {
			if (table[i][j] == table[i - 1][j]) {
				i--;
			} else {
				j = searchJ(i, table[i][j] - knapsack.get(i).value);
				item += i + " ";
				i--;

			}
		}
		System.out.println("max : " + max);
		System.out.println("item : " + item);
	}

	// Method for finding j in table
	public int searchJ(int i, int value) {
		for (int j = limit_weight; j > -1; j--) {
			if (table[i][j] == value)
				return j;
		}
		return 0;
	}

	// Print the table value
	public void print_table() {
		for (int i = 0; i < size + 1; i++) {
			for (int j = 0; j < limit_weight + 1; j++) {
				System.out.print("\t" + table[i][j]);
			}
			System.out.println("");
		}
	}

	// Method to find the result of knapsack algorithm.
	public int opt(int i, int w) {
		if (i == 0) {
			return 0;
		}
		if (knapsack.get(i).weight > w) {
			return opt(i - 1, w);
		} else {
			int pre = opt(i - 1, w);
			int post = knapsack.get(i).value + opt(i - 1, w - knapsack.get(i).weight);
			return Math.max(pre, post);
		}
	}
}

// Item class that stores the value and weight of each item.
class Item {
	int value;
	int weight;

	public Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
}
