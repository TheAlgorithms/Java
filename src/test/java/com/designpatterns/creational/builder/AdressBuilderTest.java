package com.designpatterns.creational.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdressBuilderTest {
	private final String homeAddress = "Address{street='Home Street', apartment='Home Apartment', number='7'}";
	private final String workAddress = "Address{street='Work Street', apartment='Work Apartment', number='3'}";

	@Test
	void testAdressBuilder () {
		Address address1 = Address.AddressBuilder.adress ()
			.withStreet ("Home Street")
			.withApartment ("Home Apartment")
			.withNumber ("7")
			.build ();
		Assertions.assertEquals (address1.toString (), homeAddress);

		Address address2 = Address.AddressBuilder.adress ()
			.withStreet ("Work Street")
			.withApartment ("Work Apartment")
			.withNumber ("3")
			.build ();
		Assertions.assertEquals (address2.toString (), workAddress);
	}
}
