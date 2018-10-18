package net.ebuy.apiapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.MediUser;
import net.ebuy.apiapp.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvin on 23/01/18.
 */
@SuppressWarnings("unused")
@Component
public class AppUserDetailsService implements UserDetailsService {
   
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public MediUser loadUserByUsername(String s) throws UsernameNotFoundException {
    		MediUser userDetails;
    		String[] sTemp = s.split(",");
    
			Customer user = customerRepository.findCustomerByEmail(sTemp[0]);

	        if(user == null) {
	            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
	        }

	        List<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

	        userDetails = new MediUser(
	        		user.getEmail(), 
	        		user.getPassword(),
	        		authorities);
    		return userDetails;
    }
}

