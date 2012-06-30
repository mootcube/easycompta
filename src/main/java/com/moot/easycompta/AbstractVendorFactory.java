/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public interface AbstractVendorFactory {
    public Vendor createVendor(String name);
}
