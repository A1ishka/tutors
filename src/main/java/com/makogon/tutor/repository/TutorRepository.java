package com.makogon.tutor.repository;

import com.makogon.tutor.model.Tutor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Override
    List<Tutor> findAll();
    Tutor findByTutorId(long tutorId);
    @Query("SELECT t FROM Tutor t WHERE LOWER(t.fullName) LIKE %:query%")
    List<Tutor> findByFullNameContainingIgnoreCase(@Param("query") String query);
}