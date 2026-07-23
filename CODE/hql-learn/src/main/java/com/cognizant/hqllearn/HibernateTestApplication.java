package com.cognizant.hqllearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cognizant.hqllearn.model.Country;

public class HibernateTestApplication {

    public static void main(String[] args) {
        System.out.println("Running Hibernate XML Configuration Test...");

        SessionFactory factory = null;
        Session session = null;
        Transaction tx = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

            session = factory.openSession();

            tx = session.beginTransaction();

            // Clear old FR record if exists to allow running multiple times
            Country oldFrance = session.get(Country.class, "FR");
            if (oldFrance != null) {
                session.remove(oldFrance);
            }

            session.persist(
                    new Country(
                            "FR",
                            "France"));

            tx.commit();
            System.out.println("France country saved successfully via Hibernate XML Configuration!");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }
}
