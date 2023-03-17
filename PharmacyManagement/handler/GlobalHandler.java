package com.example.PharmacyManagement.handler;

import com.example.PharmacyManagement.exception.BadRequestException;
import com.example.PharmacyManagement.exception.NotFoundException;
import com.example.PharmacyManagement.exception.ServerSideException;
import com.example.PharmacyManagement.padroes.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GloblaHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity tratarNotFoundException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        e.getCause()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity tratarBadRequestException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        e.getCause()),

                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerSideException.class)
    public ResponseEntity tratarServerSideException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        e.getCause()
                ),

                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarMethodArgumentNotValidException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        e.getCause()
                ),

                HttpStatus.BAD_REQUEST);
    }

}
