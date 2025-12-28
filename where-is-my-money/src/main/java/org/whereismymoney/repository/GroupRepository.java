package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.Query;
import org.whereismymoney.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Query("""
                SELECT g FROM Group g
                WHERE g.id = :id AND TRIM(g.token.value) = TRIM(:token)
            """)
    Optional<Group> findByIdAndToken(UUID id, String token);
}
