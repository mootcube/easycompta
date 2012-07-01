/*
 * 
 * 
 */
package com.moot.easycompta;

import static com.moot.easycompta.MyLogger.warn;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class Person implements Actor {

	private final String name;

	private final Map<String, PersonalAccount> accounts = new HashMap<>();

	public Person(String name, String surname) {
		this.name = name + "." + surname;
	}

	public Collection<PersonalAccount> getAccounts() {
		return this.accounts.values();
	}

	public PersonalAccount getAccount(String id) {
		return accounts.get(id);
	}

	public void addAccount(PersonalAccount p) throws Exception {
		if (p == null)
			warn("Person.addAccount - PersonalAccount is null. " + this);
		if (accounts.containsKey(p.getId()))
			throw new Exception(
					"an other account with the same id is already registered with this person");
		if (!p.getOwner().equals(this))
			throw new Exception("different owner");
		accounts.put(p.getId(), p);
	}

	// peut être changer personalaccount en classe interne et faire des accès
	// avec les strings comme id.

	@Override
	public String getId() {
		return this.name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	// @Override
	// public String toString()
	// {
	// return "Person:"+this.name;
	// }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Person))
			return false;
		Person cast = (Person) o;
		return cast.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
