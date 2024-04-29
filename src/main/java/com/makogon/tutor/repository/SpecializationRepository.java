package com.makogon.tutor.repository;

import com.makogon.tutor.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    List<Specialization> findAll();

    Specialization findBySpecializationId(Long specializationId);
}