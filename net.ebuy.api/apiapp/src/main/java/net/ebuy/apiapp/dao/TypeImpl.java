package net.ebuy.apiapp.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.ebuy.apiapp.model.Type;
/**
 * @author Donald Trieu
 *
 */
@Repository("typeDao")
public class TypeImpl extends AbstractDao<Integer, Type> implements TypeDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Type findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public void saveType(Type type) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(type);

	}

	@Override
	public void deleteTypeById(int id_type) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public List<Type> findAllType() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Type.class);
		return criteria.list();
	}

	@Override
	public Type findTypeById(int id) {
		// TODO Auto-generated method stub
		return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
	}

	@Override
	public List<Object> findListTypeByIdListProduct(List<Type> listType, int idListProduct) {
		// TODO Auto-generated method stub
				
		List<Type> list = listType.stream().filter(type-> type.getId_list().getId()==idListProduct)
				.collect(Collectors.toList());
		List<Object> listTypeResponse = new ArrayList<>();
		for(Type type : list) {
			Object data = new Object() {
				public final int id_type = type.getId();
				public final String name_type = type.getName_type();
				public final String image_type = type.getImage_type();
			};
			listTypeResponse.add(data);
		}
		return listTypeResponse;
	}

}
