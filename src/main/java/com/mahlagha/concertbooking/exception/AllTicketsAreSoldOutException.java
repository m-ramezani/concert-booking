package com.mahlagha.concertbooking.exception;

public class AllTicketsAreSoldOutException extends RuntimeException {
    public AllTicketsAreSoldOutException() {
    }

    public AllTicketsAreSoldOutException(String message) {
        super(message);
    }

    public AllTicketsAreSoldOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
