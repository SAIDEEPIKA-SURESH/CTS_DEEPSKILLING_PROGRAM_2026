package com.cognizant.hqllearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.hqllearn.model.Country;

@Repository
public interface CountryRepository
        extends JpaRepository<Country,String>{

    List<Country> findByNameContaining(String name);

    List<Country> findByNameContainingOrderByNameAsc(String name);

    List<Country> findByNameStartingWith(String code);
}
