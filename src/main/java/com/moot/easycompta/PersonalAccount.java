/*
 * 
 * 
 */
package com.moot.easycompta;

import java.util.Objects;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class PersonalAccount extends BasicAccount implements Account
{
    private Person p;
    public PersonalAccount(String id,Person p)
    {
        super(id);
        this.p=p;
    }
    public PersonalAccount(String id,double amount,Person p)
    {
        super(id,amount);
        this.p=p;
    }
    
    public Person getOwner()
    {
        return this.p;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof PersonalAccount))return false;
        PersonalAccount cast=(PersonalAccount)o;
        return cast.getOwner().equals(this.getOwner())&&cast.getId().equals(this.getId());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.getOwner().hashCode() + this.getId().hashCode();
        return hash;
    }
//    @Override
//    public String toString()
//    {
//        return "PersonalAccount("+p.toString()+":"+this.getAmount()+")";
//    }
}
