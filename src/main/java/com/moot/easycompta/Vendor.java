/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public interface Vendor extends Actor, Account {
	public Account getAccount();
}
