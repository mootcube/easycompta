/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public abstract class BasicAccount implements Account{
    private String id;
    private double amount=0;

    public BasicAccount(String id)
    {
        this.id=id;
    }
    
    public BasicAccount(String id,double amount)
    {
        this.id=id;
        this.amount=amount;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void modify(double amount) {
        this.amount+=amount;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }
    
    @Override
    public boolean equals(Object o)
    {
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return (int)Math.random();
    }
}
