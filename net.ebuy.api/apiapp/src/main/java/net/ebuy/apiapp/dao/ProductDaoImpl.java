package net.ebuy.apiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.Product;
/**
 * @author Donald Trieu
 *
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

	@Autowired SessionFactory sessionFactory;
	
	
	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
		return criteria.list();
	}

	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Override
	public List<Integer> findListIdProductByIdListProduct(List<Product>  listProduct,int idListProduct) {
		// TODO Auto-generated method stub
		
		List<Product> list = listProduct.stream().filter(p -> p.getId_list().getId() == idListProduct)
				.collect(Collectors.toList());
		
		List<Integer> listProductResponse = new ArrayList<>();
		for(Product product: list) {
			int id_Product = product.getId();		
			listProductResponse.add(id_Product);
		}
		return listProductResponse;
	}

	@Override
	public List<Integer> findListIdProductByIdType(List<Product> listProduct, int idType) {
		// TODO Auto-generated method stub
		List<Product> list = listProduct.stream().filter(p -> p.getId_type().getId() == idType)
				.collect(Collectors.toList());
		
		List<Integer> listProductResponse = new ArrayList<>();
		for(Product product: list) {
			int id_Product = product.getId();		
			listProductResponse.add(id_Product);
		}
		return listProductResponse;
	}

	@Override
	public List<Integer> findListIdProductByIdTypeProduct(List<Product> listProduct, int idTypeProduct) {
		// TODO Auto-generated method stub
		List<Product> list = listProduct.stream().filter(p -> p.getId_type_product().getId() == idTypeProduct)
				.collect(Collectors.toList());
		
		List<Integer> listProductResponse = new ArrayList<>();
		for(Product product: list) {
			int id_Product = product.getId();		
			listProductResponse.add(id_Product);
		}
		return listProductResponse;
	}

	

	@Override
	public void savaProduct(Product product) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		
	}

	@Override
	public Product findNewProduct(List<Product> products, int customerId, String created_at) {
		// TODO Auto-generated method stub
		Product product = products.stream()
				.filter(p->p.getCreatedAtFormatVN().equals(created_at)&&p.getId_customer().getId()==customerId)
				.findFirst()
				.orElse(null);
		return product;
	}

	@Override
	public List<Product> findListIdProductByIdCustomer(List<Product> listProduct, int idCustomer) {
		// TODO Auto-generated method stub
		List<Product> list = listProduct.stream().filter(p -> p.getId_customer().getId()==idCustomer)
				.collect(Collectors.toList());
		return list;	
	}
	

}
