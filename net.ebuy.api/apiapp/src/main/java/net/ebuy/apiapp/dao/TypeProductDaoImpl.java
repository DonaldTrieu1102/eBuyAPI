package net.ebuy.apiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.Type;
import net.ebuy.apiapp.model.TypeProduct;

@Repository("typeProductDao")
public class TypeProductDaoImpl extends AbstractDao<Integer, TypeProduct> implements TypeProductDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public TypeProduct findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public void saveTypeProduct(TypeProduct typeProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTypeProductById(int id_typeProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeProduct> findAllType() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TypeProduct.class);
		return criteria.list();
	}

	@Override
	public TypeProduct findTypeProductById(int id) {
		// TODO Auto-generated method stub
		return (TypeProduct) sessionFactory.getCurrentSession().get(TypeProduct.class, id);
	}

	@Override
	public List<Object> findListTypeProductByIdType(List<TypeProduct> listTypeProduct, int idListTypeProduct) {
		// TODO Auto-generated method stub
		List<TypeProduct> list = listTypeProduct.stream().filter(typeProduct-> typeProduct.getId_type().getId()==idListTypeProduct)
				.collect(Collectors.toList());
		List<Object> listTypeResponse = new ArrayList<>();
		for(TypeProduct typeProduct : list) {
			Object data = new Object() {
				public final int id_typeProduct = typeProduct.getId();
				public final String name_typeProduct = typeProduct.getName_type_product();
			};
			listTypeResponse.add(data);
		}
		return listTypeResponse;
	}

}
