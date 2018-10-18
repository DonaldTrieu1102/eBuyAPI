package net.ebuy.apiapp.model.response;

import java.util.List;

/**
 * @author Donald Trieu
 *
 */
public interface BaseModelResponse {
	
	public List<?> mapToListResponse(List<?> baseEntities);
	
	public List<?> mapToListResponse(List<?> baseEntities, long optionId);
}
