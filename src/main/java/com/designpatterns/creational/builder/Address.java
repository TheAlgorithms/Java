package com.designpatterns.creational.builder;

/**
 * The Step Builder is a design pattern extended from Builder pattern which enforces object field setting step by step.
 */
public class Address {
	private String street;
	private String apartment;
	private String number;

	private Address (String street, String apartment, String number) {
		this.street = street;
		this.apartment = apartment;
		this.number = number;
	}

	public interface StreetStep {
		ApartmentStep withStreet (String street);
	}

	public interface ApartmentStep {
		NumberStep withApartment (String apartment);
	}

	public interface NumberStep {
		BuildStep withNumber (String number);
	}

	public interface BuildStep {
		Address build ();
	}

	/**
	 * Builder class for the above Address class. Constructs the Address by invoking the Address class fields step by
	 * step.
	 */
	public static class AddressBuilder implements StreetStep, ApartmentStep, NumberStep, BuildStep {
		private String street;
		private String apartment;
		private String number;

		private AddressBuilder () {
		}

		public static StreetStep adress () {
			return new AddressBuilder ();
		}

		@Override
		public ApartmentStep withStreet (String street) {
			this.street = street;
			return this;
		}

		@Override
		public NumberStep withApartment (String apartment) {
			this.apartment = apartment;
			return this;
		}

		@Override
		public BuildStep withNumber (String number) {
			this.number = number;
			return this;
		}

		@Override
		public Address build () {
			return new Address (street, apartment, number);
		}
	}
	
	@Override
	public String toString () {
		return "Address{" +
			"street='" + street + '\'' +
			", apartment='" + apartment + '\'' +
			", number='" + number + '\'' +
			'}';
	}
}
