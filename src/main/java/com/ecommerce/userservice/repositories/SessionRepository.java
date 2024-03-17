package com.ecommerce.userservice.repositories;

import com.ecommerce.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session> findByToken(String token);
}
