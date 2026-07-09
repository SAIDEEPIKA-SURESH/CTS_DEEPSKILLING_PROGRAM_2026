package com.cognizant.ormlearn.exception;

public class CountryNotFoundException
        extends Exception{

    public CountryNotFoundException(
            String msg){
        super(msg);
    }
}
