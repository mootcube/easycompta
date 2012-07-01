package com.moot.easycompta.persistance;

import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.moot.easycompta.Person;
import com.moot.easycompta.PersonalAccount;
import com.moot.easycompta.Vendor;

public class LoaderTest {

	@Test
	public void testGetPersons() {
		assert Loader.getPersons().size()==0;
		//fail("Not yet implemented");
	}

	@Test
	public void testGetVendors() {
		assert Loader.getVendors().size()==0;
		//fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testLoadFromStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadFromFile() throws IOException {
		Loader.loadFromFile("src/test/resources/OnePerson.txt");
		assert Loader.getPersons().size()==1;
		assertNotNull(Loader.getPerson("Mathieu", "Chataigner"));
		
		Loader.reset();
		
		Loader.loadFromFile("src/test/resources/OnePersonalAccount.txt");
		assert Loader.getPersons().size()==1;
		Person p;
		assertNotNull(p=Loader.getPerson("Mathieu", "Chataigner"));
		assert p.getAccounts().size()==1;
		PersonalAccount pa;
		assertNotNull(pa=p.getAccount("test"));
		assert pa.getAmount()==10;
		
		Loader.reset();
		
		Loader.loadFromFile("src/test/resources/OneVendor.txt");
		assert Loader.getVendors().size()==1;
		Vendor v;
		assertNotNull(v=Loader.getVendor("Starbucks"));
		assert v.getAmount()==10;
		
		//fail("Not yet implemented");
	}

}
