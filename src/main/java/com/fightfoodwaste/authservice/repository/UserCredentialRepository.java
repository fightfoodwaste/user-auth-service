package com.fightfoodwaste.authservice.repository;

import com.fightfoodwaste.authservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository  extends JpaRepository<UserCredential, Long> {
    Optional<UserCredential> findByUsername(String username);

    @Query("SELECT u.id FROM UserCredential u WHERE u.username = :username")
    Optional<Long> findIdByUsername(@Param("username") String username);
}
