package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.CustomerDao;
import net.ebuy.apiapp.model.Customer;
/**
 * @author Donald Trieu
 *
 */
@Transactional
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired private CustomerDao dao;
	
	@Override
	public Customer findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		dao.saveCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		Customer entity = dao.findById(customer.getId());
        if(entity!=null){
            entity.setId(customer.getId());         
        }
        dao.saveCustomer(entity);
		
	}

	@Override
	public void deleteCustomerById(int customerId) {
		dao.deleteCustomerById(customerId);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return dao.findAllCustomer();
	}

	@Override
	public Customer findCustomerById(int areaId) {
		return dao.findCustomerById(areaId);
	}

	@Override
	public boolean isCustomerIdUnique(Integer id, int customerId) {
		Customer customer = findCustomerById(customerId);
	        return ( customer == null || ((id != null) && (customer.getId() == id)));
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		return dao.findCustomerByEmail(email);
	}

	@Override
	public Customer findCustomerByPhoneNumber(String phoneNumber) {
		return dao.findCustomerByPhoneNumber(phoneNumber);
	}

	@Override
	public Customer findCustomerByFacebookUID(String facebookUID) {
		// TODO Auto-generated method stub
		return dao.findCustomerByFacebookUID(facebookUID);
	}

	@Override
	public Customer findCustomerByGoogleUID(String googleUID) {
		// TODO Auto-generated method stub
		return dao.findCustomerByGoogleUID(googleUID);
	}

	@Override
	public Customer findCustomerByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.findCustomerByUserName(username);
	}
}
