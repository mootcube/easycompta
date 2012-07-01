package com.moot.easycompta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DealTest {

	@Test
	public void testDeal() throws Exception {
		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", 10, person);
		person.addAccount(pa);

		Vendor v = VendorFactory.getFactory().createVendor("starbucks");

		Deal deal = new Buy(pa, v, 4);
		deal.processDeal();

		assertEquals(4, v.getAmount(), 0);
		assertEquals(6, pa.getAmount(), 0);

		Vendor v2 = VendorFactory.getFactory().createVendor("starbucks");
		assertEquals(4, v2.getAmount(), 0);
	}

	@Test
	public void testBuy() throws Exception {
		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", 10, person);
		person.addAccount(pa);

		Vendor v = VendorFactory.getFactory().createVendor("starbucksBuy");

		Deal deal = new Buy(pa, v, 4);
		deal.processDeal();

		assertEquals(6, pa.getAmount(), 0);
		assertEquals(4, v.getAmount(), 0);

		Vendor v2 = VendorFactory.getFactory().createVendor("starbucksBuy");
		assertEquals(4, v2.getAmount(), 0);
	}

	@Test
	public void testSell() throws Exception {

		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", 10, person);
		person.addAccount(pa);

		Vendor v = VendorFactory.getFactory().createVendor("starbucksSell");

		Deal deal = new Sell(pa, v, 4);
		deal.processDeal();

		assertEquals(14, pa.getAmount(), 0);
		assertEquals(-4, v.getAmount(), 0);

		Vendor v2 = VendorFactory.getFactory().createVendor("starbucksSell");
		assertEquals(-4, v2.getAmount(), 0);
	}

	@Test
	public void testTransfer() throws Exception {
		Person person = new Person("Mathieu", "Chataigner");
		PersonalAccount pa = new PersonalAccount("test", 10, person);
		person.addAccount(pa);
		PersonalAccount pa2 = new PersonalAccount("cash", 10, person);
		person.addAccount(pa2);

		Deal deal = new Transfer(pa, pa2, 4);
		deal.processDeal();

		assertEquals(6, pa.getAmount(), 0);
		assertEquals(14, pa2.getAmount(), 0);

	}

}
