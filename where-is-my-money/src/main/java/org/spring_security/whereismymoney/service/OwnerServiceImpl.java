package org.spring_security.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.spring_security.whereismymoney.model.Member;
import org.spring_security.whereismymoney.model.Owner;
import org.spring_security.whereismymoney.repository.OwnerRepository;
import org.spring_security.whereismymoney.util.OwnerUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    @Override
    public Owner create(String fullName) {
        Owner ownerToCreate = OwnerUtil.buildOwner(fullName);

        return ownerRepository.save(ownerToCreate);
    }
}
