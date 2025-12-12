package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.VerificationCode;
import org.whereismymoney.repository.VerificationCodeRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final GroupService groupService;
    private final CodeGeneratorService codeGeneratorService;
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    @Transactional
    public VerificationCode createVerificationCodeForGroupWithToken(UUID groupId, String tokenValue) {
        Group groupWithToken = groupService.findGroupWithToken(groupId, tokenValue);
        VerificationCode verificationCode = codeGeneratorService.generateCode();
        groupWithToken.setVerificationCode(verificationCode);
        verificationCode.setGroup(groupWithToken);

        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public boolean checkIsVerifiedAndNotExpiried(UUID groupId, String token) {
        Group groupWithToken = groupService.findGroupWithToken(groupId, token);

        VerificationCode verificationCode = groupWithToken.getVerificationCode();
        LocalDateTime expireAt = verificationCode.getExpireAt();

        if (expireAt.isAfter(LocalDateTime.now())) {
            return false;
        }
        return verificationCode.isVerified();
    }

    @Override
    @Transactional
    public boolean checkIsVerified(UUID groupId, String token) {
        Group groupWithToken = groupService.findGroupWithToken(groupId, token);
        VerificationCode verificationCode = groupWithToken.getVerificationCode();

        return verificationCode.isVerified();
    }

    @Override
    public Optional<VerificationCode> checkIfExistForGroupWithToken(UUID groupId, String token) {
        Group groupWithToken = groupService.findGroupWithToken(groupId, token);
        return Optional.ofNullable(groupWithToken.getVerificationCode());
    }

    @Override
    @Transactional
    public void deletePreviousVerificationCodeForGroupWithToken(UUID groupId, String token) {
        Group groupWithToken = groupService.findGroupWithToken(groupId, token);
        VerificationCode verificationCode = groupWithToken.getVerificationCode();

        groupWithToken.setVerificationCode(null);
        verificationCodeRepository.delete(verificationCode);
    }
}
