package com.moot.easycompta;

public class Transfer extends Deal {

	public Transfer(PersonalAccount source, PersonalAccount destination,
			double amount) {
		super(source, destination, amount);
	}

	@Override
	public void processDeal() throws Exception {
		if (this.getSource().equals(this.getDestination()))
			throw new Exception("source is desstination");
		if (this.isProcessed())
			return;
		this.getSource().modify(-this.getAmount());
		this.getDestination().modify(this.getAmount());
		this.setProcessed(true);
	}
}
