package com.lab.pharmacy.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }


    public NotFoundException(){
        super();
    }
}
