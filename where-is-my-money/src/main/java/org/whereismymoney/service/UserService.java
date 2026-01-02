package org.whereismymoney.service;

import jakarta.validation.constraints.NotNull;
import org.whereismymoney.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(String fullName, String email);

    Optional<User> findByTokenValue(String tokenId);
    Optional<User> findByEmail(String email);

    User findById(@NotNull UUID uuid);

    List<User> findAllByIds(List<UUID> ids);

    List<User> findAllInGroup(UUID groupId);
}
