package ch.bbw.test;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.AddressComparator;
import ch.bbw.addressbook.AddressDAO;
import ch.bbw.addressbook.AddressService;

public class TestService {

	@Test
	public void testServiceAllAddresses() {
		AddressService addresseService = new AddressService();
		AddressDAO addressDAO = new AddressDAO_Mokup();
		addresseService.setAddressDAO(new AddressDAO_Mokup());

		List<Address> list = addresseService.getAllAddresses();

		// TODO: liste richtig sortiert?

		// Lösung 1: ohne die Daten zu kennen
		for (int i = 0; i < list.size() - 1; i++) {
			int compLastname = list.get(i).getLastname().compareTo(list.get(i + 1).getLastname());
			assertTrue(compLastname == 0 || compLastname < 0);

			if (compLastname == 0) {
				int compFirstname = list.get(i).getFirstname().compareTo(list.get(i + 1).getFirstname());
				assertTrue(compFirstname == 0 || compFirstname < 0);

				if (compFirstname == 0) {
					int compDate = list.get(i).getRegistrationDate().compareTo(list.get(i + 1).getRegistrationDate());
					assertTrue(compDate == 0 || compDate > 0);
				}
			}
		}

		// Grösse der Liste testen
		assertTrue(list.size() == addressDAO.readAll().size());

		// Lösung 2: mit Kenntnis über die Daten
		assertTrue(list.get(0).getId() == 4);
		assertTrue(list.get(1).getId() == 2);
		assertTrue(list.get(2).getId() == 1);
		assertTrue(list.get(3).getId() == 3);

		// Grösse der Liste testen
		assertTrue(list.size() == 4);

	}

}
