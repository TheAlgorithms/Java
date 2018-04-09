package sort;

import static sort.SortUtils.less;
import static sort.SortUtils.print;

/**
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 *
 * @see SortAlgorithm
 *
 */
public class BinaryTreeSort implements SortAlgorithm {

	interface TreeVisitor<T extends Comparable<T>>  {
		void visit(Node<T> node);
	}

	private static class SortVisitor<T extends Comparable<T>> implements TreeVisitor<T> {

		private final T[] array;
		private int counter;

		SortVisitor(T[] array) {
			this.array = array;
		}

		@Override
		public void visit(Node<T> node) {
			array[counter++] = node.value;
		}
	}

	private static class Node<T extends Comparable<T>>{
		private T value;
		private Node<T> left;
		private Node<T> right;

		Node(T value) {
			this.value = value;
		}

		void insert(Node<T> node) {
			if (less(node.value, value)){
				if (left != null) left.insert(node);
				else left = node;
			}
			else {
				if (right != null) right.insert(node);
				else right = node;
			}
		}

		void traverse(TreeVisitor<T> visitor) {
			if ( left != null)
				left.traverse(visitor);

			visitor.visit(this);

			if ( right != null )
				right.traverse(visitor );
		}

	}


	@Override
	public  <T extends Comparable<T>> T[] sort(T[] array) {

		Node<T> root = new Node<>(array[0]);
		for (int i = 1; i < array.length; i++) {
			root.insert(new Node<>(array[i]));
		}

		root.traverse(new SortVisitor<>(array));

		return array;
	}


	public static void main(String args[]) {

		Integer[] intArray = {12, 40, 9, 3, 19, 74, 7, 31, 23, 54, 26, 81, 12};
		BinaryTreeSort treeSort = new BinaryTreeSort();
		Integer[] sorted = treeSort.sort(intArray);
		print(sorted);

		Double[] decimalArray = {8.2, 1.5, 3.14159265, 9.3, 5.1, 4.8, 2.6};
		print(treeSort.sort(decimalArray));

		String[] stringArray = {"c", "a", "e", "b","d", "dd","da","zz", "AA", "aa","aB","Hb", "Z"};
		print(treeSort.sort(stringArray));
	}

}

