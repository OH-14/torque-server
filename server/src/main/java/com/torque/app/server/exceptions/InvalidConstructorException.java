package com.torque.app.server.exceptions;

public class InvalidConstructorException extends RuntimeException {
    public InvalidConstructorException(String message){
        super(message);
    }
}
