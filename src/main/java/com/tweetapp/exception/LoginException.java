package com.tweetapp.exception;

public class LoginException extends Exception {
    public LoginException(String incorrect_authentication_structure) {
        super(incorrect_authentication_structure);
    }
}
