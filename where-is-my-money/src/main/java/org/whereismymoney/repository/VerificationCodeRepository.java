package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.whereismymoney.model.VerificationCode;

import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {
    Optional<VerificationCode> findByGroup_Id(UUID groupId);

    @Modifying
    @Query("""
            delete VerificationCode vc
            WHERE vc.group.id = :id
            """)
    void deleteByGroupId(UUID id);
}
