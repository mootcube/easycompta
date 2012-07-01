package com.moot.easycompta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendorFactoryTest {

	public void testCreateVendor() throws Exception {

	}

	@Test
	public void testVendorFactory() {
		AbstractVendorFactory vFactory = VendorFactory.getFactory();
		assertEquals(vFactory, VendorFactory.getFactory());

		String vName = "test";

		Vendor v1 = vFactory.createVendor(vName);
		assertEquals(v1, vFactory.createVendor(vName));
		assert v1 == vFactory.createVendor(vName);
	}
}
