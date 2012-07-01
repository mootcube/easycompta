/*
 * 
 * 
 */
package com.moot.easycompta;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public abstract class Deal {
	private final Account source, destination;
	private final double amount;
	private boolean processed = false;

	private static volatile int nextInt = 0;

	private final int id;

	public Deal(Account source, Account destination, double amount) {
		this.id = nextInt++;
		// this.id=(int)(Math.random()*Integer.MAX_VALUE);
		this.source = source;
		this.destination = destination;
		this.amount = amount;
	}

	public Account getDestination() {
		return destination;
	}

	public Account getSource() {
		return source;
	}

	public double getAmount() {
		return amount;
	}

	public int getId() {
		return this.id;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public abstract void processDeal() throws Exception;

	// @Override
	// public String toString()
	// {
	// return "Deal("+source.getId()+"=>"+destination.getId()+":"+amount+")";
	// }
}
