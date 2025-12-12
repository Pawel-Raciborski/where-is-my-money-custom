package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.config.properties.VerificationProperties;
import org.whereismymoney.model.VerificationCode;
import org.whereismymoney.util.VerificationCodeUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    private final VerificationProperties verificationProperties;

    @Override
    public VerificationCode generateCode() {
        String generatedCode = VerificationCodeUtil.generateCode(verificationProperties.getCodeLength());

        return VerificationCode.builder()
                .code(generatedCode)
                .expireAt(LocalDateTime.now().plus(verificationProperties.getCodeExpirationMinutes()))
                .isVerified(false)
                .build();
    }
}
