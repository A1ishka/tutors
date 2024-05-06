package com.makogon.tutor.repository;

import com.makogon.tutor.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @SuppressWarnings("NullableProblems")
    List<Manager> findAll();
    Manager findByManagerId(long managerId);
    Manager findByUserUserId(long userId);
}