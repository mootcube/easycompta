package com.moot.easycompta;

import static org.junit.Assert.*;

import org.junit.Test;


public class PersonalAccountTest {

	public void testPersonalAccount()
	 throws Exception {
	
	}
    @Test
    public void testPersonnalAccount() throws Exception
    {
    	Person person = new Person("Mathieu", "Chataigner");
    	PersonalAccount pa = new PersonalAccount("test", person);
    	assert pa.getAmount() == 0;
    	
    	person.addAccount(pa);
    	assertEquals(person.getAccounts().size(), 1);
    	assert person.getAccount("test")==pa;
    	
    	PersonalAccount pa2 = new PersonalAccount("test", 10, person);
    	assert pa2.getAmount() == 10;
    	
    }
}
