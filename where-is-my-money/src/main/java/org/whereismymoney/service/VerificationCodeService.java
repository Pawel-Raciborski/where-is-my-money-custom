package org.whereismymoney.service;

import org.whereismymoney.model.VerificationCode;

import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeService {
    VerificationCode createVerificationCodeForGroupWithToken(UUID groupId, String tokenValue);

    boolean checkIsVerifiedAndNotExpiried(UUID groupId, String token);

    boolean checkIsVerified(UUID groupId, String token);

    Optional<VerificationCode> findCodeForGroupWithToken(UUID groupId, String token);

    void deletePreviousVerificationCodeForGroupWithToken(UUID groupId, String token);

    boolean verify(UUID groupId, String token, String code);
}
