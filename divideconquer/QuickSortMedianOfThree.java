package divideconquer;


/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class quickmedian3<T extends Comparable<T>> {

	public void sort(T[] array) {
		sorted(array, 0, array.length-1);
	}
	
	private void sorted(T[] array, int leftIndex, int rightIndex) {
		
		if ((leftIndex < 0 || leftIndex >= array.length) || (rightIndex < 0 || rightIndex >= array.length)) {
			return;
		}
		
		if (leftIndex < rightIndex) {
			int middle = (leftIndex + rightIndex) / 2;
			
			if (array[rightIndex].compareTo(array[leftIndex]) < 0)
				swap(array, rightIndex, leftIndex);
			if (array[middle].compareTo(array[leftIndex]) < 0)
				swap(array, middle, leftIndex);
			if (array[rightIndex].compareTo(array[middle]) < 0)
				swap(array, rightIndex, middle);
			
			swap(array, leftIndex+1, middle);
			
			int pivot = partition(array, leftIndex+1, rightIndex-1);
			sorted(array, leftIndex, pivot - 1);
			sorted(array, pivot + 1, rightIndex);
		}
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivot = leftIndex;
		int i = leftIndex;
		
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(array[pivot]) <= 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, pivot, i);
		
		return i;
	}

	private void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	
	
	} 
}
