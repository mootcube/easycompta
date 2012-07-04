/*
 * 
 * 
 */
package com.moot.easycompta.persistance;

import static com.moot.easycompta.MyLogger.info;
import static com.moot.easycompta.MyLogger.warn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.moot.easycompta.Buy;
import com.moot.easycompta.Deal;
import com.moot.easycompta.Person;
import com.moot.easycompta.PersonalAccount;
import com.moot.easycompta.Sell;
import com.moot.easycompta.Transfer;
import com.moot.easycompta.Vendor;
import com.moot.easycompta.VendorFactory;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Loader {
	private static Map<String, Person> persons = new HashMap<>();
	private static Map<String, Vendor> vendors = new HashMap<>();
	private static Collection<Deal> deals = new ArrayList<>();

	public static Map<String, Person> getPersons() {
		return persons;
	}

	public static Map<String, Vendor> getVendors() {
		return vendors;
	}

	public static Collection<Deal> getDeals() {
		return deals;
	}

	protected static void reset() {
		persons = new HashMap<>();
		vendors = new HashMap<>();
		deals = new ArrayList<>();
		VendorFactory.reset();
	}

	public static Person getPerson(String name, String surname) {
		Person p = persons.get(name + "." + surname);
		// if(p==null)
		// persons.put(name+"."+surname, p=new Person(name,surname));
		return p;
	}

	public static Vendor getVendor(String id) {
		Vendor v = vendors.get(id);
		// if(v==null)
		// vendors.put(id, v=vFactory.createVendor(id));
		return v;
	}

	public static void createPersonalAccount(String pid, String id, double amount) {
		Person p = persons.get(pid);
		if (p == null) {
			warn("no person found with this id " + pid);
			return;
		}
		try {
			p.addAccount(new PersonalAccount(id, amount, p));
		} catch (Exception e) {
		}
	}

	public static void loadFromStream(InputStream is) {

	}

	public static void loadFromFile(String name) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
			String line = "";
			while (reader.ready() && (line = reader.readLine()) != null) {
				parseLine(line);
			}
		}

	}

	public static Object parseLine(String line) throws Exception {
		if (!line.trim().isEmpty()) {
			String[] split = line.split(":");
			String[] elems = {};
			if (split.length > 1) {
				elems = split[1].split(",");
			}
			String[] ids;
			Person person;
			PersonalAccount pa, pa2;
			Vendor vendor;
			switch (split[0]) {
			case "Person":
				person = new Person(elems[0], elems[1]);
				if (persons.put(person.getId(), person) != null)
					throw new Exception("another identical person is present");
				return person;
			case "PersonalAccount":
				ids = elems[0].split("\\|");
				person = persons.get(ids[0]);
				if (person == null) {
					throw new Exception("not found");
				}
				pa = new PersonalAccount(ids[1], Double.parseDouble(elems[1]), person);
				person.addAccount(pa);
				return pa;
			case "Vendor":
				if (elems.length == 1) {
					vendor = VendorFactory.getFactory().createVendor(elems[0]);
				} else {
					vendor = VendorFactory.getFactory().createVendor(elems[0], Double.parseDouble(elems[1]));
				}
				if (vendors.put(vendor.getId(), vendor) != null)
					throw new Exception("Same vendor is already here");
				return vendor;
			case "Buy":
				ids = elems[0].split("\\|");
				person = persons.get(ids[0]);
				if (person == null) {
					throw new Exception("not found");
				}
				pa = person.getAccount(ids[1]);
				vendor = getVendor(elems[1]);
				Buy buy = new Buy(pa, vendor, Double.parseDouble(elems[2]));

				info(buy + " " + buy.getDestination().getAmount());
				deals.add(buy);
				return buy;
			case "Sell":
				ids = elems[0].split("\\|");
				person = persons.get(ids[0]);
				if (person == null) {
					throw new Exception("not found");
				}
				pa = person.getAccount(ids[1]);
				vendor = getVendor(elems[1]);

				Sell sell = new Sell(pa, vendor, Double.parseDouble(elems[2]));
				info(sell + " " + sell.getDestination().getAmount());

				deals.add(sell);
				return sell;
			case "Transfer":
				ids = elems[0].split("\\|");
				person = persons.get(ids[0]);
				if (person == null) {
					throw new Exception("not found");
				}
				pa = person.getAccount(ids[1]);

				ids = elems[1].split("\\|");
				person = persons.get(ids[0]);
				if (person == null) {
					throw new Exception("not found");
				}
				pa2 = person.getAccount(ids[1]);
				Transfer transfer = new Transfer(pa, pa2, Double.parseDouble(elems[2]));
				info(transfer + " " + transfer.getDestination().getAmount());
				deals.add(transfer);
				return transfer;
			case "Process":
			case "process":
				for (Deal i : deals)
					i.processDeal();
			case "Print":
			case "print":
				StringBuilder str = new StringBuilder();
				for (Person theP : persons.values()) {
					str.append(theP.getId()).append("\n");
					for (PersonalAccount thePa : theP.getAccounts()) {
						str.append("\t").append(String.format("%-7s", thePa.getId())).append(":").append(String.format("%15.2f", thePa.getAmount())).append("\n");
					}
				}
				for (Vendor theVendor : vendors.values()) {
					str.append(String.format("%-15s", theVendor.getId())).append(":").append(String.format("%15.2f", theVendor.getAmount())).append("\n");
				}
				for (Deal theDeal : deals) {
					if (!theDeal.isProcessed()) {
						PersonalAccount thePa = (PersonalAccount) theDeal.getSource();
						str.append(String.format("%-9s", theDeal.getClass().getSimpleName())).append(":").append(String.format("%-25s", thePa.getOwner().getId() + "|" + thePa.getId()));
						String arrow = "=> ";
						switch (theDeal.getClass().getSimpleName()) {
						case "Sell":
							arrow = "<= ";
						case "Buy":
							str.append(arrow).append(String.format("%-25s", theDeal.getDestination().getId()));
							break;
						case "Transfer":
							thePa = (PersonalAccount) theDeal.getDestination();
							str.append(arrow).append(String.format("%-25s", thePa.getOwner().getId() + "|" + thePa.getId()));
							break;
						}
						str.append(String.format("%15.2f", theDeal.getAmount())).append("\n");
					}
				}
				return str.toString().trim();
			case "Save":
			case "save":
				break;
			case "Load":
			case "load":
				loadFromFile(split[1]);
				return split[1] + " loaded!";
			case "Reset":
			case "reset":
				reset();
				return "Reset success";
			case "Quit":
			case "quit":
				System.exit(0);
			}
		}
		return null;
	}
}
