package com.example.endterm_project.repository;
import com.example.endterm_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    Optional<User> findByName(String name);

}
