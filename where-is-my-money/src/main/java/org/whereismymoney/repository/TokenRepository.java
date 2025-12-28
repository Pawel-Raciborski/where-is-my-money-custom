package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whereismymoney.model.Token;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByValue(String token);
}
