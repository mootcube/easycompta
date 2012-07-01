package com.moot.easycompta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PersonalAccountTest {

	@Test
	public void testPersonalAccount() throws Exception {
		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", person);
		assert pa.getAmount() == 0;

		person.addAccount(pa);
		assertEquals(person.getAccounts().size(), 1);
		assert person.getAccount("test") == pa;

		PersonalAccount pa2 = new PersonalAccount("test", 10, person);
		assert pa2.getAmount() == 10;
	}

	@Test
	public void testAddSamePersonalAccount() {
		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", person);
		try {
			person.addAccount(pa);
			person.addAccount(pa);
			fail("try adding the same account to a person");
		} catch (Exception e) {
		}

		PersonalAccount pa2 = new PersonalAccount("test", person);
		try {
			person.addAccount(pa2);
			fail("try adding the same account to a person");
		} catch (Exception e) {
		}
	}

	@Test
	public void testAddPersonalAccountDifferentPerson() {
		Person person = new Person("Mathieu", "Chataigner");
		Person person2 = new Person("Moot", "Cube");
		PersonalAccount pa = new PersonalAccount("test", person);

		try {
			person2.addAccount(pa);
			fail("try to add a personalaccount to a different person");
		} catch (Exception e) {
		}

	}
}
