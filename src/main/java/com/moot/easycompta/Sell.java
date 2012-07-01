package com.moot.easycompta;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Sell extends Deal {

	public Sell(PersonalAccount account, Vendor entreprise, double amount) {
		super(account, entreprise, amount);
	}

	@Override
	public void processDeal() throws Exception {
		if (this.getSource().equals(this.getDestination()))
			throw new Exception("source is desstination");
		if (this.isProcessed())
			return;
		this.getSource().modify(this.getAmount());
		this.getDestination().modify(-this.getAmount());
		this.setProcessed(true);
	}
}
