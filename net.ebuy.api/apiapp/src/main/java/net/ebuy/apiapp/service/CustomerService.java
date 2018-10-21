package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.Customer;
/**
 * @author Donald Trieu
 *
 */
public interface CustomerService {

	Customer findById(int id);

	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomerById(int customerId);

	List<Customer> findAllCustomers();

	Customer findCustomerById(int customerId);
	
	Customer findCustomerByUserName(String username);

	
	Customer findCustomerByEmail(String email);
	
	Customer findCustomerByPhoneNumber(String phoneNumber);
	
	Customer findCustomerByFacebookUID(String facebookUID);
	
	Customer findCustomerByGoogleUID(String googleUID);

	boolean isCustomerIdUnique(Integer id, int customerId);
}
