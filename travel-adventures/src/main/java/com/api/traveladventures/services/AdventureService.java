package com.api.traveladventures.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.api.traveladventures.entities.Adventure;
import com.api.traveladventures.repositories.AdventureRepository;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Iterable<Adventure> getAllAdventures() {
        return adventureRepository.findAll();
    }

    public Adventure getAdventureById(Integer id) {
        Optional<Adventure> adventure = adventureRepository.findById(id);
        if (adventure.isPresent()) {
            return adventure.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure with given id not found.");
        }
    }

    public Iterable<Adventure> getAdventuresByCountry(String country) {
        return adventureRepository.findByCountry(country);
    }

    public Iterable<Adventure> getAdventuresByState(String state) {
        return adventureRepository.findByState(state);
    }

    public void addAdventure(Adventure newAdventure) {
        Optional<Adventure> existingAdventure = adventureRepository.findById(newAdventure.getId());
        if (existingAdventure.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Adventure with given id already exists.");
        } else {
            // Validate date to match dd/mm/yyyy format
            if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", newAdventure.getDate())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be in the format dd/mm/yyyy.");
            }
            // Validate numPhotos
            if (newAdventure.getNumPhotos() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of photos cannot be negative.");
            }

            adventureRepository.save(newAdventure);
        }

    }

    public void updateAdventure(Integer id, Adventure adventure) {
        Optional<Adventure> existingAdventure = adventureRepository.findById(id);
        if (existingAdventure.isPresent()) {
            Adventure updatedAdventure = existingAdventure.get();
            updatedAdventure.setDate(adventure.getDate());
            updatedAdventure.setCountry(adventure.getCountry());
            updatedAdventure.setCity(adventure.getCity());
            updatedAdventure.setState(adventure.getState());
            updatedAdventure.setNumPhotos(adventure.getNumPhotos());
            updatedAdventure.setBlogCompleted(adventure.getBlogCompleted());
            adventureRepository.save(updatedAdventure);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure with given id not found.");
        }
    }

    public void deleteAdventure(Integer id) {
        Optional<Adventure> existingAdventure = adventureRepository.findById(id);
        if (existingAdventure.isPresent()) {
            adventureRepository.delete(existingAdventure.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure with given id not found.");
        }
    }

}
