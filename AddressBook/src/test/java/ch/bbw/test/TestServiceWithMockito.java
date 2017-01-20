package ch.bbw.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.AddressDAO;
import ch.bbw.addressbook.AddressService;

public class TestServiceWithMockito {

	private AddressDAO addressDAO;

	private List<Address> addresses;

	@Before
	public void setup() {
		addresses = new ArrayList<>();
		addresses.add(new Address(1, "Petra", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(2, "Peter", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(3, "Fritz", "Müller", "000 0000 0000", new Date()));
		addresses.add(new Address(4, "Dario", "Andres", "000 0000 0000", new Date()));

		addressDAO = Mockito.mock(AddressDAO.class);
		Mockito.when(addressDAO.readAll()).thenReturn(addresses);
	}

	@Test
	public void testSort() {
		AddressService addressService = new AddressService();
		addressService.setAddressDAO(addressDAO);

		List<Address> listFromService = addressService.getAllAddresses();
		assertTrue(addressDAO.readAll().size() == listFromService.size());

		// Lösung 1: ohne die Daten zu kennen
		for (int i = 0; i < listFromService.size() - 1; i++) {
			int compLastname = listFromService.get(i).getLastname().compareTo(listFromService.get(i + 1).getLastname());
			assertTrue(compLastname == 0 || compLastname < 0);

			if (compLastname == 0) {
				int compFirstname = listFromService.get(i).getFirstname()
						.compareTo(listFromService.get(i + 1).getFirstname());
				assertTrue(compFirstname == 0 || compFirstname < 0);

				if (compFirstname == 0) {
					int compDate = listFromService.get(i).getRegistrationDate()
							.compareTo(listFromService.get(i + 1).getRegistrationDate());
					assertTrue(compDate == 0 || compDate > 0);
				}
			}
		}

		// Lösung 2: mit Kenntnis über die Daten
		assertTrue(listFromService.get(0).getId() == 4);
		assertTrue(listFromService.get(1).getId() == 2);
		assertTrue(listFromService.get(2).getId() == 1);
		assertTrue(listFromService.get(3).getId() == 3);

		// Grösse der addressese testen
		assertTrue(listFromService.size() == 4);
		
		// Mockito testen
		Mockito.verify(addressDAO, Mockito.atLeastOnce()).readAll();

	}

	@After
	public void afterTest() {

	}

}
