package net.ebuy.apiapp.repository;

import org.springframework.data.repository.CrudRepository;

import net.ebuy.apiapp.model.City;


/**
 * @author Donald Trieu
 *
 */
public interface CityRepository extends CrudRepository<City, Integer> {

}