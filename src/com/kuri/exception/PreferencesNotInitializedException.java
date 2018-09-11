package com.kuri.exception;

public class PreferencesNotInitializedException extends Exception {
    public PreferencesNotInitializedException() {
        super("This canidate has no preference list. Please set a preference list to continue.");
    }

    public PreferencesNotInitializedException(String s) {
        super(s);
    }

}
