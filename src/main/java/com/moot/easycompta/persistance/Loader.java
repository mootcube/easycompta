/*
 * 
 * 
 */
package com.moot.easycompta.persistance;

import static com.moot.easycompta.MyLogger.warn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.moot.easycompta.Person;
import com.moot.easycompta.PersonalAccount;
import com.moot.easycompta.Vendor;
import com.moot.easycompta.VendorFactory;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Loader {
    private static Map<String,Person> persons=new HashMap<>();
    private static Map<String,Vendor> vendors=new HashMap<>();
    
    private static VendorFactory vFactory=VendorFactory.getFactory();
    
    public static Map<String, Person> getPersons() {
		return persons;
	}

    public static Map<String, Vendor> getVendors() {
		return vendors;
	}
    
    
    public static Person getPerson(String name,String surname)
    {
        Person p= persons.get(name+"."+surname);
        if(p==null)
            persons.put(name+"."+surname, p=new Person(name,surname));
        return p;
    }
    
    public static Vendor getVendor(String id)
    {
        Vendor v=vendors.get(id);
        if(v==null)
            vendors.put(id, v=vFactory.createVendor(id));
        return v;
    }
    
    public static void createPersonalAccount(String pid,String id,double amount)
    {
        Person p=persons.get(pid);
        if(p==null)
        {
            warn("no person found with this id "+pid);
            return;
        }
        try
        {
            p.addAccount(new PersonalAccount(id, amount, p));
        }
        catch(Exception e){}
    }
    
    
    public static void loadFromStream(InputStream is)
    {
    	
    	
    	
    }
    
    public static void loadFromFile(String name) throws IOException
    {
    	try(BufferedReader reader = new BufferedReader(new FileReader(name)))
    	{
    		String line="";
    		while(reader.ready()&&(line=reader.readLine())!=null && !line.isEmpty())
    		{
    			String[] split = line.split(":");
    			switch(split[0])
    			{
    			case "Person":
    				String[] elems = split[1].split(",");
    				
    				break;
    			case "PersonnalAccount":
    				break;
    			case "Vendor":
    				break;
    			case "Deal":
    				break;
    			}
    		}
    	}
    	
    	
    }
}
