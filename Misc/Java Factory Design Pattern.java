package Misc;


interface Currency {
	String getCurrencySign();
}

class Rupee implements Currency {
	@Override
	public String getCurrencySign() {
		return "RS";
	}
}

class USDollar implements Currency {
	@Override
	public String getCurrencySign() {
		return "USD";
	}
}

class BritishPound implements Currency {
	@Override
	public String getCurrencySign() {
		return "GBP";
	}
}

class CurrencyFactory {

	public static Currency createCurrency(String country) {
		if (country.equalsIgnoreCase("India")) {
			return new Rupee();
		} else if (country.equalsIgnoreCase("UK")) {
			return new BritishPound();
		} else if (country.equalsIgnoreCase("US")) {
			return new USDollar();
		}
		return null;
	}
}

public class JavaFactoryDesign {

	public static void main(String args[]) {

		Currency rupee = CurrencyFactory.createCurrency("India");
		System.out.println("Currency for India : " + rupee.getCurrencySign());

		rupee = CurrencyFactory.createCurrency("UK");
		System.out.println("Currency for UK : " + rupee.getCurrencySign());

	}

}