package com.kuri.exception;

public class NoMorePreferencesException extends Exception {
    public NoMorePreferencesException() {
        super("There are no preferences left for this candidate");
    }

    public NoMorePreferencesException(String message) {
        super(message);
    }
}
