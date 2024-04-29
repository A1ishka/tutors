package com.makogon.tutor.repository;

import com.makogon.tutor.model.Class;
import com.makogon.tutor.model.Specialization;
import com.makogon.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findAll();
    List<Class> findAllByTutor(Tutor tutor);
    List<Class> findAllBySpecialization(Specialization specialization);
    Class findByClassId(long classId);
}