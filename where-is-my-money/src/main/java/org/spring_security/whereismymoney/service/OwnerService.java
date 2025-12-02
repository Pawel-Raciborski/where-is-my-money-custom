package org.spring_security.whereismymoney.service;

import org.spring_security.whereismymoney.model.Owner;

public interface OwnerService {
    Owner create(String fullName);
}
