package io.github.jairandersonsouza.authorizer.exceptions;

public class AccountNotExistsException extends RuntimeException {

    public AccountNotExistsException(String msg) {
        super(msg);
    }

}
