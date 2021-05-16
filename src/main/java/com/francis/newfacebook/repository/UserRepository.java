package com.francis.newfacebook.repository;

import com.francis.newfacebook.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);
    Users findUserByEmailAndPassword(String email, String password);
}
