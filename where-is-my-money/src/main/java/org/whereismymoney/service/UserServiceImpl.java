package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.whereismymoney.model.User;
import org.whereismymoney.repository.UserRepository;
import org.whereismymoney.util.UserUtil;
import org.springframework.stereotype.Service;
import org.whereismymoney.validation.UserValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User create(String fullName) {
        User ownerToCreate = UserUtil.buildUser(fullName);
        return userRepository.save(ownerToCreate);
    }

    @Override
    public Optional<User> findByTokenValue(String tokenId) {
        return userRepository.findByTokenValue(tokenId);
    }
}
