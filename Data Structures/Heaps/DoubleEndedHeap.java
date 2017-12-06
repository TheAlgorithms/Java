
public class DoubleEndedHeap {
	int[] deap;
	int elements = 0;

	public DoubleEndedHeap() {
		deap = new int[14];
	}

	// Returns true if index i is in max-heap, false otherwise.
	private boolean inMaxHeap(int i) {
		for (int j = i; j >= 2; j = (j - 1) / 2) {
			if (j == 2) {
				return true;
			}
		}
		return false;
	}

	// Returns index of max partner when index pos is located in min-heap
	private int maxPartner(int pos) {
		Double exponent = Math.floor(Math.log(pos + 1) / Math.log(2)) - 1;
		int temp = (int) (pos + Math.pow(2, exponent));
		// Return the parent of the index location if there is no partner
		if (temp > elements)
			return (temp - 1) / 2;
		return temp;
	}

	// Returns index of min partner when index pos is in max-heap
	private int minPartner(int pos) {
		Double exponent = Math.floor(Math.log(pos + 1) / Math.log(2)) - 1;
		return (int) (pos - Math.pow(2, exponent));
	}

	// Insert key at index at position in min-heap
	private void minInsert(int at, int key) {
		for (int parent; (parent = (at - 1) / 2) != 0 && key < deap[parent]; deap[at] = deap[parent], at = parent)
			;
		deap[at] = key;
	}

	// Insert key at index at position in max-heap
	private void maxInsert(int at, int key) {
		for (int parent; (parent = (at - 1) / 2) != 0 && key > deap[parent]; deap[at] = deap[parent], at = parent)
			;
		deap[at] = key;
	}

	// Return the max value by deleting it
	public int deleteMax() {
		int temp = deap[elements];// Temporarily save last element
		int i = 2; // maxindex
		int max = deap[i];// Remove min value
		deap[elements--] = 0;// Remove last element

		if (elements < 2) {// maxheap has no elements
			System.out.println("The deap is empty");
			return 0;
		}
		while (2 * i + 1 < elements) {
			if (deap[2 * i + 1] > deap[2 * i + 2]) {// left child is bigger than right child
				deap[i] = deap[2 * i + 1];
				i = 2 * i + 1;
			} else {// left right is bigger than left child
				deap[i] = deap[2 * i + 2];
				i = 2 * i + 2;
			}
		}
		// deap[i] == Leaf node
		/*
		 * If minPartner and any of its children have a value greater than temp, store
		 * the larger value in the leaf node and store the temp in the larger value.
		 */
		int bigminPartner = 0;
		if (deap[minPartner(i)] > deap[2 * minPartner(i) + 1] && deap[minPartner(i)] > deap[2 * minPartner(i) + 2])
			bigminPartner = minPartner(i);
		else if (deap[2 * minPartner(i) + 1] > deap[minPartner(i)]
				&& deap[2 * minPartner(i) + 1] > deap[2 * minPartner(i) + 2])
			bigminPartner = 2 * minPartner(i) + 1;
		else if (deap[2 * minPartner(i) + 2] > deap[minPartner(i)]
				&& deap[2 * minPartner(i) + 2] > deap[2 * minPartner(i) + 1])
			bigminPartner = 2 * minPartner(i) + 2;

		if (temp < deap[bigminPartner]) {// If minPartner and its children have larger values than temp
			deap[i] = deap[bigminPartner];// Move deap [minPartner (i) to the leaf node
			minInsert(bigminPartner, temp);// Store temp in place of minPartner
		} else
			deap[i] = temp;
		return max;

	}

	// Return the min value by deleting it
	public int deleteMin() {
		if (elements < 1) {// Missing element in minheap
			System.out.println("The deap is empty");
			return 0;
		}
		int temp = deap[elements];// Store last element in temp
		deap[elements--] = 0;// Remove last element
		int i = 1;// minindex
		int min = deap[i];// Remove min value
		while (2 * i + 1 < elements) {
			if (deap[2 * i + 1] < deap[2 * i + 2]) {// When the left child is smaller
				deap[i] = deap[2 * i + 1];
				i = 2 * i + 1;
			} else {// When the right child is smaller
				deap[i] = deap[2 * i + 2];
				i = 2 * i + 2;
			}
		}
		// i is the index of the leaf node
		if (temp > deap[maxPartner(i)]) {// If the value of temp is larger than the value of maxPartner
			deap[i] = deap[maxPartner(i)];
			maxInsert(maxPartner(i), temp);
		} else
			deap[i] = temp;
		return min;
	}

	// Insert x
	public void insert(int x) {
		if (elements == deap.length - 1) {
			System.out.println("The heap is full");
			System.exit(1);
		}
		if (++elements == 1) {// When inserting the first element
			deap[1] = x;
			return;
		}
		if (inMaxHeap(elements)) {// Make sure index is in maxHeap
			int i = minPartner(elements);
			if (x < deap[i]) {// Compares values with minPartner, and if less, relocates
				deap[elements] = deap[i];
				minInsert(i, x);
			} else {
				maxInsert(elements, x);
			}
		} else {
			int i = maxPartner(elements);
			if (x > deap[i]) {// Compares the value with maxPartner, and if it is bigger, it changes position
				deap[elements] = deap[i];
				maxInsert(i, x);
			} else {
				minInsert(elements, x);
			}
		}
	}

	// Print deap
	public void print() {
		int levelNum = 2;
		int thisLevel = 0;
		int gap = 8;
		for (int i = 1; i <= elements; i++) {
			for (int j = 0; j < gap - 1; j++) {
				System.out.print(" ");
			}
			if (thisLevel != 0) {
				for (int j = 0; j < gap - 1; j++) {
					System.out.print(" ");
				}
			}
			if (Integer.toString(deap[i]).length() == 1) {
				System.out.print(" ");
			}
			System.out.print(deap[i]);
			thisLevel++;
			if (thisLevel == levelNum) {
				System.out.println();
				thisLevel = 0;
				levelNum *= 2;
				gap /= 2;
			}
		}
		System.out.println();
		if (thisLevel != 0) {
			System.out.println();
		}
	}

	public static void main(String[] argv) {
		DoubleEndedHeap doubleenedheap = new DoubleEndedHeap();

		int[] heapElement = { 4, 65, 8, 9, 48, 55, 10, 19, 20, 30, 15, 25, 50 };
		for (int i = 0; i < heapElement.length; i++) {
			doubleenedheap.insert(heapElement[i]);
		}

		System.out.println("initial Deap");
		doubleenedheap.print();
		System.out.println("delete Min");
		doubleenedheap.deleteMin();
		doubleenedheap.print();
		System.out.println("delete Min");
		doubleenedheap.deleteMin();
		doubleenedheap.print();
		System.out.println("delete Min");
		doubleenedheap.deleteMin();
		doubleenedheap.print();
		System.out.println("delete Max");
		doubleenedheap.deleteMax();
		doubleenedheap.print();
		System.out.println("delete Max");
		doubleenedheap.deleteMax();
		doubleenedheap.print();
		System.out.println("delete Max");
		doubleenedheap.deleteMax();
		doubleenedheap.print();

	}
}