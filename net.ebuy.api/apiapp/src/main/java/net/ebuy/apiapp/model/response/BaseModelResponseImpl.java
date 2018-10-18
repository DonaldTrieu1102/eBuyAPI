package net.ebuy.apiapp.model.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Donald Trieu
 * @param <E>
 *
 */
@SuppressWarnings("unused")
public class BaseModelResponseImpl<E> implements BaseModelResponse {

	public List<?> mapToListResponse(List<?> baseEntities) {
		// TODO Auto-generated method stub
		return new ArrayList<E>();
	}

	public List<?> mapToListResponse(List<?> baseEntities, long optionId) {
		// TODO Auto-generated method stub
		return new ArrayList<E>();
	}

	
	
}
