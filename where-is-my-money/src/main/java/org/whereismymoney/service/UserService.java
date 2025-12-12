package org.whereismymoney.service;

import org.whereismymoney.model.User;

import java.util.Optional;

public interface UserService {
    User create(String fullName, String email);

    Optional<User> findByTokenValue(String tokenId);
}
