package net.ebuy.apiapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import net.ebuy.apiapp.model.Customer;

/**
 * Created by kelvin on 23/01/18.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByUsername(String username);
}
