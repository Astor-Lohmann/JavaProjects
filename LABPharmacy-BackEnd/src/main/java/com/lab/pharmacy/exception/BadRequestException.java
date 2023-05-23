package com.lab.pharmacy.exception;

public class BadRequestException extends RuntimeException {


    public BadRequestException(String message){
        super(message);
    }

    BadRequestException(){

        super();
    }
}