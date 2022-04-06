package com.AcadianaPower.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AcadianaUserRepository extends JpaRepository<AcadianaUser, Long> {

    @Query(value="SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<AcadianaUser> findByEmail(String email);
}
