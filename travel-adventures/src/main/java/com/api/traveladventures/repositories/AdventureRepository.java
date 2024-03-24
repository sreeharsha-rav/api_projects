package com.api.traveladventures.repositories;

import com.api.traveladventures.entities.Adventure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains CRUD operations for Adventure entity for the travel adventures API.
 * findByCountry() - returns a list of adventures by country.
 * findByState() - returns a list of adventures by state.
 * findByID() - returns an adventure by ID.
 * findAll() - returns all adventures.
 * save() - saves an adventure.
 * delete() - deletes an adventure.
 */
@Repository
public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
}
