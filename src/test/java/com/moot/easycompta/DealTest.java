package com.moot.easycompta;

import org.junit.Test;


public class DealTest {
	
	@Test
	public void testDeal() throws Exception
	{
    	Person person = new Person("Mathieu", "Chataigner");
    	PersonalAccount pa = new PersonalAccount("test",10, person);
    	person.addAccount(pa);
    	
    	Vendor v=VendorFactory.getFactory().createVendor("starbucks");
        
    	Deal deal = new Deal(pa,v,4);
    	deal.processDeal();
    	
    	assert v.getAmount()==4;
    	assert pa.getAmount()==6;
    	
    	Vendor v2=VendorFactory.getFactory().createVendor("starbucks");
    	assert v2.getAmount()==4;
	}
}
