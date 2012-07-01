/*
 * 
 * 
 */
package com.moot.easycompta;

import java.util.HashMap;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class VendorFactory implements AbstractVendorFactory {

	private static HashMap<String, Vendor> vendors = new HashMap<>();

	private static final VendorFactory factory = new VendorFactory();

	private VendorFactory() {
	}

	public static VendorFactory getFactory() {
		return factory;
	}

	public static void reset() {
		vendors = new HashMap<>();
	}

	@Override
	public Vendor createVendor(String name) {
		if (name == null || name.trim().isEmpty())
			return null;
		if (vendors.containsKey(name))
			return vendors.get(name);
		Vendor v = new VendorImpl(name);
		vendors.put(name, v);
		return v;
	}

	@Override
	public Vendor createVendor(String name, double amount) {
		if (name == null || name.trim().isEmpty())
			return null;
		if (vendors.containsKey(name))
			return vendors.get(name);
		Vendor v = new VendorImpl(name, amount);
		vendors.put(name, v);
		return v;
	}

	private static class VendorImpl implements Vendor {
		private final String name;
		private double amount = 0;

		public VendorImpl(String name) {
			this.name = name;
		}

		public VendorImpl(String name, double amount) {
			this.name = name;
			this.amount = amount;
		}

		@Override
		public String getId() {
			return this.name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public void modify(double amount) {
			this.amount += amount;
		}

		@Override
		public double getAmount() {
			return this.amount;
		}

		@Override
		public Account getAccount() {
			return this;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Vendor))
				return false;
			Vendor cast = (Vendor) o;
			return cast.getId().equals(this.getId());
		}

		@Override
		public int hashCode() {
			return this.getId().hashCode();
		}
	}
}
