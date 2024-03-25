package com.codecademy.goldmedal.controller;

import com.codecademy.goldmedal.model.CountriesResponse;
import com.codecademy.goldmedal.model.CountryDetailsResponse;
import com.codecademy.goldmedal.model.CountryMedalsListResponse;
import com.codecademy.goldmedal.service.GoldMedalService;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/countries")
public class GoldMedalController {

    @Autowired
    private GoldMedalService goldMedalService;

    @GetMapping
    public CountriesResponse getCountries(@RequestParam String sort_by, @RequestParam String ascending) {
        var ascendingOrder = ascending.equalsIgnoreCase("y");
        return new CountriesResponse(goldMedalService.getCountrySummaries(sort_by.toLowerCase(), ascendingOrder));
    }

    @GetMapping("/{country}")
    public CountryDetailsResponse getCountryDetails(@PathVariable String country) {
        String countryName = WordUtils.capitalizeFully(country);
        return goldMedalService.getCountryDetailsResponse(countryName);
    }

    @GetMapping("/{country}/medals")
    public CountryMedalsListResponse getCountryMedalsList(@PathVariable String country, @RequestParam String sort_by, @RequestParam String ascending) {
        String countryName = WordUtils.capitalizeFully(country);
        var ascendingOrder = ascending.equalsIgnoreCase("y");
        return goldMedalService.getCountryMedalsListResponse(countryName, sort_by.toLowerCase(), ascendingOrder);
    }

}
