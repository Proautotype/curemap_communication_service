package com.custard.curemapcommunicationservice.application.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message){
        super(message);
    }
}
