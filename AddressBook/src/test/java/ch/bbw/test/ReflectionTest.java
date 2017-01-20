package ch.bbw.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ch.bbw.addressbook.AddressDAO;
import ch.bbw.addressbook.AddressService;

public class ReflectionTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		System.out.println("Full Name: " + AddressDAO.class.getName());
		System.out.println("Name: " + AddressDAO.class.getSimpleName());
		System.out.println("Full Name: " + AddressDAO.class.getName());
		
		Method[] methods = AddressDAO.class.getMethods();
		for (Method method:methods) {
			System.out.println("Methods: " + method.getName());
		}

		Constructor[] constructors = AddressService.class.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println("Constructor: " + 
					constructor.getName() + " - " +
					constructor.getParameterCount());
		}
		
		AddressService addressService = AddressService.class.newInstance();
		System.out.println(addressService.getClass());
		
		Constructor constructor = AddressService.class.getConstructors()[0];
		addressService = (AddressService) constructor.newInstance();
		System.out.println(addressService.getClass().getName());
		
	}
}
