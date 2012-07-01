package com.moot.easycompta.persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.moot.easycompta.Deal;
import com.moot.easycompta.Person;
import com.moot.easycompta.PersonalAccount;
import com.moot.easycompta.Vendor;
import com.moot.easycompta.VendorFactory;

public class LoaderTest {

	@Before
	public void reset() {
		Loader.reset();
	}

	@Test
	public void testGetPersons() {
		assert Loader.getPersons().size() == 0;
		// fail("Not yet implemented");
	}

	@Test
	public void testGetVendors() {
		assert Loader.getVendors().size() == 0;
		// fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testLoadFromStream() {

		fail("Not yet implemented");
	}

	@Test
	public void testLoadPerson() throws Exception {
		Loader.reset();
		Loader.loadFromFile("src/test/resources/OnePerson.txt");
		assert Loader.getPersons().size() == 1;
		assertNotNull(Loader.getPerson("Mathieu", "Chataigner"));
		// fail("Not yet implemented");
	}

	@Test
	public void testLoadPersonalAccount() throws Exception {
		Loader.reset();
		Loader.loadFromFile("src/test/resources/OnePersonalAccount.txt");
		assert Loader.getPersons().size() == 1;
		Person p;
		assertNotNull(p = Loader.getPerson("Mathieu", "Chataigner"));
		assert p.getAccounts().size() == 1;
		PersonalAccount pa;
		assertNotNull(pa = p.getAccount("test"));
		assertEquals(8, pa.getAmount(), 0);
	}

	@Test
	public void testLoadVendor() throws Exception {
		Loader.reset();
		Loader.loadFromFile("src/test/resources/OneVendor.txt");
		assert Loader.getVendors().size() == 1;
		Vendor v;
		assertNotNull(v = Loader.getVendor("Starbucks"));
		assertEquals(11, v.getAmount(), 0);
	}

	@Test
	public void testLoadBuy() throws Exception {
		Loader.reset();
		VendorFactory.reset();
		Loader.loadFromFile("src/test/resources/OneDeal.txt");
		assertEquals(2, Loader.getVendors().size());
		Vendor v;

		for (Deal i : Loader.getDeals()) {
			i.processDeal();
		}

		assertNotNull(v = Loader.getVendor("StarbucksBuy"));
		assertEquals(21, v.getAmount(), 0);
	}

	@Test
	public void testLoadSell() throws Exception {
		Loader.reset();
		VendorFactory.reset();
		Loader.loadFromFile("src/test/resources/OneDeal.txt");
		assertEquals(2, Loader.getVendors().size());
		Vendor v;

		assertNotNull(v = Loader.getVendor("StarbucksSell"));

		for (Deal i : Loader.getDeals()) {
			i.processDeal();
		}

		assertNotNull(v = Loader.getVendor("StarbucksSell"));
		assertEquals(1, v.getAmount(), 0);
	}

	@Test
	public void testLoadTransfer() throws Exception {
		Loader.reset();
		VendorFactory.reset();
		Loader.loadFromFile("src/test/resources/OneDeal.txt");

		for (Deal i : Loader.getDeals()) {
			i.processDeal();
		}

		Person p = Loader.getPerson("Mathieu", "Chataigner");
		Person p2 = Loader.getPerson("Moot", "Cube");

		assertNotNull(p);
		assertNotNull(p2);

		PersonalAccount pa = p.getAccount("cash");
		PersonalAccount pa2 = p2.getAccount("cash");

		assertNotNull(pa);
		assertNotNull(pa2);

		assertEquals(-2, pa.getAmount(), 0);
		assertEquals(15, pa2.getAmount(), 0);
	}
}
