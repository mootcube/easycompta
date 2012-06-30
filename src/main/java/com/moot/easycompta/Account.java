/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public interface Account {
    public String getId();
    public void modify(double amount);
    public double getAmount();
    
}
