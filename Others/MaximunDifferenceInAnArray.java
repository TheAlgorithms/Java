import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Maximum Difference in an Array

public class MaximunDifferenceInAnArray {

	public static void main(String[] args) {

		int aux = 0;
		int limit = 1;
		Scanner teclado = new Scanner(System.in);
		ArrayList<Integer> A = new ArrayList<Integer>(1000000);

		while (limit != A.size()) {

			if (limit < 1000000) {

				System.out.println("Digite um número: ");

				Integer entrada = teclado.nextInt();

				A.add(entrada);

				if (aux == 0) {
					aux = -1;
					limit = entrada + 1;
				}

			} else {
				System.out.println("Limite invalido!");
				break;
			}

		}
		
		if (limit < 1000000)
			System.out.println("Resultado: " + maxDifference(A));

	}
	
	

	public static int maxDifference(ArrayList<Integer> X) {

		Integer[] Nuns = X.toArray(new Integer[X.size()]);

		int smaller = 0;
		int larger = 0;
		int largerPosition = 0;
		int limit = Nuns[0];

		for (int i = 1; i <= limit; i++) {

			if (i == 1) {
				larger = Nuns[1];
			} else {
				if (Nuns[i] > larger) {
					larger = Nuns[i];
					largerPosition = i;
				}
			}

		}

		smaller = larger;

		for (int j = 1; j <= largerPosition; j++) {

			if (j < largerPosition) {
				if (Nuns[j] < smaller) {
					smaller = Nuns[j];
				}
			}

		}

		if (smaller == larger)
			return -1;
		else
			return (larger - smaller);

	}

}
