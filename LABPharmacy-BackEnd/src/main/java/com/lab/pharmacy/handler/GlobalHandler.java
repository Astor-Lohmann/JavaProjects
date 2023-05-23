package com.lab.pharmacy.handler;

import com.lab.pharmacy.dto.DefaultErrorResponse;
import com.lab.pharmacy.exception.BadRequestException;
import com.lab.pharmacy.exception.NotFoundException;
import com.lab.pharmacy.exception.ServerSideException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultErrorResponse> tratarNotFoundException(Exception e){
        return new ResponseEntity<>(new DefaultErrorResponse(
                404,
                e.getMessage(),
                e.getCause(),
                e),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultErrorResponse> tratarBadRequestException(Exception e){
        return new ResponseEntity<>(new DefaultErrorResponse(
                400,
                e.getMessage(),
                e.getCause(),
                e),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerSideException.class)
    public ResponseEntity<DefaultErrorResponse> tratarServerSideException(Exception e){
        return new ResponseEntity<>(new DefaultErrorResponse(
                500,
                e.getMessage(),
                e.getCause(),
                e),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}