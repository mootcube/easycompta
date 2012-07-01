/*
 * 
 * 
 */
package com.moot.easycompta.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.moot.easycompta.persistance.Loader;

/**
 * 
 * @author Mathieu Chataigner <mathieu.chataigner@gmail.com>
 */
public class CommandLineController {

	private final BufferedReader is;

	public CommandLineController(InputStreamReader is) throws IOException {
		this.is = new BufferedReader(is);
	}

	public void run() throws IOException {
		String buf;
		while ((buf = is.readLine()) != null) {
			process(buf);
		}
	}

	private void process(String line) {

		if (line == null || (line = line.trim()).isEmpty())
			return;

		String[] lineItems = line.split(" *");

		switch (lineItems[0]) {
		case "Person":
			Loader.getPerson(lineItems[1], lineItems[2]);
			break;
		case "Vendor":
			Loader.getVendor(lineItems[1]);
			break;
		case "PersonalAccount":
			Loader.createPersonalAccount(lineItems[1], lineItems[2],
					Double.parseDouble(lineItems[3]));
			break;
		case "Deal":
		default:
			break;
		}
	}
}
