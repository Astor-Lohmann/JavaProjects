package com.lab.pharmacy.exception;

public class ServerSideException extends RuntimeException{

    public ServerSideException(String message){
        super(message);

    }

    public ServerSideException(){
        super();
    }
}
