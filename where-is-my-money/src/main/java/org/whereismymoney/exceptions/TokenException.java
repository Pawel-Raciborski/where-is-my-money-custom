package org.whereismymoney.exceptions;

public class TokenException extends RuntimeException{
    public TokenException(String tokenNotFound) {
        super(tokenNotFound);
    }
}
