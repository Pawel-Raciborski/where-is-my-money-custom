package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.whereismymoney.model.User;
import org.whereismymoney.repository.UserRepository;
import org.whereismymoney.util.UserUtil;
import org.springframework.stereotype.Service;
import org.whereismymoney.validation.UserValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User create(String fullName, String email) {
        userValidator.isEmailNotPresent(email);

        User ownerToCreate = UserUtil.buildUser(fullName,email);
        return userRepository.save(ownerToCreate);
    }

    @Override
    public Optional<User> findByTokenValue(String tokenId) {
        return userRepository.findByTokenValue(tokenId);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public List<User> findAllByIds(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }
}
