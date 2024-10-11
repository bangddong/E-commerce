package com.hanghae.ecommerce.interfaces.exception;

public class ConcurrencyException extends RuntimeException{

    public ConcurrencyException(String message) {
        super(message);
    }

}
