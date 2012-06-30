/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 *
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Deal {
    private Account source,destination;
    private double amount;
    private boolean processed=false;
    
    private static volatile int nextInt=0;
    
    private int id;
    
    public Deal(Account a,Account b,double amount)
    {
        this.id=nextInt++;
//        this.id=(int)(Math.random()*Integer.MAX_VALUE);
        this.source=a;
        this.destination=b;
        this.amount=amount;
    }
    
    public void processDeal() throws Exception
    {
        if(source.equals(destination))throw new Exception("source is desstination");
        if(processed)return;
        source.modify(-amount);
        destination.modify(amount);
        processed=true;
    }
    
    public int getId()
    {
        return this.id;
    }
    
//    @Override
//    public String toString()
//    {
//        return "Deal("+source.getId()+"=>"+destination.getId()+":"+amount+")";
//    }
}
