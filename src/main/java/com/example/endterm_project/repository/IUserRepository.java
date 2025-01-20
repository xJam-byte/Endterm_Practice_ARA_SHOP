package com.example.endterm_project.repository;
import com.example.endterm_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
