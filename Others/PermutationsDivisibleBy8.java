import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Permutations divisible by 8

public class PermutationsDivisibleBy8 {

	private static List<String> array = new ArrayList<String>();

	public static void main(String[] args) {

		int aux = 0;
		int limit = 1;
		Scanner teclado = new Scanner(System.in);
		List<String> A = new ArrayList<String>(45);

		while (limit != A.size()) {

			if (limit <= 45) {

				System.out.println("Digite um número: ");

				Integer entrada = teclado.nextInt();
				String ty = entrada.toString();

				A.add(ty);

				if (aux == 0) {
					aux = -1;
					limit = entrada + 1;
				}

			} else {
				System.out.println("Limite invalido!");
				break;
			}

		}

		if (limit <= 45)
			for (int k = 1; k < limit; k++) {
				System.out.println("Resultado: (" + A.get(k) + ")");
				FunctionPermut(A.get(k));
			}

	}

	public static void FunctionPermut(String text) {

		int count = 0;
		permutation(text);

		int t = array.size() - 1;

		for (int i = 0; i <= t; i++) {
			if (verify(array.get(i))) {
				System.out.println("Yes");
				break;
			}
			count++;
		}
		if (count == array.size()) {
			System.out.println("No");
		}

		array.clear();

	}

	public static void permutation(String s) {
		permut("", s);
	}

	private static void permut(String prefix, String s) {

		int n = s.length();
		if (n == 0)
			array.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permut(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
		}

	}

	public static boolean verify(String a) {
		Integer b = Integer.valueOf(a);
		if ((b % 8) == 0) {
			return true;
		} else {
			return false;
		}
	}

}
