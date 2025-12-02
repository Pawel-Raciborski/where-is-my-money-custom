package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.whereismymoney.model.Owner;
import org.whereismymoney.repository.OwnerRepository;
import org.whereismymoney.util.OwnerUtil;
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
