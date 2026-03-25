package com.torque.app.server.exceptions;

public class InvalidDrawsException extends RuntimeException{
    public InvalidDrawsException (String message){
        super(message);
    }
}
