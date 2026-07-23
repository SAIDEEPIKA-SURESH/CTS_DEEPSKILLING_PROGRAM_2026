package com.cognizant.hqllearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.hqllearn.exception.CountryNotFoundException;
import com.cognizant.hqllearn.model.Country;
import com.cognizant.hqllearn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(
            String code)
            throws CountryNotFoundException{

        Optional<Country> result=
                countryRepository.findById(code);

        if(!result.isPresent())
            throw new CountryNotFoundException(
                    "Country not found");

        return result.get();
    }

    @Transactional
    public void addCountry(
            Country country){

        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(
            String code,
            String name)
            throws CountryNotFoundException{

        Country country=
                findCountryByCode(code);

        country.setName(name);

        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(
            String code){

        countryRepository.deleteById(code);
    }

    @Transactional(readOnly = true)
    public List<Country>
            searchCountry(
                    String name){

        return countryRepository
                .findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountryAsc(String name) {
        return countryRepository.findByNameContainingOrderByNameAsc(name);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountryStartingWith(String alphabet) {
        return countryRepository.findByNameStartingWith(alphabet);
    }
}
