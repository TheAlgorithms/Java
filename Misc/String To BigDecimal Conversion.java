package Misc;

import java.math.BigDecimal;

public class JavaStringToBigDecimal {

	public static void main(String[] args) {

		String string = "100.00";
		BigDecimal bigDecimal = new BigDecimal(string);
		System.out.println(" Printing BigDecimal Value " + bigDecimal);

	}
}