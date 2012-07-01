package com.moot.easycompta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PersonTest {
	@Test
	public void testPerson() {
		Person p = new Person("Mathieu", "Chataigner");

		assertEquals(p.getName(), "Mathieu.Chataigner");

		assertEquals(p.getAccounts().size(), 0);

	}
}
