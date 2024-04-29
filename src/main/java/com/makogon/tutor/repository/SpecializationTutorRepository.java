package com.makogon.tutor.repository;

import com.makogon.tutor.model.Specialization;
import com.makogon.tutor.model.SpecializationTutor;
import com.makogon.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationTutorRepository extends JpaRepository<SpecializationTutor, Long> {
    List<SpecializationTutor> findAll();

    SpecializationTutor findBySpecializationTutorId(long specializationTutorId);

    List<SpecializationTutor> findAllByTutor(Tutor tutor);

    List<SpecializationTutor> findAllBySpecialization(Specialization specialization);
}