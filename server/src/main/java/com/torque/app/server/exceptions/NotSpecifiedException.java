package com.torque.app.server.exceptions;

public class NotSpecifiedException extends RuntimeException {
    public NotSpecifiedException(String message) {
        super(message);
    }

    public static void throwNotSpecifiedException(String message) {
        throw new NotSpecifiedException(message);
    }
    
}
