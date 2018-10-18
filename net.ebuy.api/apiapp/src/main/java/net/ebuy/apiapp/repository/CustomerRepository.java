package net.ebuy.apiapp.repository;

import org.springframework.data.repository.CrudRepository;

import net.ebuy.apiapp.model.Customer;

/**
 * Created by kelvin on 23/01/18.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Customer findCustomerByEmail(String email);
}
