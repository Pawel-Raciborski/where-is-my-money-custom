package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.whereismymoney.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
    SELECT u FROM User u
    JOIN FETCH u.token t
    WHERE t.value=:token
    """)
    Optional<User> findByTokenValue(@Param("token") String token);

    Optional<User> findByEmail(String email);
}
