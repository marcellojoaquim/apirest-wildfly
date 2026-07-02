package com.github.marcello.exception;

public class DAOException extends Exception {
    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
