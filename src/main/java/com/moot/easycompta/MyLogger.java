/*
 * 
 * 
 */
package com.moot.easycompta;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class MyLogger {
    static{BasicConfigurator.configure();}
    private static Logger l=Logger.getLogger("esaycompta");
    
    public static void info(Object o)
    {
        l.info(o);
    }
    
    public static void error(Object o)
    {
        l.error(o);
    }
    
    public static void debug(Object o)
    {
        l.debug(o);
    }
    
    public static void warn(Object o)
    {
        l.warn(o);
    }
}
