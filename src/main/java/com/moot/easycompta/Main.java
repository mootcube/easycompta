/*
 * 
 * 
 */
package com.moot.easycompta;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Main {
    private static final Logger logger=Logger.getLogger("main");
    
    public static void main(String[] args) throws Exception
    {
        BasicConfigurator.configure();
        logger.info("start application");
        Person p=new Person("Mathieu","Chataigner");
        logger.info(p);
        AbstractVendorFactory vFactory=VendorFactory.getFactory();
        logger.info(vFactory);
        Vendor v=vFactory.createVendor("starbucks");
        logger.info(v);
        PersonalAccount pa=new PersonalAccount("cash",p);
        p.addAccount(pa);
        logger.info(pa);
        logger.info("pa amount "+pa.getAmount());
        Deal d1=new Deal(v,pa,5);
        logger.info(d1.getId());
        d1.processDeal();
        d1=new Deal(v,pa,5);
        logger.info(d1.getId());
        
        
        /*
         * add a GUI in swing and CLI
         * add textloader
         * add bin loader
         */
        //        logger.info(v.getAmount());
        //        logger.info(pa.getAmount());
        //        d1.processDeal();
        //        logger.info(v.getAmount());
        //        logger.info(pa.getAmount());
        
        //new CommandLineController();
    }
}
