package com.makogon.tutor.repository;

import com.makogon.tutor.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    List<Administrator> findAll();
    Administrator findByAdministratorId(long administratorId);
}
