package com.api.traveladventures.controllers;

import com.api.traveladventures.entities.Adventure;
import com.api.traveladventures.services.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    @Autowired
    private AdventureService adventureService;

    @GetMapping()
    public Iterable<Adventure> getAllAdventures() {
        return adventureService.getAllAdventures();
    }

    @GetMapping("/{id}")
    public Adventure getAdventureById(@PathVariable Integer id) {
        return adventureService.getAdventureById(id);
    }

    @GetMapping("/bycountry/{country}")
    public Iterable<Adventure> getAdventuresByCountry(@PathVariable String country) {
        return adventureService.getAdventuresByCountry(country);
    }

    @GetMapping("/bystate")
    public Iterable<Adventure> getAdventuresByState(@RequestParam String state) {
        return adventureService.getAdventuresByState(state);
    }

    @PostMapping()
    public void addAdventure(@RequestBody Adventure newAdventure) {
        adventureService.addAdventure(newAdventure);
    }

    @PutMapping("/{id}")
    public void updateAdventure(@PathVariable Integer id, @RequestBody Adventure adventure) {
        adventureService.updateAdventure(id, adventure);
    }

    @DeleteMapping("/{id}")
    public void deleteAdventure(@PathVariable Integer id) {
        adventureService.deleteAdventure(id);
    }


}

