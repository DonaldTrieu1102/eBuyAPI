package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.Customer;

@SuppressWarnings("unused")
@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {

@Autowired SessionFactory sessionFactory;
	
	@Override
	public Customer findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomerById(int custId) {
		  sessionFactory.getCurrentSession().createQuery("DELETE FROM customers WHERE Id = "+ custId).executeUpdate();

		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAllCustomer() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		return criteria.list();
	}

	public Customer findCustomerById(int customerId) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, customerId);
	}

	public Customer findCustomerByEmail(String email) {
		return (Customer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
	}
	
	public Customer findCustomerByPhoneNumber(String phoneNumber) {
		return (Customer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
                .add(Restrictions.eq("phoneNumber", phoneNumber))
                .uniqueResult();
	}

	@Override
	public Customer findCustomerByFacebookUID(String facebookUID) {
		return (Customer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.ne("facebookUID", ""))
                .add(Restrictions.eq("facebookUID", facebookUID))
                .uniqueResult();
	}

	@Override
	public Customer findCustomerByGoogleUID(String googleUID) {
		return (Customer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.ne("googleUID", ""))
                .add(Restrictions.eq("googleUID", googleUID))
                .uniqueResult();
	}

	@Override
	public Customer findCustomerByUserName(String userName) {
		// TODO Auto-generated method stub
		return (Customer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
                .add(Restrictions.eq("username", userName))
                .uniqueResult();
	}



	
}
