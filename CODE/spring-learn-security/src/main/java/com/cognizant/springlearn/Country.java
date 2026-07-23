package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    // Exercise 4: Constructor with debug log
    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    public Country(String code, String name) {
        LOGGER.debug("Inside Country Constructor with args.");
        this.code = code;
        this.name = name;
    }

    // Getters and setters with debug logs (Exercise 4)
    public String getCode() {
        LOGGER.debug("Inside Country getCode.");
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside Country setCode. Value: {}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside Country getName.");
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside Country setName. Value: {}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
