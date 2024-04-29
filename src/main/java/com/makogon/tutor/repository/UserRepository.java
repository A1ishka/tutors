package com.makogon.tutor.repository;

import com.makogon.tutor.Role;
import com.makogon.tutor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByUserId(long userId);

    User findByLogin(String login);

    List<User> findByRole(Role role);
}