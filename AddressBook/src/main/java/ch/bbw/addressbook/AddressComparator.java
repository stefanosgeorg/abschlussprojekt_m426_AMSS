package ch.bbw.addressbook;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

	@Override
	public int compare(Address a1, Address a2) {
		if (a1.getLastname().compareTo(a2.getLastname()) == 0) {
			if (a1.getFirstname().compareTo(a2.getFirstname()) == 0) {
				return a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
			} else {
				return a1.getFirstname().compareTo(a2.getFirstname());
			}
		} else {
			return a1.getLastname().compareTo(a2.getLastname());
		}
	}

}
