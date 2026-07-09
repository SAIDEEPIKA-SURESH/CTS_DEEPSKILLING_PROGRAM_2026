package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class SpringLearnApplication {

    // Hands-on 3: SLF4J Logger Configuration
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START: main()");
        SpringApplication.run(SpringLearnApplication.class, args);

        displayDate();
        displayCountry();
        displayCountries();

        LOGGER.info("END: main()");
    }

    // Hands-on 2 & 3: Load SimpleDateFormat from xml with logging
    public static void displayDate() {
        LOGGER.info("START: displayDate()");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date parsedDate = format.parse("31/12/2018");
            LOGGER.debug("Parsed Date: {}", parsedDate);
        } catch (Exception e) {
            LOGGER.error("Error parsing date", e);
        }
        LOGGER.info("END: displayDate()");
    }

    // Hands-on 4 & 5: Load Country from xml & demonstrate Singleton vs Prototype scope
    public static void displayCountry() {
        LOGGER.info("START: displayCountry()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // Fetching bean twice to demonstrate prototype instantiation log outputs
        Country country = context.getBean("country", Country.class);
        Country anotherCountry = context.getBean("country", Country.class);

        LOGGER.debug("Country : {}", country);
        LOGGER.debug("Another Country : {}", anotherCountry);
        LOGGER.info("END: displayCountry()");
    }

    // Hands-on 6: Load list of countries from xml
    @SuppressWarnings("unchecked")
    public static void displayCountries() {
        LOGGER.info("START: displayCountries()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        ArrayList<Country> countries = context.getBean("countryList", ArrayList.class);
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("END: displayCountries()");
    }
}
