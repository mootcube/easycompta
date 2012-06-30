/*
 * 
 * 
 */
package com.moot.easycompta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.moot.easycompta.MyLogger.*;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Factory {
    private static Map<String,Person> persons=new HashMap<>();
    private static Map<String,Vendor> vendors=new HashMap<>();
    private static VendorFactory vFactory=new VendorFactory();
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
    
}
