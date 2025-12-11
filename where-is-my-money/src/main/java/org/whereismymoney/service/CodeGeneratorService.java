package org.whereismymoney.service;

import org.whereismymoney.model.VerificationCode;

public interface CodeGeneratorService {
    VerificationCode generateCode();
}
