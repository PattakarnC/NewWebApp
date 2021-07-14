package io.muic.ssc.webapp.basic.security;

public class UsernameNotUniqueException extends UserServiceException {

    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
