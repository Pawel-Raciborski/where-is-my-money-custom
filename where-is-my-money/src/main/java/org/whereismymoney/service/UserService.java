package org.whereismymoney.service;

import jakarta.validation.constraints.NotNull;
import org.whereismymoney.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(String fullName, String email);

    Optional<User> findByTokenValue(String tokenId);

    User findById(@NotNull UUID uuid);

}
