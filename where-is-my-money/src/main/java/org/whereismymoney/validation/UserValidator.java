package org.whereismymoney.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.whereismymoney.model.User;
import org.whereismymoney.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void isEmailNotPresent(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            throw new RuntimeException("Email zajęty!");
        }
    }
}
