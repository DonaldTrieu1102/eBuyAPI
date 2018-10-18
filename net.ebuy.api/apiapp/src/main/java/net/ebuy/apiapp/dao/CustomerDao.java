package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.Customer;

public interface CustomerDao {
	
	Customer findById(int id);
	
	void saveCustomer(Customer customer);
	
	void deleteCustomerById(int customerId);
	
	List<Customer> findAllCustomer();

	Customer findCustomerById(int custId);
	
	Customer findCustomerByUserName(String userName);

	
	Customer findCustomerByEmail(String email);
	
	Customer findCustomerByPhoneNumber(String phoneNumber);
	
	Customer findCustomerByFacebookUID(String facebookUID);
	
	Customer findCustomerByGoogleUID(String googleUID);
	

}
