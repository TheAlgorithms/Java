package 알고리즘_5주차;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Karatsuba {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String operand_A_temp = br.readLine();
		String operand_B_temp = br.readLine();
		BigInteger operand_A = new BigInteger(operand_A_temp);
		BigInteger operand_B = new BigInteger(operand_B_temp);
		System.out.println(karatsuba(operand_A, operand_B));
		br.close();
	}

	public static BigInteger karatsuba(BigInteger operand_A, BigInteger operand_B) {
		int operand_A_length = operand_A.toString().length();
		int operand_B_length = operand_B.toString().length();
		int mid = 0;
		if (operand_A_length > operand_B_length)
			mid = operand_A_length / 2;
		else
			mid = operand_B_length / 2;

		if (operand_A_length < 3 && operand_B_length < 3) {
			return operand_A.multiply(operand_B);
		}
		BigInteger quotient = new BigInteger(Integer.toString((int) Math.pow(10.0, mid)));

		BigInteger x1 = operand_A.divide(quotient);
		BigInteger x2 = operand_A.remainder(quotient);
		BigInteger y1 = operand_B.divide(quotient);
		BigInteger y2 = operand_B.remainder(quotient);
		BigInteger z0 = karatsuba(x2, y2);
		BigInteger z2 = karatsuba(x1, y1);
		BigInteger z1 = karatsuba(x2.add(x1), y2.add(y1)).subtract(z2).subtract(z0);
		BigInteger result = z0.add(z1.multiply(quotient))
				.add(z2.multiply(new BigInteger(Integer.toString((int) Math.pow(10, 2 * mid)))));

		return result;
	}
}