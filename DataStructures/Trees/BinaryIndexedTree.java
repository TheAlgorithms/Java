/**
 * Binary Indexed Tree = Fenwick Tree
 */
public class BinaryIndexedTree {
	
	/*
	 * Variables that may be needed
	 */
	static int[] array;
	static int[] binaryIndexedTree;
	static int[][] binaryIndexedTree2;
	
	/**
	 * Creates a binary indexed tree array from an array
	 * @param array = the original array
	 * @return binary indexed tree array
	 */
	static int[] create(int[] array) {
		int[] binaryIndexedTree = new int[array.length];
		binaryIndexedTree[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			binaryIndexedTree[i] += array[i];
			int j = i + (i & -i);
			if (j < array.length) {
				binaryIndexedTree[j] += binaryIndexedTree[i];
			}
		}
		return binaryIndexedTree;
	}
	
	/**
	 * Updates binary indexed tree by a value
	 * @param binaryIndexedTree = the binary indexed tree array
	 * @param index = index to update
	 * @param value = value to update by
	 */
	static void update(int[] binaryIndexedTree, int index, int value) {
		for (int i = index; i < binaryIndexedTree.length; i += (i & -i)) {
			binaryIndexedTree[i] += value;
		}
	}
	
	/**
	 * Updates binary indexed tree to a value
	 * To update to a value, update by (value - array[i])
	 * @param array = the original array
	 * @param binaryIndexedTree = the binary indexed tree array
	 * @param index = index to update
	 * @param value = value to update to
	 */
	static void updateToValue(int[] array, int[] binaryIndexedTree, int index, int value) {
		for (int i = index; i < binaryIndexedTree.length; i += (i & -i)) {
			binaryIndexedTree[i] += (value - array[i]);
		}
	}
	
	/**
	 * Queries sum to index
	 * @param binaryIndexedTree = the binary indexed tree array
	 * @param index = index to query to
	 * @return query to index
	 */
	static long query(int[] binaryIndexedTree, int index) {
		int answer = 0;
		for (int i = index; i > 0; i -= (i & -i)) {
			answer += binaryIndexedTree[i];
		}
		return answer;
	}
	
	/**
	 * Queries inclusive sum of two indices
	 * @param binaryIndexedTree = the binary indexed tree array
	 * @param index1 = lowerbound index
	 * @param index2 = upperbound index
	 * @return query of inclusive sum between index1 and index2
	 */
	static long query(int[] binaryIndexedTree, int index1, int index2) {
		return query(binaryIndexedTree, index2) - query(binaryIndexedTree, index1 - 1);
	}
	
	//2D Binary Indexed Tree
	
	/**
	 * Updates binary indexed tree by a value
	 * @param binaryIndexedTree2 = the binary indexed tree array
	 * @param x = x index to update
	 * @param y = y index to update
	 * @param value = value to update by
	 */
	static void update(int[][] binaryIndexedTree2, int x, int y, int value) {
		for (int i = x; i < binaryIndexedTree2.length; i += (i & -i)) {
			for (int j = y; j < binaryIndexedTree2[0].length; j += (j & -j)) {
				binaryIndexedTree2[i][j] += value;
			}
		}
	}
	
	/**
	 * Queries sum to index
	 * @param binaryIndexedTree2 = the binary indexed tree array
	 * @param x = x index to query to
	 * @param y = y index to query to
	 * @return query to index
	 */
	static int query(int[][] binaryIndexedTree2, int x, int y) {
		int answer = 0;
		for (int i = x; i > 0; i -= (i & -i)) {
			for (int j = y; j > 0; j -= (j & -j)) {
				answer += binaryIndexedTree2[i][j];
			}
		}
		return answer;
	}
	
	/**
	 * Queries inclusive sum of two sets of indices
	 * @param binaryIndexedTree2 = the binary indexed tree array
	 * @param x1 = lowerbound x index
	 * @param y1 = lowerbound y index
	 * @param x2 = upperbound x index
	 * @param y2 = upperbound y index
	 * @return query of the inclusive sum of the two sets of indices
	 */
	static int query(int[][] binaryIndexedTree2, int x1, int y1, int x2, int y2) {
		return (query(binaryIndexedTree2, x2, y2) + query(binaryIndexedTree2, x1 - 1, y1 - 1) - query(binaryIndexedTree2, x1 - 1, y2) - query(binaryIndexedTree2, x2, y1 - 1));
	}	
}
