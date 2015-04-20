package com.Andre;

/**
 * Created by Andre on 4/20/2015.
 */
public class LaptopDataAccessException extends Exception {
    public LaptopDataAccessException(String message) {
        super(message);
    }

    public LaptopDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}