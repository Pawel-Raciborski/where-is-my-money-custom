package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.model.Token;
import org.whereismymoney.repository.TokenRepository;
import org.whereismymoney.util.TokenUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;


    @Override
    public Token createEmptyToken() {
        Token emptyToken = TokenUtil.buildToken();

        return tokenRepository.save(emptyToken);
    }

    @Override
    public Optional<Token> findTokenByValue(String tokenId) {
        return tokenRepository.findByValue(tokenId);
    }
}
