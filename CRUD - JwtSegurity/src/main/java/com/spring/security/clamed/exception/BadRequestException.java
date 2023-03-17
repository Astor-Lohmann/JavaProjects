package com.spring.security.clamed.exception;

public class BadRequestException extends RuntimeException {

    BadRequestException(String message){
        super(message);
    }

    BadRequestException(){
        super();
    }
}
