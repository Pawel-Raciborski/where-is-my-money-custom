package org.whereismymoney.service;

import org.whereismymoney.model.Token;

import java.util.Optional;

public interface TokenService {

    Token createEmptyToken();

    Optional<Token> findTokenByValue(String tokenId);
}
